package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.util.MsgUtil;

public class NettyClient {
    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 7878);
    }

    private void connect(String inetHost, int inetPort) {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(worker);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer());
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            System.out.println("netty client start done.");
            //测试消息，分别发放demo01、demo02、demo03
            f.channel().writeAndFlush(MsgUtil.buildMsgDemo01(f.channel().id().toString(),"你好，消息体MsgDemo01"));
            f.channel().writeAndFlush(MsgUtil.buildMsgDemo02(f.channel().id().toString(),"你好，消息体MsgDemo02"));
            f.channel().writeAndFlush(MsgUtil.buildMsgDemo03(f.channel().id().toString(),"你好，消息体MsgDemo03"));

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}