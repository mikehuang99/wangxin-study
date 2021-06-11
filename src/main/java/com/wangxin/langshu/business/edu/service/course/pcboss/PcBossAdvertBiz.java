package com.wangxin.langshu.business.edu.service.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertViewRESQ;
import com.wangxin.langshu.business.edu.dao.course.AdvertDao;
import com.wangxin.langshu.business.edu.entity.course.old.Advert;
import com.wangxin.langshu.business.edu.entity.course.old.AdvertExample;
import com.wangxin.langshu.business.edu.entity.course.old.AdvertExample.Criteria;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossAdvertBiz;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 广告信息
 *
 */
@Service
public class PcBossAdvertBiz implements IPcBossAdvertBiz{

	//@Autowired
	//private IBossSysBiz bossSysBiz;

	@Autowired
	private AdvertDao dao;
	
	@Autowired
	private ISystemConfigService systemConfigService;

	public Result<Page<AdvertPageRESQ>> list(AdvertPageREQ req) {
		AdvertExample example = new AdvertExample();
		Criteria c = example.createCriteria();
		if (req.getPlatShow() != null) {
			c.andPlatShowEqualTo(req.getPlatShow());
		}
		if (StringUtils.hasText(req.getAdvertTitle())) {
			c.andAdvertTitleEqualTo(req.getAdvertTitle());
		}
		if (req.getValidValue() != null) {
			c.andValidValueEqualTo(req.getValidValue());
		}
		example.setOrderByClause(" valid_value desc, sort desc, id desc ");
		Page<Advert> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, AdvertPageRESQ.class));
	}

	/**
	 * 
	 * 添加轮播广告
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> save(AdvertSaveREQ req) {
		Advert record = BeanUtil.copyProperties(req, Advert.class);
		record.setBeginTime(DateUtil.parseDate(req.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		record.setEndTime(DateUtil.parseDate(req.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 更新广告信息
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> update(AdvertUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Advert advert = dao.getById(req.getId());
		if (ObjectUtil.isNull(advert)) {
			return Result.error("找不到广告信息");
		}
		if (StringUtils.hasText(req.getAdvertImg()) && !advert.getAdvertImg().equals(req.getAdvertImg())) {
			AliyunUtil.delete(advert.getAdvertImg(), BeanUtil.copyProperties(systemConfigService.view(), Aliyun.class));
		}
		Advert record = BeanUtil.copyProperties(req, Advert.class);
		record.setBeginTime(DateUtil.parseDate(req.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		record.setEndTime(DateUtil.parseDate(req.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * 删除广告信息
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> delete(AdvertDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Advert advert = dao.getById(req.getId());
		if (ObjectUtil.isNull(advert)) {
			return Result.error("找不到广告信息");
		}
		AliyunUtil.delete(advert.getAdvertImg(), BeanUtil.copyProperties(systemConfigService.view(), Aliyun.class));
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * 广告信息查看
	 * 
	 * @param req
	 * @return
	 */
	public Result<AdvertViewRESQ> view(AdvertViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Advert record = dao.getById(req.getId());
		return Result.success(BeanUtil.copyProperties(record, AdvertViewRESQ.class));
	}

}
