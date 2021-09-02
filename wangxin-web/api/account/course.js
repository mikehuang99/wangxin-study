import http from '../method.js'

import httpBlob from '../methodBlob.js'
// 课程审核列表
/*
export const teacherCourseList = (params={}) => {
  return http().post('/pcweb/course/course/apply/list', params)
}*/
// 课程审核列表
export const teacherFreshCourseList = (params={}) => {
  return http().post('/pcweb/course/fresh/course/apply/list', params)
}
// 课程审核保存
export const teacherFreshCourseSave = (params={}) => {
  return http().post('/pcweb/course/fresh/course/apply/save', params)
}
// 课程审核修改
export const teacherFreshCourseUpdate = (params={}) => {
  return http().post('/pcweb/course/fresh/course/apply/update', params)
}

// 课程审核上下架修改
export const teacherFreshCourseUpdateCoursePutaway = (params={}) => {
  return http().post('/pcweb/course/fresh/course/apply/updateCoursePutaway', params)
}
// 课程审核删除
export const teacherFreshCourseDelete = (params={}) => {
  return http().post('/pcweb/course/fresh/course/apply/delete', params)
}
// 课程审核查看
export const teacherFreshCourseView = (params={}) => {
  return http().post('/pcweb/course/fresh/course/apply/view', params)
}

// 章节审核列表
export const teacherFreshChapterList = (params={}) => {
  return http().post('/pcweb/course/fresh/chapter/apply/list', params)
}
// 章节审核保存
export const teacherFreshChapterSave = (params={}) => {
  return http().post('/pcweb/course/fresh/chapter/apply/save', params)
}
// 章节审核修改
export const teacherFreshChapterUpdate = (params={}) => {
  return http().post('/pcweb/course/fresh/chapter/apply/update', params)
}
// 章节审核删除
export const teacherFreshChapterDelete = (params={}) => {
  return http().post('/pcweb/course/fresh/chapter/apply/delete', params)
}
// 章节审核查看
export const teacherFreshChapterView = (params={}) => {
  return http().post('/pcweb/course/fresh/chapter/apply/view', params)
}
// 章节审核排序
export const teacherFreshChapterSort = (params={}) => {
  return http().post('/pcweb/course/fresh/chapter/apply/sort', params)
}


// 课时审核列表
export const teacherFreshLessonList = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/list', params)
}
// 课时审核保存
export const teacherFreshLessonSave = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/save', params)
}
// 课时审核修改
export const teacherFreshLessonUpdate = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/update', params)
}
// 课时审核直播修改
export const updateLessonApplyLiveBroadcast = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/updateLessonApplyLiveBroadcast', params)
}
// 课时信息审核更新课时类型接口
export const teacherFreshLessonUpdateLessonApplyLessonType = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/updateLessonApplyLessonType', params)
}
// 课时审核删除
export const teacherFreshLessonDelete = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/delete', params)
}
// 课时审核查看
export const teacherFreshLessonView = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/view', params)
}
// 课程视频排序
export const teacherFreshCourseVideoSort = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/apply/sort', params)
}

// 根据课时Id查询课程视频
export const queryCourseVideoByLessonId = (params={}) => {
  return http().post('/pcweb/course/fresh/course/video/queryCourseVideoByLessonId', params)
}
// 课程视频列表
export const teacherFreshCourseVideoList = (params={}) => {
  return http().post('/pcweb/course/fresh/course/video/list', params)
}
// 课程视频保存
export const teacherFreshCourseVideoSave = (params={}) => {
  return http().post('/pcweb/course/fresh/course/video/save', params)
}
// 课程视频修改
export const teacherFreshCourseVideoUpdate = (params={}) => {
  return http().post('/pcweb/course/fresh/course/video/update', params)
}
// 课程视频删除
export const teacherFreshCourseVideoDelete = (params={}) => {
  return http().post('/pcweb/course/fresh/course/video/delete', params)
}
// 课程视频查看
export const teacherFreshCourseVideoView = (params={}) => {
  return http().post('/pcweb/course/fresh/course/video/view', params)
}


