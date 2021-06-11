package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectThreeDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectTwoDTO;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.dao.course.CourseSubjectDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseSubjectBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.SubjectTypeEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.ArrayListUtil;
import com.wangxin.langshu.business.util.tools.BeanUtil;

/**
 * 课程分类
 *
 *  
 */
@Service
public class ApiCourseSubjectBiz implements IApiCourseSubjectBiz{

	@Autowired
	private CourseSubjectDao dao;

	/**
	 * 获取课程分类列表
	 * 
	 * @return
	 * 
	 */
	public Result<CourseSubjectListDTO> list() {
		CourseSubjectListDTO dto = new CourseSubjectListDTO();
		// 根据分类类型、层级查询可用状态的课程分类集合
		List<CourseSubject> oneSubjectList = dao.listBySubjectTypeAndFloorAndValidValue(SubjectTypeEnum.COURSE.getCode(), 1, ValidValueEnum.YES.getCode());
		if (StringUtils.isEmpty(oneSubjectList)) {
			return Result.success(dto);
		}
		List<CourseSubjectDTO> oneList = new ArrayList<>();
		// 查找一级分类下的二级分类
		for (CourseSubject courseSubject : oneSubjectList) {
			// 设置一级分类
			CourseSubjectDTO oneSubject = BeanUtil.copyProperties(courseSubject, CourseSubjectDTO.class);

			// 查找一级分类下的二级分类
			List<CourseSubject> twoSubjectList = dao.listByParentId(courseSubject.getId());
			if (StringUtils.isEmpty(twoSubjectList)) {
				oneList.add(oneSubject);
				dto.setCourseSubjectList(oneList);
				continue;
			}
			List<CourseSubjectTwoDTO> twoList = new ArrayList<>();
			List<CourseSubjectThreeDTO> threeList = new ArrayList<>();
			for (CourseSubject twoSubject : twoSubjectList) {
				CourseSubjectTwoDTO twoDto = new CourseSubjectTwoDTO();
				twoDto = BeanUtil.copyProperties(twoSubject, CourseSubjectTwoDTO.class);
				List<CourseSubject> threeSubjectList = dao.listByParentId(twoSubject.getId());
				if (CollectionUtils.isNotEmpty(threeSubjectList)) {
					// 复制三级分类信息返回
					threeList = ArrayListUtil.copy(threeSubjectList, CourseSubjectThreeDTO.class);
					twoDto.setThreeList(threeList);
				}
				twoList.add(twoDto);
			}
			oneSubject.setTwoList(twoList);
			// 复制二级分类信息返回
			oneList.add(oneSubject);
			dto.setCourseSubjectList(oneList);
		}
		return Result.success(dto);
	}
	
}
