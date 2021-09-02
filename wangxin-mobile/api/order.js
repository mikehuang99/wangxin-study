import http from './method.js'

// 查看订单列表
export const listOrder = (params) => {
  return http().post('/mobileApi/order/list', params)
}

// 下订单
export const saveOrder = (params) => {
  return http().post('/mobileApi/order/save', params)
}

// 订单检查支付结果接口
export const checkPayResult = (params) => {
  return http().post('/mobileApi/order/checkPayResult', params)
}

// 取消订单
export const cancelOrder = (params) => {
  return http().post('/mobileApi/order/cancelOrder', params)
}


// 继续支付
export const continuePay = (params) => {
  return http().post('/mobileApi/order/continuePay', params)
}

