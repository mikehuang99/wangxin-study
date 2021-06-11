package com.wangxin.langshu.business.edu.vo.order.payAmountRecord;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PayAmountRecordDeleteVo  implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3102725586775686248L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
