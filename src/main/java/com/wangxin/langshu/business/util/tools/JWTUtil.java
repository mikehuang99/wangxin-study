/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.tools;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 *  
 */
public final class JWTUtil {

	protected static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

	/*
	private static final String TOKEN_SECRET = "eyJhbGciOiJIUzI1NiJ9";
	private static final String ISSUER = "RONCOO";
	public static final String USERNO = "userNo";*/
	private static final String TOKEN_SECRET = "weyKdjei9834ykdskfkh";
	private static final String ISSUER = "WANGXINSHU";
	public static final String USERNO = "userNo";
	public static final Long DATE = 30 * 24 * 3600 * 1000L; // 1个月


	public static String create(String userNo, Long date) {
		try {
			return JWT.create().withIssuer(ISSUER).withClaim(USERNO, userNo.toString()).withExpiresAt(new Date(System.currentTimeMillis() + date)).sign(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
			logger.error("JWT生成失败", e);
			return "";
		}
	}

	/**
	 * 
	 * @param token
	 * @return
	 * @throws JWTVerificationException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 */
	public static DecodedJWT verify(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(ISSUER).build().verify(token);
	}

	/**
	 * 
	 * @param token
	 * @return
	 * @throws JWTVerificationException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 */
	public static String getUserNo(DecodedJWT decodedJWT) {
		return decodedJWT.getClaim(USERNO).asString();
	}

}
