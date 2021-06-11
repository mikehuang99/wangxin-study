package com.wangxin.langshu.business.edu.controller.course.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.AdvertBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.AdvertListDTO;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiAdvertBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 广告信息
 *
 */
@RestController
@RequestMapping(value = "/pccommon/course/advert")
public class PcCommonAdvertController extends BaseController {

	@Autowired
	private IApiAdvertBiz apiAdvertBiz;

	@ApiOperation(value = "广告列表接口", notes = "首页轮播广告列出")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<AdvertListDTO> list(@RequestBody AdvertBO advertBO) {
		return apiAdvertBiz.list(advertBO);
	}

}
