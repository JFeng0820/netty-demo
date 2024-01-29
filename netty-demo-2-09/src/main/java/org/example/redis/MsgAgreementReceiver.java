package org.example.redis;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.example.domain.MsgAgreement;
import org.example.util.CacheUtil;
import org.example.util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MsgAgreementReceiver extends AbstractReceiver {

    private Logger logger = LoggerFactory.getLogger(MsgAgreementReceiver.class);

    @Override
    public void receiveMessage(Object message) {
        logger.info("接收到PUSH消息：{}", message);
        MsgAgreement msgAgreement = JSON.parseObject(message.toString(), MsgAgreement.class);
        String toChannelId = msgAgreement.getToChannelId();
        Channel channel = CacheUtil.cacheChannel.get(toChannelId);
        if (null == channel) return;
        channel.writeAndFlush(MsgUtil.obj2Json(msgAgreement));
    }
}
