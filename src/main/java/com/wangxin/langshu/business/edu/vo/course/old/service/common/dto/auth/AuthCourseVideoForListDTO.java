package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程视频信息
 *
 * 
 */
@Data
@Accessors(chain = true)
public class AuthCourseVideoForListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 视频名称
	 */
	@ApiModelProperty(value = "视频名称")
	private String videoName;
	/**
	 * 视频时长
	 */
	@ApiModelProperty(value = "视频时长")
	private String videoLength;

	/**
	 * 视频状态
	 */
	@ApiModelProperty(value = "视频状态(1待上传，2上传成功，3上传失败)")
	private Integer videoUploadStatus;
	/**
	 * 视频编号
	 */
	@ApiModelProperty(value = "视频编号", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private String videoLocalNumber;
}
