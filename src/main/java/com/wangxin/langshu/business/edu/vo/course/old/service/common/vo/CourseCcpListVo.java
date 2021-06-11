package com.wangxin.langshu.business.edu.vo.course.old.service.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类
 *
 */
@Data
@Accessors(chain = true)
public class CourseCcpListVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9156836816765782126L;
	@ApiModelProperty(value = "分类列表", required = true)
	private List<CourseCcpVo> courseCcpList = new ArrayList<>();
}
