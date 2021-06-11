/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.tools;

import java.util.Date;

import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

/**
 * 时间+随机数，一定几率是会重复的，但忽略这个，数据库校验了唯一性
 */
public final class NOUtil {

	private NOUtil() {
	}

	public static String getOrderNo() {
		return DateUtil.format(new Date(), Constants.DATE.YYYYMMDDHHMMSS) + RandomUtil.randomNumbers(3);
	}

	public static String getSerialNumber() {
		return DateUtil.format(new Date(), Constants.DATE.YYYYMMDDHHMMSS) + RandomUtil.randomNumbers(4);
	}

	public static String getUserNo() {
		return DateUtil.format(new Date(), Constants.DATE.YYMMDDHHMMSSSSS) + RandomUtil.randomNumbers(17);
	}

}
