package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseSubjectBO implements Serializable {

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
     * 父分类ID
     */
    private String parentId;
    /**
     * 分类类型(1课程，2资源)
     */
    private Integer subjectType;
    /**
     * 分类名称
     */
    private String subjectName;
    /**
     * 层级
     */
    private Integer floor;
    /**
     * 备注
     */
    private String remark;
}
