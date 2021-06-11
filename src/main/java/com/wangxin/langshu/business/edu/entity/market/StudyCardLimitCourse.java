package com.wangxin.langshu.business.edu.entity.market;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="study_card_limit_course")
public class StudyCardLimitCourse extends Model<StudyCardLimitCourse> implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8689951323762779765L;
	private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	@TableField(value = "study_card_id")
    private String studyCardId;//学习卡ID	
	@TableField(value = "course_id")
    private String courseId;//课程ID	
	
	
	

}
