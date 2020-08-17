package IO.demo2_nio.demo1;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author ：xinjian.yao
 * @date : 2020/8/12
 **/
public class client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost", 8080));

        // 发生请求
        ByteBuffer byteBuffer = ByteBuffer.wrap("1234321221".getBytes());
        socketChannel.write(byteBuffer);

        // 读取响应
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int num;

        if ((num = socketChannel.read(readBuffer)) > 0) {
            readBuffer.flip();

            byte[] re = new byte[num];
            readBuffer.get(re);

            String result = new String(re, StandardCharsets.UTF_8);
            System.out.println(result);

        }


    }
}
