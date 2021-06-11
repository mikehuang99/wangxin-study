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
public enum TopicLocationEnum {

	PC(0, "PC端", ""),
	//
	WX(1, "微信端", "green");

	private Integer code;

	private String desc;

	private String color;
}
