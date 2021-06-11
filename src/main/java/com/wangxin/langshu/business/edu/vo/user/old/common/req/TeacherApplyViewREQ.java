package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息-审核查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplyViewREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8271366598467013460L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private String id;

}
