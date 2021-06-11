package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SystemConfigDeleteVo  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1893798873905917202L;
	/**
	 * 
	 */
	@ApiModelProperty(value = "ID数组(ID数组与ID只可以有一个有值,两个都有值的时候只有ID有效)", required = false)
	private String[] ids;
	@ApiModelProperty(value = "ID(ID数组与ID只可以有一个有值,两个都有值的时候只有ID有效)", required = false)
	private String id;
   
	

}
