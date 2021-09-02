import http from './method.js'


// 获取手机验证码
export const getMobileCode = (params) => {
  return http().post('/pccommon/user/user/send/code', params)
}

// 用户注册
export const register = (params={}) => {
  return http().post('/mobilecommon/register', params)
}
// 用户登录
export const userLogin = (params={}) => {
  return http().post('/mobilecommon/login/password', params)
}

// 修改密码
export const updatePassword = (params={}) => {
  return http().post('/mobilecommon/update/password', params)
}

// 查看用户
export const userView = (params={}) => {
  return http().post('/mobileApi/userView', params)
}

// 用户更新
export const userUpdate = (params={}) => {
  return http().post('/mobileApi/userUpdate', params)
}

//用户退出
export const userLogout = (params={}) => {
  return http().post('/mobileApi/userLogout', params)
}

// 用户学习记录保存接口
export const userStudyLogSave = (params={}) => {
  return http().post('/mobileApi/user/userStudyLog/save', params)
}

// 学生查询用户学习记录列表分页接口
export const listByStudentUserNo = (params={}) => {
  return http().post('/mobileApi/user/listByStudentUserNo', params)
}
