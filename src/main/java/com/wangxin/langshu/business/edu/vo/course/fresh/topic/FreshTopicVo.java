package com.wangxin.langshu.business.edu.vo.course.fresh.topic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshTopicVo  implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2242991475174235044L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "名称", required = false)
    private String topicName;//名称
	@ApiModelProperty(value = "描述", required = false)
    private String topicDesc;//描述	
	@ApiModelProperty(value = "位置(0电脑端，1微信端, 2移动端)", required = false)
    private int topicLocation;//位置(0电脑端，1微信端, 2移动端)
	
	@ApiModelProperty(value = "专区关联的课程", required = false)
	private List<FreshTopicCourseVo> topicCourseList;//专区关联的课程
	
}
