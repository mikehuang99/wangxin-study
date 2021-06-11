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
public enum MmnUrlTypeEnum {

	YES(1, "内部链接"), NO(2, "外部链接");

	private Integer code;

	private String desc;


}
