package com.wangxin.langshu.business.edu.vo.system.old.service.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemRoleDTO implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = 4013836190479715691L;
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
     * 名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;
}
