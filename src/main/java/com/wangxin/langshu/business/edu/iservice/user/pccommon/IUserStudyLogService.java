package com.wangxin.langshu.business.edu.iservice.user.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.user.UserStudyLog;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogQ;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogSaveVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogViewVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IUserStudyLogService  extends IService<UserStudyLog> {
	
	

	public Page<UserStudyLogVo> queryUserStudyLogs(UserStudyLogQ q) ;

	public Result<Integer> saveUserStudyLog(UserStudyLogSaveVo vo) ;

	public Result<Integer> updateUserStudyLog(UserStudyLogUpdateVo vo) ;

	
	public Result<Integer> deleteUserStudyLog(UserStudyLogDeleteVo vo) ;

	
	public UserStudyLogViewVo viewUserStudyLog(UserStudyLogViewVo vo) ;
	
	
	public Page<FreshCourseVo> queryFreshCoursesOnUserStudyLog(FreshCourseQ q) ;

	
	
	
}
