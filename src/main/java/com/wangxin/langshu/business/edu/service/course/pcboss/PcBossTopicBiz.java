package com.wangxin.langshu.business.edu.service.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicViewRESQ;
import com.wangxin.langshu.business.edu.entity.course.old.Topic;
import com.wangxin.langshu.business.edu.entity.course.old.TopicExample;
import com.wangxin.langshu.business.edu.entity.course.old.TopicExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.TopicDao;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossTopicBiz;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 
 * 专区
 *
 */
@Service
public class PcBossTopicBiz implements IPcBossTopicBiz{

	@Autowired
	private TopicDao dao;

	/**
	 * 分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<TopicPageRESQ>> listForPage(TopicPageREQ req) {
		TopicExample example = new TopicExample();
		Criteria c = example.createCriteria();
		if (req.getTopicLocation() != null) {
			c.andTopicLocationEqualTo(req.getTopicLocation());
		}
		if (StringUtils.hasText(req.getTopicName())) {
			c.andTopicNameEqualTo(req.getTopicName());
		}
		if (req.getValidValue() != null) {
			c.andValidValueEqualTo(req.getValidValue());
		}
		example.setOrderByClause(" valid_value desc, sort desc, id desc ");
		Page<Topic> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, TopicPageRESQ.class));
	}

	public Result<Integer> save(TopicSaveREQ req) {
		if (StringUtils.isEmpty(req.getTopicName())) {
			return Result.error("专区名称不能为空");
		}
		Topic record = BeanUtil.copyProperties(req, Topic.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	public Result<Integer> delete(TopicDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<Integer> update(TopicUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Topic record = BeanUtil.copyProperties(req, Topic.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<TopicViewRESQ> view(TopicViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Topic record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到专区信息");
		}
		return Result.success(BeanUtil.copyProperties(record, TopicViewRESQ.class));
	}

}
