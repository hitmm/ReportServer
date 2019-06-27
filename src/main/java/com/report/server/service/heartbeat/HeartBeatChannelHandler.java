package com.report.server.service.heartbeat;

import com.alibaba.fastjson.JSONObject;
import com.report.server.service.heartbeat.message.HeartBeatBean;
import com.report.server.service.utils.IpUtil;
import com.report.server.service.utils.UUIDUtils;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class HeartBeatChannelHandler extends SimpleChannelInboundHandler<HeartBeatBean> {

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatChannelHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext context, HeartBeatBean bean) throws Exception {
        if(bean==null){
            LOGGER.info("ERROR HeartBeatBean");
            return;
        }
        LOGGER.info(String.format("Client(%s) send heartBeat...remoteIp:%s.",bean.getIdent(),bean.getIp()));
        HeartBeatHolder.putOrFreshClient(bean.getIdent(), (NioSocketChannel) context.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        LOGGER.info(String.format("********************************new Client connected....remoteIp:%s.",clientIP));
        String clientId = UUIDUtils.generateId();
        String localIp = IpUtil.getLocalIp();
        HeartBeatBean bean = new HeartBeatBean();
        bean.setIdent(clientId);
        bean.setIp(localIp);
        ctx.writeAndFlush(bean);
        HeartBeatHolder.putOrFreshClient(clientId, (NioSocketChannel) ctx.channel());
        LOGGER.info(String.format("********************************send ident to client,bean: %s.", JSONObject.toJSONString(bean)));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        LOGGER.info(String.format("********************************client is disconnected,remoteIp: %s.", clientIP));
        HeartBeatHolder.closeClientByIp(clientIP);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
            String clientIP = insocket.getAddress().getHostAddress();
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                LOGGER.info(String.format("No information has been received for some seconds,we will disconnected.remoteIp : %s.",clientIP));
                //向客户端发送消息
                HeartBeatBean bean = new HeartBeatBean();
                bean.setIp(IpUtil.getLocalIp());
                ctx.writeAndFlush(bean).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
