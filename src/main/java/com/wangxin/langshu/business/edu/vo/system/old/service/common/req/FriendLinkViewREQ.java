package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点友情链接-查看
 *
 */
@Data
@Accessors(chain = true)
public class FriendLinkViewREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7862465797933759645L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id", required = true)
	private String id;
}
