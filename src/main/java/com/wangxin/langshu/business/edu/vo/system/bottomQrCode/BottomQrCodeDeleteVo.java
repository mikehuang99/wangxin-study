package com.wangxin.langshu.business.edu.vo.system.bottomQrCode;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class BottomQrCodeDeleteVo  implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 7236724828220278714L;

	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	@ApiModelProperty(value = "ID", required = true)
	private String id;

}
