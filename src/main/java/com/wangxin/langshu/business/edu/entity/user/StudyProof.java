package com.wangxin.langshu.business.edu.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="study_proof")
public class StudyProof extends Model<StudyProof> implements Serializable { 


	/**
	 * 
	 */
	private static final long serialVersionUID = -5885745530966944233L;
	//@TableId(type = IdType.UUID)
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序

	
	@TableField(value = "proof_user_no")
    private String proofUserNo;//该证明的用户编号
	@TableField(value = "real_name")
    private String realName;//用户真实姓名
	@TableField(value = "sex")
    private int sex;//性别(1男，2女，3保密)
	@TableField(value = "head_img_url")
    private String headImgUrl;//头像地址	
	@TableField(value = "mobile")
    private String mobile;//手机号码
	@TableField(value = "identity_card_no")
    private String identityCardNo;//身份证号码
	@TableField(value = "age")
    private int age;//年龄
	@TableField(value = "proof_date")
    private Date proofDate;//打印该证明的日期
	@TableField(value = "main_seal_content")
    private String mainSealContent;//主印章名称(大圆的那个印章名称)
	@TableField(value = "second_seal_content")
    private String secondSealContent;//印章内横着的那行字内容
	

   
}