package com.report.server.service.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeartBeatInitializer extends ChannelInitializer<Channel> {
    private final static ClassLoaderResolver classLoaderClassResolver = new ClassLoaderResolver(HeartBeatInitializer.class.getClassLoader());
    private final static Map<String, Class<?>> classCache = new ConcurrentHashMap<>();
    private final static CachingClassResolver cachingClassResolver = new CachingClassResolver(classLoaderClassResolver,classCache);

    @Value("${spring.heartbeat.reader.idletime}")
    private int readerIdleTimeSeconds;
    @Value("${spring.heartbeat.writer.idletime}")
    private int writerIdleTimeSeconds;
    @Value("${spring.heartbeat.all.idletime}")
    private int allIdleTimeSeconds;


    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                //五秒没有收到消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds))//(8)
                .addLast(new ObjectDecoder(cachingClassResolver))//(9)
                .addLast(new HeartBeatChannelHandler());//(10)
    }
}
