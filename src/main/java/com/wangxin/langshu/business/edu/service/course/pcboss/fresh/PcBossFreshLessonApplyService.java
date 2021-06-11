package com.wangxin.langshu.business.edu.service.course.pcboss.fresh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditViewDTO;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshLessonApplyMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLessonApply;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshLessonApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcBossFreshLessonApplyService  extends ServiceImpl<FreshLessonApplyMapper, FreshLessonApply> implements IPcBossFreshLessonApplyService {

	@Autowired
	public FreshLessonApplyDao freshLessonApplyDao;
	
	@Autowired
    private FreshLessonApplyMapper freshCourseMapper;
	
	
	@Autowired
	private PcBossFreshCourseApplyService freshCourseApplyService;
	
	@Autowired
	private PcBossFreshChapterApplyService freshChapterApplyService;
	
	@Autowired
	private PcBossFreshLessonService freshLessonService;

	@Override
	public Page<FreshLessonApplyVo> queryLessonApplys(FreshLessonApplyQ q) {
			return freshLessonApplyDao.queryLessonApplys(q);
	}
	

	@Override
	public FreshLessonApplySaveVo saveLessonApply(FreshLessonApplySaveVo vo) {
		FreshChapterApplyViewVo freshChapterApplyViewVo = new FreshChapterApplyViewVo();
		freshChapterApplyViewVo.setId(vo.getChapterId());
		FreshChapterApplyViewVo fcavo = freshChapterApplyService.viewChapterApply(freshChapterApplyViewVo);
		
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(fcavo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo);
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			throw new BaseException("不是本课程教师，不允许操作");
		}
		
		
		/*
		FreshLessonApplyQ freshLessonApplyQ = new FreshLessonApplyQ();
		freshLessonApplyQ.setChapterId(vo.getChapterId());
		List<FreshLessonApplyVo> freshLessonApplyVos = this.queryLessonApplys(freshLessonApplyQ).getList();
		int maxSort = 0;
		if(CollectionUtils.isNotEmpty(freshLessonApplyVos)){
			for(FreshLessonApplyVo freshLessonApplyVo: freshLessonApplyVos){
				if(freshLessonApplyVo.getSort()>maxSort){
					maxSort = freshLessonApplyVo.getSort();
				}
			}
		}*/
		
		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		vo.setCourseId(fcavo.getCourseId());
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		FreshLessonApply fca = new FreshLessonApply();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		
		//更新课程的审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(fcavo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		//freshCourseApply.setModifiedTime(new Date());

		freshCourseApplyService.updateById(freshCourseApply);
		
		return vo;
	}

	@Override
	public FreshLessonApplyUpdateVo updateLessonApply(FreshLessonApplyUpdateVo vo) {
		FreshLessonApplyViewVo freshLessonApplyViewVo = new FreshLessonApplyViewVo();
		freshLessonApplyViewVo.setId(vo.getId());
		FreshLessonApplyViewVo csavVo = this.viewLessonApply(freshLessonApplyViewVo);
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(vo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo);
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			throw new BaseException("不是本课程教师，不允许操作");
		}
		
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		
		FreshLessonApply Course = new FreshLessonApply();
		BeanUtils.copyProperties(vo, Course);
		this.updateById(Course);
		
		//更新课程的审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(csavVo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		//freshCourseApply.setModifiedTime(new Date());

		freshCourseApplyService.updateById(freshCourseApply);
		
		
		return vo;
	}

	@Override
	public boolean deleteLessonApply(FreshLessonApplyDeleteVo vo) {
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
	public FreshLessonApplyViewVo viewLessonApply(FreshLessonApplyViewVo viewVo) {
		FreshLessonApply e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}

	
	/**
	 * 更新课时排序接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> sort(FreshLessonApplySortVo freshLessonApplySortVo) {
		
		List<AuthLessonAuditViewDTO> lessonViewList = freshLessonApplySortVo.getLessonViewList();
		List<String> lesseonIds = new ArrayList<String>();
		if(CollectionUtil.isNotEmpty(lessonViewList)){
			for(int i=lessonViewList.size()-1;i>=0;i--){
				lesseonIds.add(lessonViewList.get(i).getId());
			}
			
		}
		if (CollectionUtil.isEmpty(lesseonIds)) {
			return Result.error("课时信息不能为空");
		}
		if (freshLessonApplySortVo.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		if (CollectionUtil.isNotEmpty(lesseonIds)) {
			int i = 1;
			for (String lessonId : lesseonIds) {
				// 更新课时审核信息表的排序
				int sort = i++;
				FreshLessonApply fla = this.getById(lessonId);
				fla.setSort(sort);
				this.updateById(fla);
				
				
				FreshLessonApply freshLessonApply = this.getById(lessonId);
				FreshLesson freshLesson = freshLessonService.getById(lessonId);
				FreshCourseApply course = freshCourseApplyService.getById(freshLessonApply.getCourseId());
				if (ObjectUtil.isNull(course)) {
					return Result.error("找不到课程信息");
				}
				if (!freshLessonApplySortVo.getUserNo().equals(course.getTeacherUserNo())) {
					return Result.error("不是本课程教师，不允许操作");
				}
				// 更新课时信息集合
				if (ObjectUtil.isNotNull(freshLesson)) {
					FreshLesson record = freshLessonService.getById(freshLesson.getId());
					record.setSort(sort);
					freshLessonService.updateById(record);
				}
			}
			return Result.success(i);
		}
		return Result.error("设置排序失败");
	}

	
	
}
