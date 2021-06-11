package com.wangxin.langshu.business.edu.entity.market;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="dot_operate_record")
public class DotOperateRecord extends Model<DotOperateRecord> implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = -323986738077849565L;
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
	
	@TableField(value = "user_no_of_dot")
    private String userNoOfDot;//用户编号
	@TableField(value = "operate_type")
    private int operateType;//操作类型，0增加，1减少
	
	
	
	@TableField(value = "dot_change_reson")
    private int dotChangeReson;//点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册、5兑换积分
	
	@TableField(value = "operate_date")
    private String operateDate;//操作日期字符串，格式：2010-09-10
	
	@TableField(value = "dot")
    private int dot;//点券
	
	@TableField(value = "total_dot")
    private int totalDot;//点券总量


	
	
	
}
