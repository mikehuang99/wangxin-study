package com.wangxin.langshu.business.edu.vo.user.studyProof;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudyProofDeleteVo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1494401824250787610L;

	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
	
	@ApiModelProperty(value = "ID", required = true)
	private String id;
	@ApiModelProperty(value = "userNo", required = true)
	private String userNo;

}
