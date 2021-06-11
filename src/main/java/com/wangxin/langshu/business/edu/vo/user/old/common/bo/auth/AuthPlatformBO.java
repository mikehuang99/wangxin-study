package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 平台信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthPlatformBO implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = -2286245692489241744L;
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
     * 机构号
     */
    private String orgNo;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端密匙
     */
    private String clientSecret;
    /**
     * 客户端名称
     */
    private String clientName;
    /**
     * 角色类型
     */
    private Integer roleType;
    /**
     * 备注
     */
    private String remark;
}
