package org.example.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.example.domain.MsgInfo;
import org.example.codec.ObjDecoder;
import org.example.codec.ObjEncoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        ch.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        ch.pipeline().addLast(new MyClientHandler());
    }
}
