package com.wangxin.langshu.business.util.pretool;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.JWTUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNoUtil {
	private static final Logger logger = LoggerFactory.getLogger(UserNoUtil.class);

	public static final String TOKEN = "token";
	
	public static String getUserNo(HttpServletRequest request) {
		String token = request.getHeader(TOKEN); // 检验token
		if (StringUtils.isEmpty(token)) { // token不存在，则从报文里面获取
			//throw new BaseException("token不存在，请重新登录");
			throw new BaseException(ResultEnum.TOKEN_ERROR);

		}
		// 解析 token
		DecodedJWT jwt = null;
		try {
			jwt = JWTUtil.verify(token);
		} catch (Exception e) {
			logger.error("token异常，token={}", token.toString());
			throw new BaseException(ResultEnum.TOKEN_ERROR);
		}

		// 校验token
		if (null == jwt) {
			throw new BaseException(ResultEnum.TOKEN_ERROR);
		}
		String userNo = JWTUtil.getUserNo(jwt);
		return userNo;
	}

}
