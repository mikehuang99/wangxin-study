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
public class AuthChapterApplySaveBO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = false)
    private Integer sort;
	/**
	 * 课程编号
	 */
	@ApiModelProperty(value = "课程编号", required = true)
	private String courseId;
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
