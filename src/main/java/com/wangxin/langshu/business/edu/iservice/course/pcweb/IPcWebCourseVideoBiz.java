package com.wangxin.langshu.business.edu.iservice.course.pcweb;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoSaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonIdVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseVideoListDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程视频信息
 *
 * 
 */

public interface IPcWebCourseVideoBiz {


	/**
	 * 章节视频库，课时视频库添加接口
	 * 
	 * @param authCourseVideoSaveBo
	 * 
	 */
	public Result<Integer> save(AuthCourseVideoSaveBO authCourseVideoSaveBO) ;

	/**
	 * 章节视频库列出
	 * 
	 * @param chapterId
	 * 
	 */
	public Result<AuthCourseVideoListDTO> listByChapterId(AuthCourseVideoBO authCourseVideoBO) ;

	/**
	 * 课时视频列出
	 * 
	 * @param lessonId
	 * 
	 */
	public Result<AuthCourseVideoListDTO> listByLessonId(AuthLessonIdVideoBO authCourseVideoBO);
	

	/**
	 * 课时视频更新接口
	 * 
	 * @param authCourseVideoUpdateBo
	 * 
	 */
	public Result<Integer> update(AuthCourseVideoUpdateBO authCourseVideoUpdateBO);

	// 更新课程、章节、课时
	//private void updateLesson(LessonApply lessonApply, CourseVideo courseVideo) ;
	/**
	 * 视频删除
	 * 
	 * @param bo
	 * 
	 */
	public Result<Integer> udpateById(AuthCourseVideoDeleteBO authCourseVideoDeleteBO) ;
	

}
