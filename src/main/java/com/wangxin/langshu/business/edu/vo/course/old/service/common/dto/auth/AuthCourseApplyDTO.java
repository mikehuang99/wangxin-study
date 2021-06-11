package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthCourseApplyDTO implements Serializable {

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
     * 审核状态(0:待审核,1:审核通过,2:审核不通过)
     */
    private Integer applyStatus;
    /**
     * 审核意见
     */
    private String applyResultDesc;
    /**
     * 教师用户编码
     */
    private String teacherUserNo;
    /**
     * 一级分类ID
     */
    private String subjectId1;
    /**
     * 二级分类ID
     */
    private String subjectId2;
    /**
     * 三级分类ID
     */
    private String subjectId3;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程封面
     */
    private String courseLogo;
    /**
     * 课程介绍ID
     */
    private String introduceId;
    /**
     * 是否免费：1免费，0收费
     */
    private Integer isFree;
    /**
     * 原价
     */
    private BigDecimal courseOriginal;
    /**
     * 优惠价
     */
    private BigDecimal courseDiscount;
    /**
     * 是否上架(1:上架，0:下架)
     */
    private Integer isPutaway;
    
    /**
     * 章节信息集合
     */
    private List<AuthChapterApplyDTO> authChapterApplyList;
    
}
