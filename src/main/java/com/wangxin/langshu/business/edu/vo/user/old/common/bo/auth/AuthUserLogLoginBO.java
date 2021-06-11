package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户错误登录日志
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthUserLogLoginBO implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = 520751320549136144L;
	/**
     * 主键
     */
    private String id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 客户端ID
     */
    private String platformId;
    /**
     * 登录状态(1成功，0失败)
     */
    private Integer loginStatus;
    /**
     * 登录IP
     */
    private String loginIp;
}
