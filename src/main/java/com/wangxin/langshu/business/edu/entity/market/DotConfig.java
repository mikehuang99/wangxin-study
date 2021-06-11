package com.wangxin.langshu.business.edu.entity.market;

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
@TableName(value ="dot_config")
public class DotConfig extends Model<DotConfig> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5191953402070164563L;
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
	
	@TableField(value ="reg_dot")
    private int regDot;//初次注册获得点券
	@TableField(value ="login_dot")
    private int loginDot;//每次登陆获取点券
	@TableField(value ="login_max_dot_per_day")
    private int loginMaxDotPerDay;//登陆一天获得的最大点券
	@TableField(value ="share_dot")
    private int shareDot;//分享每次获得得点券
	@TableField(value ="share_max_dot_per_day")
    private int shareMaxDotPerDay;//分享链接每天获得的最大点券
	@TableField(value ="share_reg_dot")
    private int shareRegDot;//分享后每次注册获得的点券
	@TableField(value ="share_reg_max_dot_per_day")
    private int shareRegMaxDotPerDay;//分享链接被注册每天获得的最大点券
	@TableField(value ="dot_point")
    private int dotPoint;//多少点券可以换一个积分
	
	
	@TableField(value ="reg_link")
    private String regLink;//推广注册链接
	
	
	

	
	


}