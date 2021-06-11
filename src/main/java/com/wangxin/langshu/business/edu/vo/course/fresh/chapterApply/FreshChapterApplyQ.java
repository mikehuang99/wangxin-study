package com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshChapterApplyQ  implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -7003214059847327396L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	@ApiModelProperty(value = "章节名称", required = false)
    private String chapterName;//章节名称
	@ApiModelProperty(value = "章节描述", required = false)
    private String chapterDesc;//章节描述
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = false)
    private String isFree;//是否免费：1免费，0收费
	@ApiModelProperty(value = "原价", required = false)
    private String chapterOriginal;//原价
	@ApiModelProperty(value = "优惠价", required = false)
    private String chapterDiscount;//优惠价
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = false)
    private String applyStatus;//审核状态(0:待审核,1:审核通过,2:审核不通过)
	@ApiModelProperty(value = "审核意见", required = false)
    private String applyResultDesc;//审核意见
	
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
}
