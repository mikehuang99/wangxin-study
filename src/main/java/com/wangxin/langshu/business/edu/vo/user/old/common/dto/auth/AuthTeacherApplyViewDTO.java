/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.user.old.common.dto.auth;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 教师信息-审核 -查看接口
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class AuthTeacherApplyViewDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4610057587124761945L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 状态(1:有效;0:无效)
	 */
	@ApiModelProperty(value = "状态(1:有效;0:无效)")
	private Integer validValue;
	/**
	 * 教师用户编号
	 */
	@ApiModelProperty(value = "教师用户编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String teacherUserNo;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称")
	private String teacherName;
	/**
	 * 电话
	 */
	@ApiModelProperty(value = "电话")
	private String teacherMobile;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String teacherEmail;
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "职位")
	private String position;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headImgUrl;
	/**
	 * 简介
	 */
	@ApiModelProperty(value = "简介")
	private String introduce;

	/**
	 * 审核状态(1:待审核,2:审核通过,3:审核不通过)
	 */
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)")
	private Integer applyStatus;
	/**
	 * 审核意见
	 */
	@ApiModelProperty(value = "审核意见")
	private String applyResultDesc;

}
