/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否开启统计
 * 
 * 
 */
@Getter
@AllArgsConstructor
public enum IsOpenExternalCodeEnum {

	YES(1, "开启"), NO(0, "关闭");

	private Integer code;

	private String desc;

}
