package org.example.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NioClient {

    public static void main(String[] args) throws IOException {
        // 1、打开事件选择器
        // 在选择器中注册事件类型
        // 将选择器传入 Handler ，不断轮训 channel
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        // 非阻塞模式
        socketChannel.configureBlocking(false);

        boolean isConnect = socketChannel.connect(new InetSocketAddress("192.168.31.199", 7575));
        if (isConnect) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        System.out.println("client start done");
        new NioClientHandler(selector, Charset.forName("GBK"));
    }
}
