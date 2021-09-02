import http from './method.js'
// 首页推荐课程
export const topicCourse = (params={}) => {
  return http().post('/pccommon/course/topic/course/list', params)
}
// 首页轮播图
export const advertList = (params={}) => {
  return http().post('/pccommon/course/advert/list', params)
}
// 关于我们列表
export const aboutList = (params={}) => {
  return http().post('/pccommon/system/website/navigation/list', params)
}
// 关于我们信息
export const aboutInfo = (params={}) => {
  return http().post('/pccommon/system/website/navigation/article/get', params)
}
// 客服信息
export const serviceInfo = (params={}) => {
  return http().post('/pccommon/system/fresh/website/get', params)
}

// 友情链接
export const friendLink = (params={}) => {
  return http().post('/pccommon/system/website/link', params)
}
// 导航条
export const getNavigation = (params={}) => {
  return http().post('/pccommon/system/fresh/navigation/bar/list', params)
}
// 轮播图分类
export const indexClass = (params={}) => {
  return http().post('/pccommon/course/course/subject/list', params)
}

//新闻列表
export const newsList = (params={}) => {
  return http().post('/pccommon/system/news/list', params)
}

//新闻查看
export const newsView = (params={}) => {
  return http().post('/pccommon/system/news/view', params)
}

//新闻列表
export const newsTypeList = (params={}) => {
  return http().post('/pccommon/system/newsType/list', params)
}

// 底部二维码
export const bottomQrCodeList = (params={}) => {
  return http().post('/pccommon/system/bottom/qr/code/list', params)
}




