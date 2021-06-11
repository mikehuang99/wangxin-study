package com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherQO implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2392990909882606221L;
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
     * 排序
     */
    private Integer sort;
    /**
     * 教师用户编号
     */
    private String teacherUserNo;
    /**
     * 教师名称
     */
    private String teacherName;
    /**
     * 教师手机
     */
    private String teacherMobile;
    /**
     * 教师邮箱
     */
    private String teacherEmail;
    /**
     * 职位
     */
    private String position;
    /**
     * 头像
     */
    private String headImgUrl;
    /**
     * 简介
     */
    private String introduce;

}
