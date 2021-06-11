/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.user.old.common.bo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class UserSendCodeBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8442003539823532528L;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * clientId
	 */
	private String clientId;

}
