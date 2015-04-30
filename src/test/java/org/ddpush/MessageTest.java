package org.ddpush;

import org.ddpush.im.v1.client.appuser.Message;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/4/29
 * Time: 19:14
 */
public class MessageTest {


    public static void main(String[] args) {


        String data = "数据数据";

        byte[] dataLen = new byte[2];
        dataLen[0] = (byte) 0;
        dataLen[1] = (byte) data.getBytes().length;


        ByteBuffer byteBuffer = ByteBuffer.allocate(13);

        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        //消息分类
        byteBuffer.put((byte) 17);
        //消息长度
        byteBuffer.put(dataLen, 0, 2);
        //消息内容
        byteBuffer.put(data.getBytes());


        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);


        Message message = new Message(socketAddress, byteBuffer.array());

        int cmd = message.getCmd();
        int dl = message.getData().length;
        int contentLen = message.getContentLength();
        boolean format = message.checkFormat();
        System.out.println("分类：" + cmd);
        System.out.println("数据长度：" + dl);
        System.out.println("消息长度：" + contentLen);
        System.out.println("协议格式验证：" + format);


    }
}
