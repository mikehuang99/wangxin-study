/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.tools;

import java.util.Random;

import com.xiaoleilu.hutool.crypto.SecureUtil;

/**
 *  
 */
public final class StrUtil {

	private StrUtil() {
	}

	public static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String getPrefix(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * @return
	 */
	public static String getRandom(int bound) {
		Random ra = new Random();
		String result = "";
		for (int i = 1; i <= bound; i++) {
			result += ra.nextInt(10);
		}
		return result;
	}

	public static String get32UUID() {
		return SecureUtil.simpleUUID();
	}

}
