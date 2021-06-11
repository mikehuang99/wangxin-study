/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.tools;

/**
 * 常量工具类
 * 
 *  
 */
public final class Constants {

	private Constants() {
	}

	public final static String ADMIN = "admin";// admin
	public final static Integer FREEZE = 3;// 冻结状态
	public final static String REGEX_MOBILE = "^1[0-9]{10}$";

	/**
	 * session
	 * 
	 *  
	 */
	public interface Session {
		public final static String BOSS_MENU = "BOSS_MENU";//
		public final static String USER_NO = "USERNO"; // userno
		public final static String USER_VO = "USERVO";// 不能使用user，关键词
		public final static String REAL_NAME = "REALNAME";//
	}

	/**
	 * cookie
	 * 
	 *  
	 */
	public interface Cookie {
		public final static String USER_TOKEN = "USERTOKEN";
	}

	/**
	 * 日期类型
	 * 
	 *  
	 */
	public interface DATE {
		public final static String YYYYMMDD = "yyyyMMdd";
		public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
		public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
		public final static String YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";
		public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
		public final static String YYYY_MM_DD = "yyyy-MM-dd";
	}

}
