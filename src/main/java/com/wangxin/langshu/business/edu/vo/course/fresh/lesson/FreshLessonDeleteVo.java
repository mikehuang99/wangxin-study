package com.wangxin.langshu.business.edu.vo.course.fresh.lesson;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshLessonDeleteVo  implements Serializable{
	





	/**
	 * 
	 */
	private static final long serialVersionUID = 2704545873842956152L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;


	@ApiModelProperty(value = "ID", required = true)
	private String id;
	

	@ApiModelProperty(value = "userNo", required = true)
	private String userNo;

	

}
