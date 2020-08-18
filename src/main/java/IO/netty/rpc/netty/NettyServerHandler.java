package IO.netty.rpc.netty;

import IO.netty.rpc.provider.HelloserviceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/18
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final String STRING_PRE = "remote#";

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端请求到底");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 获取客户端发送的消息，并且调用我们的服务
        System.out.println("msg=" + msg);
        // 客户端调用我们的服务的时候，我们需要定义一个协议
        // eg ： 每次发消息的时候，必须以某个字符串开头。 这里是"remote#" 开头
        if (msg.toString().startsWith(STRING_PRE)) {
            String result = new HelloserviceImpl().hello(msg.toString().substring(STRING_PRE.length()));
            ctx.writeAndFlush(result);
            return;
        }
        ctx.writeAndFlush("协议错误");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务结束");
    }
}
