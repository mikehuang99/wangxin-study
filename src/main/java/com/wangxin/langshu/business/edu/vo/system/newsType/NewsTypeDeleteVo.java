package com.wangxin.langshu.business.edu.vo.system.newsType;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class NewsTypeDeleteVo  implements Serializable{
	
	



	/**
	 * 
	 */
	private static final long serialVersionUID = -1506032007706959576L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	@ApiModelProperty(value = "ID", required = true)
	private String id;

}
