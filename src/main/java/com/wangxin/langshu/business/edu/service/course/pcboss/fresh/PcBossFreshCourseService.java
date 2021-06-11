package com.wangxin.langshu.business.edu.service.course.pcboss.fresh;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.TopicCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshCourseMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourse;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshCourseService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.IsFreeEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcBossFreshCourseService  extends ServiceImpl<FreshCourseMapper, FreshCourse> implements IPcBossFreshCourseService {

	@Autowired
	public FreshCourseDao freshCourseDao;
	
	@Autowired
    private FreshCourseMapper freshCourseMapper;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private PcBossFreshCourseApplyService pcBossFreshCourseApplyService;
	
	@Autowired
	private TopicCourseDao topicCourseDao;


	@Override
	public Page<FreshCourseVo> queryCourses(FreshCourseQ q) {
		Page<FreshCourseVo>  page = freshCourseDao.queryCourses(q);
		if(StringUtils.isNotEmpty(q.getTopicId())&&CollectionUtil.isNotEmpty(page.getList())) {
			for(FreshCourseVo vo : page.getList()) {
				// 校验专区是否存在课程
				TopicCourse topicCourse = topicCourseDao.getTopicIdAndCourseId(vo.getId(), q.getTopicId());
				if (ObjectUtil.isNull(topicCourse)) {
					// 不存在
					vo.setIsAddTopicCourse(0);
				} else {
					// 存在
					vo.setIsAddTopicCourse(1);
				}
			}
		}
		return page;
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
		FreshCourse freshCourse = this.getById(vo.getId());
		if (ObjectUtil.isNull(freshCourse)) {
			return Result.error("找不到课程信息,或者该课程还没有进行过审核");
		}
		if (IsFreeEnum.FREE.getCode().equals(vo.getIsFree())) {
			vo.setCourseOriginal(BigDecimal.ZERO);
			vo.setCourseDiscount(BigDecimal.ZERO);
		}
		FreshCourse record = BeanUtil.copyProperties(vo, FreshCourse.class);
		boolean flag = this.updateById(record);
		if (flag) {
			// 同步更新审核表
			FreshCourseApply freshCourseApply = BeanUtil.copyProperties(vo, FreshCourseApply.class);
			
			flag = pcBossFreshCourseApplyService.updateById(freshCourseApply);
			if (!flag) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
		}

		if (StringUtils.isNotEmpty(vo.getIntroduce())) {
			// 更新课程介绍
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(freshCourse.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduce)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduce.setIntroduce(vo.getIntroduce());
			int results = courseIntroduceDao.updateById(courseIntroduce);
			if (results < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
			// 同步更新审核表
			CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(freshCourse.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduceApply)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduceApply.setIntroduce(vo.getIntroduce());
			courseIntroduceApplyDao.updateById(courseIntroduceApply);
		}
		return Result.success(1);
	}
	
	@Override
	public Result<Integer> updateCoursePutaway(FreshCourseUpdateVo vo) {
		FreshCourse freshCourse = this.getById(vo.getId());
		freshCourse.setIsPutaway(vo.getIsPutaway());
		this.updateById(freshCourse);
		
		FreshCourseApply freshCourseApply = pcBossFreshCourseApplyService.getById(vo.getId());
		freshCourseApply.setIsPutaway(vo.getIsPutaway());
		pcBossFreshCourseApplyService.updateById(freshCourseApply);
		
		
		return Result.success(1);
	}


	@Override
	public Result<Integer> updateCourseValidValue(FreshCourseUpdateVo vo) {
		FreshCourse freshCourse = this.getById(vo.getId());
		freshCourse.setValidValue(vo.getValidValue());
		this.updateById(freshCourse);
		
		FreshCourseApply freshCourseApply = pcBossFreshCourseApplyService.getById(vo.getId());
		freshCourseApply.setValidValue(vo.getValidValue());
		pcBossFreshCourseApplyService.updateById(freshCourseApply);
		
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


	


	


	
	
}
