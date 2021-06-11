package com.wangxin.langshu.business.edu.iservice.course.pcweb;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseSignBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseSignDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseViewDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程信息
 *
 */

public interface IPcWebCourseBiz  {

	

	public Result<AuthCourseSignDTO> sign(AuthCourseSignBO authCourseSignBO) ;

	public Result<AuthCourseViewDTO> view(AuthCourseViewBO authCourseViewBO) ;
	
	public AuthCourseViewDTO viewByCourseId(String courseId);

	

	

}
