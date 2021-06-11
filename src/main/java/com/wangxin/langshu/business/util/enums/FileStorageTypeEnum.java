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
public enum FileStorageTypeEnum {

	DOC(1, "附件"), PICTURE(2, "图片"), VIDEO(3, "视频");

	private Integer code;

	private String desc;

}
