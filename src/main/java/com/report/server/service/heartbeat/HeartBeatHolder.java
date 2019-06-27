package com.report.server.service.heartbeat;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 心跳变量Holder
 */
public class HeartBeatHolder {
    private static final Map<String, NioSocketChannel> livingClientMap = new ConcurrentHashMap<>();
    private static final Map<String, String> livingClientIdMap = new ConcurrentHashMap<>();

    public static synchronized void putOrFreshClient(String ident,NioSocketChannel channel){
        InetSocketAddress insocket = channel.remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        livingClientMap.put(ident,channel);
        livingClientIdMap.put(clientIP,ident);
    }

    public static synchronized void closeClient(String ident){
        livingClientMap.remove(ident);
        livingClientIdMap.entrySet().stream().filter(entry -> entry.getValue()==ident).forEach(entry -> livingClientIdMap.remove(entry.getKey()));
    }

    public static NioSocketChannel getClient(String ident){
        return livingClientMap.get(ident);
    }

    public static synchronized void closeClient(NioSocketChannel channel){
        InetSocketAddress insocket = channel.remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        livingClientMap.entrySet().stream().filter(entry -> entry.getValue() == channel).forEach(entry-> livingClientMap.remove(entry.getKey()));
        livingClientIdMap.remove(clientIP);
    }
    public static synchronized void closeClientByIp(String localIp){
        String ident = livingClientIdMap.get(localIp);
        closeClient(ident);
    }

}
