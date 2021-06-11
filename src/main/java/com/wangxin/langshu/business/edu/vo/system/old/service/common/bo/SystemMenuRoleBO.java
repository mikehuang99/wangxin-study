package com.wangxin.langshu.business.edu.vo.system.old.service.common.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单角色关联表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemMenuRoleBO implements Serializable {

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
     * 菜单ID
     */
    private String menuId;
    /**
     * 角色ID
     */
    private String roleId;
}
