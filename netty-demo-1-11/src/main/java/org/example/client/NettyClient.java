package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyClient {
    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 7879);
    }

    private void connect(String hostIp, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new MyChannelInitializer());
            Channel ch = b.bind(port).sync().channel();
            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("我是客户端，你在吗", Charset.forName("GBK")),
                    new InetSocketAddress(hostIp, port)
            )).sync();
            ch.closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
