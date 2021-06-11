/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FloorEnum {

    ONE(1, "1"), TWO(2, "2");

    private Integer code;

    private String desc;

}
