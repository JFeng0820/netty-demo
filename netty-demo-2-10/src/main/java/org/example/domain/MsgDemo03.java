package org.example.domain;

import org.example.domain.protocol.Command;
import org.example.domain.protocol.Packet;

public class MsgDemo03 extends Packet {

    private String channelId;
    private String demo03;

    public MsgDemo03(String channelId, String demo02) {
        this.channelId = channelId;
        this.demo03 = demo02;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDemo03() {
        return demo03;
    }

    public void setDemo03(String demo03) {
        this.demo03 = demo03;
    }

    @Override
    public Byte getCommand() {
        return Command.Demo02;
    }
}
