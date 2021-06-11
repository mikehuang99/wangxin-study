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
public enum AdvertTargetEnum {

	BLANK("_blank", "新窗口打开"), SELF("_self", "同窗口打开");

	private String code;

	private String desc;

}
