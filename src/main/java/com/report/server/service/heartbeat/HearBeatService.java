package com.report.server.service.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class HearBeatService implements IHeartBeatService{

    private final static Logger LOGGER = LoggerFactory.getLogger(HearBeatService.class);
    private EventLoopGroup boss = new NioEventLoopGroup(); //(1)
    private EventLoopGroup work = new NioEventLoopGroup();

    @Value("${spring.heartbeat.port}")
    private int nettyPort;

    public IHeartBeatService getInstance(){
        return Singleton.INSTANCE;
    }

    @Override
    @PostConstruct
    public void start() throws Exception {
        LOGGER.info("heartbeat service starting......port:"+nettyPort);
        ServerBootstrap bootstrap = new ServerBootstrap() //(2)
                .group(boss, work)
                .channel(NioServerSocketChannel.class)// (3)
                .localAddress(new InetSocketAddress(nettyPort))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)//(4)
                .childHandler(new HeartBeatInitializer());// (5)
        //绑定并开始接受传入的连接。
        ChannelFuture future = bootstrap.bind().sync();//(6)
        if (future.isSuccess()) {
            LOGGER.info("heartbeat service start success......");
        }
    }

    @Override
    @PreDestroy
    public void stop() throws Exception {
        LOGGER.info("heartbeat service shutdown......");
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        LOGGER.info("heartbeat service shutdown success......");
    }

    private final static class Singleton {
        private final static IHeartBeatService INSTANCE = new HearBeatService();
    }
}
