package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类-更新
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseSubjectUpdateREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String subjectName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = false)
	private String remark;
	
	/**
	 * 移动端图标
	 */
	@ApiModelProperty(value = "移动端图标", required = false)
	private String mobileImg;

}
