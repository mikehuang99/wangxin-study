package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程关联表-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TopicCoursePageRESQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 状态(1:正常;0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常;0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String courseId;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;
	/**
	 * 位置(0电脑端，1微信端)
	 */
	@ApiModelProperty(value = "位置(0电脑端，1微信端)")
	private Integer topicLocation;
	/**
	 * 一级分类名
	 */
	@ApiModelProperty(value = "一级分类名")
	private String subjectName1;
	/**
	 * 二级分类名
	 */
	@ApiModelProperty(value = "二级分类名")
	private String subjectName2;
	/**
	 * 三级分类名
	 */
	@ApiModelProperty(value = "三级分类名")
	private String subjectName3;
}
