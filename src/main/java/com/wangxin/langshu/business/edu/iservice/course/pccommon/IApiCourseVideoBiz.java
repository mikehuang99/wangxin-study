package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.CourseVideo;

/**
 * 课程视频信息 
 *
 * 
 */

public interface IApiCourseVideoBiz {
	
	 /**
	 * 根据视频编号查询课程视频信息集合
	 * 
	 * @param videoLocalNumber
	 * @return
	 */
	public List<CourseVideo> listByVideoLocalNumber(String videoLocalNumber);
	
	/**
	 * 根据视频编号课程视频信息
	 * 
	 * @param videoLocalNumber
	 * @return
	 */
	CourseVideo getByVideoLocalNumber(String videoLocalNumber);

}
