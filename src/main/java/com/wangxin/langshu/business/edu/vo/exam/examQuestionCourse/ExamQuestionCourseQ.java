package com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ExamQuestionCourseQ  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2643970852640108010L;
	/**
	 * 
	 */
	private String id;
    private Date createTime;//创建时间	
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
    private int sort;//排序
	@ApiModelProperty(value = "题干", required = false)
    private String questionTitle;//题干		
	@ApiModelProperty(value = "题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)", required = false)
    private String	questionType;//题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)
    private String	itemA;//A选项	
    private String itemB;//B选项		
    private String itemC;	//C选项		
    private String itemD;//D选项		
    private String itemE;// E选项	
    private String itemF;//	F选项		
    private String itemG;	//	G选项	
    private String itemH;//	H选项	
    private String answer;	//答案。单选、多选、判断(1正确，0错误)、填空(json形式，{"item1":"aa","item2":"bb"})、简答题答案	
    private String analysis;//解析	
    private String userNo;//user表对应的user_no
    private String	level;//		难度，1到5，1最容易，5最难	
    private String	subjectId1;	
    private String	subjectId2;	
    private String  subjectId3;
    
	@ApiModelProperty(value = "试题的ID", required = false)
    private String questionId;//试题表exam_question的ID
	@ApiModelProperty(value = "课程ID", required = false)
    private String	courseId;//课程表course的ID
	@ApiModelProperty(value = "章节ID", required = false)
    private String	chapterId;//章节表chapter的ID
	@ApiModelProperty(value = "课时ID", required = false)
    private String	lessonId;//课时表lesson的ID
    
	
	@ApiModelProperty(value = "绑定类型(1课程、2章节、3课时)", required = false)
	private String bindType;//绑定类型(1课程、2章节、3课时,4可以只传单独章节ID，5可以只传单独课时ID)
	@ApiModelProperty(value = "是否查询关联的或者未关联的（0未关联，1已经关联）", required = false)
	private String isRelate;//是否查询关联的或者未关联的（0未关联，1已经关联）
	@ApiModelProperty(value = "自定义标签，用于方便搜索", required = false)
    private String customLabel;//自定义标签，用于方便搜索
    

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
