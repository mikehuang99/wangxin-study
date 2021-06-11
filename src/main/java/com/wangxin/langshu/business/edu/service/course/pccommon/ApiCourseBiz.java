package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseInfoPageBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseInfoSearchBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.ChapterDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseInfoPageDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseInfoSearchPageDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseIntroduceDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseViewDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.LessonDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.TeacherDTO;
import com.wangxin.langshu.business.edu.entity.course.old.Chapter;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseExample.Criteria;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.dao.course.ChapterDao;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseBiz;
import com.wangxin.langshu.business.edu.service.user.pccommon.BossTeacherBiz;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.IsPutawayEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.SqlUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 课程信息
 *
 *  
 */
@Service
public class ApiCourseBiz implements IApiCourseBiz{

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private LessonDao lessonDao;

	@Autowired
	private BossTeacherBiz bossTeacherBiz;



	/**
	 * 课程详情接口
	 * 
	 * @param courseView
	 * @return
	 */
	public Result<CourseViewDTO> view(CourseVideoBO courseVideoBO) {
		if (courseVideoBO.getCourseId() == null) {
			return Result.error("课程ID不能为空");
		}
		// 课程信息
		Course course = courseDao.getById(courseVideoBO.getCourseId());
		if (course == null) {
			return Result.error("找不到该课程信息");
		}
		CourseViewDTO data = BeanUtil.copyProperties(course, CourseViewDTO.class);

		// 课程介绍
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(data.getIntroduceId());
		if (!StringUtils.isEmpty(courseIntroduce)) {
			data.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());
		}

		// 教师信息
		TeacherVO teacherVO = bossTeacherBiz.getByTeacherUserNo(data.getTeacherUserNo());
		if (StringUtils.isEmpty(teacherVO)) {
			return Result.error("根据教师用户编号没找到对应的教师信息!");
		}
		data.setTeacher(BeanUtil.copyProperties(teacherVO, TeacherDTO.class));

		// 章节信息
		List<Chapter> chapterList = chapterDao.listByCourseIdAndValidValue(courseVideoBO.getCourseId(), ValidValueEnum.YES.getCode());
		if (CollectionUtil.isNotEmpty(chapterList)) {
			data.setChapterList(PageUtil.copyList(chapterList, ChapterDTO.class));
		}

		// 课时信息
		if (CollectionUtil.isNotEmpty(data.getChapterList())) {
			for (ChapterDTO chapterDTO : data.getChapterList()) {
				List<Lesson> lessonList = lessonDao.listByChapterIdAndValidValue(chapterDTO.getId(), ValidValueEnum.YES.getCode());
				System.out.println("lessonList:::"+lessonList);
				chapterDTO.setLessonList(PageUtil.copyList(lessonList, LessonDTO.class));
			}
		}
		return Result.success(data);
	}

	/**
	 * 课程信息列表接口
	 * 
	 * @param courseInfoPageBO
	 * @return
	 * 
	 */
	public Result<Page<CourseInfoPageDTO>> list(CourseInfoPageBO courseInfoPageBO) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andValidValueEqualTo(ValidValueEnum.YES.getCode());
		c.andIsPutawayEqualTo(IsPutawayEnum.YES.getCode());
		if (!StringUtils.isEmpty(courseInfoPageBO.getSubjectId1())) {
			c.andSubjectId1EqualTo(courseInfoPageBO.getSubjectId1());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getSubjectId2())) {
			c.andSubjectId2EqualTo(courseInfoPageBO.getSubjectId2());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getSubjectId3())) {
			c.andSubjectId3EqualTo(courseInfoPageBO.getSubjectId3());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getIsFree())) {
			c.andIsFreeEqualTo(courseInfoPageBO.getIsFree());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getCourseName())) {
			//c.andCourseNameLike(PageUtil.rightLike(SqlUtil.checkSql(courseInfoPageBO.getCourseName())));
			c.andCourseNameLike(PageUtil.like(SqlUtil.checkSql(courseInfoPageBO.getCourseName())));

		}
		
		if(!StringUtils.isEmpty(courseInfoPageBO.getSortType())){
			/**
			 * 1、智能排序
				2、学习数量
				3、购买数量
				4、原价最高
				5、原价最低
				6、折扣价后最高
				7、折扣价后最低
			 */
			switch(courseInfoPageBO.getSortType()){
			case "1":
				example.setOrderByClause(" course_sort desc, id desc ");
			    break;
			case "2":
				example.setOrderByClause(" study_count desc, id desc ");
			    break;
			case "3":
				example.setOrderByClause(" buy_count desc, id desc ");
			    break;
			case "4":
				example.setOrderByClause(" course_original desc, id desc ");
			    break;
			case "5":
				example.setOrderByClause(" course_original asc, id desc ");
			    break;
			case "6":
				example.setOrderByClause(" course_discount desc, id desc ");
			    break;
			case "7":
				example.setOrderByClause(" course_discount asc, id desc ");
			    break;
			default:
			    break;
			}
		}else{
			example.setOrderByClause(" course_sort desc, id desc ");
		}
		Page<Course> page = courseDao.listForPage(courseInfoPageBO.getPageCurrent(), courseInfoPageBO.getPageSize(), example);
		return Result.success(PageUtil.transform(page, CourseInfoPageDTO.class));
	}

	/**
	 * 课程搜索列表接口
	 * 
	 * @param courseInfoSearchBO
	 * 
	 */
	public Result<Page<CourseInfoSearchPageDTO>> searchList(CourseInfoSearchBO bo) {
		/*
		

		
		
		*/
		return Result.error("该方法已经停用");
	}

	
}
