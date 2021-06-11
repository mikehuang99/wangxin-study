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
@TableName(value ="point_operate_record")
public class PointOperateRecord extends Model<PointOperateRecord> implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1448329569822299259L;
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
	
	@TableField(value = "user_no_of_point")
    private String userNoOfPoint;//用户编号
	@TableField(value = "operate_type")
    private int operateType;//操作类型，0增加，1减少
	@TableField(value = "point")
    private int point;//积分
	
	@TableField(value = "total_point")
    private int totalPoint;//积分


	
	
	
}
