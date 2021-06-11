package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息-审核-查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class ChapterApplyViewRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "章节ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String courseId;
	/**
	 * 章节名称
	 */
	@ApiModelProperty(value = "章节名称")
	private String chapterName;
	/**
	 * 课时集合
	 */
	@ApiModelProperty(value = "课时集合")
	private List<LessonApplyViewRESQ> lessonApplyList = new ArrayList<>();;
}
