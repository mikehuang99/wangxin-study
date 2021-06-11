/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.service.course.pcboss;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wangxin.langshu.business.edu.entity.course.old.FileStorage;
import com.wangxin.langshu.business.edu.dao.course.FileStorageDao;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossUploadBiz;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.FileStorageTypeEnum;
import com.wangxin.langshu.business.util.enums.FileTypeEnum;
import com.wangxin.langshu.business.util.enums.PlatformEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;
import com.wangxin.langshu.business.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 上传接口
 */
@Service
public class PcBossUploadBiz extends BaseBiz implements IPcBossUploadBiz{


    //@Autowired
    //private BossSysBiz bossSysBiz;
    @Autowired
    private FileStorageDao fileStorageDao;
    
	@Autowired
	private ISystemConfigService systemConfigService;

    /**
     * 上传图片接口
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
                    fileStorageDao.save(fileStorage);
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
                    fileStorageDao.save(fileStorage);
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
