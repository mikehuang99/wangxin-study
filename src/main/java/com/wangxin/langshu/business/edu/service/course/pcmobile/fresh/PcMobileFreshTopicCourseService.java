package com.wangxin.langshu.business.edu.service.course.pcmobile.fresh;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshTopicCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshTopicCourseMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshTopicCourse;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshTopicCourseService;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.Constants;


@Service
public class PcMobileFreshTopicCourseService  extends ServiceImpl<FreshTopicCourseMapper, FreshTopicCourse> implements IPcMobileFreshTopicCourseService {

	@Autowired
	public FreshTopicCourseDao freshTopicDao;
	
	
	@Autowired
    private FreshTopicCourseMapper freshCourseMapper;

	
	@Override
	public Result<Page<FreshTopicCourseVo>> queryTopics(FreshTopicCourseQ q) {
			return Result.success(freshTopicDao.queryTopicCourses(q));
	}
	

	@Override
	public Result<FreshTopicCourseSaveVo> saveTopic(FreshTopicCourseSaveVo vo) {
		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		FreshTopicCourse fca = new FreshTopicCourse();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		return Result.success(vo);
	}

	@Override
	public Result<Integer> updateTopic(FreshTopicCourseUpdateVo vo) {
		FreshTopicCourse fca = new FreshTopicCourse();
		BeanUtils.copyProperties(vo, fca);
		this.updateById(fca);
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteTopic(FreshTopicCourseDeleteVo vo) {
		// 将状态改为冻结状态
		FreshTopicCourse record = this.getById(vo.getId());
		record.setValidValue(Constants.FREEZE);
		boolean result = this.updateById(record);
		if (result) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);

	}

	@Override
	public FreshTopicCourseViewVo viewTopic(FreshTopicCourseViewVo viewVo) {
		FreshTopicCourse e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}


	

	


	
	
}
