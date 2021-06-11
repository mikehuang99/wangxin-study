package com.wangxin.langshu.business.edu.vo.exam.examPaper;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamPaperVo  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2937574845933507023L;
/**
	 * 
	 */
	
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "试卷标题", required = false)
	private String title;//试卷标题	
	@ApiModelProperty(value = "试卷副标题", required = false)
	private String subtitle;//试卷副标题	
	@ApiModelProperty(value = "限时(分钟)", required = false)
	private String limitTime;//限时(分钟)
	@ApiModelProperty(value = "总分", required = false)
	private String totalScore;//总分
	@ApiModelProperty(value = "及格分数", required = false)
	private String passScore;//及格分数
	@ApiModelProperty(value = "难度", required = false)
	private int level;//难度
	@ApiModelProperty(value = "出卷人", required = false)
	private String author;//出卷人
	@ApiModelProperty(value = "试题简介", required = false)
	private String introduce;//试题简介
	@ApiModelProperty(value = "注意事项", required = false)
	private String attentionPoints;//注意事项
	@ApiModelProperty(value = "出题范围", required = false)
	private String areaType;//出题范围
	@ApiModelProperty(value = "课程ID", required = false)
	private String courseId;//课程ID
	@ApiModelProperty(value = "课程名称", required = false)
	private String courseName;//课程名称
	@ApiModelProperty(value = "单选题数量", required = false)
	private int singleChoiceCount;//单选题数量
	@ApiModelProperty(value = "判断题数量", required = false)
	private int multiChoiceCount;//多选题数量
	@ApiModelProperty(value = "判断题数量", required = false)
	private int judgeCount;//判断题数量
	@ApiModelProperty(value = "填空题数量", required = false)
	private int fillBlankCount;//填空题数量
	@ApiModelProperty(value = "简单题数量", required = false)
	private int simpleCount;//简单题数量
	@ApiModelProperty(value = "单选题分数", required = false)
	private float singleChoiceScore;//单选题分数
	@ApiModelProperty(value = "多选题分数", required = false)
	private float multiChoiceScore;//多选题分数
	@ApiModelProperty(value = "判断题分数", required = false)
	private float judgeScore;//判断题分数
	@ApiModelProperty(value = "填空题分数", required = false)
	private float fillBlankScore;//填空题分数
	@ApiModelProperty(value = "simpleScore", required = false)
	private float simpleScore;//简答题分数
	@ApiModelProperty(value = "按章节比例出题内容(json存储)", required = false)
	private String detailByChapter;//按章节比例出题内容(json存储)	
	
	@ApiModelProperty(value = "页面传过来的userNo", required = false)
    private String userNo;//页面传过来的userNo
	@ApiModelProperty(value = "teacherUserNo", required = false)
    private String teacherUserNo;//出这试卷的教师用户编号
	@ApiModelProperty(value = "试卷构建状态(0未生成，1正在生成，2已经生成，3生成失败)", required = false)
    private int buildStatus;
	@ApiModelProperty(value = "生成试卷失败原因", required = false)
	private String buildError;//生成试卷失败原因


}
