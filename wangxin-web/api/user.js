import http from './method'
// 获取手机验证码
export const getMobileCode = (params) => {
  return http().post('/pccommon/user/user/send/code', params)
}
// 用户注册
export const register = (params={}) => {
  return http().post('/pccommon/user/user/register', params)
}
// 用户登录
export const userLogin = (params={}) => {
  return http().post('/pccommon/user/user/login/password', params)
}
// 用户注销
export const userLoginOut = (params={}) => {
  return http().post('/pcweb/user/user/ext/logout', params)
}
// 用户信息
export const getUserInfo = (params={}) => {
  return http().post('/pcweb/user/user/ext/view', params)
}
// 课程详情
export const userCourseDetail = (params={}) => {
  return http().post('/pcweb/course/course/apply/view', params)
}
// 申请教师
export const teacherEnter = (params={}) => {
  return http().post('/pcweb/user/teacher/audit/save', params)
}

// 用户学习记录保存接口
export const userStudyLogSave = (params={}) => {
  return http().post('/pcweb/user/userStudyLog/save', params)
}

// 用户学习记录列表分页接口
export const userStudyLogListByStudentUserNo = (params={}) => {
  return http().post('/pcweb/user/userStudyLog/listByStudentUserNo', params)
}


// 学习证明查看接口
export const viewStudyProof = (params={}) => {
  return http().post('/pccommon/user/studyProof/view', params)
}
