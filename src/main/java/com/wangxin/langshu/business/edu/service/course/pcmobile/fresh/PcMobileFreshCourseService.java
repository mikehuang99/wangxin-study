package com.wangxin.langshu.business.edu.service.course.pcmobile.fresh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseIntroduceDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.TeacherDTO;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.impl.CourseUserDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshChapterDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshCourseMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshCourseService;
import com.wangxin.langshu.business.edu.service.user.pccommon.BossTeacherBiz;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseMobileView;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcMobileFreshCourseService  extends ServiceImpl<FreshCourseMapper, FreshCourse> implements IPcMobileFreshCourseService {

	@Autowired
	public FreshCourseDao freshCourseDao;
	
	@Autowired
	public FreshChapterDao freshChapterDao;
	
	
	@Autowired
	public FreshLessonDao freshLessonDao;
	
	
	@Autowired
    private FreshCourseMapper freshCourseMapper;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private BossTeacherBiz bossTeacherBiz;
	
	@Autowired
	private CourseUserDao courseUserDao;


	@Override
	public Page<FreshCourseVo> queryCourses(FreshCourseQ q) {
		return freshCourseDao.queryCommonCourses(q);
	}
	

	@Override
	public FreshCourseSaveVo saveCourse(FreshCourseSaveVo vo) {
		FreshCourse course = new FreshCourse();
		
		BeanUtils.copyProperties(vo, course);
		this.save(course);

		
		return vo;
	}

	@Override
	public Result<Integer> updateCourse(FreshCourseUpdateVo vo) {
		if (vo.getId() == null) {
			return Result.error("ID不能为空");
		}
		
		FreshCourse course = new FreshCourse();
		BeanUtils.copyProperties(vo, course);
		this.updateById(course);
		
		
		return Result.success(1);

		
	}

	@Override
	public boolean deleteCourse(FreshCourseDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
			
		}

		return true;
	}

	@Override
	public FreshCourseViewVo viewCourse(FreshCourseViewVo viewVo) {
		FreshCourse e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}


	@Override
	public Result<FreshCourseMobileView> viewMobileCourse(String courseId,String userNo) {
		if (courseId == null) {
			return Result.error("课程ID不能为空");
		}
		// 课程信息
		FreshCourse course = this.getById(courseId);
		
		if (course == null) {
			return Result.error("找不到该课程信息");
		}
		
		/*
		if ("0".equals(course.getValidValue())) {
			return Result.error("该课程状态不可用");
		}*/
		
		FreshCourseMobileView data = BeanUtil.copyProperties(course, FreshCourseMobileView.class);

		// 课程介绍
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(data.getIntroduceId());
		if (ObjectUtil.isNotNull(courseIntroduce)) {
			data.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());
		}

		// 教师信息
		TeacherVO teacherVO = bossTeacherBiz.getByTeacherUserNo(data.getTeacherUserNo());
		if (ObjectUtil.isNull(teacherVO)) {
			return Result.error("根据教师用户编号没找到对应的教师信息!");
		}
		data.setTeacher(BeanUtil.copyProperties(teacherVO, TeacherDTO.class));

		// 章节信息
		FreshChapterQ freshChapterQ = new FreshChapterQ();
		freshChapterQ.setCourseId(courseId);
		//freshChapterQ.setValidValue(ValidValueEnum.YES.getCode()+"");
		freshChapterQ.setPageCurrent(-1);
		freshChapterQ.setPageSize(-1);
		List<FreshChapterVo> chapterList =  freshChapterDao.queryChapters(freshChapterQ).getList();
		if (CollectionUtil.isNotEmpty(chapterList)) {
			List<FreshChapterMobileViewVo> FreshChapterMobileViewVoList = new ArrayList<FreshChapterMobileViewVo>();
			for(FreshChapterVo fcv :chapterList){
				FreshChapterMobileViewVo fcmv= new FreshChapterMobileViewVo();
				BeanUtils.copyProperties(fcv, fcmv);
				FreshChapterMobileViewVoList.add(fcmv);
			}
			data.setChapterList(FreshChapterMobileViewVoList);
		}
		
		// 课时信息
		if (CollectionUtil.isNotEmpty(data.getChapterList())) {
			for (FreshChapterMobileViewVo fcmvv : data.getChapterList()) {
				FreshLessonQ freshLessonQ = new FreshLessonQ();
				freshLessonQ.setChapterId(fcmvv.getId());
				//freshLessonQ.setValidValue(ValidValueEnum.YES.getCode()+"");
				freshLessonQ.setPageCurrent(-1);
				freshLessonQ.setPageSize(-1);
				List<FreshLessonVo> lessonList = freshLessonDao.queryCommonLessons(freshLessonQ).getList();
				List<FreshLessonMobileViewVo> freshLessonMobileViewVos= new ArrayList<>();
				if(CollectionUtil.isNotEmpty(lessonList)){
					for(FreshLessonVo ffv:lessonList){
						FreshLessonMobileViewVo fllmvv= new FreshLessonMobileViewVo();
						BeanUtils.copyProperties(ffv,fllmvv);
						fllmvv.setLiveBroadcastObsStreamAddress("*");
						fllmvv.setLiveBroadcastObsStreamName("*");
						fllmvv.setDocUrl("*");
						freshLessonMobileViewVos.add(fllmvv);
					}
				}
				fcmvv.setLessonList(freshLessonMobileViewVos);
			}
		}		
		
		if(StringUtils.isNotEmpty(userNo)){
			CourseUserQ courseUserQ = new CourseUserQ();
			courseUserQ.setCourseId(courseId);
			courseUserQ.setBindUserNo(userNo);
			courseUserQ.setValidValue("1");
			Page<CourseUserVo> courseUserVoPage = courseUserDao.queryCourseUsers(courseUserQ);
			
			if(CollectionUtil.isNotEmpty(courseUserVoPage.getList())){
				CourseUserVo courseUserVo = courseUserVoPage.getList().get(0);
				Date date = new Date();
				if(ObjectUtil.isNotNull(courseUserVo.getValidTerm())){
					if(courseUserVo.getValidTerm().getTime()>=date.getTime()){
						data.setIsPay(1);
						data.setValidTerm(courseUserVo.getValidTerm());
					}
				}else{
					return Result.error("数据错误");
				}
			}
		}
		
		return Result.success(data);
	}


	


	
	
}
