package com.wangxin.langshu.business.edu.vo.exam.examQuestion;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamQuestionSaveVo  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2325018892087956486L;
	/**
	 * 
	 */
	@ApiModelProperty(value = "id", required = true)
	private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "题干", required = true)
    private String questionTitle;//题干		
	@ApiModelProperty(value = "题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)", required = true)
    private String	questionType;//题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)	
	@ApiModelProperty(value = "A选项", required = false)
    private String	itemA;//A选项	
	@ApiModelProperty(value = "B选项", required = false)
    private String itemB;//B选项		
	@ApiModelProperty(value = "C选项", required = false)
    private String itemC;	//C选项	
	@ApiModelProperty(value = "D选项", required = false)
    private String itemD;//D选项	
	@ApiModelProperty(value = "E选项", required = false)
    private String itemE;// E选项	
	@ApiModelProperty(value = "F选项", required = false)
    private String itemF;//	F选项	
	@ApiModelProperty(value = "G选项", required = false)
    private String itemG;	//	G选项	
	@ApiModelProperty(value = "H选项", required = false)
    private String itemH;//	H选项	
	@ApiModelProperty(value = "答案。单选、多选、判断(1正确，0错误)、填空(json形式，{\"item1\":\"aa\",\"item2\":\"bb\"})、简答题答案", required = false)
    private String answer;	//答案。单选、多选、判断(1正确，0错误)、填空(json形式，{"item1":"aa","item2":"bb"})、简答题答案
	@ApiModelProperty(value = "解析", required = false)
    private String analysis;//解析	
	@ApiModelProperty(value = "userNo", required = false)
    private String userNo;//user表对应的user_no
	@ApiModelProperty(value = "难度，1到5，1最容易，5最难,6未知", required = true)
    private String	level;//		难度，1到5，1最容易，5最难	
	@ApiModelProperty(value = "subjectId1", required = false)
    private String	subjectId1;	
	@ApiModelProperty(value = "subjectId2", required = false)
    private String	subjectId2;	
	@ApiModelProperty(value = "subjectId3", required = false)
    private String  subjectId3;
	@ApiModelProperty(value = "customLabel", required = false)
    private String customLabel;//自定义标签，用于方便搜索

	

}
