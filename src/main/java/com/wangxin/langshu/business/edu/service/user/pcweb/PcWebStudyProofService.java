package com.wangxin.langshu.business.edu.service.user.pcweb;

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
import com.wangxin.langshu.business.edu.dao.course.impl.StudyProofCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.StudyProofDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.StudyProofMapper;
import com.wangxin.langshu.business.edu.dao.system.FreshWebSiteDao;
import com.wangxin.langshu.business.edu.entity.course.CourseUser;
import com.wangxin.langshu.business.edu.entity.user.StudyProof;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.fresh.IPcCommonFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudentBiz;
import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudyProofCourseService;
import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudyProofService;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.system.fresh.webSite.FreshWebSiteQ;
import com.wangxin.langshu.business.edu.vo.system.fresh.webSite.FreshWebSiteVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.AuthStudentViewBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.dto.auth.AuthStudentDTO;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofQ;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofSaveVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofViewVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseQ;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcWebStudyProofService  extends ServiceImpl<StudyProofMapper, StudyProof> implements IPcWebStudyProofService {

	@Autowired
	public StudyProofDao studyProofDao;
	


	@Autowired
	public FreshWebSiteDao freshWebSiteDao;
	
	@Autowired
	public CourseUserDao courseUserDao;
	
	@Autowired
	public IPcWebCourseUserService pcWebCourseUserService;
	
	@Autowired
	public IPcWebStudentBiz pcWebStudentBiz;
	
	@Autowired
	public IPcCommonFreshCourseService pcCommonFreshCourseService;
	
	@Autowired
	public IPcWebStudyProofCourseService pcWebStudyProofCourseService;
	
	@Autowired
	public StudyProofCourseDao studyProofCourseDao;
	
	
	@Override
	public Page<StudyProofVo> queryStudyProofs(StudyProofQ q) {
		return studyProofDao.queryStudyProofs(q);
	}
	

	@Override
	public Result<StudyProofSaveVo> saveStudyProof(StudyProofSaveVo vo) {
		if(StringUtils.isEmpty(vo.getUserNo())) {
			return Result.error("用户编号不能为空");
		}else {
			vo.setProofUserNo(vo.getUserNo());
		}
		if(CollectionUtil.isEmpty(vo.getCourseUserIds())) {
			return Result.error("课程用户关系列表不能为空");
		}
		
		FreshWebSiteQ freshWebSiteQ = new FreshWebSiteQ();
		freshWebSiteQ.setPageCurrent(-1);
		freshWebSiteQ.setPageSize(-1);
		Page<FreshWebSiteVo> pageFreshWebSiteVo = freshWebSiteDao.queryEntities(freshWebSiteQ);
		if(CollectionUtil.isEmpty(pageFreshWebSiteVo.getList())) {
			return Result.error("站点配置错误");
		}
		FreshWebSiteVo freshWebSiteVo = pageFreshWebSiteVo.getList().get(0);
		if(StringUtils.isEmpty(freshWebSiteVo.getMainSealContent())||StringUtils.isEmpty(freshWebSiteVo.getSecondSealContent())) {
			return Result.error("站点配置缺少配置学习证明印章信息");
		}
		ArrayList<StudyProofCourseSaveVo> newStudyProofCourseVos = new ArrayList<StudyProofCourseSaveVo>();

		String id = UUID.randomUUID().toString();
		
		List<String> courseUserIds = vo.getCourseUserIds();
		
		for(String courseUserId : courseUserIds) {
			if(StringUtils.isNotEmpty(courseUserId)) {
				
				CourseUser courseUser = pcWebCourseUserService.getById(courseUserId);
				if(ObjectUtil.isNull(courseUser)) {
					return Result.error("课程用户关系ID错误，找不到该课程用户关系");
				}
				String courseId = courseUser.getCourseId();
				
				FreshCourseViewVo freshCourseViewVo = new FreshCourseViewVo();
				freshCourseViewVo.setId(courseUser.getCourseId());
				FreshCourseViewVo f = pcCommonFreshCourseService.viewCourse(freshCourseViewVo);
				if(ObjectUtil.isNull(f)) {
					return Result.error("课程ID错误，找不到该课程");
				}
				
				CourseUserQ courseUserQ = new CourseUserQ();
				courseUserQ.setCourseId(courseId);
				courseUserQ.setBindUserNo(vo.getProofUserNo());
				courseUserQ.setPageCurrent(-1);
				courseUserQ.setPageSize(-1);
				Page<CourseUserVo> pageCouserUserVosPage = courseUserDao.queryCourseUsers(courseUserQ);
				if(CollectionUtil.isEmpty(pageCouserUserVosPage.getList())||pageCouserUserVosPage.getList().size()!=1) {
					return Result.error("课程ID错误，找不到该用户的该课程");
				}
				
				StudyProofCourseSaveVo spcv = new StudyProofCourseSaveVo();
				spcv.setCourseId(courseId);
				spcv.setCourseName(f.getCourseName());
				spcv.setStudyProofId(id);
				spcv.setTotalStudyProgress(pageCouserUserVosPage.getList().get(0).getTotalStudyProgress());
				newStudyProofCourseVos.add(spcv);
			}
		}
		
		AuthStudentViewBO authStudentViewBO = new AuthStudentViewBO();
		authStudentViewBO.setUserNo(vo.getProofUserNo());
		Result<AuthStudentDTO> resultAuthStudent= pcWebStudentBiz.view(authStudentViewBO);
		if(ObjectUtil.isNull(resultAuthStudent.getData())){
			return Result.error("根据userNo找不到学生信息");
		}
		vo.setRealName(resultAuthStudent.getData().getRealName());
		vo.setHeadImgUrl(resultAuthStudent.getData().getHeadImgUrl());
		vo.setAge(resultAuthStudent.getData().getAge());
		vo.setSex(resultAuthStudent.getData().getSex());
		vo.setMobile(resultAuthStudent.getData().getMobile());
		vo.setIdentityCardNo(resultAuthStudent.getData().getIdentityCardNo());
		
		vo.setProofDate(new Date());
		vo.setMainSealContent(freshWebSiteVo.getMainSealContent());
		vo.setSecondSealContent(freshWebSiteVo.getSecondSealContent());
		

		vo.setId(id);
		vo.setValidValue(1);
		StudyProof course = new StudyProof();
		BeanUtils.copyProperties(vo, course);
		this.save(course);
		
		for(StudyProofCourseSaveVo spcv:newStudyProofCourseVos) {
			pcWebStudyProofCourseService.saveStudyProofCourse(spcv);
		}
		
		return Result.success(vo);
	}

	@Override
	public Result<Integer> updateStudyProof(StudyProofUpdateVo vo) {
		if (vo.getId() == null) {
			return Result.error("ID不能为空");
		}
		
		StudyProof course = new StudyProof();
		BeanUtils.copyProperties(vo, course);
		this.updateById(course);
		
		
		return Result.success(1);

		
	}

	@Override
	public boolean deleteStudyProof(StudyProofDeleteVo vo) {
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
	public Result<StudyProofViewVo> viewStudyProof(StudyProofViewVo viewVo) {
		if(StringUtils.isEmpty(viewVo.getId())) {
			return Result.error("学习证明的ID不能为空");
		}
		StudyProof e = this.getById(viewVo.getId());
		if(ObjectUtil.isNull(e)) {
			return Result.error("没有查到学习证明");
		}
		
		BeanUtils.copyProperties(e, viewVo);
		
		StudyProofCourseQ studyProofCourseQ = new StudyProofCourseQ();
		studyProofCourseQ.setStudyProofId(viewVo.getId());
		studyProofCourseQ.setPageCurrent(-1);
		studyProofCourseQ.setPageSize(-1);
		Page<StudyProofCourseVo> page = studyProofCourseDao.queryStudyProofCourses(studyProofCourseQ);
		
		List<StudyProofCourseVo> studyProofCourseVos = page.getList();
		viewVo.setStudyProofCourseVos(studyProofCourseVos);

		return Result.success(viewVo);
	}


	
}
