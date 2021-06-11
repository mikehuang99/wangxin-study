/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

	USER(1, "用户", ""), LECTURER(2, "教师", "blue");

	private Integer code;

	private String desc;

	private String color;
}
