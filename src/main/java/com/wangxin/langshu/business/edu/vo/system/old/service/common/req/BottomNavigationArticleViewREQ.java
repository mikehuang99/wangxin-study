package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航文章-查看
 */
@Data
@Accessors(chain = true)
public class BottomNavigationArticleViewREQ implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3059604947031753681L;
	/**
	 * 导航ID
	 */
	@ApiModelProperty(value = "导航ID")
	private String navigationId;
}
