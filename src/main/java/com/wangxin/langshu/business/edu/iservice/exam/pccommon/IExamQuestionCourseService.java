package com.wangxin.langshu.business.edu.iservice.exam.pccommon;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.exam.ExamQuestionCourse;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


public interface IExamQuestionCourseService  extends IService<ExamQuestionCourse> {
	
	

	public Page<ExamQuestionCourseVo> queryExamQuestionCourses(ExamQuestionCourseQ examQuestionCourseQ) ;
	
	public int countExamQuestionCourses(ExamQuestionCourseQ examQuestionCourseQ) ;


	public boolean saveExamQuestionCourse(ExamQuestionCourseSaveVo examQuestionCourseSaveVo) ;

	public boolean updateExamQuestionCourse(ExamQuestionCourseUpdateVo examQuestionCourseUpdateVo) ;

	
	public boolean deleteExamQuestionCourse(ExamQuestionCourseDeleteVo examQuestionCourseDeleteVo) ;

	
	public ExamQuestionCourseViewVo viewExamQuestionCourse(ExamQuestionCourseViewVo examQuestionCourseViewVo) ;
	
	public List<Map<String, Object>> countExamQuestionCourses(List<String> ids,String bindType,String status);
	
	/**
	 * 查找尚未做的题
	 * @param userNo 学员ID
	 * @param ccpId 课程ID 或者 章节ID 或者 课时ID
	 * @param type 1代表课程，2代表章节，3代表课时
	 * @param  amount 题目数量
	 * @return
	 */
	public List<ExamQuestionVo> queryNotYetDoneExamQuestions(String userNo,String ccpId,String type,int amount);
	
	/**
	 * 根据ccpId查询试题
	 * @param ccpId 课程ID 或者 章节ID 或者 课时ID
	 * @param type 1代表课程，2代表章节，3代表课时
	 * @return 题目集合
	 */
	public List<ExamQuestionVo> queryExamQuestions(String ccpId,String type);
	
	/**
	 * 根据ccpId查询用户做错的试题
	 * @param userNo
	 * @param courseId 课程ID 
	 * @return 题目集合
	 */
	public List<ExamQuestionVo> queryErrorExamQuestions(String userNo,String courseId);
	
	/**
	 * 存储用户错题
	 * @param userNo
	 * @param courseId
	 */
	public void cacheErrorExamQuestion(String userNo,String courseId,String questionId);
	
	/**
	 * 删除用户错题
	 * @param userNo
	 * @param courseId
	 */
	public void delErrorExamQuestion(String userNo,String courseId,String questionId);
	
	/**
	 * 根据ccpId查询用户高频做错的试题
	 * @param userNo
	 * @param courseId 课程ID 
	 * @return 题目集合
	 */
	public List<ExamQuestionVo> queryHighErrorExamQuestions(String userNo,String courseId);
	
	/**
	 * 存储用户高频错题
	 * @param userNo
	 * @param courseId
	 */
	public void cacheHighErrorExamQuestion(String userNo,String courseId,String questionId);
	
	/**
	 * 删除用户高频错题
	 * @param userNo
	 * @param courseId
	 */
	public void delHighErrorExamQuestion(String userNo,String courseId,String questionId);
	

	/**
	 * 根据ccpId查询用户收藏的试题
	 * @param userNo
	 * @param courseId 课程ID 
	 * @return 题目集合
	 */
	public List<ExamQuestionVo> queryFavoritesExamQuestions(String userNo,String courseId);
	
	/**
	 * 存储用户收藏
	 * @param userNo
	 * @param courseId
	 */
	public void cacheFavoritesExamQuestion(String userNo,String courseId,String questionId);
	
	/**
	 * 删除用户收藏
	 * @param userNo
	 * @param courseId
	 */
	public void delFavoritesExamQuestion(String userNo,String courseId,String questionId);
	
	
	/**
	 * 根据ccpId查询用户笔记
	 * @param userNo
	 * @param courseId 课程ID 
	 * @return 题目集合
	 */
	public List<ExamQuestionVo> queryNoteExamQuestions(String userNo,String courseId);
	
	/**
	 * 查询笔记
	 * @param userNo
	 * @param courseId 课程ID 
	 * @param questionId 问题ID
	 * @return 笔记内容
	 */
	public String queryNoteContentByQuestionId(String userNo,String courseId,String questionId);
	
	/**
	 * 存储用户笔记
	 * @param userNo
	 * @param courseId
	 */
	public void cacheNoteExamQuestion(String userNo,String courseId,String questionId,String noteContent);
	
	
	/**
	 * 删除用户笔记
	 * @param userNo
	 * @param courseId
	 */
	public void delNoteExamQuestion(String userNo,String courseId,String questionId);
	
	/**
	 * 重新缓存所有的课程绑定试题
	 */
	public void cacheAllExamQuestionCourses();
	
	/**
	 * 查询是否有该课时权限
	 * @return
	 */
	public Result<Integer> isHaveLessonPermission(FreshCourseVideoQ q);
	
	
	
}
