package com.wangxin.langshu.business.util.alipay.trade.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangxin.langshu.business.SpringUtil;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;

import net.bytebuddy.asm.Advice.This;

public class Configs {
	
	
    private static Log log = LogFactory.getLog(Configs.class);
    private static Configuration configs;

    public static String openApiDomain="";   // 支付宝openapi域名
    public static String mcloudApiDomain;  // 支付宝mcloudmonitor域名
    public static String pid;             // 商户partner id
    public static String appid="";           // 商户应用id

    public static String privateKey="";      // RSA私钥，用于对商户请求报文加签
    public static String publicKey;       // RSA公钥，仅用于验证开发者网关
    public static String alipayPublicKey=""; // 支付宝RSA公钥，用于验签支付宝应答
    public static String signType;     // 签名类型  

    public static int maxQueryRetry;   // 最大查询次数
    public static long queryDuration;  // 查询间隔（毫秒）

    public static int maxCancelRetry;  // 最大撤销次数
    public static long cancelDuration; // 撤销间隔（毫秒）

    public static long heartbeatDelay ; // 交易保障线程第一次调度延迟（秒）
    public static long heartbeatDuration ; // 交易保障线程调度间隔（秒）
    
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notifyUrl = "";
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//例如http://192.168.1.102:8080/wxmobile/index.html#/order/order
	public static String returnUrl = "";
	

    public Configs() {
        // No Constructor
    }

    // 根据文件名读取配置文件，文件后缀名必须为.properties
    public static void init(String openApiDomain,String appid,String privateKey,String alipayPublicKey,String alipayReturnUrl) {
    	/*
        if (configs != null) {
            return;
        }

        try {
            configs = new PropertiesConfiguration(filePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        if (configs == null) {
            throw new IllegalStateException("can`t find file by path:" + filePath);
        }*/


        //openApiDomain = "https://openapi.alipay.com/gateway.do";
    	Configs.openApiDomain = openApiDomain;
    	Configs.mcloudApiDomain = "";

    	Configs.pid = "";
        //appid = "2021001184681274";
    	Configs.appid = appid;

        // RSA
    	Configs.privateKey = privateKey;
    	Configs.publicKey = "";
    	Configs.alipayPublicKey = alipayPublicKey;
    	Configs.signType = "RSA2";

        // 查询参数
    	Configs.maxQueryRetry = 5;
    	Configs.queryDuration = 5000;
    	Configs.maxCancelRetry = 3;
    	Configs.cancelDuration = 2000;

        // 交易保障调度线程
    	Configs.heartbeatDelay = 5;
    	Configs.heartbeatDuration = 900;
        
    	Configs.returnUrl = alipayReturnUrl;

       // log.info("配置文件名: " + filePath);
        //log.info(description());
    }

    public static String description() {
        StringBuilder sb = new StringBuilder("Configs{");
        sb.append("支付宝openapi网关: ").append(openApiDomain).append("\n");
        if (StringUtils.isNotEmpty(mcloudApiDomain)) {
            sb.append(", 支付宝mcloudapi网关域名: ").append(mcloudApiDomain).append("\n");
        }

        if (StringUtils.isNotEmpty(pid)) {
            sb.append(", pid: ").append(pid).append("\n");
        }
        sb.append(", appid: ").append(appid).append("\n");

        sb.append(", 商户RSA私钥: ").append(getKeyDescription(privateKey)).append("\n");
        sb.append(", 商户RSA公钥: ").append(getKeyDescription(publicKey)).append("\n");
        sb.append(", 支付宝RSA公钥: ").append(getKeyDescription(alipayPublicKey)).append("\n");
        sb.append(", 签名类型: ").append(signType).append("\n");

        sb.append(", 查询重试次数: ").append(maxQueryRetry).append("\n");
        sb.append(", 查询间隔(毫秒): ").append(queryDuration).append("\n");
        sb.append(", 撤销尝试次数: ").append(maxCancelRetry).append("\n");
        sb.append(", 撤销重试间隔(毫秒): ").append(cancelDuration).append("\n");

        sb.append(", 交易保障调度延迟(秒): ").append(heartbeatDelay).append("\n");
        sb.append(", 交易保障调度间隔(秒): ").append(heartbeatDuration).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private static String getKeyDescription(String key) {
        int showLength = 6;
        if (StringUtils.isNotEmpty(key) &&
                key.length() > showLength) {
            return new StringBuilder(key.substring(0, showLength))
                    .append("******")
                    .append(key.substring(key.length() - showLength))
                    .toString();
        }
        return null;
    }

    public static Configuration getConfigs() {
        return configs;
    }

    public static String getOpenApiDomain() {
        return openApiDomain;
    }

    public static String getMcloudApiDomain() {
        return mcloudApiDomain;
    }

    public static void setMcloudApiDomain(String mcloudApiDomain) {
        Configs.mcloudApiDomain = mcloudApiDomain;
    }

    public static String getPid() {
        return pid;
    }

    public static String getAppid() {
        return appid;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public static String getSignType() {
        return signType;
    }

    public static int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public static long getQueryDuration() {
        return queryDuration;
    }

    public static int getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public static long getCancelDuration() {
        return cancelDuration;
    }

    public static void setConfigs(Configuration configs) {
        Configs.configs = configs;
    }

    public static void setOpenApiDomain(String openApiDomain) {
        Configs.openApiDomain = openApiDomain;
    }

    public static void setPid(String pid) {
        Configs.pid = pid;
    }

    public static void setAppid(String appid) {
        Configs.appid = appid;
    }

    public static void setPrivateKey(String privateKey) {
        Configs.privateKey = privateKey;
    }

    public static void setPublicKey(String publicKey) {
        Configs.publicKey = publicKey;
    }

    public static void setAlipayPublicKey(String alipayPublicKey) {
        Configs.alipayPublicKey = alipayPublicKey;
    }

    public static void setSignType(String signType) {
        Configs.signType = signType;
    }
    
    public static void setMaxQueryRetry(int maxQueryRetry) {
        Configs.maxQueryRetry = maxQueryRetry;
    }

    public static void setQueryDuration(long queryDuration) {
        Configs.queryDuration = queryDuration;
    }

    public static void setMaxCancelRetry(int maxCancelRetry) {
        Configs.maxCancelRetry = maxCancelRetry;
    }

    public static void setCancelDuration(long cancelDuration) {
        Configs.cancelDuration = cancelDuration;
    }

    public static long getHeartbeatDelay() {
        return heartbeatDelay;
    }

    public static void setHeartbeatDelay(long heartbeatDelay) {
        Configs.heartbeatDelay = heartbeatDelay;
    }

    public static long getHeartbeatDuration() {
        return heartbeatDuration;
    }

    public static void setHeartbeatDuration(long heartbeatDuration) {
        Configs.heartbeatDuration = heartbeatDuration;
    }

	public static String getNotifyUrl() {
		return notifyUrl;
	}

	public static void setNotifyUrl(String notifyUrl) {
		Configs.notifyUrl = notifyUrl;
	}

	public static String getReturnUrl() {
		return returnUrl;
	}

	public static void setReturnUrl(String returnUrl) {
		Configs.returnUrl = returnUrl;
	}
    
    
}

