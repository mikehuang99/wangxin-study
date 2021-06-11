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
public enum ProfitStatusEnum {

	CONFIRMING(1, "确认中"), SUCCESS(2, "成功"), FAIL(3, "失败");

	private Integer code;

	private String desc;
}
