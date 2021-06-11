package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoForChartsBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师用户编号
     */
    @ApiModelProperty(value = "教师用户编号", required = true)
    private String teacherUserNo;
    /**
	 * 开始时间
	 */
    @ApiModelProperty(value = "开始时间", required = false)
	private String beginCreate;
	/**
	 * 结束时间
	 */
    @ApiModelProperty(value = "结束时间", required = false)
	private String endCreate;
}
