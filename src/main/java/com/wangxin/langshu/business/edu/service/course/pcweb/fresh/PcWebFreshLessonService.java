package com.wangxin.langshu.business.edu.service.course.pcweb.fresh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.impl.CourseUserDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshLessonMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapter;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshChapterService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonService;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonSortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.Constants;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcWebFreshLessonService  extends ServiceImpl<FreshLessonMapper, FreshLesson> implements IPcWebFreshLessonService {

	@Autowired
	public FreshLessonDao freshLessonDao;
	
	@Autowired
	public CourseUserDao courseUserDao;
	
	@Autowired
    private FreshLessonMapper freshCourseMapper;
	
	@Autowired
	private IPcWebFreshCourseService pcWebFreshCourseService;
	
	@Autowired
	private IPcWebFreshChapterService pcWebFreshChapterService;
	
	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;
	
	/*
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private FreshCourseApplyService freshCourseApplyService;
	
	@Autowired
	private FreshLessonApplyService freshLessonApplyService;
	 */
	
	@Override
	public Page<FreshLessonVo> queryLessons(FreshLessonQ q) {
			if(StringUtils.isEmpty(q.getCourseId())){
				throw new BaseException("??????courseId");
			}
			return freshLessonDao.queryLessons(q);
	}
	

	@Override
	public FreshLessonSaveVo saveLesson(FreshLessonSaveVo vo) {

		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		FreshLesson fca = new FreshLesson();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		
		
		
		return vo;
	}

	@Override
	public FreshLessonUpdateVo updateLesson(FreshLessonUpdateVo vo) {
		
		FreshLesson fca = new FreshLesson();
		BeanUtils.copyProperties(vo, fca);
		this.updateById(fca);
		
		
		
		
		return vo;
	}

	@Override
	public Result<Integer> deleteLesson(FreshLessonDeleteVo vo) {
		// ???????????????????????????
		FreshLesson record = this.getById(vo.getId());
		record.setValidValue(Constants.FREEZE);
		boolean result = this.updateById(record);
		if (result) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);

	}

	@Override
	public FreshLessonViewVo viewLesson(FreshLessonViewVo viewVo) {
		FreshLesson e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}


	@Override
	public Result<FreshLessonViewVo> viewHadPayLesson(FreshLessonViewVo vo) {
		if(StringUtils.isEmpty(vo.getUserNo())){
			return Result.error("??????userNo");
		}
		if(StringUtils.isEmpty(vo.getId())){
			return Result.error("??????lessonId");
		}
		if(StringUtils.isEmpty(vo.getChapterId())){
			return Result.error("??????chapterId");
		}
		if(StringUtils.isEmpty(vo.getCourseId())){
			return Result.error("??????courseId");
		}
		FreshLesson freshLesson = this.getById(vo.getId());
		if(ObjectUtil.isNull(freshLesson)){
			return Result.error("????????????id?????????????????????");
		}
		if(!vo.getChapterId().equals(freshLesson.getChapterId())){
			return Result.error("????????????chapterId???????????????ID??????????????????ID????????????????????????");
		}
		
		FreshChapter freshChapter = pcWebFreshChapterService.getById(vo.getChapterId());
		if(ObjectUtil.isNull(freshChapter)){
			return Result.error("??????chapterId?????????????????????");
		}
		if(!vo.getCourseId().equals(freshChapter.getCourseId())){
			return Result.error("????????????courseId?????????????????????????????????ID????????????????????????");
		}
		
		FreshCourse freshCourse = pcWebFreshCourseService.getById(vo.getCourseId());
		if(ObjectUtil.isNull(freshCourse)){
			return Result.error("??????courseId?????????????????????");
		}
		
		CourseUserQ courseUserQ = new CourseUserQ();
		courseUserQ.setCourseId(vo.getCourseId());
		courseUserQ.setBindUserNo(vo.getUserNo());
		courseUserQ.setPageCurrent(-1);
		courseUserQ.setPageSize(-1);
		Page<CourseUserVo> courseUserVoPage = pcWebCourseUserService.queryCourseUsers(courseUserQ);
		if(CollectionUtil.isEmpty(courseUserVoPage.getList())){
			return Result.error("??????????????????????????????");
		}
		CourseUserVo courseUserVo = courseUserVoPage.getList().get(0);
		
		if(ObjectUtil.isNotNull(courseUserVo.getValidTerm())){
			Date date = new Date();
			if(date.getTime()>=courseUserVo.getValidTerm().getTime()){
				return Result.error("?????????????????????????????????????????????");
			}
		}
		
		
		return null;
	}


	
	@Override
	public Result<FreshLessonViewVo> queryCourseDocUrlByLessonId(FreshLessonViewVo vo) {
		if(StringUtils.isEmpty(vo.getId())){
			return Result.error("??????ID????????????");
		}
		if(StringUtils.isEmpty(vo.getUserNo())){
			return Result.error("userNo????????????");
		}
		FreshLesson freshLesson = this.getById(vo.getId());
		if(ObjectUtil.isNull(freshLesson)){
			return Result.error("????????????ID???????????????");
		}
		
		if(StringUtils.isEmpty(freshLesson.getCourseId())){
			return Result.error("???????????????courseId?????????????????????");
		}
	
		if(freshLesson.getIsFree()==0){
			boolean flag = courseUserDao.queryCourseUserIsExist(freshLesson.getCourseId(), vo.getUserNo());
			if(!flag){
				return Result.error("???????????? ??????/?????? ???????????? ??????/?????????????????? ?????????");
			}
		}
		
		FreshLessonViewVo freshLessonViewVo = new FreshLessonViewVo();
		freshLessonViewVo.setDocUrl(freshLesson.getDocUrl());
		
		return Result.success(freshLessonViewVo);
	}
	


	
	
}
