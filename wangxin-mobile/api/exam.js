import http from './method.js'


export const queryNotYetDoneExamQuestions = (params) => {
  return http().post('/mobileApi/exam/queryNotYetDoneExamQuestions', params)
}

export const queryExamQuestions = (params) => {
  return http().post('/mobileApi/exam/queryExamQuestions', params)
}

export const queryErrorExamQuestions = (params) => {
  return http().post('/mobileApi/exam/queryErrorExamQuestions', params)
}

export const cacheErrorExamQuestion = (params) => {
  return http().post('/mobileApi/exam/cacheErrorExamQuestion', params)
}

export const delErrorExamQuestion = (params) => {
  return http().post('/mobileApi/exam/delErrorExamQuestion', params)
}

export const queryHighErrorExamQuestions = (params) => {
  return http().post('/mobileApi/exam/queryHighErrorExamQuestions', params)
}

export const cacheHighErrorExamQuestion = (params) => {
  return http().post('/mobileApi/exam/cacheHighErrorExamQuestion', params)
}

export const delHighErrorExamQuestion = (params) => {
  return http().post('/mobileApi/exam/delHighErrorExamQuestion', params)
}

export const queryFavoritesExamQuestions = (params) => {
  return http().post('/mobileApi/exam/queryFavoritesExamQuestions', params)
}

export const cacheFavoritesExamQuestion = (params) => {
  return http().post('/mobileApi/exam/cacheFavoritesExamQuestion', params)
}

export const delFavoritesExamQuestion = (params) => {
  return http().post('/mobileApi/exam/delFavoritesExamQuestion', params)
}

export const queryNoteExamQuestions = (params) => {
  return http().post('/mobileApi/exam/queryNoteExamQuestions', params)
}

export const queryNoteContentByQuestionId = (params) => {
  return http().post('/mobileApi/exam/queryNoteContentByQuestionId', params)
}

export const cacheNoteExamQuestion = (params) => {
  return http().post('/mobileApi/exam/cacheNoteExamQuestion', params)
}

export const delNoteExamQuestion = (params) => {
  return http().post('/mobileApi/exam/delNoteExamQuestion', params)
}

export const cacheDoExercisesData = (params) => {
  return http().post('/mobileApi/exam/cacheDoExercisesData', params)
}

export const deleteDoExercisesData = (params) => {
  return http().post('/mobileApi/exam/deleteDoExercisesData', params)
}

export const findDoExercisesDataByCcpId = (params) => {
  return http().post('/mobileApi/exam/findDoExercisesDataByCcpId', params)
}


