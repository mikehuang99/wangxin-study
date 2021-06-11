package com.wangxin.langshu.business.edu.dao.course;

import java.util.Date;
import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.Advert;
import com.wangxin.langshu.business.edu.entity.course.old.AdvertExample;
import com.wangxin.langshu.business.util.base.Page;

public interface AdvertDao {
	int save(Advert record);

	int deleteById(String id);

	int updateById(Advert record);

	Advert getById(String id);

	Page<Advert> listForPage(int pageCurrent, int pageSize, AdvertExample example);

	/**
	 * 列出广告信息，注意：开始时间<现在时间<结束时间
	 *
	 * @param platShow
	 * @param validValue
	 * 
	 */
	List<Advert> listByPlatShowAndValidValueAndBeginTimeAndEndTime(Integer platShow, Integer validValue, Date beginTime, Date endTime);
}