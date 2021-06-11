package com.wangxin.langshu.business.edu.service.course.pcweb.fresh;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshCourseMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PcWebFreshCourseService  extends ServiceImpl<FreshCourseMapper, FreshCourse> implements IPcWebFreshCourseService {

	@Autowired
	public FreshCourseDao freshCourseDao;
	
	@Autowired
    private FreshCourseMapper freshCourseMapper;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	



	@Override
	public Page<FreshCourseVo> queryCourses(FreshCourseQ q) {
		return freshCourseDao.queryCourses(q);
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


	


	
	
}
