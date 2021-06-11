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
public enum IsSuccessEnum {

	SUCCESS(1, "成功", "green"), FAIL(0, "失败", "red");

	private Integer code;

	private String desc;

	private String color;

}
