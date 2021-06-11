package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpListVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpThreeVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpTwoVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpVo;
import com.wangxin.langshu.business.edu.dao.course.jdbc.CourseApplyCcpDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseApplyCcpBiz;

/**
 * 课程分类
 *
 */
@Service
public class ApiCourseApplyCcpBiz implements IApiCourseApplyCcpBiz{

	@Autowired
	private CourseApplyCcpDao courseApplyCcpDao;

	/**
	 * 获取课程分类列表
	 * 
	 * @return
	 */
	public CourseCcpListVo list(CourseCcpQ courseCcpQ) {
		
		
		List<Map<String, Object>> ccpMaps = courseApplyCcpDao.queryCcp(courseCcpQ);
		Set<String> courseIds = new HashSet<>();
		
		CourseCcpListVo courseCcpListVo = new CourseCcpListVo();
		List<CourseCcpVo> courseCcpVosList= new ArrayList<CourseCcpVo>();
		
		
		for(Map<String, Object> map : ccpMaps){
			String courseId = map.get("course_id").toString();
			if(!courseIds.contains(courseId)){
				CourseCcpVo courseCcpVo = new CourseCcpVo();
				courseCcpVo.setId(courseId);
				courseCcpVo.setName(map.get("course_name").toString());
				courseCcpVosList.add(courseCcpVo);
				courseIds.add(courseId);
			}
		}
		
		for(CourseCcpVo courseCcpVo:courseCcpVosList){
			String courseId = courseCcpVo.getId();
			List<CourseCcpTwoVo> courseCcpTwoVosList = new ArrayList<CourseCcpTwoVo>();
			Set<String> chapterIds = new HashSet<>();

			for(Map<String, Object> map : ccpMaps){
				//if(map.get("course_id").toString().equalsIgnoreCase(String.valueOf(courseId))){
				//if(Long.valueOf(map.get("course_id").toString())==courseId){
					//System.out.println("=====");
				//}
				if(map.get("course_id").toString().equalsIgnoreCase(String.valueOf(courseId))){
					String chapterId = map.get("chapter_id")==null?"":map.get("chapter_id").toString();
					if(!chapterIds.contains(chapterId)&&!"".equals(chapterId)){
						CourseCcpTwoVo courseCcpTwoVo = new CourseCcpTwoVo();
						courseCcpTwoVo.setId(chapterId);
						courseCcpTwoVo.setName(map.get("chapter_name").toString());
						
						List<CourseCcpThreeVo> courseCcpThreeVosList = new ArrayList<CourseCcpThreeVo>();
						Set<String> lessonIds = new HashSet<>();

	
						for(Map<String, Object> m : ccpMaps){
							if((m.get("chapter_id")==null?"":m.get("chapter_id").toString()).equalsIgnoreCase(String.valueOf(chapterId))){
								if(null!=m.get("lesson_id")){
									String lessonId = m.get("lesson_id")==null?"":m.get("lesson_id").toString();
									if(!lessonIds.contains(lessonId)&&!"".equals(lessonId)){
										CourseCcpThreeVo courseCcpThreeVo = new CourseCcpThreeVo();
										courseCcpThreeVo.setId(lessonId);
										courseCcpThreeVo.setName(m.get("lesson_name").toString());
										courseCcpThreeVosList.add(courseCcpThreeVo);
										lessonIds.add(lessonId);
									}
									courseCcpTwoVo.setThreeList(courseCcpThreeVosList);
								}
							}
						}
						
						courseCcpTwoVosList.add(courseCcpTwoVo);
						chapterIds.add(chapterId);
					}
				}
			}
			
			
			courseCcpVo.setTwoList(courseCcpTwoVosList);
		}
		
		
		
		courseCcpListVo.setCourseCcpList(courseCcpVosList);
		
		
		return courseCcpListVo;
	}
	
}
