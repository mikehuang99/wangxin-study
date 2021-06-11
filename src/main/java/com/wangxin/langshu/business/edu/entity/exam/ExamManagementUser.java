package com.wangxin.langshu.business.edu.entity.exam;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="exam_management_user")
public class ExamManagementUser extends Model<ExamManagementUser> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5831083065599599041L;
	//@TableId(type = IdType.UUID)
	@TableField(value ="id")
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	@TableField(value = "exam_management_id")
	private String examManagementId;//考试ID	
	@TableField(value = "exam_paper_id")
	private String examPaperId;//试卷ID
	@TableField(value = "exam_user_no")
	private String examUserNo;//考试用户
	@TableField(value = "exam_paper_answer")
	private String examPaperAnswer;//用户考试答案	
	
	@TableField(value = "is_finish_user_total_score")
	private int isFinishUserTotalScore;//0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分
	@TableField(value = "user_total_score")
	private String userTotalScore;//用户总分

}