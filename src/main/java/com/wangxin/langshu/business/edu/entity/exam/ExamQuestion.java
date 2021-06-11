package com.wangxin.langshu.business.edu.entity.exam;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="exam_question")
public class ExamQuestion extends Model<ExamQuestion> implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -928575042907476996L;
	
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
	@TableField(value = "question_title")
    private String questionTitle;//题干		
	@TableField(value = "question_type")
    private String	questionType;//题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)	
	@TableField(value = "item_a")
    private String	itemA;//A选项	
	@TableField(value = "item_b")
    private String itemB;//B选项		
	@TableField(value = "item_c")
    private String itemC;	//C选项		
	@TableField(value = "item_d")
    private String itemD;//D选项		
	@TableField(value = "item_e")
    private String itemE;// E选项	
	@TableField(value = "item_f")
    private String itemF;//	F选项		
	@TableField(value = "item_g")
    private String itemG;	//	G选项	
	@TableField(value = "item_h")
    private String itemH;//	H选项	
	@TableField(value = "answer")
    private String answer;	//答案。单选、多选、判断(1正确，0错误)、填空(json形式，{"item1":"aa","item2":"bb"})、简答题答案	
	@TableField(value = "analysis")
    private String analysis;//解析	
	@TableField(value = "user_no")
    private String userNo;//user表对应的user_no
	@TableField(value = "level")
    private String	level;//		难度，1到5，1最容易，5最难	
	@TableField(value = "subject_id1")
    private String	subjectId1;	
	@TableField(value = "subject_id2")
    private String	subjectId2;	
	@TableField(value = "subject_id3")
    private String  subjectId3;
	@TableField(value = "custom_label")
    private String customLabel;//自定义标签，用于方便搜索

}
