package com.wangxin.langshu.business.edu.vo.exam.examPaper;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamPaperDeleteVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8491752764433861880L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
