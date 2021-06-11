package com.wangxin.langshu.business.edu.vo.market.studyCard;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class StudyCardQ  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5500557160268259612L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "卡主题", required = false)
    private String cardTitle;//卡主题	
	@ApiModelProperty(value = "卡号", required = false)
    private String cardNo;//卡号	
	@ApiModelProperty(value = "卡密", required = false)
    private String cardPsw;//卡密	
	@ApiModelProperty(value = "使用课程限制，0不限制，1限制课程", required = false)
    private String isLimitCourse;//使用课程限制，0不限制，1限制课程		
	@ApiModelProperty(value = "卡消费状态，0未做任何操作，1已经绑定用户，2已经消费使用", required = false)
    private String cardStatus;//卡消费状态，0未做任何操作，1已经绑定用户，2已经消费使用
	@ApiModelProperty(value = "有效期开始查询开始", required = false)
    private String studyCardTimeBeginBegin;//有效期开始查询开始	
	@ApiModelProperty(value = "有效期开始查询结束", required = false)
    private String studyCardTimeBeginEnd;//有效期开始查询结束
	@ApiModelProperty(value = "有效期结束查询开始", required = false)
    private String studyCardTimeEndBegin;//有效期结束查询开始
	@ApiModelProperty(value = "有效期结束查询结束", required = false)
    private String studyCardTimeEndEnd;//有效期结束查询结束
	
	@ApiModelProperty(value = "有效期结束是否今天之前的,如果是值为TRUE", required = false)
    private String isStudyCardTimeEndBeforeToday;//有效期结束是否今天之前的,如果是值为TRUE
	
	
	@ApiModelProperty(value = "绑定用户", required = false)
    private String bindUserNo;//绑定用户	
	@ApiModelProperty(value = "绑定用户手机", required = false)
    private String bindUserMobile;//绑定用户手机	
	@ApiModelProperty(value = "面值金额", required = false)
    private String totalFaceValueAmount;//面值金额	
	@ApiModelProperty(value = "已经使用面额", required = false)
    private String usedFaceValueAmount;//已经使用面额	
	@ApiModelProperty(value = "被冻结的面额(例如下了订单尚未支付)", required = false)
    private String frozenFaceValueAmount;//被冻结的面额(例如下了订单尚未支付)
	@ApiModelProperty(value = "绑定用户(激活)时间查询开始", required = false)
    private String bindUserTimeBegin;//绑定用户(激活)时间查询开始	
	@ApiModelProperty(value = "绑定用户(激活)时间查询结束", required = false)
    private String bindUserTimeEnd;//绑定用户(激活)时间查询结束
	@ApiModelProperty(value = "从绑定用户的时间(bind_user_time)开始算(days_from_bind_user_time)", required = false)
    private String daysFromBindUserTime;//从绑定用户的时间(bind_user_time)开始算(days_from_bind_user_time)
	@ApiModelProperty(value = "需抵扣积分额", required = false)
    private String valuePoint;//需抵扣积分额
	@ApiModelProperty(value = "学习卡描述", required = false)
    private String cardDesc;//学习卡描述
	
	@ApiModelProperty(value = "查询用户", required = false)
    private String userNo;//查询用户	
	
	@ApiModelProperty(value = "该课程是否可用", required = false)
    private String courseId;//该课程是否可用
	
	
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;


}
