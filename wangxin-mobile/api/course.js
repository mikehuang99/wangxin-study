import http from './method.js'

import 'video.js/dist/video-js.css'//直播相关引入
import 'videojs-contrib-hls'//直播相关引入


// 查询我的课程
export const listMyCourse = (params) => {
  return http().post('/mobileApi/course/listMyCourse', params)
}

// 根据课时Id查询课程视频
export const queryCourseVideoByLessonId = (params={}) => {
  return http().post('/mobileApi/course/queryCourseVideoByLessonId', params)
}
