package com.wangxin.langshu.business.edu.vo.market.dotOperateRecord;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DotOperateRecordDeleteVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5724706098282122429L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
