import http from './method.js'

import 'video.js/dist/video-js.css'//直播相关引入
import 'videojs-contrib-hls'//直播相关引入

// 课程列表
/*
export const courseList = (params={}) => {
  return http().post('/pccommon/course/course/list', params)
}*/
// 课程列表
export const courseList = (params={}) => {
  return http().post('/pccommon/course/fresh/course/list', params)
}
// 课程分类
export const courseClass = (params={}) => {
  return http().post('/pccommon/course/course/subject/list', params)
}
// 课程详情(未登录)
export const courseDetail = (params={}) => {
  return http().post('/pccommon/course/course/view', params)
}
// 课程详情(登录后)
export const userCourseDetail = (params={}, token) => {
  return http(token).post('/pcweb/course/course/view', params)
}
// 获取播放sign
export const chapterSign = (params={}, token) => {
  return http(token).post('/pcweb/course/course/sign', params)
}

// 依靠课程ID课程用户关系列表分页接口
export const listByCourseId = (params={}) => {
  return http().post('/pccommon/course/courseUser/listByCourseId', params)
}
