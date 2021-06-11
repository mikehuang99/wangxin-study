/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wangxin.langshu.business.edu.entity.course.old.CourseVideo;
import com.wangxin.langshu.business.edu.entity.course.old.FileStorage;
import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.dao.course.CourseVideoDao;
import com.wangxin.langshu.business.edu.dao.course.FileStorageDao;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiUploadBiz;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.FileStorageTypeEnum;
import com.wangxin.langshu.business.util.enums.FileTypeEnum;
import com.wangxin.langshu.business.util.enums.PlatformEnum;
import com.wangxin.langshu.business.util.enums.VideoUploadStatusEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;
import com.wangxin.langshu.business.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 上传接口
 * 
 * 
 */
@Service
public class ApiUploadBiz extends BaseBiz implements IApiUploadBiz{



	@Autowired
	private LessonApplyDao lessonApplyDao;
	@Autowired
	private LessonDao lessonDao;

	@Autowired
	private CourseVideoDao courseVideoDao;
	@Autowired
	private FileStorageDao fileStorageDao;
	
	@Autowired
	private ISystemConfigService systemConfigService;

	/**
	 * 上传视频接口
	 * 
	 * 
	 */
	public Result<String> uploadVideo(MultipartFile videoFile) {
		// 视频上传
		if (videoFile == null || videoFile.isEmpty()) {
			return Result.error("请选择视频进行上传");
		}

		// 获取上传文件的原名
		String fileName = videoFile.getOriginalFilename();
		boolean fileStatus = true;
		List<String> fileTypes = Arrays.asList("avi", "mp4", "flv", "mpg", "mov", "asf", "3gp", "f4v", "wmv",
				"x-ms-wmv\n");
		for (String filetype : fileTypes) {
			// 上传文件的原名+小写+后缀
			if (fileName.toLowerCase().endsWith(filetype)) {
				fileStatus = false;
				break;
			}
		}
		if (fileStatus) {
			return Result.error("上传的视频类型不正确");
		}

		String videoLocalNumber = IdWorker.getId(); // 当作存储到本地的文件名，方便定时任务的处理
		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();


		// 1、上传到本地
		File targetFile = new File(
				systemConfigViewVo.getLessonVideoPath() + videoLocalNumber + "." + StrUtil.getSuffix(fileName));
		targetFile.setLastModified(System.currentTimeMillis());// 设置最后修改时间
		// 判断文件目录是否存在，不存在就创建文件目录
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		try {
			videoFile.transferTo(targetFile);
		} catch (Exception e) {
			logger.error("上传到本地失败", e);
			return Result.error("上传文件出错，请重新上传");
		}

		// 新增课程视频表信息
		CourseVideo courseVideo = new CourseVideo();
		courseVideo.setVideoLocalNumber(videoLocalNumber);
		courseVideo.setCreateTime(null);
		courseVideo.setModifiedTime(null);
		courseVideo.setVideoName(fileName);
		courseVideo.setVideoUploadStatus(VideoUploadStatusEnum.WAIT.getCode());
		int result = courseVideoDao.save(courseVideo);

		if (result > 0) {
			callbackExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 2、异步上传到保利威视
					/*
					UploadFile uploadFile = new UploadFile();
					uploadFile.setTitle(fileName);
					uploadFile.setDesc(fileName);
					uploadFile.setTag(videoFile.getOriginalFilename());
					uploadFile.setCataid(1L);*/

					// 获取系统配置信息
					//SysVO sys = bossSysBiz.getSys();

					//UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile, sys.getPolyvWritetoken());
					//if (result == null) {
						// 上传异常，不再进行处理，定时任务会继续进行处理
					//	return;
					//}

					//courseVideo.setVideoLength(result.getDuration());
					courseVideo.setVideoUploadStatus(VideoUploadStatusEnum.SUCCES.getCode());
					//courseVideoDao.updateById(courseVideo);

					// 3、异步上传到阿里云
					String videoOasUrl = AliyunUtil.uploadDoc(PlatformEnum.COURSE, targetFile,"",
							BeanUtil.copyProperties(systemConfigViewVo, Aliyun.class));
					courseVideo.setVideoOasUrl(videoOasUrl);
					courseVideoDao.updateById(courseVideo);

					// 根据视频编号、课时ID查询课程视频信息
					CourseVideo courseVideo = courseVideoDao.getByVideoLocalNumberAndLessonId(videoLocalNumber, "0");

					// 根据视频编号更新视频信息
					List<CourseVideo> list = courseVideoDao.listByVideoLocalNumber(videoLocalNumber);
					for (CourseVideo video : list) {
						video.setVideoLength(courseVideo.getVideoLength());
						video.setVideoUploadStatus(VideoUploadStatusEnum.SUCCES.getCode());
						video.setVideoOasUrl(courseVideo.getVideoOasUrl());
						courseVideoDao.updateById(video);
					}

					// 更新课时审核表视频信息
					List<LessonApply> lessonAuditList = lessonApplyDao.listByVideoLocalNumber(videoLocalNumber);
					for (LessonApply lessonAudit : lessonAuditList) {
						lessonAudit.setVideoName(courseVideo.getVideoName());
						lessonAudit.setVideoLength(courseVideo.getVideoLength());
						lessonApplyDao.updateById(lessonAudit);
					}
					// 更新课时视频信息
					List<Lesson> lessonList = lessonDao.listByVideoLocalNumber(videoLocalNumber);
					for (Lesson lesson : lessonList) {
						lesson.setVideoName(courseVideo.getVideoName());
						lesson.setVideoLength(courseVideo.getVideoLength());
						lessonDao.updateById(lesson);
					}
					// 4、成功删除本地文件
					if (targetFile.isFile() && targetFile.exists()) {
						targetFile.delete();
					}
				}
			});
		} else {
			return Result.error("系统异常，请重试");
		}
		return Result.success(String.valueOf(courseVideo.getVideoLocalNumber()));
	}

	/**
	 * 上传图片接口
	 * 
	 * 
	 */
	public Result<String> uploadPic(MultipartFile picFile) {
		if (ObjectUtil.isNotNull(picFile)) {
			// 获取系统配置信息
			//SysVO sys = bossSysBiz.getSys();
			SystemConfigViewVo systemConfigViewVo = systemConfigService.view();

			if (ObjectUtil.isNull(systemConfigViewVo)) {
				Result.error("未配置系统配置表");
			}
			String fileNo = IdWorker.getId();
			// 1、上传到本地
			if (systemConfigViewVo.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
				File pic = new File(systemConfigViewVo.getPicStoragePath() + fileNo.toString() + "."
						+ StrUtil.getSuffix(picFile.getOriginalFilename()));
				try {
					// 判断文件目录是否存在，不存在就创建文件目录
					if (!pic.getParentFile().exists()) {
						pic.getParentFile().mkdirs();// 创建父级文件路径
					}
					picFile.transferTo(pic);
					FileStorage fileStorage = new FileStorage();
					fileStorage.setFileName(picFile.getOriginalFilename());
					fileStorage.setFileNo(fileNo);
					fileStorage.setFileSize(picFile.getSize());
					fileStorage.setFileType(FileStorageTypeEnum.PICTURE.getCode());
					fileStorage.setFileUrl(pic.toString());
					fileStorageDao.save(fileStorage);
					return Result.success(pic.toString());
				} catch (Exception e) {
					logger.error("上传到本地失败", e);
					return Result.error("上传文件出错，请重新上传");
				}
			}
			return Result.success(AliyunUtil.uploadPic(PlatformEnum.COURSE, picFile,
					BeanUtil.copyProperties(systemConfigViewVo, Aliyun.class)));
		}
		return Result.error("请选择上传的图片");
	}

	/**
	 * 上传文档接口
	 * 
	 * 
	 */
	public Result<String> uploadDoc(MultipartFile docFile) {
		if (ObjectUtil.isNotNull(docFile)) {
			// 获取系统配置信息
			//SysVO sys = bossSysBiz.getSys();
			SystemConfigViewVo systemConfigViewVo = systemConfigService.view();

			if (ObjectUtil.isNull(systemConfigViewVo)) {
				Result.error("未配置系统配置表");
			}
			String fileNo = IdWorker.getId();
			// 1、上传到本地
			if (systemConfigViewVo.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
				File pic = new File(systemConfigViewVo.getDocStoragePath() + fileNo.toString() + "."
						+ StrUtil.getSuffix(docFile.getOriginalFilename()));
				try {
					// 判断文件目录是否存在，不存在就创建文件目录
					if (!pic.getParentFile().exists()) {
						pic.getParentFile().mkdirs();// 创建父级文件路径
					}
					docFile.transferTo(pic);
					FileStorage fileStorage = new FileStorage();
					fileStorage.setFileName(docFile.getOriginalFilename());
					fileStorage.setFileNo(fileNo);
					fileStorage.setFileSize(docFile.getSize());
					fileStorage.setFileType(FileStorageTypeEnum.DOC.getCode());
					fileStorage.setFileUrl(pic.toString());
					fileStorageDao.save(fileStorage);
					return Result.success(pic.toString());
				} catch (Exception e) {
					logger.error("上传到本地失败", e);
					return Result.error("上传文件出错，请重新上传");
				}
			}
			return Result.success(AliyunUtil.uploadDoc(PlatformEnum.COURSE, docFile,
					BeanUtil.copyProperties(systemConfigViewVo, Aliyun.class)));
		}
		return Result.error("请选择上传的文件");

	}

}
