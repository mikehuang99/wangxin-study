package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class MobileMiddleNavigationQ  implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3731321974711449923L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "导航名称", required = false)
	private String mmnName;//导航名称	
	@ApiModelProperty(value = "导航链接", required = false)
	private String mmnUrl;//导航链接
	@ApiModelProperty(value = "导航图标	", required = false)
	private String mmnIco;//导航图标	
	@ApiModelProperty(value = "导航描述", required = false)
	private String mmnDesc;//导航描述
	@TableField(value = "mmn_url_type")
	private String mmnUrlType;//导航链接类型，内部链接类型为1，外部链接2
	
	
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;


}
