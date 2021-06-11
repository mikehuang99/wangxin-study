package com.wangxin.langshu.business;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangxin.langshu.business.util.base.BaseController;


@Controller
public class IndexController extends BaseController{

	
	@RequestMapping(value = "/")
	public String listForPage(){
		return "redirect:index.html";
	}

	
	
}
