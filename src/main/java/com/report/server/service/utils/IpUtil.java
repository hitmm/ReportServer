package com.report.server.service.utils;

import java.net.InetAddress;

public class IpUtil {
    public static String getLocalIp(){
        InetAddress ia;
        String ip = null;
        try {
            ia=InetAddress.getLocalHost();
            ip=ia.getHostAddress();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ip;
    }
}
