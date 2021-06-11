package com.wangxin.langshu.business.edu.service.course.pccommon;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.entity.course.old.CourseVideo;
import com.wangxin.langshu.business.edu.dao.course.CourseVideoDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseVideoBiz;

/**
 * 课程视频信息 
 *
 */
@Service
public class ApiCourseVideoBiz implements IApiCourseVideoBiz{

	@Autowired
	private CourseVideoDao dao;
	
	@Override
	public List<CourseVideo> listByVideoLocalNumber(String videoLocalNumber) {
		return dao.listByVideoLocalNumber(videoLocalNumber);
	}

	@Override
	public CourseVideo getByVideoLocalNumber(String videoLocalNumber) {
		return dao.getByVideoLocalNumber(videoLocalNumber);
	}
	
	

}
