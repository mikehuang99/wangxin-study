package com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudyCardLimitCourseDeleteVo  implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6259996906662058585L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
