package com.wangxin.langshu.business.edu.vo.course.old.service.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类
 *
 */
@Data
@Accessors(chain = true)
public class CourseCcpVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2939835865990027212L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "分类编号", required = true)
	private String id;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称", required = true)
	private String name;
	/**
	 * 层级
	 */
	@ApiModelProperty(value = "分类层级", required = true)
	private Integer floor;
	/**
	 * 分类备注信息
	 */
	@ApiModelProperty(value = "备注", required = true)
	private String remark;
	
	/**
	 * 课程分类,二级分类列表
	 */
	@ApiModelProperty(value = "二级分类信息列表", required = true)
	private List<CourseCcpTwoVo> twoList = new ArrayList<>();
}
