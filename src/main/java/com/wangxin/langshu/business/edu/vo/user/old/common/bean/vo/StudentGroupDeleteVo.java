package com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudentGroupDeleteVo  implements Serializable{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7107124509902252011L;
	@ApiModelProperty(value = "ID", required = true)
	private String id;
	
	//@ApiModelProperty(value = "ID数组", required = true)
	//private String[] ids;
   
	

}
