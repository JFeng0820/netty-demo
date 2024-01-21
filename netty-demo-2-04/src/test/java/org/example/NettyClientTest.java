package org.example;

import io.netty.channel.ChannelFuture;
import org.example.client.NettyClient;
import org.example.domain.FileTransferProtocol;
import org.example.util.MsgUtil;

import java.io.File;

public class NettyClientTest {
    public static void main(String[] args) {

        //启动客户端
        ChannelFuture channelFuture = new NettyClient().connect("127.0.0.1", 7878);
        //文件信息{文件大于1024kb方便测试断点续传}
        File file = new File("H:\\ideaWorkspace\\netty-demo\\netty-demo-2-04\\src\\test\\java\\org\\example\\test.txt");
        FileTransferProtocol fileTransferProtocol = MsgUtil.buildRequestTransferFile(file.getAbsolutePath(), file.getName(), file.length());

        //发送信息；请求传输文件
        channelFuture.channel().writeAndFlush(fileTransferProtocol);
    }
}
