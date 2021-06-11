/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *  
 */
public final class CookieUtil {

	private CookieUtil() {
	}

	public static String getCookieByName(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return "";
		} else {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}

}
