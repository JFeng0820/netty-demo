package org.example.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.example.service.ExtServerService;

import java.nio.charset.Charset;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private ExtServerService extServerService;

    public MyChannelInitializer(ExtServerService extServerService) {
        this.extServerService = extServerService;
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new MyServerHandler(extServerService));
    }

}