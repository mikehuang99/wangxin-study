/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import org.springframework.web.multipart.MultipartFile;

import com.wangxin.langshu.business.util.base.Result;

/**
 * 上传接口
 * 
 * 
 */

public interface IApiUploadBiz {


	/**
	 * 上传视频接口
	 * 
	 * 
	 */
	public Result<String> uploadVideo(MultipartFile videoFile) ;

	/**
	 * 上传图片接口
	 * 
	 * 
	 */
	public Result<String> uploadPic(MultipartFile picFile) ;

	/**
	 * 上传文档接口
	 * 
	 * 
	 */
	public Result<String> uploadDoc(MultipartFile docFile) ;

}
