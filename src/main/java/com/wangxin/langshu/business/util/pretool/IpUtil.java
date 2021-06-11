package com.wangxin.langshu.business.util.pretool;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class IpUtil {

	public static String getIpAddr(HttpServletRequest request) {
        String ipAddressStr = null;
        try {
        	ipAddressStr = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ipAddressStr) || ipAddressStr.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddressStr)) {
            	ipAddressStr = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ipAddressStr) || ipAddressStr.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddressStr)) {
            	ipAddressStr = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ipAddressStr) || ipAddressStr.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddressStr)) {
            	ipAddressStr = request.getRemoteAddr();
                if (ipAddressStr.equals("127.0.0.1")) {
                    // 根据网卡获取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddressStr = inet.getHostAddress();
                }
            }
            // 通过多个代理的情况。第1个IP为客户端真实IP,多个IP按照','分割
            if (StringUtils.isNotEmpty(ipAddressStr) && ipAddressStr.length() > 15) {                               
                if (ipAddressStr.indexOf(",") > 0) {
                	ipAddressStr = ipAddressStr.substring(0, ipAddressStr.indexOf(","));
                }
            }
        } catch (Exception e) {
        	ipAddressStr = "";
        }
        return ipAddressStr;
    }
}
