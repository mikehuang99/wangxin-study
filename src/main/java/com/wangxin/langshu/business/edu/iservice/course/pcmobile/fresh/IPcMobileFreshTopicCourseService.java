package com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshTopicCourse;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcMobileFreshTopicCourseService  extends IService<FreshTopicCourse> {
	
	

	public Result<Page<FreshTopicCourseVo>> queryTopics(FreshTopicCourseQ q) ;
	

	public Result<FreshTopicCourseSaveVo>  saveTopic(FreshTopicCourseSaveVo vo) ;

	public Result<Integer>  updateTopic(FreshTopicCourseUpdateVo vo) ;

	
	public Result<Integer> deleteTopic(FreshTopicCourseDeleteVo vo) ;

	
	public FreshTopicCourseViewVo viewTopic(FreshTopicCourseViewVo vo) ;
	
	
	
	
}
