package com.wangxin.langshu.business.edu.service.course.pcweb.fresh;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshChapterApplyMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapter;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapterApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshChapterApplyService;
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
public class PcWebFreshChapterApplyService  extends ServiceImpl<FreshChapterApplyMapper, FreshChapterApply> implements IPcWebFreshChapterApplyService {

	@Autowired
	public FreshChapterApplyDao freshChapterApplyDao;
	
	@Autowired
    private FreshChapterApplyMapper freshChapterApplyMapper;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private PcWebFreshCourseApplyService freshCourseApplyService;
	
	@Autowired
	private PcWebFreshLessonApplyService freshLessonApplyService;
	
	@Autowired
	private PcWebFreshChapterService freshChapterService;
	
	@Autowired
	private PcWebFreshChapterApplyService freshChapterApplyService;
	

	@Override
	public Page<FreshChapterApplyVo> queryChapterApplys(FreshChapterApplyQ q) {
			if(StringUtils.isEmpty(q.getCourseId())){
				throw new BaseException("??????courseId");
			}
			return freshChapterApplyDao.queryChapterApplys(q);
	}
	

	@Override
	public FreshChapterApplySaveVo saveChapterApply(FreshChapterApplySaveVo vo) {
		
		
		FreshChapterApplyQ freshChapterApplyQ = new FreshChapterApplyQ();
		freshChapterApplyQ.setCourseId(vo.getCourseId());
		List<FreshChapterApplyVo> list = freshChapterApplyDao.queryChapterApplys(freshChapterApplyQ).getList();
		if(CollectionUtil.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				FreshChapterApply f=  freshChapterApplyService.getById(list.get(i).getId());
				f.setSort(list.get(i).getSort()+1);
				this.updateById(f);
			}
		}

		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		vo.setSort(1);
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		FreshChapterApply fca = new FreshChapterApply();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		
		//???????????????????????????
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
		
		//???????????????????????????
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(csavVo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		//freshCourseApply.setModifiedTime(new Date());

		freshCourseApplyService.updateById(freshCourseApply);
		
		
		return vo;
	}

	@Override
	public Result<Integer> deleteChapterApply(FreshChapterApplyDeleteVo vo) {

		if (vo.getId() == null) {
			return Result.error("??????id????????????");
		}
		if (vo.getUserNo() == null) {
			return Result.error("????????????????????????");
		}

		// ??????????????????????????????
		FreshChapterApply freshChapterApply = this.getById(vo.getId());
		if (ObjectUtils.isEmpty(freshChapterApply)) {
			return Result.error("?????????????????????");
		}
		FreshCourseApply course = freshCourseApplyService.getById(freshChapterApply.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("?????????????????????");
		}
		if (!vo.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("???????????????????????????????????????");
		}

		// ???????????????????????????????????????
		if(StringUtils.isEmpty(freshChapterApply.getId())){
			throw new BaseException("??????chapterId");
		}
		FreshLessonApplyQ freshLessonApplyQ = new FreshLessonApplyQ();
		freshLessonApplyQ.setChapterId(freshChapterApply.getId());
		freshLessonApplyQ.setValidValue(ValidValueEnum.YES.getCode()+"");
		freshLessonApplyQ.setPageCurrent(-1);
		freshLessonApplyQ.setPageSize(-1);
		Page<FreshLessonApplyVo> pageLesson = freshLessonApplyService.queryLessonApplys(freshLessonApplyQ);
		List<FreshLessonApplyVo> lessonApplyList = pageLesson.getList();
		if (CollectionUtil.isNotEmpty(lessonApplyList)) {
			return Result.error("??????????????????????????????????????????");
		}
		

		// ???????????????????????????
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
	 * ????????????????????????
	 * 
	 * @param 
	 * @return
	 * 
	 */
	//@Transactional
	public Result<Integer> sort(FreshChapterApplySortVo freshChapterApplySortVo) {
		if (CollectionUtil.isEmpty(freshChapterApplySortVo.getChapterIdList())) {
			return Result.error("??????????????????????????????");
		}
		if (freshChapterApplySortVo.getUserNo() == null) {
			return Result.error("userNo????????????");
		}
		
		List<String> idStrings = freshChapterApplySortVo.getChapterIdList();

		
		// ??????ID??????????????????????????????ID
		if (CollectionUtil.isNotEmpty(freshChapterApplySortVo.getChapterIdList())) {
			int i = 1;
			//for (String chapterId : freshChapterApplySortVo.getChapterIdList()) {
			for (int n=idStrings.size()-1;n>=0;n--) {
				
				String chapterId = idStrings.get(n);
				
				// ???????????????????????????
				FreshChapterApply fca = freshChapterApplyService.getById(chapterId);
				int sort = i++;
				fca.setSort(sort);
				this.updateById(fca);
				
				// ????????????????????????????????????
				FreshChapterApply freshChapterApply = this.getById(chapterId);
				FreshCourseApply course = freshCourseApplyService.getById(freshChapterApply.getCourseId());
				if (ObjectUtil.isNull(course)) {
					return Result.error("????????????????????????");
				}
				if (!freshChapterApplySortVo.getUserNo().equals(course.getTeacherUserNo())) {
					return Result.error("???????????????????????????????????????");
				}
				// ???????????????????????????
				FreshChapter chapter = freshChapterService.getById(chapterId);
				if (ObjectUtil.isNotNull(chapter)) {
					FreshChapter chapterInfo = freshChapterService.getById(chapter.getId());
					chapterInfo.setSort(sort);
					freshChapterService.updateById(chapterInfo);
				}
			}
			return Result.success(i);
		}
		return Result.error("??????????????????");
	}


	
	
}
