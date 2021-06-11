/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 *  
 */
public final class SystemUtil {

	private SystemUtil() {
	}

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getProperty(String keyName) {
		return properties.getProperty(keyName, "").trim();
	}

	

	// 支付回调配置
	public static final String WEIXIN_PAY_NOTIFY = getProperty("weixin_pay_notify");
	public static final String ALI_PAY_NOTIFY = getProperty("ali_pay_notify");


	public static final String TEST_COURSE = getProperty("test_course");


}
