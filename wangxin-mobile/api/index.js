import http from './method.js'


export const findAdverts = (params={}) => {
  return http().post('/mobilecommon/findAdverts', params)
}

export const findNewsList = (params={}) => {
  return http().post('/mobilecommon/findNewsList', params)
}

export const findNewsById = (params={}) => {
  return http().post('/mobilecommon/findNewsById', params)
}
export const findIndexMiddleNavs = (params={}) => {
  return http().post('/mobilecommon/findIndexMiddleNavs', params)
}

export const findTopics = (params={}) => {
  return http().post('/mobilecommon/findTopics', params)
}

export const findSubjects = (params={}) => {
  return http().post('/mobilecommon/findSubjects', params)
}

export const findCourselist = (params={}) => {
  return http().post('/mobilecommon/findCourselist', params)
}

export const findCourseDetailNotLogin = (params={}) => {
  return http().post('/mobilecommon/findCourseDetailNotLogin', params)
}

export const findCourseDetailHadLogin = (params={}) => {
  return http().post('/mobileApi/course/findCourseDetailHadLogin', params)
}

export const findWebsite = (params={}) => {
  return http().post('/mobilecommon/auth/findWebsite', params)
}

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
