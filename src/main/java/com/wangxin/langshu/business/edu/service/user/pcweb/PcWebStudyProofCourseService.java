package com.wangxin.langshu.business.edu.service.user.pcweb;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.impl.StudyProofCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.StudyProofCourseMapper;
import com.wangxin.langshu.business.edu.entity.user.StudyProofCourse;
import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudyProofCourseService;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseQ;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseViewVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PcWebStudyProofCourseService  extends ServiceImpl<StudyProofCourseMapper, StudyProofCourse> implements IPcWebStudyProofCourseService {

	@Autowired
	public StudyProofCourseDao studyProofCourseDao;
	
	

	@Override
	public Page<StudyProofCourseVo> queryStudyProofCourses(StudyProofCourseQ q) {
		return studyProofCourseDao.queryStudyProofCourses(q);
	}
	

	@Override
	public StudyProofCourseSaveVo saveStudyProofCourse(StudyProofCourseSaveVo vo) {
		String id = UUID.randomUUID().toString();
		vo.setId(id);
		vo.setValidValue(1);
		StudyProofCourse course = new StudyProofCourse();
		BeanUtils.copyProperties(vo, course);
		
		this.save(course);

		
		return vo;
	}

	@Override
	public Result<Integer> updateStudyProofCourse(StudyProofCourseUpdateVo vo) {
		if (vo.getId() == null) {
			return Result.error("ID不能为空");
		}
		
		StudyProofCourse course = new StudyProofCourse();
		BeanUtils.copyProperties(vo, course);
		this.updateById(course);
		
		
		return Result.success(1);

		
	}

	@Override
	public boolean deleteStudyProofCourse(StudyProofCourseDeleteVo vo) {
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
	public StudyProofCourseViewVo viewStudyProofCourse(StudyProofCourseViewVo viewVo) {
		StudyProofCourse e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}


	


	
	
}
