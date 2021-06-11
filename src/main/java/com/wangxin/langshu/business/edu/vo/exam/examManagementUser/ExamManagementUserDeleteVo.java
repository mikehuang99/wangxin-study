package com.wangxin.langshu.business.edu.vo.exam.examManagementUser;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamManagementUserDeleteVo  implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3817223170628886185L;

	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
   
	@ApiModelProperty(value = "userNo", required = false)
    private String userNo;//页面传过来的userNo

}
