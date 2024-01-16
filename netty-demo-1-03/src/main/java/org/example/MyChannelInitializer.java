package org.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 基于换行符号
//        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 基于最大长度
        ch.pipeline().addLast(new FixedLengthFrameDecoder(4));
        // 解码转String
        ch.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        // 在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new MyServerHandler());
    }
}
