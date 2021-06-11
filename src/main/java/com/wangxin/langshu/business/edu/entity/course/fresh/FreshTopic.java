package com.wangxin.langshu.business.edu.entity.course.fresh;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="topic")
public class FreshTopic extends Model<FreshTopic> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3887932669451123475L;
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
	
	@TableField(value = "topic_name")
    private String topicName;//名称
	@TableField(value = "topic_desc")
    private String topicDesc;//描述	
	@TableField(value = "topic_location")
    private int topicLocation;//位置(0电脑端，1微信端, 2移动端)
	
}
