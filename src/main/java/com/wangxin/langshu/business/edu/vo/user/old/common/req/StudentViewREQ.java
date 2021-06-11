package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户查看信息
 * 
 */
@Data
@Accessors(chain = true)
public class StudentViewREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3387809585423929615L;
	/**
	 * 用户主键
	 */
	@ApiModelProperty(value = "用户主键")
	private String id;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private String orderUserNo;

}
