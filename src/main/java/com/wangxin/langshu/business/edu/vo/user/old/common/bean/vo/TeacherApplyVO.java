package com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplyVO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1883690734680829328L;
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
	 * 审核状态(0:待审核,1:审核通过,2:审核不通过)
	 */
	private Integer applyStatus;
	/**
	 * 审核意见
	 */
	private String applyResultDesc;
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



	/**
	 * 校验教师用户、教师是否存在(1:用户不存在 2:教师存在) (添加教师时候使用)
	 */
	private Integer checkUserAndTeacher;
}
