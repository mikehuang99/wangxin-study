package com.wangxin.langshu.business.edu.service.course.pcboss.fresh;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshLessonMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshLessonService;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.Constants;


@Service
public class PcBossFreshLessonService  extends ServiceImpl<FreshLessonMapper, FreshLesson> implements IPcBossFreshLessonService {

	@Autowired
	public FreshLessonDao freshLessonDao;
	
	
	@Autowired
    private FreshLessonMapper freshCourseMapper;
	
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
				throw new BaseException("缺少courseId");
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
		// 将状态改为冻结状态
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


	

	


	
	
}
