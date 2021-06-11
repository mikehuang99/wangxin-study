package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	private Integer validValue;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 父ID
	 */
	private String parentId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单路径
	 */
	private String menuUrl;
	/**
	 * 接口URL
	 */
	@ApiModelProperty(value = "接口URL")
	private String apiUrl;
	/**
	 * 菜单图标
	 */
	private String menuIcon;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否显示(1: 显示；0:不显示)
	 */
	@ApiModelProperty(value = "是否显示(1: 显示；0:不显示)")
	private Integer hiddenType;
	/**
	 * 菜单集合
	 */
	List<SystemMenuVO> list;

}
