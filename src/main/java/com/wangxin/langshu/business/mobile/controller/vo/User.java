package com.wangxin.langshu.business.mobile.controller.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1720879081263233422L;

	private String phone;
	
	private String password;
	
	
}
