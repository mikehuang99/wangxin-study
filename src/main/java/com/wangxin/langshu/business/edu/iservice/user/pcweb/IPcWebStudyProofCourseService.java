package com.wangxin.langshu.business.edu.iservice.user.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.user.StudyProofCourse;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseQ;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseViewVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebStudyProofCourseService  extends IService<StudyProofCourse> {
	
	

	public Page<StudyProofCourseVo> queryStudyProofCourses(StudyProofCourseQ q) ;
	

	public StudyProofCourseSaveVo saveStudyProofCourse(StudyProofCourseSaveVo vo) ;

	public Result<Integer> updateStudyProofCourse(StudyProofCourseUpdateVo vo) ;

	
	public boolean deleteStudyProofCourse(StudyProofCourseDeleteVo vo) ;

	
	public StudyProofCourseViewVo viewStudyProofCourse(StudyProofCourseViewVo vo) ;
	
	
	
	
}
