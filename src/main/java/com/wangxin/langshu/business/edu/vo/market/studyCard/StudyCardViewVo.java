package com.wangxin.langshu.business.edu.vo.market.studyCard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudyCardViewVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 653271569646826683L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "卡主题", required = false)
    private String cardTitle;//卡主题	
	@ApiModelProperty(value = "卡号", required = false)
    private String cardNo;//卡号	
	@ApiModelProperty(value = "卡密", required = false)
    private String cardPsw;//卡密	
	@ApiModelProperty(value = "使用课程限制，0不限制，1限制课程", required = false)
    private int isLimitCourse;//使用课程限制，0不限制，1限制课程		
	@ApiModelProperty(value = "卡消费状态，0未做任何操作，1已经绑定用户，2已经消费使用", required = false)
    private int cardStatus;//卡消费状态，0未做任何操作，1已经绑定用户，2已经消费使用
	@ApiModelProperty(value = "有效期开始", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date studyCardTimeBegin;//有效期开始		
	@ApiModelProperty(value = "有效期结束", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date studyCardTimeEnd;//有效期结束	
	@ApiModelProperty(value = "绑定用户", required = false)
    private String bindUserNo;//绑定用户	
	@ApiModelProperty(value = "绑定用户手机", required = false)
    private String bindUserMobile;//绑定用户手机	
	@ApiModelProperty(value = "面值金额", required = false)
    private BigDecimal totalFaceValueAmount;//面值金额	
	@ApiModelProperty(value = "已经使用面额", required = false)
    private BigDecimal usedFaceValueAmount;//已经使用面额	
	@ApiModelProperty(value = "被冻结的面额(例如下了订单尚未支付)", required = false)
    private BigDecimal frozenFaceValueAmount;//被冻结的面额(例如下了订单尚未支付)
	@ApiModelProperty(value = "用户绑定的时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date bindUserTime;//用户绑定的时间		
	@ApiModelProperty(value = "从绑定用户的时间(bind_user_time)开始算(days_from_bind_user_time)", required = false)
    private int daysFromBindUserTime;//从绑定用户的时间(bind_user_time)开始算(days_from_bind_user_time)
	@ApiModelProperty(value = "需抵扣积分额", required = false)
    private int valuePoint;//需抵扣积分额
	@ApiModelProperty(value = "学习卡描述", required = false)
    private String cardDesc;//学习卡描述
	@ApiModelProperty(value = "已经选择的课程ID的集合串(以英文逗号分割)", required = false)
	private String selectedCourseIdsString;//已经选择的课程ID的集合串(以英文逗号分割)

}
