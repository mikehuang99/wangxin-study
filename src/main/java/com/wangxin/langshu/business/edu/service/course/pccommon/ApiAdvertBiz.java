package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.AdvertBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.AdvertDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.AdvertListDTO;
import com.wangxin.langshu.business.edu.entity.course.old.Advert;
import com.wangxin.langshu.business.edu.dao.course.AdvertDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiAdvertBiz;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.DateUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 广告信息
 *
 *  
 */
@Service
public class ApiAdvertBiz implements IApiAdvertBiz{

	@Autowired
	private AdvertDao advertDao;

	public Result<AdvertListDTO> list(AdvertBO advertBO) {
		AdvertListDTO dto = new AdvertListDTO();
		// 开始时间和结束时间
		List<Advert> advertList = advertDao.listByPlatShowAndValidValueAndBeginTimeAndEndTime(advertBO.getPlatShow(), ValidValueEnum.YES.getCode(), DateUtil.parseDate("2019-07-04", "yyyy-MM-dd"), DateUtil.parseDate("2019-07-03", "yyyy-MM-dd"));

		if (CollectionUtil.isNotEmpty(advertList)) {
			dto.setAdvertList(PageUtil.copyList(advertList, AdvertDTO.class));
		}
		return Result.success(dto);
	}

}
