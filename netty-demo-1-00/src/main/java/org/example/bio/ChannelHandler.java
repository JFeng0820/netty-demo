package org.example.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class ChannelHandler {
    private Socket socket;
    private Charset charset;

    public ChannelHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg) {
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
            out.write(msg.toString().getBytes(charset));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket socket() {
        return socket;
    }
}
