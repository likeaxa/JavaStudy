package IO.demo2_nio.demo1;

import org.apache.poi.hssf.record.DVALRecord;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/12
 **/
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while (true){
            // 阻塞等待请求
            SocketChannel socketChannel = serverSocketChannel.accept();

            MySocketHandler socketHandler = new MySocketHandler(socketChannel);
            new Thread(socketHandler).start();
        }
    }


}
