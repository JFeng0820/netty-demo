package org.example.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.example.codec.ObjDecoder;
import org.example.codec.ObjEncoder;
import org.example.domain.FileTransferProtocol;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        ch.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        ch.pipeline().addLast(new MyClientHandler());
    }
}
