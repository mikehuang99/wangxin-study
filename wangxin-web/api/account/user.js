import http from '../method.js'

// 修改用户信息
export const updateUserEducationInf = (params={}) => {
  return http().post('/pcweb/user/user/ext/update', params)
}
// 获取教师信息
export const getTeacherInfo = (params={}) => {
  return http().post('/pcweb/user/teacher/view', params)
}
// 修改教师信息
export const updataTeacherInfo = (params={}) => {
  return http().post('/pcweb/user/teacher/audit/update', params)
}
// 修改密码
export const updatePassword = (params={}) => {
  return http().post('/pccommon/user/user/update/password', params)
}
// 银行卡信息
export const cardInfo = (params={}) => {
  return http().post('/pcweb/user/teacher/ext/view', params)
}
// 教师订单收益
export const teacherOrderList = (params={}) => {
  return http().post('/pcweb/course/order/info/teacher', params)
}
// 教师提现记录
export const teacherCashList = (params={}) => {
  return http().post('/pcweb/user/teacher/profit/list', params)
}
// 绑定银行卡
export const bindCard = (params={}) => {
  return http().post('/pcweb/user/teacher/ext/update', params)
}
// 学习记录
export const studyList = (params={}) => {
  return http().post('/pcweb/course/course/user/study/log/list', params)
}
// 查询所有可以领取的优惠券
export const canCollectCouponsList = (params={}) => {
  return http().post('/pcweb/market/coupon/list', params)
}
// 保存优惠券与用户的关系
export const saveCouponUser = (params={}) => {
  return http().post('/pcweb/market/couponUser/save', params)
}
// 优惠券用户关系列表
export const couponUserList = (params={}) => {
  return http().post('/pcweb/market/couponUser/list', params)
}

// 查询该用户的学习卡列表
export const studyCardList = (params={}) => {
  return http().post('/pcweb/market/studyCard/list', params)
}

// 绑定学习卡用户接口
export const bindStudyCardUserNo = (params={}) => {
  return http().post('/pcweb/market/studyCard/bindStudyCardUserNo', params)
}

// 绑定学习卡用户之前检查接口
export const checkBeforebindStudyCardUserNo = (params={}) => {
  return http().post('/pcweb/market/studyCard/checkBeforebindStudyCardUserNo', params)
}

// 点券转换为多少积分
export const checkDotToPoint = (params={}) => {
  return http().post('/pcweb/market/dot/checkDotToPoint', params)
}

// 点券转换为积分
export const dotToPoint = (params={}) => {
  return http().post('/pcweb/market/dot/dotToPoint', params)
}
// 点券历史操作记录列表查询
export const dotOperateRecordList = (params={}) => {
  return http().post('/pcweb/market/dotOperateRecord/list', params)
}
// 根据userNo查询点券
export const viewDotByUserNo = (params={}) => {
  return http().post('/pcweb/market/dot/viewDotByUserNo', params)
}

// 查看系统配置表
export const viewDotConfig = (params={}) => {
  return http().post('/pcweb/market/dotConfig/viewDotConfig', params)
}
// 根据userNo查询积分
export const viewPointByUserNo = (params={}) => {
    return http().post('/pcweb/market/point/viewPointByUserNo', params)
}
// 积分历史操作记录列表查询
export const pointOperateRecordList = (params={}) => {
  return http().post('/pcweb/market/pointOperateRecord/list', params)
}

// 获取系统配置
export const systemConfig = (params={}) => {
  return http().post('/pcweb/system/system/config/view', params)
}

// 教师查询用户学习记录列表分页接口
export const userStudyLogListByTeacherUserNo = (params={}) => {
  return http().post('/pcweb/user/userStudyLog/listByTeacherUserNo', params)
}

// 用于查询课程学习记录而查询的课程列表分页接口
export const queryFreshCoursesOnUserStudyLog = (params={}) => {
  return http().post('/pcweb/user/userStudyLog/queryFreshCoursesOnUserStudyLog', params)
}

// 学习证明添加接口
export const saveStudyProof = (params={}) => {
  return http().post('/pcweb/user/studyProof/save', params)
}
