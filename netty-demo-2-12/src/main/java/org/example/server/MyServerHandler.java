package org.example.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import org.example.server.common.MyServerCommonHandler;

import java.util.function.Consumer;

public class MyServerHandler extends MyServerCommonHandler {

    @Override
    protected void sendData(ChannelHandlerContext ctx) {
        sentFlag = true;
        ctx.writeAndFlush( "111111111122222222223333333333\r\n", getChannelProgressivePromise(ctx, new Consumer<ChannelProgressiveFuture>() {
            @Override
            public void accept(ChannelProgressiveFuture channelProgressiveFuture) {
                if (ctx.channel().isWritable() && !sentFlag) {
                    sendData(ctx);
                }
            }
        }));
    }

}
