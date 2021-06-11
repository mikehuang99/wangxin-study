package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthChapterApplyBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = true)
    private String courseId;
}
