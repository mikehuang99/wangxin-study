package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseRecommendBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseRecommendDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseRecommendListDTO;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommend;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseRecommendDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseRecommendBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 
 * 课程推荐
 * 
 * 
 *
 */
@Service
public class ApiCourseRecommendBiz implements IApiCourseRecommendBiz{

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private CourseRecommendDao dao;

	/**
	 * 根据分类ID获取推荐课程信息
	 * 
	 * @param courseRecommendBO
	 * 
	 */
	public Result<CourseRecommendListDTO> list(CourseRecommendBO bo) {
		List<CourseRecommend> list = dao.listBySubjectIdAndValidValue(bo.getSubjectId(), ValidValueEnum.YES.getCode());
		CourseRecommendListDTO dto = new CourseRecommendListDTO();
		if (CollectionUtil.isEmpty(list)) {
			return Result.success(dto);
		}
		List<CourseRecommendDTO> listDTO = new ArrayList<>();
		for (CourseRecommend courseRecommend : list) {
			Course course = courseDao.getById(courseRecommend.getSubjectId());
			if (ObjectUtil.isNotNull(course)) {
				CourseRecommendDTO courseRecommendDTO = BeanUtil.copyProperties(course, CourseRecommendDTO.class);
				listDTO.add(courseRecommendDTO);
			}
		}
		dto.setList(listDTO);
		return Result.success(dto);
	}

}
