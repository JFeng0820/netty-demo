package org.example.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.example.codec.ObjDecoder;
import org.example.codec.ObjEncoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjDecoder());
        ch.pipeline().addLast(new ObjEncoder());
        ch.pipeline().addLast(new MyClientHandler());
    }
}