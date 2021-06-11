package com.wangxin.langshu.business.edu.entity.exam;

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
@TableName(value ="exam_paper")
public class ExamPaper extends Model<ExamPaper> implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 5838613868773992277L;
	@TableId(type = IdType.UUID)
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	@TableField(value = "title")
	private String title;//试卷标题	
	@TableField(value = "subtitle")
	private String subtitle;//试卷副标题	
	@TableField(value = "limit_time")
	private String limitTime;//限时(分钟)
	@TableField(value = "total_score")
	private String totalScore;//总分
	@TableField(value = "pass_score")
	private String passScore;//及格分数
	@TableField(value = "level")
	private int level;//难度
	@TableField(value = "author")
	private String author;//出卷人
	@TableField(value = "introduce")
	private String introduce;//试题简介
	@TableField(value = "attention_points")
	private String attentionPoints;//注意事项
	@TableField(value = "area_type")
	private String areaType;//出题范围
	@TableField(value = "course_id")
	private String courseId;//课程ID
	@TableField(value = "single_choice_count")
	private int singleChoiceCount;//单选题数量
	@TableField(value = "multi_choice_count")
	private int multiChoiceCount;//多选题数量
	@TableField(value = "judge_count")
	private int judgeCount;//判断题数量
	@TableField(value = "fill_blank_count")
	private int fillBlankCount;//填空题数量
	@TableField(value = "simple_count")
	private int simpleCount;//简单题数量
	@TableField(value = "single_choice_score")
	private float singleChoiceScore;//单选题分数
	@TableField(value = "multi_choice_score")
	private float multiChoiceScore;//多选题分数
	@TableField(value = "judge_score")
	private float judgeScore;//判断题分数
	@TableField(value = "fill_blank_score")
	private float fillBlankScore;//填空题分数
	@TableField(value = "simple_score")
	private float simpleScore;//简答题分数
	@TableField(value = "detail_by_chapter")
	private String detailByChapter;//按章节比例出题内容(json存储)	
	/*
	@TableField(value = "user_no")
    private String userNo;//user表对应的user_no*/
	@TableField(value = "teacher_user_no")
    private String teacherUserNo;//u出这试卷的教师用户编号
	
	@TableField(value = "build_status")
    private int buildStatus;//试卷构建状态(0未生成，1正在生成，2已经生成，3生成失败)
	@TableField(value = "build_error")
	private String buildError;//生成试卷失败原因
	
	


}
