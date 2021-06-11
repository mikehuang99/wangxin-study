package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程介绍信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseIntroduceBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 课程简介
     */
    private String introduce;
}
