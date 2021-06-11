package com.wangxin.langshu.business.edu.vo.course.fresh.course;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.TeacherDTO;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterMobileViewVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 *  
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshCourseMobileView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7183130182926252946L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 教师用户编码
	 */
	@ApiModelProperty(value = "教师用户编码")
	@JsonSerialize(using = ToStringSerializer.class)
	private String teacherUserNo;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;
	/**
	 * 课程封面
	 */
	@ApiModelProperty(value = "课程封面")
	private String courseLogo;
	/**
	 * 课程介绍ID
	 */
	@ApiModelProperty(value = "课程介绍ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String introduceId;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	@ApiModelProperty(value = "是否上架(1:上架，0:下架)")
    private int isPutaway;
	
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private int	validValue;//状态(1:正常，0:禁用)	
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal courseOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal courseDiscount;
	/**
	 * 购买人数
	 */
	@ApiModelProperty(value = "购买人数")
	private Integer buyCount;
	/**
	 * 学习人数
	 */
	@ApiModelProperty(value = "学习人数")
	private Integer studyCount;
	/**
	 * 总课时数
	 */
	@ApiModelProperty(value = "总课时数")
	private Integer lessonTotal;

	/**
	 * 课程介绍
	 */
	@ApiModelProperty(value = "课程介绍")
	private String introduce;

	/**
	 * 是否购买
	 */
	@ApiModelProperty(value = "是否支付(1:已支付;0:未支付)")
	private int isPay;
	
	/**
	 * 教师信息
	 */
	@ApiModelProperty(value = "教师信息")
	private TeacherDTO teacher;
	/**
	 * 章节信息
	 */
	@ApiModelProperty(value = "章节信息")
	private List<FreshChapterMobileViewVo> chapterList;
	
	/**
	 *关联试题数量
	 */
	@ApiModelProperty(value = "课程关联试题数量")
	private Integer examQuestionCourseCount;
	
	@ApiModelProperty(value = "计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)")
    private int costTimeType;//计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
	@ApiModelProperty(value = "课程类型，0点播，1直播，2点播+直播")
    private int courseType;//课程类型，0点播，1直播，2点播+直播
	
	
	
	@ApiModelProperty(value = "用户有效日期", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date validTerm;//用户有效日期
	
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private String userNo;//用户编号
	
	private int userRightCount;//用户正确试题数量(针对课时刷题统计)
	
	private int userErrorCount;//用户错误试题数量(针对课时刷题统计)
	
	

}
