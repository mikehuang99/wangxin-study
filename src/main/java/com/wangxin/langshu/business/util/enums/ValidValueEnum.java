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
public enum ValidValueEnum {

	YES(1, "正常", ""), NO(0, "禁用", "red");

	private Integer code;

	private String desc;

	private String color;

}
