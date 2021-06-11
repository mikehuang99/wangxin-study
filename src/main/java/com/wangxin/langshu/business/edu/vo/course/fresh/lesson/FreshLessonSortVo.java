package com.wangxin.langshu.business.edu.vo.course.fresh.lesson;

import java.io.Serializable;
import java.util.List;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditViewDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课时信息- 排序
 *
 *  
 */
@Data
@Accessors(chain = true)
public class FreshLessonSortVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7978365932030251426L;

	/**
	 * 课时集合
	 */
	@ApiModelProperty(value = "课时集合", required = true)
	private List<AuthLessonAuditViewDTO> lessonViewList;
 
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String userNo;
}
