package org.example.bio.client;

import java.net.Socket;
import java.nio.charset.Charset;

public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.31.199", 7676);
            System.out.println("netty client start done");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("GBK"));
            bioClientHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
