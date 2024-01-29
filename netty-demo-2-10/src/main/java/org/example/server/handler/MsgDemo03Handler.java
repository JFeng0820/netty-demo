package org.example.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.domain.MsgDemo03;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgDemo03Handler extends SimpleChannelInboundHandler<MsgDemo03> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgDemo03 msg) throws Exception {
        System.out.println("\r\n> msg handler ing ...");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msg.getChannelId());
        System.out.println("消息内容：" + msg.getDemo03());
    }

}

