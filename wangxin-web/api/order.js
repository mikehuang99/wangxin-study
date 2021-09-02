import http from './method.js'

// 创建订单
export const orderMainSave = (params={}) => {
  return http().post('/pcweb/order/ordermain/save', params)
}
// 订单信息
export const orderInfo = (params={}) => {
  return http().post('/pcweb/course/order/info/view', params)
}
// 检查订单支付结果
export const checkPayResult = (params={}) => {
  return http().post('/pcweb/order/ordermain/checkPayResult', params)
}
