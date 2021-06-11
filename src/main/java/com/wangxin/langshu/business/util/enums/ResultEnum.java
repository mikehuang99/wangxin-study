/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
	// 成功
	SUCCESS(200, "成功"),

	// token异常
	TOKEN_PAST(301, "TOKEN过期，请重新登录"), TOKEN_ERROR(302, "TOKEN异常，请重新登录"),
	// 登录异常
	LOGIN_ERROR(303, "登录异常"), REMOTE_ERROR(304, "该账号被异地登录，或者本地已重新登录"),

	MENU_PAST(305, "菜单过期"), MENU_NO(306, "没有此权限，请联系管理员！"),

	// 课程异常，4开头
	COURSE_SAVE_FAIL(403, "添加失败"), COURSE_UPDATE_FAIL(404, "更新失败"), COURSE_DELETE_FAIL(405, "删除失败"),
	//
	COLLECTION(406, "已收藏"), USER_ADVICE(406, "保存建议失败,不能重复提建议"), COURSE_AUDIT_FAIL(407, "审核失败"),
	
	// 你还没拥有该课程，请购买该课程后再进行操作
	NOT_YET_BUY(408, "你还没拥有该课程，请购买该课程后再进行操作"),

	// 用户异常，5开头
	LECTURER_REQUISITION_REGISTERED(501, "申请失败！该手机还没注册，请先注册账号"), LECTURER_REQUISITION_WAIT(502, "申请失败！该账号已提交申请入驻成为教师，待审核中，在7个工作日内会有相关人员与您联系确认"), LECTURER_REQUISITION_YET(503, "申请失败！该账号已成为教师，请直接登录"),
	//
	USER_SAVE_FAIL(504, "添加失败"), USER_UPDATE_FAIL(505, "更新失败"), LECTURER_REQUISITION_FAIL(506, "申请失败！该账号已提交申请入驻成为教师，审核不通过，请联系平台管理员"), USER_LECTURER_AUDIT(507, "审核失败"), USER_SEND_FAIL(508, "发送失败"),
	USER_DELETE_FAIL(509, "删除失败"),

	// 系統异常，6开头
	SYSTEM_SAVE_FAIL(601, "添加失败"), SYSTEM_UPDATE_FAIL(602, "更新失败"), SYSTEM_DELETE_FAIL(603, "删除失败"),

	// 错误
	ERROR(999999, "错误"),
	
	
	
	// 考试时间还没到
	NOT_YET_OPEN_EXAM(608, "考试时间还没到"),
	
	// 已经交卷，该考试不能重复交卷
	CAN_NOT_REPEATE_HAND_INT_EXAM(609, "已经交卷，该考试不能重复交卷");
	

	private Integer code;

	private String desc;

}
