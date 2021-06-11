package com.wangxin.langshu.business.edu.vo.system.old.service.common.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航
 *
 * 
 */
@Data
@Accessors(chain = true)
public class BottomNavigationBO implements Serializable {

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
     * 状态(1有效, 0无效)
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
     * 导航名称
     */
    private String navigationName;
}
