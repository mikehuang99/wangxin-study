package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 推荐课程集合
 * 
 * 
 *
 */
@Data
@Accessors(chain = true)
public class CourseRecommendListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 推荐课程集合
	 */
	@ApiModelProperty(value = "推荐课程集合")
	private List<CourseRecommendDTO> list = new ArrayList<>();

}
