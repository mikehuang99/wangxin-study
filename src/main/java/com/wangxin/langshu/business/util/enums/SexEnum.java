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
public enum SexEnum {

	MALE(1, "男", "green"), FEMALE(2, "女", "red"), SECRET(3, "保密", "orange");

	private Integer code;

	private String desc;

	private String color;
}
