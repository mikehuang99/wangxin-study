package com.wangxin.langshu.business.edu.entity.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="bottom_qr_code")
public class BottomQrCode extends Model<BottomQrCode> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4578805960640738548L;
	//@TableId(type = IdType.UUID)
	@TableField(value ="id")
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序

	@TableField(value = "qr_code_name")
    private String qrCodeName;//二维码名称
	@TableField(value = "qr_code_image_url")
    private String qrCodeImageUrl;//二维码图片地址
	
	

}

