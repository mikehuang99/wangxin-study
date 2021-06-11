package com.wangxin.langshu.business.edu.vo.market.studyCard;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudyCardDeleteVo  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4502600504698770436L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
