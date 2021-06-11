/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.base;

import com.wangxin.langshu.business.util.enums.ResultEnum;

/**
 * 异常处理类
 * 
 *  
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** 异常码 */
	protected int code;
	
	public static int SYSTEM_ERROR = 99;
	
	public static int CUSTOM_ERROR = 88;

	public BaseException() {
		super("系统异常");
		this.code = SYSTEM_ERROR;
	}

	public BaseException(ResultEnum resultEnum) {
		super(resultEnum.getDesc());
		this.code = resultEnum.getCode();
	}

	public BaseException(String message) {
		super(message);
		this.code = SYSTEM_ERROR;
	}

	public BaseException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
