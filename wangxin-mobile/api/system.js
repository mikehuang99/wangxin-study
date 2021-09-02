import http from './method.js'



// 获取系统配置信息接口
export const viewSystemConfig = (params) => {
  return http().post('/mobileApi/system/view', params)
}


