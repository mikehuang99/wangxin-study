package com.wangxin.langshu.business.edu.entity.system.old;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="mobile_middle_navigation")
public class MobileMiddleNavigation extends Model<MobileMiddleNavigation> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6724506254903414450L;
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
	@TableField(value = "mmn_name")
	private String mmnName;//导航名称	
	@TableField(value = "mmn_url")
	private String mmnUrl;//导航链接
	@TableField(value = "mmn_ico")
	private String mmnIco;//导航图标	
	@TableField(value = "mmn_desc")
	private String mmnDesc;//导航描述
	@TableField(value = "mmn_url_type")
	private int mmnUrlType;//导航链接类型，内部链接类型为1，外部链接2
}
