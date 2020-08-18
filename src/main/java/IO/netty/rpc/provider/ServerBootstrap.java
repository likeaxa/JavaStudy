package IO.netty.rpc.provider;

import IO.netty.rpc.netty.NettyServer;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/18
 **/
public class ServerBootstrap {

    public static void main(String[] args) throws Exception {
        NettyServer.startServer("127.0.0.1", 6669);
    }
}
