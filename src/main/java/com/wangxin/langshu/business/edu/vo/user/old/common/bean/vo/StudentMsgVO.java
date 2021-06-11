/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息,批量生成用户站内信用
 *
 */
@Data
@Accessors(chain = true)
public class StudentMsgVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	private String userNo;
	
	/**
	 * 用户手机
	 */
	private String mobile;
}
