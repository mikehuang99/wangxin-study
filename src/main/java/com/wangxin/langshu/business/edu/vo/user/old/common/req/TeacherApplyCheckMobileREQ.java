package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 教师信息-审核-校验手机号是否是否存在
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplyCheckMobileREQ implements Serializable {

  
    /**
	 * 
	 */
	private static final long serialVersionUID = -5936905971903863091L;
	/**
     * 教师手机
     */
    @ApiModelProperty(value = "教师手机", required = true)
    private String teacherMobile;

}
