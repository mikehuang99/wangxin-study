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
public enum recruitTypeEnum {

	LECTURER(1, "教师招募"), AGENT(2, "代理招募");

	private Integer code;

	private String desc;

}
