package com.wangxin.langshu.business.edu.service.course.pcweb.fresh;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshChapterDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshChapterMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapter;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshChapterService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.Constants;


@Service
public class PcWebFreshChapterService  extends ServiceImpl<FreshChapterMapper, FreshChapter> implements IPcWebFreshChapterService {

	@Autowired
	public FreshChapterDao freshChapterDao;
	
	
	@Autowired
    private FreshChapterMapper freshCourseMapper;
	
	/*
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private FreshCourseApplyService freshCourseApplyService;
	
	@Autowired
	private FreshLessonApplyService freshLessonApplyService;
	 */
	
	@Override
	public Page<FreshChapterVo> queryChapters(FreshChapterQ q) {
			if(StringUtils.isEmpty(q.getCourseId())){
				throw new BaseException("缺少courseId");
			}
			return freshChapterDao.queryChapters(q);
	}
	

	@Override
	public FreshChapterSaveVo saveChapter(FreshChapterSaveVo vo) {

		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		FreshChapter fca = new FreshChapter();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		
		
		
		return vo;
	}

	@Override
	public FreshChapterUpdateVo updateChapter(FreshChapterUpdateVo vo) {
		
		FreshChapter fca = new FreshChapter();
		BeanUtils.copyProperties(vo, fca);
		this.updateById(fca);
		
		
		
		
		return vo;
	}

	@Override
	public Result<Integer> deleteChapter(FreshChapterDeleteVo vo) {
		// 将状态改为冻结状态
		FreshChapter record = this.getById(vo.getId());
		record.setValidValue(Constants.FREEZE);
		boolean result = this.updateById(record);
		if (result) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);

	}

	@Override
	public FreshChapterViewVo viewChapter(FreshChapterViewVo viewVo) {
		FreshChapter e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}

	


	
	
}
