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
public enum PayStatusEnum {

	NOTYETPAY(0, "未支付"), HADPAY(1, "已支付"), EXPIRE(2, "已过期"),CANCEL(3,"已取消");

	private Integer code;

	private String desc;


}
