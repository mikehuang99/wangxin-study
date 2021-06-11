package com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户基本信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class UserQO implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1891270941604660185L;
	/**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
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
     * 用户编号
     */
    private String userNo;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 密码盐
     */
    private String pswSalt;
    /**
     * 登录密码
     */
    private String userPsw;
   
}
