package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程视频信息
 *
 * 
 */
@Data
@Accessors(chain = true)
public class CourseVideoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer validValue;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 教师用户编号
     */
    private String teacherUserNo;
    /**
     * 课程ID
     */
    private String courseId;
    /**
     * 章节ID
     */
    private String chapterId;
    /**
     * 课时ID
     */
    private String lessonId;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频状态(1待上传，2上传成功，3上传失败)
     */
    private Integer videoUploadStatus;
    /**
     * 时长
     */
    private String videoLength;

    /**
     * 阿里云oas
     */
    private String videoOasUrl;

}
