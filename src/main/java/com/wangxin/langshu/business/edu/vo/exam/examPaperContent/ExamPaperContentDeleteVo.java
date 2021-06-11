package com.wangxin.langshu.business.edu.vo.exam.examPaperContent;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamPaperContentDeleteVo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8201366491766897883L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	@ApiModelProperty(value = "用户编号", required = false)
	private String userNo;//用户编号

}
