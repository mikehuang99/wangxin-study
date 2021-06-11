package com.wangxin.langshu.business.edu.service.course.pcweb;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.impl.CourseUserDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseUserMapper;
import com.wangxin.langshu.business.edu.entity.course.CourseUser;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserSaveVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserViewVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PcWebCourseUserService  extends ServiceImpl<CourseUserMapper, CourseUser> implements IPcWebCourseUserService {

	@Autowired
	public CourseUserDao courseUserDao;
	
	@Autowired
    private CourseUserMapper courseUserMapper;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;

	@Override
	public Page<CourseUserVo> queryCourseUsers(CourseUserQ q) {
		return courseUserDao.queryCourseUsers(q);
	}
	

	@Override
	public CourseUserSaveVo saveCourseUser(CourseUserSaveVo vo) {
		String id = UUID.randomUUID().toString();
		vo.setId(id);
		vo.setValidValue(1);
		CourseUser course = new CourseUser();
		BeanUtils.copyProperties(vo, course);
		
		this.save(course);

		
		return vo;
	}

	@Override
	public Result<Integer> updateCourseUser(CourseUserUpdateVo vo) {
		if (vo.getId() == null) {
			return Result.error("ID不能为空");
		}
		
		CourseUser course = new CourseUser();
		BeanUtils.copyProperties(vo, course);
		this.updateById(course);
		
		
		return Result.success(1);

		
	}

	@Override
	public boolean deleteCourseUser(CourseUserDeleteVo vo) {
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
	public CourseUserViewVo viewCourseUser(CourseUserViewVo viewVo) {
		CourseUser e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}


	


	
	
}