/*
// 课程上下架
export const coursePutaway = (params={}) => {
  return http().post('/pcweb/course/course/apply/stand', params)
}*/
// 删除课程
export const deleteCourse = (params={}) => {
  return http().post('/pcweb/course/course/apply/delete', params)
}
// 订单列表
export const orderList = (params={}) => {
  return http().post('/pcweb/order/ordermain/list', params)
}
// 取消订单
export const cancelOrder = (params={}) => {
  return http().post('/pcweb/order/ordermain/cancelOrder', params)
}
// 订单继续支付
export const continuePay = (params={}) => {
  return http().post('/pcweb/order/ordermain/continuePay', params)
}
// 订单详情
export const orderInfo = (params={}) => {
  return http().post('/pcweb/course/order/info/view', params)
}
// 课程分类
export const infiniteCate = (params={}) => {
  return http().post('/pccommon/course/course/subject/list', params)
}
/*
// 课程添加
export const saveCourseInfo = (params={}) => {
  return http().post('/pcweb/course/course/apply/save', params)
}*/
// 课程详情
export const courseDetail = (params={}) => {
  return http().post('/pcweb/course/course/apply/view', params)
}
// 更新课程信息
export const courseUpdate = (params={}) => {
  return http().post('/pcweb/course/course/apply/update', params)
}
/*
// 课程章节列表
export const chapterList = (params={}) => {
  return http().post('/pcweb/course/course/chapter/audit/list', params)
}*/
/*
// 添加课程章节
export const saveChapter = (params={}) => {
  return http().post('/pcweb/course/course/chapter/audit/save', params)
}
// 修改课程章节
export const updateChapter = (params={}) => {
  return http().post('/pcweb/course/course/chapter/audit/update', params)
}*/
// 删除课程章节
export const deleteChapter = (params={}) => {
  return http().post('/pcweb/course/course/chapter/audit/delete', params)
}
// 课程章节排序
export const chapterSort = (params={}) => {
  return http().post('/pcweb/course/course/chapter/audit/sort', params)
}
// 章节课时列表
export const chapterPraxisList = (params={}) => {
  return http().post('/pcweb/course/course/chapter/lesson/audit/list', params)
}
// 章节课时添加
export const savePraxis = (params={}) => {
  return http().post('/pcweb/course/course/chapter/lesson/audit/save', params)
}
// 章节课时更新
export const updatePraxis = (params={}) => {
  return http().post('/pcweb/course/course/chapter/lesson/audit/update', params)
}
// 章节课时删除
export const deletePraxis = (params={}) => {
  return http().post('/pcweb/course/course/chapter/lesson/audit/delete', params)
}
// 章节课时删除
export const updatePraxisSort = (params={}) => {
  return http().post('/pcweb/course/course/chapter/lesson/audit/sort', params)
}
// 章节视频库
export const chapterVideo = (params={}) => {
  return http().post('/pcweb/course/course/video/list/chapter', params)
}
// 章节视频添加
export const chapterVideoSave = (params={}) => {
  return http().post('/pcweb/course/course/video/save', params)
}
// 章节视频删除
export const chapterVideodel = (params={}) => {
  return http().post('/pcweb/course/course/video/delete', params)
}
// 课时视频
export const lessonVideo = (params={}) => {
  return http().post('/pcweb/course/course/video/list/lesson', params)
}
// 课时视频更新
export const lessonVideoUpdate = (params={}) => {
  return http().post('/pcweb/course/course/video/update', params)
}
// 试题增加
export const examQuestionSave = (params={}) => {
  return http().post('/pcweb/exam/question/save', params)
}
// 试题更新
export const examQuestionUpdate = (params={}) => {
  return http().post('/pcweb/exam/question/update', params)
}
// 试题列表
export const examQuestionList = (params={}) => {
  return http().post('/pcweb/exam/question/list', params)
}
// 试题查看
export const examQuestionView = (params={}) => {
  return http().post('/pcweb/exam/question/view', params)
}
// 试题删除
export const examQuestionDelete = (params={}) => {
  return http().post('/pcweb/exam/question/delete', params)
}
// 试题导入
export const examQuestionImport = (params={}) => {
  return http().post('/pcweb/exam/question/import', params)
}
// 试题导入模版
export const examQuestionImportTemplate= (params={}) => {
  return httpBlob().post('/pcweb/exam/question/importTemplate', params)
}

// 导出单选试题
export const exportExamQuestion= (params={}) => {
  return httpBlob().post('/pcweb/exam/question/exportExamQuestion', params)
}


// 课程Ccp分类(课程-章节-课时)查询
export const infiniteCcpCate = (params={}) => {
  return http().post('/pcweb/course/ccp/audit/list', params)
}

// 课程Ccp分类(课程-章节-课时)查询(未审核的)
export const infiniteAuditCcpCate = (params={}) => {
  return http().post('/pcweb/course/ccp/audit/auditlist', params)
}

// 试题课程绑定增加
export const examQuestionCourseSave = (params={}) => {
  return http().post('/pcweb/exam/question/course/save', params)
}
// 试题课程绑定更新
export const examQuestionCourseUpdate = (params={}) => {
  return http().post('/pcweb/exam/question/course/update', params)
}
// 试题课程绑定列表
export const examQuestionCourseList = (params={}) => {
  return http().post('/pcweb/exam/question/course/list', params)
}
// 试题课程绑定查看
export const examQuestionCourseView = (params={}) => {
  return http().post('/pcweb/exam/question/course/view', params)
}
// 试题课程绑定删除
export const examQuestionCourseDelete = (params={}) => {
  return http().post('/pcweb/exam/question/course/delete', params)
}

