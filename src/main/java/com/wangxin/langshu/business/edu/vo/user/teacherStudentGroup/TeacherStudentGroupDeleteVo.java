package com.wangxin.langshu.business.edu.vo.user.teacherStudentGroup;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TeacherStudentGroupDeleteVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4872355116616776183L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
