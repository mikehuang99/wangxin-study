package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课时信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthLessonApplyViewBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private String id;

}
