package com.wangxin.langshu.business.edu.vo.system.old.service.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台用户信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemUserDTO implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -2216114675073665005L;
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
     * 用户编号
     */
    private String userNo;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号码
     */
    private String identityCardNo;//身份证号码
    /**
     * 备注
     */
    private String remark;
}
