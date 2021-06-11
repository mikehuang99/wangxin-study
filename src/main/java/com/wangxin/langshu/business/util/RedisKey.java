package com.wangxin.langshu.business.util;

public class RedisKey {
	
	//试题缓存前缀
	public static String EXAM_QUESTION_PREFIX_ = "EXAM_QUESTION_PREFIX_";

	//课程试题绑定前缀
	public static String COURSE_EXAM_QUESTION_PREFIX_ = "COURSE_EXAM_QUESTION_PREFIX_";
	
	//章节试题绑定前缀
	public static String CHAPTER_EXAM_QUESTION_PREFIX_ = "CHAPTER_EXAM_QUESTION_PREFIX_";
	
	//课时试题绑定前缀
	public static String PERIOD_EXAM_QUESTION_PREFIX_ = "PERIOD_EXAM_QUESTION_PREFIX_";
	
	//用户已经做的课程试题绑定前缀
	public static String USER_HAD_DONE_COURSE_EXAM_QUESTION_PREFIX_ = "USER_HAD_DONE_COURSE_EXAM_QUESTION_PREFIX_";
	
	//用户已经做的章节试题绑定前缀
	public static String USER_HAD_DONE_CHAPTER_EXAM_QUESTION_PREFIX_ = "USER_HAD_DONE_CHAPTER_EXAM_QUESTION_PREFIX_";
	
	//用户已经做的课时试题绑定前缀
	public static String USER_HAD_DONE_PERIOD_EXAM_QUESTION_PREFIX_ = "USER_HAD_DONE_PERIOD_EXAM_QUESTION_PREFIX_";
	
	//用户错误试题前缀
	public static String USER_ERROR_EXAM_QUESTION_PREFIX_ = "USER_ERROR_EXAM_QUESTION_PREFIX_";
	
	//用户高频错误试题前缀
	public static String USER_HIGH_ERROR_EXAM_QUESTION_PREFIX_ = "USER_HIGH_ERROR_EXAM_QUESTION_PREFIX_";

	//用户收藏试题前缀
	public static String USER_FAVORITES_EXAM_QUESTION_PREFIX_ = "USER_FAVORITES_EXAM_QUESTION_PREFIX_";
	
	//用户笔记试题前缀
	public static String USER_NOTE_EXAM_QUESTION_PREFIX_ = "USER_NOTE_EXAM_QUESTION_PREFIX_";
	
	//用户笔记内容前缀
	public static String USER_NOTE_CONTENT_PREFIX_ = "USER_NOTE_CONTENT_PREFIX_";
	
	//短信验证码前缀
	public static String SMS_PREFIX_ = "SMS_";
	
	//登陆 用户名密码 错误次数校验
	public static String LOGIN_FAIL_COUNT_PREFIX_ = "LOGIN_FAIL_COUNT_";
	
	public static String DOT_CONFIG = "DOT_CONFIG";
	
	//依靠订单号临时存储订单前缀，用于轮询订单是否已经支付
	public static String ORDER_MAIN_ = "ORDER_MAIN_";

	//试卷带答案前缀
	public static String EXAM_PAPER_WITH_ANSWER_PREFIX_ = "EXAM_PAPER_WITH_ANSWER_PREFIX_";
	
	//试卷不带答案前缀
	public static String EXAM_PAPER_WITH_NO_ANSWER_PREFIX_ = "EXAM_PAPER_WITH_NO_ANSWER_PREFIX_";
	
	//用户交卷答案 前缀_考试ID_试卷ID_用户userNo
	public static String EXAM_MANAGEMENT_EXAM_PAPER_USER_ANSWER_ = "EXAM_MANAGEMENT_EXAM_PAPER_USER_ANSWER_";
	
	
	//用户学习记录 前缀_随机版本号
	public static String USER_STUDY_LOG_PREFIX_ = "USER_STUDY_LOG_PREFIX_";
	
	/*
	 * 刷题KEY前缀，后面加用户编号
	 */
	public static String DO_EXERCISES_="DO_EXERCISES_";


}
