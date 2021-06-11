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
public enum FileTypeEnum {

	ALIYUN(1, "阿里云"), QINIU(2, "七牛"), LOCAL(3, "本地");

	private Integer code;

	private String desc;

}
