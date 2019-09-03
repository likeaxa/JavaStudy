package IO.demo2_nio;

/**
 * @Classname IOClient
 * @Date 2019/9/3 15:02
 * @Created by yaoxinjian
 */

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * bio 模式 一问一答模式 客户端发送一个请求 服务的会起一个线程来处理
 * 确定 服务的的线程启动 关闭 消耗很大 并发大时服务器承受不了
 */
public class IOClient {

    public static void main(String[] args) {
        // TODO 创建多个线程，模拟多个客户端连接服务端
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 3333);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();

    }
}