// 试卷增加
export const examPaperSave = (params={}) => {
  return http().post('/pcweb/exam/paper/save', params)
}
// 试卷更新
export const examPaperUpdate = (params={}) => {
  return http().post('/pcweb/exam/paper/update', params)
}
// 试卷列表
export const examPaperList = (params={}) => {
  return http().post('/pcweb/exam/paper/list', params)
}
// 试卷查看
export const examPaperView = (params={}) => {
  return http().post('/pcweb/exam/paper/view', params)
}

//根据考试管理的ID和试卷ID查看试卷接口
export const examPaperViewByExamManagementIdAndExamPaper = (params={}) => {
  return http().post('/pcweb/exam/paper/viewByExamManagementIdAndExamPaperId', params)
}

//根据考试管理的ID和试卷ID查看试卷内容接口
export const viewExamPaperContentByExamManagementIdAndExamPaperId = (params={}) => {
  return http().post('/pcweb/exam/paper/viewExamPaperContentByExamManagementIdAndExamPaperId', params)
}

//根据examPaperId查看试卷内容接口(允许教师角色)
export const viewExamPaperContentByExamPaperIdAllowTeacher = (params={}) => {
  return http().post('/pcweb/exam/paper/content/viewExamPaperContentByExamPaperIdAllowTeacher', params)
}

//根据examPaperId查看试卷内容接口(允许学生角色)
export const viewExamPaperContentByExamPaperIdAllowStudent = (params={}) => {
  return http().post('/pcweb/exam/paper/content/viewExamPaperContentByExamPaperIdAllowStudent', params)
}

// 试卷删除
export const examPaperDelete = (params={}) => {
  return http().post('/pcweb/exam/paper/delete', params)
}
// 生成试卷
export const generalExamPaper = (params={}) => {
  return http().post('/pcweb/exam/paper/generalExamPaper', params)
}
//获取章节对应的题目数量列表
export const findDetailByChapterList = (params={}) => {
  return http().post('/pcweb/exam/paper/findDetailByChapterList', params)
}

// 考试管理增加
export const examManagementSave = (params={}) => {
  return http().post('/pcweb/exam/management/save', params)
}
// 考试管理更新
export const examManagementUpdate = (params={}) => {
  return http().post('/pcweb/exam/management/update', params)
}
// 考试管理列表
export const examManagementList = (params={}) => {
  return http().post('/pcweb/exam/management/list', params)
}
// 考试管理查看
export const examManagementView = (params={}) => {
  return http().post('/pcweb/exam/management/view', params)
}
// 考试管理删除
export const examManagementDelete = (params={}) => {
  return http().post('/pcweb/exam/management/delete', params)
}
// 根据学生编号查看考试管理
export const examManagementViewByStudentUserNo = (params={}) => {
  return http().post('/pcweb/exam/management/viewByIdAndStudentUserNo', params)
}

// 根据考试ID查询所有考试对象
export const queryExamObjectsExamManagementId = (params={}) => {
  return http().post('/pcweb/exam/management/queryExamObjectsExamManagementId', params)
}

//查看考试与用户关系
export const viewExamManagementUserByMPU = (params={}) => {
  return http().post('/pcweb/exam/management/user/viewExamManagementUserByMPU', params)
}

//根据考试ID和试卷ID查看考试与用户关系(只能查学生本人)
export const viewExamManagementUserByExamManagementIdAndExamPaperId = (params={}) => {
  return http().post('/pcweb/exam/management/user/viewExamManagementUserByExamManagementIdAndExamPaperId', params)
}

//评卷
export const markExamPaperByTeacher = (params={}) => {
  return http().post('/pcweb/exam/management/user/markExamPaperByTeacher', params)
}

// 学员分组列表
export const studentGroupList = (params={}) => {
  return http().post('/pcweb/user/user/group/list', params)
}

// 用户课程列表
export const courseUserList = (params={}) => {
  return http().post('/pcweb/course/courseUser/list', params)
}

// 依据课时ID查询课程文档
export const queryCourseDocUrlByLessonId = (params={}) => {
  return http().post('/pcweb/course/fresh/lesson/queryCourseDocUrlByLessonId', params)
}

//依靠学生userNo查找考试列表分页接口
export const queryExamManagementsByStudentUserNo = (params={}) => {
  return http().post('/pcweb/exam/management/queryExamManagementsByStudentUserNo', params)
}

//交试卷
export const handInExamPaper = (params={}) => {
  return http().post('/pcweb/exam/management/handInExamPaper', params)
}

//保持会话,防止被登出
export const keepSession = (params={}) => {
  return http().post('/pcweb/exam/management/keepSession', params)
}

// 教师查询课程用户关系列表分页接口
export const courseUserListByTeacherUserNo = (params={}) => {
  return http().post('/pcweb/course/courseUser/listByTeacherUserNo', params)
}
