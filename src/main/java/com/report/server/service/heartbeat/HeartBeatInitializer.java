package com.report.server.service.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
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
        System.out.println("readerIdleTimeSeconds"+readerIdleTimeSeconds);
        channel.pipeline()
                //五秒没有收到消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(5, 0, 0))
                .addLast(new ObjectDecoder(cachingClassResolver))
                .addLast(new ObjectEncoder())
                .addLast(new HeartBeatChannelHandler());
    }

    public int getReaderIdleTimeSeconds() {
        return readerIdleTimeSeconds;
    }

    public void setReaderIdleTimeSeconds(int readerIdleTimeSeconds) {
        this.readerIdleTimeSeconds = readerIdleTimeSeconds;
    }

    public int getWriterIdleTimeSeconds() {
        return writerIdleTimeSeconds;
    }

    public void setWriterIdleTimeSeconds(int writerIdleTimeSeconds) {
        this.writerIdleTimeSeconds = writerIdleTimeSeconds;
    }

    public int getAllIdleTimeSeconds() {
        return allIdleTimeSeconds;
    }

    public void setAllIdleTimeSeconds(int allIdleTimeSeconds) {
        this.allIdleTimeSeconds = allIdleTimeSeconds;
    }
}
