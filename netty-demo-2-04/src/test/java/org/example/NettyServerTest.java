package org.example;

import org.example.server.NettyServer;

public class NettyServerTest {
    public static void main(String[] args) {
        //启动服务
        new NettyServer().bing(7878);
    }
}
