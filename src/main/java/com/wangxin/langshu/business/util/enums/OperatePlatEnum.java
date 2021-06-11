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
public enum OperatePlatEnum {
	
	PCWEB(1,"pcweb", "电脑端"), 
	MOBILE(2, "mobile","移动端"),
	PCBOSS(3, "pcboss","后台管理");


	private Integer code;

	private String codeName;

	private String desc;


}
