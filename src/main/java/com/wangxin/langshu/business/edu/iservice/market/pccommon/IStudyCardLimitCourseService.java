package com.wangxin.langshu.business.edu.iservice.market.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.StudyCardLimitCourse;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseViewVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseVo;
import com.wangxin.langshu.business.util.base.Page;

public interface IStudyCardLimitCourseService  extends IService<StudyCardLimitCourse> {
	
	

	public Page<StudyCardLimitCourseVo> queryStudyCardLimitCourses(StudyCardLimitCourseQ q) ;

	public boolean saveStudyCardLimitCourse(StudyCardLimitCourseSaveVo vo) ;

	public boolean updateStudyCardLimitCourse(StudyCardLimitCourseUpdateVo vo) ;

	
	public boolean deleteStudyCardLimitCourse(StudyCardLimitCourseDeleteVo vo) ;

	
	public StudyCardLimitCourseViewVo viewStudyCardLimitCourse(StudyCardLimitCourseViewVo vo) ;
	
	public String queryAllStudyCardLimitCoursesString(String studyCardId);
	
	public int deleteStudyCardLimitCoursesByStudyCardId(String studyCardId);
	
	
	
	
}
