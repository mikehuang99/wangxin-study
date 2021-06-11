package com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class FreshChapterApplySortVo implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3154337937289532666L;
	/**
	 * 章节ID
	 */
	@ApiModelProperty(value = "章节ID", required = true)
	private List<String> chapterIdList;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String userNo;
}
