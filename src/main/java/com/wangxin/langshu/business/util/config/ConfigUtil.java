/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.config;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置文件读取工具类
 *
 *  
 */
@Slf4j
public final class ConfigUtil {

	private ConfigUtil() {
	}

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			log.warn("当前环境配置为：{}", getProperty("env"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getProperty(String keyName) {
		return properties.getProperty(keyName, "").trim();
	}

}
