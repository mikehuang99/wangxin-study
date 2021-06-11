package com.wangxin.langshu.business.edu.iservice.user.pccommon;

import java.util.List;

import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentVO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserLoginCodeBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserLoginPasswordBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserRegisterBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserSendCodeBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.UserUpdateBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.dto.UserLoginDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 用户基本信息
 *
 *  
 */

public interface IApiUserInfoBiz {

	
	public void save(List<UserRegisterBO> userRegisterBOs);
	
	public Result<UserLoginDTO> register(UserRegisterBO userRegisterBO) ;

	public Result<UserLoginDTO> loginPassword(UserLoginPasswordBO userLoginPasswordBO) ;

	public Result<UserLoginDTO> loginCode(UserLoginCodeBO userLoginCodeBO) ;

	public Result<String> sendCode(UserSendCodeBO userSendCodeBO);

	//private User register(String mobile, String password, String clientId) ;

	//private void loginLog(Long userNo, String clientId, LoginStatusEnum status, String ip);

	public Result<Integer> updatePassword(UserUpdateBO userUpdateBO);
	
	//管理员重置密码
	public Result<Integer> bossResetPassword(UserUpdateBO userUpdateBO);

	
	public StudentVO findStudentByMobile(String mobile);
}
