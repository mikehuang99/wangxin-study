package com.wangxin.langshu.business.edu.entity.course.fresh;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="chapter_apply")
public class FreshChapterApply extends Model<FreshChapterApply> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4450224083041520547L;
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
	
	@TableField(value = "course_id")
    private String courseId;//课程ID
	@TableField(value = "chapter_name")
    private String chapterName;//章节名称
	@TableField(value = "chapter_desc")
    private String chapterDesc;//章节描述
	@TableField(value = "is_free")
    private int isFree;//是否免费：1免费，0收费
	
	
	@TableField(value = "chapter_original")
    private BigDecimal chapterOriginal;//原价
	@TableField(value = "chapter_discount")
    private BigDecimal chapterDiscount;//优惠价
	
	@TableField(value = "apply_status")
    private int applyStatus;//审核状态(0:待审核,1:审核通过,2:审核不通过)
	@TableField(value = "apply_result_desc")
    private String applyResultDesc;//审核意见
	
	
}
