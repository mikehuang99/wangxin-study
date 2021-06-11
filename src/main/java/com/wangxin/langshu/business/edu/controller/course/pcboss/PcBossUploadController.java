/**
 *   广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.controller.course.pcboss;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wangxin.langshu.business.edu.entity.course.old.FileStorage;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IFileStorageBiz;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.FileStorageTypeEnum;
import com.wangxin.langshu.business.util.enums.FileTypeEnum;
import com.wangxin.langshu.business.util.enums.PlatformEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;
import com.wangxin.langshu.business.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 上传接口
 * 
 */
@RestController
@RequestMapping(value = "/pcboss/course/api/upload")
public class PcBossUploadController extends BaseController {

	//@Autowired
	//private IPcApiUploadBiz pcBossUploadBiz;
	
	//@Autowired
	//private IBossSysBiz bossSysBiz;
	
	@Autowired
	private ISystemConfigService systemConfigService;
	//@Autowired
	//private ILessonApplyBiz lessonApplyBiz;
	//@Autowired
	//private ILessonBiz lessonBiz;

	//@Autowired
	//private ICourseVideoBiz courseVideoBiz;
	@Autowired
	private IFileStorageBiz fileStorageBiz;

	/**
	 * 上传图片接口
	 * 
	 * @param picFile
	 */
	@ApiOperation(value = "上传图片接口", notes = "上传图片")
	@RequestMapping(value = "/pic", method = RequestMethod.POST)
	public Result<String> uploadPic(@RequestParam(value = "picFile", required = false) MultipartFile picFile) {
		//return pcBossUploadBiz.uploadPic(picFile);
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
                File pic = new File(systemConfigViewVo.getPicStoragePath() + fileNo.toString() + "." + StrUtil.getSuffix(picFile.getOriginalFilename()));
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
                    fileStorageBiz.save(fileStorage);
                    return Result.success(pic.toString());
                } catch (Exception e) {
                    logger.error("上传到本地失败", e);
                    return Result.error("上传文件出错，请重新上传");
                }
            }
            return Result.success(AliyunUtil.uploadPic(PlatformEnum.COURSE, picFile, BeanUtil.copyProperties(systemConfigViewVo, Aliyun.class)));
        }
        return Result.error("请选择上传的图片");
	}

	/**
	 * 上传文档接口
	 * 
	 * @param docFile
	 */
	@ApiOperation(value = "上传文档接口", notes = "上传文档")
	@RequestMapping(value = "/doc", method = RequestMethod.POST)
	public Result<String> uploadDoc(@RequestParam(name = "docFile", required = false) MultipartFile docFile) {
		//return pcBossUploadBiz.uploadDoc(docFile);
        if (ObjectUtil.isNotNull(docFile)) {
            // 获取系统配置信息
           // SysVO sys = bossSysBiz.getSys();
			SystemConfigViewVo systemConfigViewVo = systemConfigService.view();

            if (ObjectUtil.isNull(systemConfigViewVo)) {
                Result.error("未配置系统配置表");
            }
            String fileNo = IdWorker.getId();
            // 1、上传到本地
            if (systemConfigViewVo.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
                File pic = new File(systemConfigViewVo.getDocStoragePath() + fileNo.toString() + "." + StrUtil.getSuffix(docFile.getOriginalFilename()));
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
                    fileStorageBiz.save(fileStorage);
                    return Result.success(pic.toString());
                } catch (Exception e) {
                    logger.error("上传到本地失败", e);
                    return Result.error("上传文件出错，请重新上传");
                }
            }
            return Result.success(AliyunUtil.uploadDoc(PlatformEnum.COURSE, docFile, BeanUtil.copyProperties(systemConfigViewVo, Aliyun.class)));
        }
        return Result.error("请选择上传的文件");
	}

}
