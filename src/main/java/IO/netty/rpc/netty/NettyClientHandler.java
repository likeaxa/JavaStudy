package IO.netty.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/18
 **/
public class NettyClientHandler extends SimpleChannelInboundHandler<String> implements Callable<String> {

    private ChannelHandlerContext context;
    private String result;
    // 客户端调用方法的，传入的参数
    private String params;

    // 被代理方法调用， 发送数据给服务器，然后就线程等待
    @Override
    public synchronized String call() throws Exception {
        context.writeAndFlush(params);
        wait();
        return result;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接结束");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接建立");
        context = ctx;
    }


    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        result = s;
        // 唤醒等待的线程
        notifyAll();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    void setParams(String p) {
        params = p;
    }
}
