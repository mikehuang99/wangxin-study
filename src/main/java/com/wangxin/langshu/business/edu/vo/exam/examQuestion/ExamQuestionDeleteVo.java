package com.wangxin.langshu.business.edu.vo.exam.examQuestion;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamQuestionDeleteVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2245729893734658192L;
	@ApiModelProperty(value = "id", required = true)
	private String id;
   
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;

}
