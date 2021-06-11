/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 *  forest
 */
@Getter
@AllArgsConstructor
public enum FundDirectionEnum {

    ADD(1, "增加"), SUBSTRACT(2, "减少");

    private Integer code;

    private String desc;

}
