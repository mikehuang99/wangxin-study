package com.wangxin.langshu.business.edu.entity.exam;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="exam_paper_content")
public class ExamPaperContent extends Model<ExamPaperContent> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4499055561602729634L;
	@TableId(type = IdType.UUID)
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	@TableField(value = "exam_paper_id")
	private String examPaperId;//exam_paper表的ID
	@TableField(value = "exam_paper_content")
	private String examPaperContent;//试卷内容
	@TableField(value = "teacher_user_no")
    private String teacherUserNo;//出这试卷的教师用户编号
	


}
