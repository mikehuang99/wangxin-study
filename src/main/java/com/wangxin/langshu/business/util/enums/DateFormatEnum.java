/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

/**
 * 日期格式
 * 
 *  forest
 */
public enum DateFormatEnum {

    YYYYMMDDHHMMSSSSS("yyyyMMddHHmmssSSS"), YYYYMMDD("yyyyMMdd"), YYYYMMDDHHMMSS("yyyyMMddHHmmss");

    private String dateFormat;

    /**
     * @param dateFormat
     */
    private DateFormatEnum(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

}
