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
public enum IsVideoEnum {

    YES("1", "存在"), NO("0", "否");

    private String code;

    private String desc;

}
