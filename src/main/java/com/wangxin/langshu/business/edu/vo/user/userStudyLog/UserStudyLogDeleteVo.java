package com.wangxin.langshu.business.edu.vo.user.userStudyLog;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserStudyLogDeleteVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5333966749896839447L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	

}
