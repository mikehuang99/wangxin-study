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
public enum PlatShowEnum {
	
	PC(0, "电脑端"), 
	//WINXIN(1, "微信端"), 
	MOBILE(2, "移动网页端");

	private Integer code;

	private String desc;


}
