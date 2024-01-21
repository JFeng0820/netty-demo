package org.example.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString(Charset.forName("GBK"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " UDP服务端接收到消息：" + msg);
        //向客户端发送消息
        String json = "通知：我已经收到你的消息\r\n";
        byte[] byteps = json.getBytes(Charset.forName("GBK"));
        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(byteps), packet.sender());
        ctx.writeAndFlush(data);
    }
}
