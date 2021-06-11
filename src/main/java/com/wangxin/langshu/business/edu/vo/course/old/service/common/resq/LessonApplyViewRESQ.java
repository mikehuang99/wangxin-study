package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课时信息-审核-查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class LessonApplyViewRESQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课时ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String courseId;
	/**
	 * 章节ID
	 */
	@ApiModelProperty(value = "章节ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String chapterId;
	/**
	 * 课时名称
	 */
	@ApiModelProperty(value = "课时名称")
	private String lessonName;
	/**
	 * 课时描述
	 */
	@ApiModelProperty(value = "课时描述")
	private String lessonDesc;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal lessonOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal lessonDiscount;
}
