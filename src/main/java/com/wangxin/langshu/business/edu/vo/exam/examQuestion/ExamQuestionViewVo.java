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
public class ExamQuestionViewVo  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6397512882181160233L;
	@ApiModelProperty(value = "id", required = true)
	private String id;
	
    private Date createTime;//创建时间	
    private Date modifiedTime;	//修改时间	
    private int	validValue;//状态(1:正常，0:禁用)	
    private int sort;//排序
    private String questionTitle;//题干		
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
    private String customLabel;//自定义标签，用于方便搜索
   
	

}
