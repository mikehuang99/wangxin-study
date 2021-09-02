import http from './method.js'


// 根据学生编号查看考试管理
export const examManagementViewByStudentUserNo = (params={}) => {
  return http().post('/mobileApi/exam/management/viewByIdAndStudentUserNo', params)
}

//依靠学生userNo查找考试列表分页接口
export const queryExamManagementsByStudentUserNo = (params={}) => {
  return http().post('/mobileApi/exam/management/queryExamManagementsByStudentUserNo', params)
}

//根据考试管理的ID和试卷ID查看试卷接口
export const examPaperViewByExamManagementIdAndExamPaper = (params={}) => {
  return http().post('/mobileApi/exam/paper/viewByExamManagementIdAndExamPaperId', params)
}

//根据考试管理的ID和试卷ID查看试卷内容接口
export const viewExamPaperContentByExamManagementIdAndExamPaperId = (params={}) => {
  return http().post('/mobileApi/exam/paper/viewExamPaperContentByExamManagementIdAndExamPaperId', params)
}

//交试卷
export const handInExamPaper = (params={}) => {
  return http().post('/mobileApi/exam/management/handInExamPaper', params)
}

//保持会话,防止被登出
export const keepSession = (params={}) => {
  return http().post('/mobileApi/exam/management/keepSession', params)
}

//根据考试ID和试卷ID查看考试与用户关系(只能查学生本人)
export const viewExamManagementUserByExamManagementIdAndExamPaperId = (params={}) => {
  return http().post('/mobileApi/exam/management/viewExamManagementUserByExamManagementIdAndExamPaperId', params)
}

//根据examPaperId查看试卷内容接口(允许学生角色)
export const viewExamPaperContentByExamPaperIdAllowStudent = (params={}) => {
  return http().post('/mobileApi/exam/paper/viewExamPaperContentByExamPaperIdAllowStudent', params)
}
