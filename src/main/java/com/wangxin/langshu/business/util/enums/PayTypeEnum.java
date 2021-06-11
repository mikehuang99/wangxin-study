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
public enum PayTypeEnum {

	WEIXIN(1, "支付宝"), ALIPAY(2, "微信"),STUDYCARD(3,"学习卡"),COUPON(4,"优惠券");

	private Integer code;

	private String desc;

}
