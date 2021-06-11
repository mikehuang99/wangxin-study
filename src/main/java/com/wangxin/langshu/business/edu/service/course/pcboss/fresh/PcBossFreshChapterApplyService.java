package com.wangxin.langshu.business.edu.service.course.pcboss.fresh;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshChapterApplyMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapter;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapterApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshChapterApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.Constants;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcBossFreshChapterApplyService  extends ServiceImpl<FreshChapterApplyMapper, FreshChapterApply> implements IPcBossFreshChapterApplyService {

	@Autowired
	public FreshChapterApplyDao freshChapterApplyDao;
	
	@Autowired
    private FreshChapterApplyMapper freshChapterApplyMapper;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private PcBossFreshCourseApplyService freshCourseApplyService;
	
	@Autowired
	private PcBossFreshLessonApplyService freshLessonApplyService;
	
	@Autowired
	private PcBossFreshChapterService freshChapterService;
	

	@Override
	public Page<FreshChapterApplyVo> queryChapterApplys(FreshChapterApplyQ q) {
			if(StringUtils.isEmpty(q.getCourseId())){
				throw new BaseException("缺少courseId");
			}
			return freshChapterApplyDao.queryChapterApplys(q);
	}
	

	@Override
	public FreshChapterApplySaveVo saveChapterApply(FreshChapterApplySaveVo vo) {

		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		FreshChapterApply fca = new FreshChapterApply();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		
		//更新课程的审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(vo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		//freshCourseApply.setModifiedTime(new Date());
		freshCourseApplyService.updateById(freshCourseApply);
		
		return vo;
	}

	@Override
	public FreshChapterApplyUpdateVo updateChapterApply(FreshChapterApplyUpdateVo vo) {
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		
		FreshChapterApply fca = new FreshChapterApply();
		BeanUtils.copyProperties(vo, fca);
		this.updateById(fca);
		
		FreshChapterApplyViewVo freshChapterApplyViewVo = new FreshChapterApplyViewVo();
		freshChapterApplyViewVo.setId(vo.getId());
		FreshChapterApplyViewVo csavVo = this.viewChapterApply(freshChapterApplyViewVo);
		
		//更新课程的审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(csavVo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		//freshCourseApply.setModifiedTime(new Date());

		freshCourseApplyService.updateById(freshCourseApply);
		
		
		return vo;
	}

	@Override
	public Result<Integer> deleteChapterApply(FreshChapterApplyDeleteVo vo) {

		if (vo.getId() == null) {
			return Result.error("章节id不能为空");
		}
		if (vo.getUserNo() == null) {
			return Result.error("用户编号不能为空");
		}

		// 查询更新后的章节信息
		FreshChapterApply freshChapterApply = this.getById(vo.getId());
		if (ObjectUtils.isEmpty(freshChapterApply)) {
			return Result.error("找不到章节信息");
		}
		FreshCourseApply course = freshCourseApplyService.getById(freshChapterApply.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!vo.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("不是本课程教师，不允许操作");
		}

		// 查询章节下是否存在课时信息
		FreshLessonApplyQ freshLessonApplyQ = new FreshLessonApplyQ();
		if(StringUtils.isEmpty(freshChapterApply.getId())){
			return Result.error("章节ID不能为空");
		}
		freshLessonApplyQ.setChapterId(freshChapterApply.getId());
		freshLessonApplyQ.setValidValue(ValidValueEnum.YES.getCode()+"");
		freshLessonApplyQ.setPageCurrent(-1);
		freshLessonApplyQ.setPageSize(-1);
		Page<FreshLessonApplyVo> pageLesson = freshLessonApplyService.queryLessonApplys(freshLessonApplyQ);
		List<FreshLessonApplyVo> lessonApplyList = pageLesson.getList();
		if (CollectionUtil.isNotEmpty(lessonApplyList)) {
			return Result.error("请先删除章节下的所有课时信息");
		}
		

		// 将状态改为冻结状态
		FreshChapterApply record = this.getById(vo.getId());
		record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		record.setValidValue(Constants.FREEZE);
		boolean result = this.updateById(record);
		if (result) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);

	}

	@Override
	public FreshChapterApplyViewVo viewChapterApply(FreshChapterApplyViewVo viewVo) {
		FreshChapterApply e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}

	/**
	 * 更新章节排序接口
	 * 
	 * @param 
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> sort(FreshChapterApplySortVo freshChapterApplySortVo) {
		if (CollectionUtil.isEmpty(freshChapterApplySortVo.getChapterIdList())) {
			return Result.error("排序失败，请刷新重试");
		}
		if (freshChapterApplySortVo.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		
		// 章节ID集合不为空就遍历拿到ID
		if (CollectionUtil.isNotEmpty(freshChapterApplySortVo.getChapterIdList())) {
			int i = 1;
			for (String chapterId : freshChapterApplySortVo.getChapterIdList()) {
				// 更新章节审核表排序
				FreshChapterApply fca = this.getById(chapterId);
				int sort = i++;
				fca.setSort(sort);
				this.updateById(fca);
				
				// 查询更新后的章节审核信息
				FreshChapterApply freshChapterApply = this.getById(chapterId);
				FreshCourseApply course = freshCourseApplyService.getById(freshChapterApply.getCourseId());
				if (ObjectUtil.isNull(course)) {
					return Result.error("找不到该课程信息");
				}
				if (!freshChapterApplySortVo.getUserNo().equals(course.getTeacherUserNo())) {
					return Result.error("不是本课程教师，不允许操作");
				}
				// 更新章节信息的排序
				FreshChapter chapter = freshChapterService.getById(chapterId);
				if (ObjectUtil.isNotNull(chapter)) {
					FreshChapter chapterInfo =freshChapterService.getById(chapter.getId());
					chapterInfo.setSort(sort);
					freshChapterService.updateById(chapterInfo);
				}
			}
			return Result.success(i);
		}
		return Result.error("更新排序失败");
	}


	
	
}
