import http from './method.js'


// 查询我的学习卡
export const listUserStudyCard = (params) => {
  return http().post('/mobileApi/market/studyCard/list', params)
}

// 绑定学习卡用户之前检查接口
export const checkBeforebindStudyCardUserNo = (params) => {
  return http().post('/mobileApi/market/studyCard/checkBeforebindStudyCardUserNo', params)
}

// 绑定学习卡用户接口
export const bindStudyCardUserNo = (params) => {
  return http().post('/mobileApi/market/studyCard/bindStudyCardUserNo', params)
}

//查询我的优惠券(不分页)
export const listUserCoupon = (params) => {
  return http().post('/mobileApi/market/couponUser/list', params)
}

// 保存优惠券与用户的关系
export const saveCouponUser = (params={}) => {
  return http().post('/mobileApi/market/couponUser/save', params)
}

// 查询所有可以领取的优惠券
export const canCollectCouponsList = (params={}) => {
  return http().post('/mobileApi/market/coupon/list', params)
}

//积分历史操作记录列表查询
export const pointOperateRecordList = (params={}) => {
  return http().post('/mobileApi/market/pointOperateRecord/list', params)
}

//查询该用户的点券操作记录
export const dotOperateRecordList = (params={}) => {
  return http().post('/mobileApi/market/dotOperateRecord/list', params)
}

// 点券转换为多少积分
export const checkDotToPoint = (params={}) => {
  return http().post('/mobileApi/market/dot/checkDotToPoint', params)
}

// 点券转换为积分
export const dotToPoint = (params={}) => {
  return http().post('/mobileApi/market/dot/dotToPoint', params)
}

// 根据userNo查询点券
export const viewDotByUserNo = (params={}) => {
  return http().post('/mobileApi/market/dot/viewDotByUserNo', params)
}
