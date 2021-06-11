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
public enum SysTypeEnum {

	RONCOOPAY(1, "龙果支付"), OTHERPAYMENT(2, "其他");

	private Integer code;

	private String desc;

}
