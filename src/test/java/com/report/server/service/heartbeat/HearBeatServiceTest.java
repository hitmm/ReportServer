package com.report.server.service.heartbeat;

import com.report.server.service.heartbeat.message.HeartBeatBean;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class HearBeatServiceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(HearBeatServiceTest.class);
    private EventLoopGroup group = new NioEventLoopGroup();//(1)
    private int nettyPort = 8089;
    private String host = "127.0.0.1";

    private SocketChannel socketChannel;

    @PostConstruct
    public void start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();//(2)
        /**
         * NioSocketChannel用于创建客户端通道，而不是NioServerSocketChannel。
         * 请注意，我们不像在ServerBootstrap中那样使用childOption()，因为客户端SocketChannel没有父服务器。
         */
        bootstrap.group(group)
                .channel(NioSocketChannel.class)//(3)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                                .addLast(new IdleStateHandler(0, 10, 0))//(6)
                                .addLast(new ObjectEncoder())//(7)
                                .addLast(new EchoClientHandle());//(8)
                    }
                });//(4)
        /**
         * 启动客户端
         * 我们应该调用connect()方法而不是bind()方法。
         */
        ChannelFuture future = bootstrap.connect(host, nettyPort).sync();//(5)
        if (future.isSuccess()) {
            LOGGER.info("启动 Netty 成功");
        }

        socketChannel = (SocketChannel) future.channel();
        HeartBeatBean bean = new HeartBeatBean();
        bean.setIp("127.0.0.1");
        socketChannel.writeAndFlush(bean);
    }

    @Test
    public void start1() throws InterruptedException {
        new HearBeatServiceTest().start();
        Thread.sleep(1000000000);
    }
}