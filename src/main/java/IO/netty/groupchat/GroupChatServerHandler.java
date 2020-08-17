package IO.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/17
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 管理所有的channel组
     * GlobalEventExecutor  全局事件执行器 单例
     */
    private static ChannelGroup CHANNELGROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 私聊功能
     */
    public static Map<String, Channel> channelMap = new HashMap<>();


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        /*
          通知其他channel
         */
        CHANNELGROUP.writeAndFlush("客户端" + channel.remoteAddress() + "加入聊天");
        // 加入channel组别
        CHANNELGROUP.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CHANNELGROUP.writeAndFlush("客户端" + channel.remoteAddress() + "退出群聊");
        System.out.println("channel group size" + CHANNELGROUP.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();

        for (Channel ch : CHANNELGROUP) {
            if (channel != ch) {
                ch.writeAndFlush("客户" + channel.remoteAddress() + "发送了消息:" + s + "\n");
            } else {
                ch.writeAndFlush("自己发送了消息" + s + "\n");
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
