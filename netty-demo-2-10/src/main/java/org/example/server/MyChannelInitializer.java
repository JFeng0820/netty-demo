package org.example.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.example.codec.ObjDecoder;
import org.example.codec.ObjEncoder;
import org.example.server.handler.MsgDemo01Handler;
import org.example.server.handler.MsgDemo02Handler;
import org.example.server.handler.MsgDemo03Handler;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //对象传输处理[解码]
        ch.pipeline().addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new MsgDemo01Handler());
        ch.pipeline().addLast(new MsgDemo02Handler());
        ch.pipeline().addLast(new MsgDemo03Handler());
        //对象传输处理[编码]
        ch.pipeline().addLast(new ObjEncoder());
    }
}