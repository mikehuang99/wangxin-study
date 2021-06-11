package com.wangxin.langshu.business.edu.entity.market;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="study_card")
public class StudyCard extends Model<StudyCard> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5332362175233208752L;
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
	@TableField(value = "card_title")
    private String cardTitle;//卡主题	
	@TableField(value = "card_no")
    private String cardNo;//卡号	
	@TableField(value = "card_psw")
    private String cardPsw;//卡密	
	@TableField(value = "is_limit_course")
    private int isLimitCourse;//使用课程限制，0不限制，1限制课程		
	@TableField(value = "card_status")
    private int cardStatus;//卡状态，0未做任何操作，1绑定用户，2已经消费使用，3禁止使用	
	@TableField(value = "study_card_time_begin")
    private Date studyCardTimeBegin;//有效期开始		
	@TableField(value = "study_card_time_end")
    private Date studyCardTimeEnd;//有效期结束	
	@TableField(value = "bind_user_no")
    private String bindUserNo;//绑定用户	
	@TableField(value = "total_face_value_amount")
    private BigDecimal totalFaceValueAmount;//面值金额	
	@TableField(value = "used_face_value_amount")
    private BigDecimal usedFaceValueAmount;//已经使用面额		
	@TableField(value = "frozen_face_value_amount")
    private BigDecimal frozenFaceValueAmount;//被冻结的面额(例如下了订单尚未支付)
	@TableField(value = "bind_user_time")
    private Date bindUserTime;//用户绑定的时间		
	@TableField(value = "days_from_bind_user_time")
    private int daysFromBindUserTime;//从绑定用户的时间(bind_user_time)开始算(days_from_bind_user_time)
	@TableField(value = "value_point")
    private int valuePoint;//需抵扣积分额
	@TableField(value = "card_desc")
    private String cardDesc;//学习卡描述
	
}
