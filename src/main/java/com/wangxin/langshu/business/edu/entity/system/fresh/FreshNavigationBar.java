package com.wangxin.langshu.business.edu.entity.system.fresh;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="navigation_bar")
public class FreshNavigationBar extends Model<FreshNavigationBar> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3577253913980719171L;
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

	@TableField(value = "navigation_title")
    private String navigationTitle;//导航标题
	@TableField(value = "navigation_url")
    private String navigationUrl;//导航url
	@TableField(value = "target")
    private String target;//跳转方式	
	

}

