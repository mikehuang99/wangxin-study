package com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseVideo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebFreshCourseVideoService  extends IService<FreshCourseVideo> {
	
	

	public Result<Page<FreshCourseVideoVo>> queryCourseVideos(FreshCourseVideoQ q) ;
	
	public Result<Page<FreshCourseVideoVo>> queryAllCourseVideos(FreshCourseVideoQ q) ;
	

	public Result<FreshCourseVideoSaveVo> saveCourseVideo(FreshCourseVideoSaveVo vo) ;

	public Result<Integer> updateCourseVideo(FreshCourseVideoUpdateVo vo) ;

	
	public Result<Integer> deleteCourseVideo(FreshCourseVideoDeleteVo vo) ;

	
	public Result<FreshCourseVideoViewVo> viewCourseVideo(FreshCourseVideoViewVo vo) ;
	
	
	public Result<Page<FreshCourseVideoVo>> queryCourseVideoByLessonId(FreshCourseVideoQ q) ;



	
}
