package org.example.web;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.example.domain.EasyResult;
import org.example.domain.ServerInfo;
import org.example.domain.UserChannelInfo;
import org.example.redis.RedisUtil;
import org.example.server.NettyServer;
import org.example.service.ExtServerService;
import org.example.util.CacheUtil;
import org.example.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class NettyController {
    private Logger logger = LoggerFactory.getLogger(NettyController.class);
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Value("${server.port}")
    private int serverPort;
    @Autowired
    private ExtServerService extServerService;
    @Resource
    private RedisUtil redisUtil;
    //Netty服务端
    private NettyServer nettyServer;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("serverPort", serverPort);
        return "index";
    }

    @RequestMapping("/openNettyServer")
    @ResponseBody
    public EasyResult openNettyServer() {
        try {
            int port = NetUtil.getPort();
            logger.info("启动Netty服务，获取可用端口：{}", port);
            nettyServer = new NettyServer(new InetSocketAddress(port), extServerService);
            Future<Channel> future = executorService.submit(nettyServer);
            Channel channel = future.get();
            if (null == channel) {
                throw new RuntimeException("netty server open error channel is null");
            }
            while (!channel.isActive()) {
                logger.info("启动Netty服务，循环等待启动...");
                Thread.sleep(500);
            }
            CacheUtil.serverInfoMap.put(port, new ServerInfo(NetUtil.getHost(), port, new Date()));
            CacheUtil.serverMap.put(port, nettyServer);
            logger.info("启动Netty服务，完成：{}", channel.localAddress());
            return EasyResult.buildSuccessResult();
        } catch (Exception e) {
            logger.error("启动Netty服务失败", e);
            return EasyResult.buildErrResult(e);
        }
    }

    @RequestMapping("/closeNettyServer")
    @ResponseBody
    public EasyResult closeNettyServer(int port) {
        try {
            logger.info("关闭Netty服务开始，端口：{}", port);
            NettyServer nettyServer = CacheUtil.serverMap.get(port);
            if (null == nettyServer) {
                CacheUtil.serverMap.remove(port);
                return EasyResult.buildSuccessResult();
            }
            nettyServer.destroy();
            CacheUtil.serverMap.remove(port);
            CacheUtil.serverInfoMap.remove(port);
            logger.info("关闭Netty服务完成，端口：{}", port);
            return EasyResult.buildSuccessResult();
        } catch (Exception e) {
            logger.error("关闭Netty服务失败，端口：{}", port, e);
            return EasyResult.buildErrResult(e);
        }
    }

    @RequestMapping("/queryNettyServerList")
    @ResponseBody
    public Collection<ServerInfo> queryNettyServerList() {
        try {
            Collection<ServerInfo> serverInfos = CacheUtil.serverInfoMap.values();
            logger.info("查询服务端列表。{}", JSON.toJSONString(serverInfos));
            return serverInfos;
        } catch (Exception e) {
            logger.info("查询服务端列表失败。", e);
            return null;
        }
    }

    @RequestMapping("/queryUserChannelInfoList")
    @ResponseBody
    public List<UserChannelInfo> queryUserChannelInfoList() {
        try {
            logger.info("查询用户列表信息开始");
            List<UserChannelInfo> userChannelInfoList = redisUtil.popList();
            logger.info("查询用户列表信息完成。list：{}", JSON.toJSONString(userChannelInfoList));
            return userChannelInfoList;
        } catch (Exception e) {
            logger.error("查询用户列表信息失败", e);
            return null;
        }
    }

}