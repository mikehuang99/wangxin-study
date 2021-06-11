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
@TableName(value ="exam_management")
public class ExamManagement extends Model<ExamManagement> implements Serializable{

	private static final long serialVersionUID = 2235448224517225020L;
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
	@TableField(value = "title")
	private String title;//考试主题
	@TableField(value = "introduce")
	private String introduce;//考试简介
	@TableField(value = "exam_object_type")
	private String examObjectType;//参考对象类型，1为全体学员，2为分组(user_group_ids)，3为全体学员中拥有该课程的学员，4为分组(user_group_ids)学员中拥有该课程的学员
	@TableField(value = "exam_type")
	private int examType;//考试类型，1定时考试，2时段考试
	@TableField(value = "start_time")
	private Date startTime;//考试开始时间
	@TableField(value = "end_time")
	private Date endTime;//考试结束时间
	@TableField(value = "is_allow_switch_window")
	private String isAllowSwitchWindow;//是否允许切换窗口(是1，否0)	
	@TableField(value = "is_show_confirm_button")
	private String isShowConfirmButton;//是否显示确认按钮(是1，否0)
	@TableField(value = "is_allow_select")
	private String isAllowSelect;//是否允许选择(0不允许，1允许)
	@TableField(value = "is_allow_right_click")
	private String isAllowRightClick;//是否允许右键(0不允许，1允许)	
	@TableField(value = "is_allow_repeate_hand_in_exam_paper")
	private String isAllowRepeateHandInExamPaper;//是否允许重复提交试卷(0不允许，1允许)	
	@TableField(value = "exam_paper_id")
	private String examPaperId;//试卷ID
	@TableField(value = "course_id")
    private String courseId;//课程ID
	@TableField(value = "teacher_user_no")
    private String teacherUserNo;//user表对应的user_no


}
