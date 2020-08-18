package IO.netty.rpc.customer;

import IO.netty.rpc.netty.NettyClient;
import IO.netty.rpc.publicInterface.HelloService;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/18
 **/
public class ClientBootstrap {

    private static String pre = "remote#";

    public static void main(String[] args) throws InterruptedException {
        // 创建消费者
        NettyClient customer = new NettyClient();
        // 创建代理对象
        HelloService helloService = (HelloService) customer.getBean(HelloService.class, pre);
        String res = helloService.hello("客户端");
        System.out.println(res);

        Thread.sleep(2000);
    }
}
