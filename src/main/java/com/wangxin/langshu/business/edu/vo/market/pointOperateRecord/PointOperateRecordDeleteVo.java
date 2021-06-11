package com.wangxin.langshu.business.edu.vo.market.pointOperateRecord;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PointOperateRecordDeleteVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3877747588242805744L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
