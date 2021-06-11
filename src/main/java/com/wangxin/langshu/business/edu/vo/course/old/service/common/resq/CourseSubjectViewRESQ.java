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
 * 专区表-查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseSubjectViewRESQ implements Serializable {

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
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifiedTime;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 父分类ID
	 */
	@ApiModelProperty(value = "父分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String parentId;
	/**
	 * 分类类型(1课程，2资源)
	 */
	@ApiModelProperty(value = "分类类型(1课程，2资源)")
	private Integer subjectType;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String subjectName;
	/**
	 * 层级
	 */
	@ApiModelProperty(value = "层级")
	private Integer floor;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	/**
	 * 移动端图标
	 */
	@ApiModelProperty(value = "移动端图标")
	private String mobileImg;
}
