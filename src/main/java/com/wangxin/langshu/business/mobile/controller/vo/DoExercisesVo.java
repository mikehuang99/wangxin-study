package com.wangxin.langshu.business.mobile.controller.vo;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DoExercisesVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5136744240476111577L;

	private String userNo;
	
	private String key;
	
	private String value;
	
	private HashMap<String,String> dataMap;
	
	private String dataList;
	
	private String courseId;//课程ID
	
	/**
	 * ccpId一般是指课时ID。但是得看前缀。
	 * 一、没有前缀是课时ID。
	 * 二、带有_ERROR_COLLECTION_XXX是错题ID，XXX是指courseId。
	 * 三、带有_FAVORITES_COLLECTION_XXX的是收藏题ID，XXX是courseId。
	 * 四、带有_HIGH_ERROR_COLLECTION_XXX的是高频错题ID，XXX是courseId。
	 * 五、带有_NOTE_COLLECTION_XXX的是笔记题ID，XXX是courseId。
	 * 
	 * 
	 * 有五种key
	 * continueStartIndex_ccpId 开始
		answer_ids_ccpId  答案ID集合
		answer_ccpId_questionId  具体某条题答案,questionId是题目的ID。此种一般采用answer_ccpId_*全部查询出来
		answer_right_ccpId
		answer_error_ccpId
	 */
	private String ccpId;
	
	

}
