package com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamManagementStudentGroupRelateDeleteVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9084811908605779050L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
