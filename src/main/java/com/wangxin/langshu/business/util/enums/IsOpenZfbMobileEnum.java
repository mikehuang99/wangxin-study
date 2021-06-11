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
public enum IsOpenZfbMobileEnum {

	YES(1, "开启", ""), NO(0, "关闭", "red");

	private Integer code;

	private String desc;

	private String color;

}
