package com.wangxin.langshu.business.edu.entity.system.old;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="news")
public class News extends Model<News> implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8744453450783518443L;
	@TableId(type = IdType.UUID)
	private String id;
	@TableField(value ="create_time")
    private Date createTime;
	@TableField(value ="modified_time")
    private Date modifiedTime;
	@TableField(value ="valid_value")
    private Integer validValue;
	@TableField(value ="sort")
    private Integer sort;
	@TableField(value ="news_title")
    private String newsTitle;
	@TableField(value ="plat_show")
    private Integer platShow;
	@TableField(value ="news_content")
	private String newsContent;
	@TableField(value ="news_type_id")
	private String newsTypeId;

}