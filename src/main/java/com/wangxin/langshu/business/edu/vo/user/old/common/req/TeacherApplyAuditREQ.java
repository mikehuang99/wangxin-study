package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplyAuditREQ implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -1149436039780312743L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private String id;
	/**
	 * 审核状态(0:待审核,1:审核通过,2:审核不通过)
	 */
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = true)
	private Integer applyStatus;
	/**
	 * 审核意见
	 */
	@ApiModelProperty(value = "审核意见", required = false)
	private String applyResultDesc;

}
