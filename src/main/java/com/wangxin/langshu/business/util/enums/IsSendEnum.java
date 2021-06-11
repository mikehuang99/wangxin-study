/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否是最新
 * 
 *
 */
@Getter
@AllArgsConstructor
public enum IsSendEnum {

	YES(1, "已发送"), NO(0, "待发送");

	private Integer code;

	private String desc;

}
