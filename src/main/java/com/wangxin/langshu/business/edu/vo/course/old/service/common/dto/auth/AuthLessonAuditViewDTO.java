package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课时信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthLessonAuditViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课时ID
     */
    @ApiModelProperty(value = "课时ID")
	@JsonSerialize(using = ToStringSerializer.class)
    private String id;
    /**
     * 审核状态(0:待审核;1:审核通过;2:审核不通过)
     */
    @ApiModelProperty(value = "审核状态(0:待审核;1:审核通过;2:审核不通过)")
    private Integer applyStatus;
    /**
     * 课时名称
     */
    @ApiModelProperty(value = "课时名称")
    private String lessonName;
    /**
     * 课时描述
     */
    @ApiModelProperty(value = "课时描述")
    private String lessonDesc;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费")
    private Integer isFree;
    /**
	 * 是否存在文档
	 */
	@ApiModelProperty(value = "是否存在文档")
	private Integer isDoc;
	/**
	 * 文档名称
	 */
	@ApiModelProperty(value = "文档名称")
	private String docName;
	/**
	 * 文档地址
	 */
	@ApiModelProperty(value = "文档地址")
	private String docUrl;
	/**
	 * 是否存在视频
	 */
	@ApiModelProperty(value = "是否存在视频")
	private Integer isVideo;
	/**
	 * 视频编号
	 */
	@ApiModelProperty(value = "视频编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String videoLocalNumber;
	/**
	 * 视频名称
	 */
	@ApiModelProperty(value = "视频名称")
	private String videoName;
	/**
	 * 时长
	 */
	@ApiModelProperty(value = "时长")
	private String videoLength;

	/**
	 * 阿里云oas
	 */
	@ApiModelProperty(value = "阿里云oas")
	private String videoOasUrl;
	
	@ApiModelProperty(value = "课程ID")
	private String courseId;
}
