package IO.netty.rpc.provider;

import IO.netty.rpc.publicInterface.HelloService;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/18
 **/
public class HelloserviceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息" + msg);
        return msg + ": 你好";
    }
}
