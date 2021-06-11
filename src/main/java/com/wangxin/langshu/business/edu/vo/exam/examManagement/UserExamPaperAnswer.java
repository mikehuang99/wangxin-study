package com.wangxin.langshu.business.edu.vo.exam.examManagement;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserExamPaperAnswer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4644522062188880114L;

	private String id;

	@ApiModelProperty(value = "questionType", required = false)
	private String questionType;//题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)	
	
	@ApiModelProperty(value = "试题答案(单选题、多选题、判断题)", required = false)
	private String answer;//试题答案(单选题、多选题、判断题)
	
	@ApiModelProperty(value = "第一个空答案", required = false)
	private String fillBlankItemAAnswer;//第一个空答案
	
	@ApiModelProperty(value = "第二个空答案", required = false)
	private String fillBlankItemBAnswer;//第二个空答案
	
	@ApiModelProperty(value = "第三个空答案", required = false)
	private String fillBlankItemCAnswer;//第三个空答案
	
	@ApiModelProperty(value = "第四个空答案", required = false)
	private String fillBlankItemDAnswer;//第四个空答案
	
	@ApiModelProperty(value = "第五个空答案", required = false)
	private String fillBlankItemEAnswer;//第五个空答案
	
	@ApiModelProperty(value = "第六个空答案", required = false)
	private String fillBlankItemFAnswer;//第六个空答案
	
	@ApiModelProperty(value = "第七个空答案", required = false)
	private String fillBlankItemGAnswer;//第七个空答案
	
	@ApiModelProperty(value = "第八个空答案", required = false)
	private String fillBlankItemHAnswer;//第八个空答案
	
	@ApiModelProperty(value = "简答题答案", required = false)
	private String simpleQuestionItemAAnswer;//简答题答案
	
	@ApiModelProperty(value = "我的得分", required = false)
	private String myScore;//我的得分
	
	@ApiModelProperty(value = "答案是否对，答案对就为1，否则为空", required = false)
	private String isRight;//答案是否对，答案对就为1，否则为空
}
