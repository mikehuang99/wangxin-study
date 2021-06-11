/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 */
@Data
@Accessors(chain = true)
public class AuthCourseApplySaveBO implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	/**
     * 一级分类ID
     */
	@ApiModelProperty(value = "一级分类ID", required = false)
    private String subjectId1;
    /**
     * 二级分类ID
     */
	@ApiModelProperty(value = "二级分类ID", required = false)
    private String subjectId2;
    /**
     * 三级分类ID
     */
	@ApiModelProperty(value = "三级分类ID", required = false)
    private String subjectId3;
    /**
     * 课程名称
     */
	@ApiModelProperty(value = "课程名称", required = true)
    private String courseName;
    /**
     * 课程封面
     */
	@ApiModelProperty(value = "课程封面", required = true)
    private String courseLogo;
    /**
     * 课程简介
     */
	@ApiModelProperty(value = "课程简介", required = true)
    private String introduce;
	/**
     * 原价
     */
	@ApiModelProperty(value = "原价", required = true)
    private BigDecimal courseOriginal;
    /**
     * 用户编号
     */
	@ApiModelProperty(value = "用户编号", required = true)
    private String userNo;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = true)
	private Integer isFree;
}
