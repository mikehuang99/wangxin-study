package com.wangxin.langshu.business.edu.service.course.pcweb.fresh;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshCourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshCourseApplyMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.IsFreeEnum;
import com.wangxin.langshu.business.util.enums.IsPutawayEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcWebFreshCourseApplyService  extends ServiceImpl<FreshCourseApplyMapper, FreshCourseApply> implements IPcWebFreshCourseApplyService {

	@Autowired
	public FreshCourseApplyDao freshCourseDao;
	
	@Autowired
    private FreshCourseApplyMapper freshCourseApplyMapper;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;


	@Override
	public Page<FreshCourseApplyVo> queryCourseApplys(FreshCourseApplyQ q) {
			return freshCourseDao.queryCourseApplys(q);
	}
	

	@Override
	public FreshCourseApplySaveVo saveCourseApply(FreshCourseApplySaveVo vo) {

		if (StringUtils.isEmpty(vo.getUserNo())) {
			throw new BaseException("userNo不能为空");
		}
		// 原价小于0
		if (vo.getCourseOriginal().compareTo(BigDecimal.valueOf(0)) == -1) {
			throw new BaseException("售价不能小于0");
		}
		// 课程收费但价格为空
		if (IsFreeEnum.CHARGE.getCode().equals(vo.getIsFree())) {
			if (vo.getCourseOriginal() == null) {
				throw new BaseException("价格不能为空");
			}
		}
		// 课程介绍
		CourseIntroduceApply courseIntroduceApply = new CourseIntroduceApply();
		String introduceId = UUID.randomUUID().toString().replace("-", "");
		courseIntroduceApply.setId(introduceId);//等改完后就起效果了
		courseIntroduceApply.setIntroduce(vo.getIntroduce());
		courseIntroduceApplyDao.save(courseIntroduceApply);
		
		
		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		vo.setIntroduceId(courseIntroduceApply.getId());
		vo.setValidValue(ValidValueEnum.YES.getCode());
		vo.setIsPutaway(IsPutawayEnum.YES.getCode());
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		vo.setTeacherUserNo(vo.getUserNo());
		
		FreshCourseApply course = new FreshCourseApply();
		
		BeanUtils.copyProperties(vo, course);
		this.save(course);

		
		return vo;
	}

	@Override
	public boolean updateCourseApply(FreshCourseApplyUpdateVo vo) {
		FreshCourseApply course = new FreshCourseApply();
		BeanUtils.copyProperties(vo, course);
		this.updateById(course);
		
		// 课程简介更新
		if(StringUtils.isNotEmpty(vo.getIntroduce())){
			FreshCourseApply freshCourseApply = this.getById(vo.getId());
			CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(freshCourseApply.getIntroduceId());
			courseIntroduceApply.setIntroduce(vo.getIntroduce());
			courseIntroduceApplyDao.updateById(courseIntroduceApply);
		}
		
		return true;
	}
	
	
	@Override
	public Result<Integer> updateCoursePutaway(FreshCourseUpdateVo vo) {
		FreshCourseApply freshCourseApply = this.getById(vo.getId());
		freshCourseApply.setIsPutaway(vo.getIsPutaway());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		this.updateById(freshCourseApply);
		
		return Result.success(1);
	}

	@Override
	public boolean deleteCourseApply(FreshCourseApplyDeleteVo vo) {
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
	public Result<FreshCourseApplyViewVo> viewCourseApply(FreshCourseApplyViewVo viewVo) {
		FreshCourseApply f = this.getById(viewVo.getId());
		if(ObjectUtil.isNotNull(f)){
			// 课程介绍
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(f.getIntroduceId());
			if(ObjectUtil.isNotNull(courseIntroduce)){
				viewVo.setIntroduce(courseIntroduce.getIntroduce());
			}
			BeanUtils.copyProperties(f, viewVo);
		}else{
			return Result.error("参数错误，查到的对象为空");
		}
		

		return Result.success(viewVo);
	}


	

	


	
	
}
