package IO.netty.rpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.ibatis.javassist.bytecode.analysis.Executor;

import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/18
 **/
public class NettyClient {

    // 创建线程池

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    private static NettyClientHandler client;

    // 使用代理模式 获取一个代理对象

    public Object getBean(final Class<?> serviceClass, final String pre) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass}, ((proxy, method, args) -> {
                    System.out.println("代理被调用");
                    if (client == null) {
                        initClient();
                    }
                    client.setParams(pre + args[0]);
                    return executor.submit(client).get();
                }));
    }

    // 初始化工作
    private static void initClient() throws Exception {
        client = new NettyClientHandler();
        EventLoopGroup gr = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(gr)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(client);
                    }
                });

        bootstrap.connect("127.0.0.1", 6669).sync();

    }
}
