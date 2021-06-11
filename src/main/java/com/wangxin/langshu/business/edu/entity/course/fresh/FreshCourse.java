package com.wangxin.langshu.business.edu.entity.course.fresh;

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
@TableName(value ="course")
public class FreshCourse extends Model<FreshCourse> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1818057253673053471L;
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
	
	@TableField(value = "teacher_user_no")
    private String teacherUserNo;//教师userNo
	
	@TableField(value = "subject_id1")
    private String subjectId1;//一级分类ID
	@TableField(value = "subject_id2")
    private String subjectId2;//二级分类ID
	@TableField(value = "subject_id3")
    private String subjectId3;//三级分类ID
	
	@TableField(value = "course_name")
    private String courseName;//课程名称
	@TableField(value = "course_logo")
    private String courseLogo;//课程封面
	@TableField(value = "introduce_id")
    private String introduceId;//课程介绍ID
	

	@TableField(value = "is_free")
    private int isFree;//是否免费：1免费，0收费
	@TableField(value = "course_original")
    private BigDecimal courseOriginal;//原价
	@TableField(value = "course_discount")
    private BigDecimal courseDiscount;//优惠价
	
	
	@TableField(value = "is_putaway")
    private int isPutaway;//是否上架(1:上架，0:下架)
	
	@TableField(value = "course_sort")
    private String courseSort;//课程排序(前端显示使用)
	@TableField(value = "buy_count")
    private int buyCount;//购买人数

	@TableField(value = "study_count")
    private int studyCount;//学习人数
	@TableField(value = "lesson_total")
    private int lessonTotal;//总课时数
	
	@TableField(value = "cost_time_type")
    private int costTimeType;//计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
	@TableField(value = "course_type")
    private int courseType;//课程类型，0点播，1直播，2点播+直播
	
	
}
