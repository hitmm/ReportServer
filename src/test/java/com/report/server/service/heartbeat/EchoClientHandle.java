package com.report.server.service.heartbeat;

import com.alibaba.fastjson.JSONObject;
import com.report.server.model.service.message.HeartBeatMessage;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by haoxy on 2018/10/17.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public class EchoClientHandle extends SimpleChannelInboundHandler<HeartBeatMessage> {

    private final static Logger LOGGER = LoggerFactory.getLogger(EchoClientHandle.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                LOGGER.info("已经10秒没收到消息了");
                //向服务端发送消息
                HeartBeatMessage heartBeat = new HeartBeatMessage();
                heartBeat.setIp("127.0.0.1");
                ctx.writeAndFlush(heartBeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }

        }
        super.userEventTriggered(ctx, evt);
    }

    /**
     *  每当从服务端接收到新数据时，都会使用收到的消息调用此方法 channelRead0(),在此示例中，接收消息的类型是ByteBuf。
     * @param channelHandlerContext
     * @param bean
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatMessage bean) throws Exception {
        //从服务端收到消息时被调用
        LOGGER.info(String.format("客户端收到消息=%s", JSONObject.toJSONString(bean)));
    }
}

