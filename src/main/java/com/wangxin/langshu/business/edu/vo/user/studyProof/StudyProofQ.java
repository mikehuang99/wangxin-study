package com.wangxin.langshu.business.edu.vo.user.studyProof;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudyProofQ  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1428692513584493910L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	
	
	@ApiModelProperty(value = "该证明的用户编号", required = false)
    private String proofUserNo;//该证明的用户编号
	@ApiModelProperty(value = "用户真实姓名", required = false)
    private String realName;//用户真实姓名
	@ApiModelProperty(value = "性别(1男，2女，3保密)", required = false)
    private int sex;//性别(1男，2女，3保密)
	@ApiModelProperty(value = "头像地址", required = false)
    private String headImgUrl;//头像地址	
	@ApiModelProperty(value = "手机号码", required = false)
    private String mobile;//手机号码
	@ApiModelProperty(value = "身份证号码", required = false)
    private String identityCardNo;//身份证号码
	@ApiModelProperty(value = "年龄", required = false)
    private int age;//年龄
	@ApiModelProperty(value = "打印该证明日期", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date proofDate;//打印该证明日期
	@ApiModelProperty(value = "主印章名称(大圆的那个印章名称)", required = false)
    private String mainSealContent;//主印章名称(大圆的那个印章名称)
	@ApiModelProperty(value = "印章内横着的那行字内容", required = false)
    private String secondSealContent;//印章内横着的那行字内容
	

	
	

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
}
