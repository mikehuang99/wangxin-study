package com.wangxin.langshu.business.edu.controller.auto;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pccommon/auto")
public class PcCommonAutoController extends BaseController {

	
	@ApiOperation(value = "自动测试", notes = "自动测试")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public Result<Page<HashMap<String,String>>> test(@RequestBody ArrayList<HashMap<String,Object>> mapList) {
		HashMap<String,String> map = new HashMap<>();
		map.put("222", "ppp");
		map.put("wee", "2333");
		HashMap<String,String> map2 = new HashMap<>();
		map2.put("222", "ppp222");
		map2.put("wee", "2333555");
		ArrayList<HashMap> list = new ArrayList<HashMap>();
		list.add(map);
		list.add(map2);
		Page page = new Page<>();
		page.setList(list);
		page.setPageCurrent(1);
		page.setPageSize(20);
		return Result.success(page);
	}

	
	
	
}
