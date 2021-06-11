/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师章节审核信息保存
 * 
 */
@Data
@Accessors(chain = true)
public class AuthChapterApplyUpdateBO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 章节编号
	 */
	@ApiModelProperty(value = "章节编号", required = true)
	private String id;
	/**
	 * 章节名称
	 */
	@ApiModelProperty(value = "章节名称", required = true)
	private String chapterName;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = true)
	private Integer isFree;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String userNo;
}
