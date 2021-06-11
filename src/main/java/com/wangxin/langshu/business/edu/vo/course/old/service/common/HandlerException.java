package com.wangxin.langshu.business.edu.vo.course.old.service.common;

//import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.wangxin.langshu.business.util.base.BaseController;

//import feign.FeignException;

/**
 * 
 *  
 */
//@RestControllerAdvertice(basePackages = "com.wangxin.langshu.business.edu.vo.course.old.service.controller.gateway")
public class HandlerException extends BaseController {

	/*
	@ExceptionHandler({ FeignException.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processBizException(FeignException e) {
		String msg = e.getCause().toString().split("\n|\r\n|\r")[1];
		logger.error(msg);
		@SuppressWarnings("unchecked")
		Map<String, Object> m = JSONUtil.parseObject(msg, Map.class);
		if (null != m.get("message")) {
			return Result.error(m.get("message").toString());
		}
		return Result.error("系统错误");
	}

	@ExceptionHandler({ HystrixRuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(HystrixRuntimeException e) {
		String msg = e.getCause().toString().split("\n|\r\n|\r")[1];
		logger.error(msg);
		@SuppressWarnings("unchecked")
		Map<String, Object> m = JSONUtil.parseObject(msg, Map.class);
		if (null != m.get("message")) {
			return Result.error(m.get("message").toString());
		}
		return Result.error("系统错误");
	}

	@ExceptionHandler({ BaseException.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(BaseException e) {
		logger.error("BaseException", e);
		return Result.error(e.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(Exception e) {
		logger.error("Exception", e);
		return Result.error("系统错误");
	}
	*/

}
