package com.wangxin.langshu.business.edu.vo.system.old.service.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuDTO implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 7721811155364594350L;
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
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 目标名称
     */
    private String targetName;
    /**
     * 备注
     */
    private String remark;
}
