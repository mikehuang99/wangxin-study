package com.wangxin.langshu.business.edu.vo.system.old.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点友情链接
 *
 * 
 */
@Data
@Accessors(chain = true)
public class FriendLinkListDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8825801033344323591L;
	/**
	 * 站点友情链接集合
	 */
	@ApiModelProperty(value = "站点友情链接集合")
	private List<FriendLinkDTO> FriendLinkList = new ArrayList<>();

}
