package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点友情链接-删除
 *
 */
@Data
@Accessors(chain = true)
public class FriendLinkDeleteREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3891059676523899237L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id", required = true)
	private String id;
}
