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
public enum IsFreeEnum {

    FREE(1, "免费", ""), CHARGE(0, "收费", "green");

    private Integer code;

    private String desc;
    
    private String color;

}
