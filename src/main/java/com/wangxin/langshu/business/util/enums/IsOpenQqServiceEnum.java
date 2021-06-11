/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否显示客服信息
 * 
 * 
 */
@Getter
@AllArgsConstructor
public enum IsOpenQqServiceEnum {

	YES(1, "是"), NO(0, "否");

	private Integer code;

	private String desc;
}
