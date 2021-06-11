package com.wangxin.langshu.business.edu.controller.course.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertViewRESQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossAdvertBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 广告信息
 *
 */
@RestController
@RequestMapping(value = "/pcboss/course/advert")
public class PcBossAdvertController extends BaseController {

	@Autowired
	private IPcBossAdvertBiz pcBossAdvertBiz;

	/**
	 * 轮播广告分页列表接口
	 * 
	 * @param advertPageREQ
	 * @return
	 */
	@ApiOperation(value = "轮播广告分页列表接口", notes = "轮播广告分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AdvertPageRESQ>> list(@RequestBody AdvertPageREQ advertPageREQ) {
		return pcBossAdvertBiz.list(advertPageREQ);
	}

	/**
	 * 轮播广告添加接口
	 * 
	 * @param advertSaveREQ
	 * @return
	 */
	@ApiOperation(value = "轮播广告添加接口", notes = "轮播广告添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody AdvertSaveREQ advertSaveREQ) {
		return pcBossAdvertBiz.save(advertSaveREQ);
	}

	/**
	 * 轮播广告更新接口
	 * 
	 * @param advertUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "轮播广告更新接口", notes = "轮播广告更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody AdvertUpdateREQ advertUpdateREQ) {
		return pcBossAdvertBiz.update(advertUpdateREQ);
	}

	/**
	 * 轮播广告删除接口
	 * 
	 * @param advertDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "轮播广告删除接口", notes = "轮播广告删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody AdvertDeleteREQ advertDeleteREQ) {
		return pcBossAdvertBiz.delete(advertDeleteREQ);
	}

	/**
	 * 轮播广告查看接口
	 * 
	 * @param advertViewREQ
	 * @return
	 */
	@ApiOperation(value = "轮播广告查看接口", notes = "轮播广告查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AdvertViewRESQ> view(@RequestBody AdvertViewREQ advertViewREQ) {
		return pcBossAdvertBiz.view(advertViewREQ);
	}

}
