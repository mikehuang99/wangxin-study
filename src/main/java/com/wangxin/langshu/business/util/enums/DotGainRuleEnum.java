package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DotGainRuleEnum {

	REG(1, "注册"), LOGIN(2, "登陆"), SHARE(3, "分享"), SHARE_REG(4, "分享后被注册");

	private Integer code;

	private String desc;
	
}
