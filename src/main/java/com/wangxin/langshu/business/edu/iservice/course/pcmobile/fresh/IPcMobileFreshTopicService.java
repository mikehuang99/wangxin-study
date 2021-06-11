package com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshTopic;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcMobileFreshTopicService  extends IService<FreshTopic> {
	
	

	public Result<Page<FreshTopicVo>> queryTopics(FreshTopicQ q) ;
	

	public Result<FreshTopicSaveVo>  saveTopic(FreshTopicSaveVo vo) ;

	public Result<Integer>  updateTopic(FreshTopicUpdateVo vo) ;

	
	public Result<Integer> deleteTopic(FreshTopicDeleteVo vo) ;

	
	public FreshTopicViewVo viewTopic(FreshTopicViewVo vo) ;
	
	
	
	
}
