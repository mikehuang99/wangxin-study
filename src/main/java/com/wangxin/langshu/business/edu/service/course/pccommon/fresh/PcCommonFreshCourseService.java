package com.wangxin.langshu.business.edu.service.course.pccommon.fresh;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshCourseMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.fresh.IPcCommonFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IStudentGroupService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcCommonFreshCourseService  extends ServiceImpl<FreshCourseMapper, FreshCourse> implements IPcCommonFreshCourseService {

	@Autowired
	public FreshCourseDao freshCourseDao;
	
	@Autowired
    private FreshCourseMapper freshCourseMapper;
	
	@Autowired
	private IStudentGroupService studentGroupService;


	@Override
	public Page<FreshCourseVo> queryCourses(FreshCourseQ q) {
			return freshCourseDao.queryCourses(q);
	}
	

	@Override
	public boolean saveCourse(FreshCourseSaveVo vo) {
		String id = UUID.randomUUID().toString().replace("-", "");
		FreshCourse Course = new FreshCourse();
		
		BeanUtils.copyProperties(vo, Course);
		Course.setId(id);
		this.save(Course);
		/*
		if(null!=vo.getStudentGroupIds()&&"2".equals(vo.getExamObjectType())){
			for(String s:vo.getStudentGroupIds()){
				if(null==s){
					continue;
				}
				CourseStudentGroupRelateSaveVo emugrVo = new CourseStudentGroupRelateSaveVo();
				emugrVo.setCourseId(id);
				emugrVo.setStudentGroupId(s);
				examManagementStudentGroupRelateService.saveEntity(emugrVo);
			}
		}*/
		
		return true;
	}

	@Override
	public boolean updateCourse(FreshCourseUpdateVo vo) {
		FreshCourse Course = new FreshCourse();
		BeanUtils.copyProperties(vo, Course);
		this.updateById(Course);
		
		
		return true;
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
		FreshCourse examManagement = this.getById(viewVo.getId());
		if(ObjectUtil.isNull(examManagement)) {
			return null;
		}
		BeanUtils.copyProperties(examManagement, viewVo);

		return viewVo;
	}

	


	
	
}
