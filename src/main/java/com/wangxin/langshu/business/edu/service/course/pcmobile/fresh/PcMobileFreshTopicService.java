package com.wangxin.langshu.business.edu.service.course.pcmobile.fresh;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshTopicCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshTopicDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshTopicMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshTopic;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshTopicService;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topicCourse.FreshTopicCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.Constants;


@Service
public class PcMobileFreshTopicService  extends ServiceImpl<FreshTopicMapper, FreshTopic> implements IPcMobileFreshTopicService {

	@Autowired
	public FreshTopicDao freshTopicDao;
	
	@Autowired
	public FreshTopicCourseDao freshTopicCourseDao;
	
	
	@Autowired
    private FreshTopicMapper freshCourseMapper;

	
	@Override
	public Result<Page<FreshTopicVo>> queryTopics(FreshTopicQ q) {
		if(StringUtils.isEmpty(q.getTopicLocation())){
			q.setTopicLocation("2");
		}
		Page<FreshTopicVo> page =  freshTopicDao.queryTopics(q);
		List<FreshTopicVo> list = page.getList();
		if(CollectionUtils.isNotEmpty(list)){
			for(FreshTopicVo freshTopicVo : list){
				FreshTopicCourseQ freshTopicCourseQ = new FreshTopicCourseQ();
				freshTopicCourseQ.setTopicId(freshTopicVo.getId());
				freshTopicCourseQ.setValidValue("1");
				Page<FreshTopicCourseVo> freshTopicCourseVos = freshTopicCourseDao.queryTopicCourses(freshTopicCourseQ);
				freshTopicVo.setTopicCourseList(freshTopicCourseVos.getList());
			}
		}
		
		return Result.success(page);
	}
	

	@Override
	public Result<FreshTopicSaveVo> saveTopic(FreshTopicSaveVo vo) {
		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		FreshTopic fca = new FreshTopic();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		return Result.success(vo);
	}

	@Override
	public Result<Integer> updateTopic(FreshTopicUpdateVo vo) {
		FreshTopic fca = new FreshTopic();
		BeanUtils.copyProperties(vo, fca);
		this.updateById(fca);
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteTopic(FreshTopicDeleteVo vo) {
		// 将状态改为冻结状态
		FreshTopic record = this.getById(vo.getId());
		record.setValidValue(Constants.FREEZE);
		boolean result = this.updateById(record);
		if (result) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);

	}

	@Override
	public FreshTopicViewVo viewTopic(FreshTopicViewVo viewVo) {
		FreshTopic e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return viewVo;
	}


	

	


	
	
}
