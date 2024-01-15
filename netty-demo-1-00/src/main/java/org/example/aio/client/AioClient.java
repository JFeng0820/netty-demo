package org.example.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioClient {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = socketChannel.connect(new InetSocketAddress("192.168.31.199", 7878));
        System.out.println("netty client start done");
        future.get();
        socketChannel.read(ByteBuffer.allocate(1024), null, new AioClientHandler(socketChannel, Charset.forName("GBK")));
        Thread.sleep(100000);
    }
}
