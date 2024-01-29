package org.example.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.example.domain.protocol.PacketClazzMap;
import org.example.util.SerializationUtil;

import java.util.List;

/**
 * 解码器
 */

public class ObjDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLen = in.readInt();
        if (in.readableBytes() < dataLen) {
            in.resetReaderIndex();
            return;
        }
        byte command = in.readByte(); //读取指令 只读取一个字节
        byte[] data = new byte[dataLen - 1]; //指令占了一位，剔除掉
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data, PacketClazzMap.packetTypeMap.get(command)));
    }
}