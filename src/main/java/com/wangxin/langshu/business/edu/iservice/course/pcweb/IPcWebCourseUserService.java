package com.wangxin.langshu.business.edu.iservice.course.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.CourseUser;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserSaveVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserViewVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebCourseUserService  extends IService<CourseUser> {
	
	

	public Page<CourseUserVo> queryCourseUsers(CourseUserQ q) ;
	

	public CourseUserSaveVo saveCourseUser(CourseUserSaveVo vo) ;

	public Result<Integer> updateCourseUser(CourseUserUpdateVo vo) ;

	
	public boolean deleteCourseUser(CourseUserDeleteVo vo) ;

	
	public CourseUserViewVo viewCourseUser(CourseUserViewVo vo) ;
	
	
	
	
}
