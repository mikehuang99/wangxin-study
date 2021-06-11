package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课时信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class LessonBO implements Serializable {

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
     * 课程ID
     */
    private String courseId;
    /**
     * 章节ID
     */
    private String chapterId;
    /**
     * 课时名称
     */
    private String lessonName;
    /**
     * 课时描述
     */
    private String lessonDesc;
    /**
     * 是否免费：1免费，0收费
     */
    private Integer isFree;
    /**
     * 原价
     */
    private BigDecimal lessonOriginal;
    /**
     * 优惠价
     */
    private BigDecimal lessonDiscount;
    /**
     * 购买人数
     */
    private Integer buyCount;
    /**
     * 学习人数
     */
    private Integer studyCount;
    /**
     * 是否存在文档(1存在，0否)
     */
    private Integer isDoc;
    /**
     * 文档名称
     */
    private String docName;
    /**
     * 文档地址
     */
    private String docUrl;
    /**
     * 是否存在视频(1存在，0否)
     */
    private String isVideo;
    /**
     * 视频编号
     */
    private String videoLocalNumber;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 时长
     */
    private String videoLength;

    /**
     * 阿里云oas
     */
    private String videoOasUrl;
}
