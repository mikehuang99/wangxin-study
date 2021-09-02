<template>
  <div>
    <div>
    <ul v-load-more="loaderMore" v-if="orderArr.length">
      <div tag='li' :data-id="items.id" :key="index" v-for="(items, index) in orderArr">
        <div class="course_child_list n">
          <div class="course_child_con" :id="items.id | conFilter">
            <div class="course_content_box">
              <ul class="con_right_area on" style="width:100%;padding-left:6px;">
                <li class="course_title_h1">
                  <h1>
                    <i><span style="font-weight:bold;font-size: 18px">[{{index+1}}].考试标题:</span>{{items.title}}</i>
									</h1>
                </li>
                <div>
                  <li class="course_price">
                    <div>
                      <span style="font-weight:bold" v-if="items.examType==1">
                        考试类型:定时考试
                      </span>
                      <span style="font-weight:bold" v-if="items.examType==2">
                        考试类型:时段考试
                      </span>
                    </div>
                  </li>
                  <li class="course_price">
                    <div>
                      <span style="font-weight:bold">开始时间:{{items.startTime}}</span>
                    </div>
                  </li>
                  <li class="course_price">
                    <div>
                      <span style="font-weight:bold">结束时间:{{items.endTime}}</span>
                    </div>
                  </li>
                  <li class="course_price">
                    <div>
                      <span style="font-weight:bold"  v-if="items.isFinishUserTotalScore==0">状态：未考试或者未完成评分</span>
                      <span style="font-weight:bold"  v-if="items.isFinishUserTotalScore==1">状态：已经完成评分</span>
                      <span style="font-weight:bold"  v-if="items.isFinishUserTotalScore==2">状态：等待教师评分</span>
                    </div>
                  </li>
                  <li class="course_price" v-if="items.isFinishUserTotalScore!=0">
                    <div>
                      <span style="font-weight:bold">总得分:{{items.userTotalScore}}分</span>
                    </div>
                  </li>
                  <li class="course_price">
                    <div>
                      <span style="font-weight:bold">课程名称:{{items.courseName}}</span>
                    </div>
                  </li>
                </div>
                <div class="btn_box" @click="takeAnExamination(items)">
                  进入考试
                </div>
                <div  class="continue_btn_box" @click="reviewExamination(items)">
                  考试回顾
                </div>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </ul>
		<no-content :content="noContent" v-show="this.orderArr.length===0"></no-content>
		<p class="no_data" v-show="this.touchend">没有更多考试</p>
		<transition name="loading">
			<loading v-show="showLoading"></loading>
		</transition>
    </div>
    <div style="height: 70px">
      <Footer></Footer>
    </div>

    <div v-show="openExamDialogFlag" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999998; filter: alpha(opacity=60); opacity: 0.5 !important;">
      <!--该DIV为遮罩层-->
    </div>
    <div style="position:fixed; *position:absolute; width:280px; height:160px; top:50%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 999999;border-radius:5px;"
         v-show="openExamDialogFlag">
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">提示</span>
      </div>
      <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">距离考试还有{{openExamTime}}</span>
      </div>
      <div @click="closeOpenExamDialogFlag()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;left: 20px;width: 120px;height: 30px;border: 1px #333333;background-color: greenyellow;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >取消</span>
      </div>
      <div @click="handInExamPaper()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;right: 20px;width: 60px;height: 30px;border: 1px #333333;background-color: red;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >确定</span>
      </div>
    </div>

    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" :toUrlParams="toUrlParams" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
	</div>
</template>
<script>
import loading from '~/components/common/loading.vue'
import noContent from '~/components/common/no_content/no_content.vue'
import {loadMore} from '~/plugins/loadMorePlugin.js'
import {listOrder,checkPayResult,cancelOrder,continuePay} from '~/api/order'
import {queryExamManagementsByStudentUserNo,examManagementViewByStudentUserNo} from '~/api/examManagement'
import { logout } from '~/store/common'
import Footer from '~/components/myExamManagement/Footer.vue'
import Alert from "~/components/common/alert";
import {setStore,getStore} from '~/store/common.js'


import {viewExamPaperContentByExamManagementIdAndExamPaperId,viewExamPaperContentByExamPaperIdAllowStudent,viewExamManagementUserByExamManagementIdAndExamPaperId} from '~/api/examManagement'



export default {
    data () {
    return {
        isShowAlert: false,
        timeOutFlag: false,
        toUrl: '',
        alertTitle: '',
        alertContent: '',
        toUrlParams: [],

        touchend: false,
        orderArr: [],
        pageOnShowing: false,//页面是否正在显示
      // 请求条数
      offset: 0,
      // 是否可以请求
      preventRepeatreuqest: false,
      // 模糊查询的当前请求条数 0
      likeOffset: 0,
      // 是否显示加载动画
      showLoading: false,
      // 无内容是否显示
      isDisplayNo: false,
      // 无内容样式
      noContent: {
        class: 'class',
        //name: '没有找到课程'
        name: '什么也没有'
      },
      obj: {
          title: ''
      },
      page: {
          pageCurrent: 0,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
      },
        getExamPaperContentViewTimeOutVar: '',
        openExamTime: '',
        openExamDialogFlag: false,
        openExamTimeInterval: '',

        examQuestionsList:[],
        examPaperAnswerResultGloble: ''

    }
  },
  mounted () {
      //进入页面钩子函数代码开始
      this.$nextTick(()=>{
          this.pageOnShowing = true;//进入页面设为true
      });
      this.initData()
  },
  //离开当前页面后执行
  destroyed: function () {
      this.pageOnShowing = false;//离开页面设为false

  },
  props: ['title'],
  mixins: [loadMore],
  computed: {

  },
  components: {
    loading,
    noContent,
      Footer,
      Alert
  },
    filters:{
        conFilter(item) {
            return "con_"+item;
        }


    },
  methods: {
      closeAlert(){
          this.isShowAlert = false;
      },
      reloadOrderList(title) {
          this.title = title;
      },
      toDetailPage(id){
          this.$router.push({path: '/courseDetail/index', query: {id: id}});
      },
    initData () {
      this.offset = 0
      this.queryExamManagementsByStudentUserNo();
    },
      closeOpenExamDialogFlag(){
          clearInterval(this.openExamTimeInterval);
          clearTimeout(this.getExamPaperContentViewTimeOutVar);

          this.openExamDialogFlag = false;
      },
      openExamTimeDown (item) {
          this.openExamDialogFlag = true;
          const endTime = new Date(Date.parse(item.startTime))
          const nowTime = new Date();
          let leftTime = parseInt((endTime.getTime()-nowTime.getTime())/1000)
          let d = parseInt(leftTime/(24*60*60))
          let h = this.openExamformate(parseInt(leftTime/(60*60)%24))
          let m = this.openExamformate(parseInt(leftTime/60%60))
          let s = this.openExamformate(parseInt(leftTime%60))
          if(leftTime <= 0){
              //alert("倒计时结束");
            this.openExamTime = "0天0小时0分0秒,正在加载试题，即将进入考试...";
            clearInterval(this.openExamTimeInterval);
            setTimeout(() => {
                 this.takeAnExamination(item);
            }, 1000)
            return;
          }
          this.openExamTime = `${d}天${h}小时${m}分${s}秒`
      },
      openExamformate (openExamTime) {
          if(openExamTime>=10){
              return openExamTime
          }else{
              return `0${openExamTime}`
          }
      },
      reviewExamination(item){
          this.viewExamPaperContentByExamPaperIdAllowStudent(item);
          //this.viewExamManagementUserByExamManagementIdAndExamPaperId(item);
      },
      takeAnExamination(item){
          //时间戳比较
          let startTime=Date.parse(item.startTime);
          let endTime=Date.parse(item.endTime);
          let nowTime = new Date().getTime();
          if(startTime>nowTime){
              //this.openExamTimeDown(item);
              this.openExamTimeInterval = setInterval(()=>{
                  this.openExamTimeDown(item)
              },500);
              return;
          }
          if(endTime<nowTime){
              this.isShowAlert = true;
              this.alertTitle = "提示";
              this.alertContent = "该考试已经结束";
              return;
          }
          this.examManagementViewByStudentUserNo(item);
      },
      viewExamPaperContentByExamManagementIdAndExamPaperId (examManagementId,examPaperId) {
          viewExamPaperContentByExamManagementIdAndExamPaperId({
              "id":examManagementId,"examPaperId":examPaperId
          }).then(res => {
              res = res.data;
              if (res.code === 200) {
                  clearTimeout(this.getExamPaperContentViewTimeOutVar);

                  let examQuestionVos = res.data.examQuestionVos || [];
                  if(examQuestionVos.length==0){
                      this.isShowAlert = true;
                      this.alertTitle = "提示";
                      this.alertContent = "没有试题";
                      return;
                  }
                  setStore("examQuestions_"+examManagementId,res.data.examQuestionVos);
                  setTimeout(() => {
                      this.$router.push({path: '/examManagement/emAnswerQuestion', query: {examManagementId: examManagementId,examPaperId:examPaperId,ccpId:examManagementId}});
                  }, 200);
              } else if (res.code > 300 && res.code < 400) {
                  clearTimeout(this.getExamPaperContentViewTimeOutVar);
                  if(res.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              }else{
                  console.log("系统错误");
                  //这里开始获取试卷。。。。。。。。
                  let that = this;
                  let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
                  this.getExamPaperContentViewTimeOutVar = setTimeout(function(){ that.viewExamPaperContentByExamManagementIdAndExamPaperId(examManagementId,examPaperId)}, timeOut);
              }
          }).catch(() => {
              //这里开始获取试卷。。。。。。。。
              let that = this;
              let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
              this.getExamPaperContentViewTimeOutVar = setTimeout(function(){ that.viewExamPaperContentByExamManagementIdAndExamPaperId(examManagementId,examPaperId)}, timeOut);
          })
      },
      queryExamManagementsByStudentUserNo(offset) {

          if(offset==0){
              this.page.pageCurrent=0;
              this.page.pageSize = 20;
              this.page.totalCount = 0;
              this.page.totalPage = 0;

              this.orderArr = [];
          }
          if(this.page.pageCurrent!=0&&(this.page.pageCurrent+1)>this.page.totalPage){
              this.touchend = true;
              return;
          }
          this.showLoading = true;
          queryExamManagementsByStudentUserNo({'title':this.obj.title,
              'pageCurrent':this.page.pageCurrent+1,'pageSize':this.page.pageSize}).then(res => {
              if (res.data.code === 200) {
                  this.showLoading = false;
                  let list = res.data.data.list || [];
                  this.orderArr = this.orderArr.concat(list);
                  this.page.pageCurrent= res.data.data.pageCurrent;
                  this.page.pageSize = res.data.data.pageSize;
                  this.page.totalCount = res.data.data.totalCount;
                  this.page.totalPage = res.data.data.totalPage;
                  //alert(list.length)
              }else if (res.data.code > 300 && res.data.code < 400) {
                  if(res.data.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              } else {
                  console.log('88999！')
              }
          }).catch(() => {
              console.log("请求异常");
              this.showLoading = false;
          });
    },
      viewExamPaperContentByExamPaperIdAllowStudent(item){
          let that = this;
          viewExamPaperContentByExamPaperIdAllowStudent({examPaperId: item.examPaperId}).then(res => {
              if (res.data.code == 200) {
                  let examPaper = res.data.data;
                  this.examQuestionsList = examPaper.examQuestionVos||[];


                  if(this.examQuestionsList.length<=0){
                      alert("试卷试题数量为0，试卷异常");
                      return;
                  }

                  for(let i=0;i<this.examQuestionsList.length;i++){
                      if(this.examQuestionsList[i].questionType=='2'){
                          if(this.examQuestionsList[i].answer!=null){
                              let answerArray = this.examQuestionsList[i].answer.split("");
                              this.examQuestionsList[i].answerArray = answerArray;
                          }
                      }
                  }

                  setStore("examQuestions_"+item.id,this.examQuestionsList);


                  this.viewExamManagementUserByExamManagementIdAndExamPaperId(item);

              } else if (res.data.code >= 300 && res.data.code <= 400) {
                  if(res.data.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              } else {
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = "获取试卷信息失败";
                  return;
              }
          }).catch(() => {
              this.isShowAlert = true;
              this.alertTitle = "提示";
              this.alertContent = "获取试卷信息异常";
              return;
          })
      },
      viewExamManagementUserByExamManagementIdAndExamPaperId(item){
          let that = this;
          viewExamManagementUserByExamManagementIdAndExamPaperId({examManagementId: item.id,examPaperId: item.examPaperId}).then(res => {
              if (res.data.code == 200) {
                  that.examManagementUser = res.data.data;

                  let examPaperAnswerResult = JSON.parse(that.examManagementUser.examPaperAnswer)||[];
                  this.examPaperAnswerResultGloble = examPaperAnswerResult;
                  for(let count=0;count<examPaperAnswerResult.length;count++){
                      for(let n=0;n<that.examQuestionsList.length;n++){
                          if(examPaperAnswerResult[count]==null){
                              continue;
                          }
                          if(examPaperAnswerResult[count].id==this.examQuestionsList[n].id){
                              that.examQuestionsList[n].isRight = examPaperAnswerResult[count].isRight;
                              that.examQuestionsList[n].myScore = examPaperAnswerResult[count].myScore;
                              this.storeScore(item.id, this.examQuestionsList[n].id, examPaperAnswerResult[count].myScore);
                              this.storeIsRight(item.id, this.examQuestionsList[n].id, examPaperAnswerResult[count].isRight);
                              if(that.examQuestionsList[n].questionType==1) {
                                  that.examQuestionsList[n].rightAnswer = that.examQuestionsList[n].answer;
                                  that.examQuestionsList[n].answer = examPaperAnswerResult[count].answer;
                                  that.storeSingleChoiceAnswer(item.id, this.examQuestionsList[n].id, examPaperAnswerResult[count].answer);
                              }
                              if(that.examQuestionsList[n].questionType==2){
                                  that.examQuestionsList[n].rightAnswer = that.examQuestionsList[n].answer;
                                  if(examPaperAnswerResult[count].answer!=null){
                                      let answerArray = examPaperAnswerResult[count].answer.split("");
                                      that.examQuestionsList[n].answerArray = answerArray;
                                      that.storeMultiChoiceAnswer(item.id,this.examQuestionsList[n].id,that.examQuestionsList[n].answerArray);
                                  }

                              }
                              if(that.examQuestionsList[n].questionType==3) {
                                  that.examQuestionsList[n].rightAnswer = that.examQuestionsList[n].answer;
                                  that.examQuestionsList[n].answer = examPaperAnswerResult[count].answer;
                                  if(examPaperAnswerResult[count].answer) {
                                      that.storeJudgeQuestionAnswer(item.id, this.examQuestionsList[n].id, examPaperAnswerResult[count].answer);
                                  }
                              }
                              if(that.examQuestionsList[n].questionType==4) {
                                  that.examQuestionsList[n].rightItemA = that.examQuestionsList[n].itemA;
                                  that.examQuestionsList[n].rightItemB = that.examQuestionsList[n].itemB;
                                  that.examQuestionsList[n].rightItemC = that.examQuestionsList[n].itemC;
                                  that.examQuestionsList[n].rightItemD = that.examQuestionsList[n].itemD;
                                  that.examQuestionsList[n].rightItemE = that.examQuestionsList[n].itemE;
                                  that.examQuestionsList[n].rightItemF = that.examQuestionsList[n].itemF;
                                  that.examQuestionsList[n].rightItemG = that.examQuestionsList[n].itemG;

                                  that.examQuestionsList[n].itemA = examPaperAnswerResult[count].fillBlankItemAAnswer;
                                  that.examQuestionsList[n].itemB = examPaperAnswerResult[count].fillBlankItemBAnswer;
                                  that.examQuestionsList[n].itemC = examPaperAnswerResult[count].fillBlankItemCAnswer;
                                  that.examQuestionsList[n].itemD = examPaperAnswerResult[count].fillBlankItemDAnswer;
                                  that.examQuestionsList[n].itemE = examPaperAnswerResult[count].fillBlankItemEAnswer;
                                  that.examQuestionsList[n].itemF = examPaperAnswerResult[count].fillBlankItemFAnswer;
                                  that.examQuestionsList[n].itemG = examPaperAnswerResult[count].fillBlankItemGAnswer;
                                  that.examQuestionsList[n].itemH = examPaperAnswerResult[count].fillBlankItemHAnswer;

                                  let fillBlankItemArray = [];
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemAAnswer==null?'':examPaperAnswerResult[count].fillBlankItemAAnswer);
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemBAnswer==null?'':examPaperAnswerResult[count].fillBlankItemBAnswer);
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemCAnswer==null?'':examPaperAnswerResult[count].fillBlankItemCAnswer);
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemDAnswer==null?'':examPaperAnswerResult[count].fillBlankItemDAnswer);
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemEAnswer==null?'':examPaperAnswerResult[count].fillBlankItemEAnswer);
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemFAnswer==null?'':examPaperAnswerResult[count].fillBlankItemFAnswer);
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemGAnswer==null?'':examPaperAnswerResult[count].fillBlankItemGAnswer);
                                  fillBlankItemArray.push(examPaperAnswerResult[count].fillBlankItemHAnswer==null?'':examPaperAnswerResult[count].fillBlankItemHAnswer);
                                  that.storeFillBlankAnswer(item.id,this.examQuestionsList[n].id,fillBlankItemArray);


                              }
                              if(that.examQuestionsList[n].questionType==5) {
                                  that.examQuestionsList[n].rightItemA = that.examQuestionsList[n].itemA;
                                  that.examQuestionsList[n].itemA = examPaperAnswerResult[count].simpleQuestionItemAAnswer;
                                  if(examPaperAnswerResult[count].simpleQuestionItemAAnswer) {
                                      that.storeSimpleQuestionAnswer(item.id, this.examQuestionsList[n].id, examPaperAnswerResult[count].simpleQuestionItemAAnswer);
                                  }
                              }
                          }
                      }
                  }

                  setTimeout(() => {
                      this.$router.push({path: '/examManagement/emAnswerQuestion', query: {examManagementId: item.id,examPaperId:item.examPaperId,ccpId:item.id,isReview:1}});
                  }, 200);
                  //设置试题和答案进examQuestionsList完毕
              } else if (res.data.code >= 300 && res.data.code <= 400) {
                  if(res.data.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              } else {
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = "查看考试与用户关系失败";
                  return;
              }
          }).catch(error => {
              this.isShowAlert = true;
              this.alertTitle = "提示";
              this.alertContent = "查看考试与用户关系异常";
              return;
          })
      },
    loaderMore () { // 加载更多的方法
        this.queryExamManagementsByStudentUserNo();
    },
      examManagementViewByStudentUserNo(item){
          let that = this;
          examManagementViewByStudentUserNo({id: item.id}).then(res => {
              if (res.data.code == 200) {
                  let examManagementObj = res.data.data;
                  if(examManagementObj.isAllowRepeateHandInExamPaper == 0) {
                      if (item.isFinishUserTotalScore == 1 || item.isFinishUserTotalScore == 2) {
                          this.isShowAlert = true;
                          this.alertTitle = "提示";
                          this.alertContent = "已经参加过考试。该考试不允许重复参加";
                          return;
                      }
                  }
                  this.viewExamPaperContentByExamManagementIdAndExamPaperId(item.id,item.examPaperId);


              } else if (res.data.code >= 300 && res.data.code <= 400) {
                  if(res.data.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              } else {
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = "获取考试信息失败";
                  return;
              }
          }).catch(() => {
              this.isShowAlert = true;
              this.alertTitle = "提示";
              this.alertContent = "获取考试信息异常";
              return;
          })
      },
      storeIsRight(ccpId,questionId,isRight){
          setStore("isRight_"+ccpId+"_"+questionId,isRight);
      },
      storeScore(ccpId,questionId,score){
          setStore("score_"+ccpId+"_"+questionId,score);
      },
      storeSingleChoiceAnswer(ccpId,questionId,item){
          //将答案存到缓存里面，已被开始时候可以加载
          let answer_ids = JSON.parse(getStore("answer_ids_"+ccpId));
          if(answer_ids==null){
              answer_ids = [];
          }
          let c =0;
          for(let i=0;i<answer_ids.length;i++){
              if(answer_ids[i]==("answer_"+ccpId+"_"+questionId)){
                  c++;
              }
          }
          if(c==0){
              answer_ids.push("answer_"+ccpId+"_"+questionId);
          }
          setStore("answer_ids_"+ccpId,JSON.stringify(answer_ids));
          setStore("answer_"+ccpId+"_"+questionId,item);
      },
      storeMultiChoiceAnswer(ccpId,questionId,item){
          //将答案存到缓存里面，已被开始时候可以加载
          let answer_ids = JSON.parse(getStore("answer_ids_"+ccpId));
          if(answer_ids==null){
              answer_ids = [];
          }
          let c =0;
          for(let i=0;i<answer_ids.length;i++){
              if(answer_ids[i]==("answer_"+ccpId+"_"+questionId)){
                  c++;
              }
          }
          if(c==0){
              answer_ids.push("answer_"+ccpId+"_"+questionId);
          }
          setStore("answer_ids_"+ccpId,JSON.stringify(answer_ids));
          setStore("answer_"+ccpId+"_"+questionId,item);
      },
      storeJudgeQuestionAnswer(ccpId,questionId,item){
          //将答案存到缓存里面，已被开始时候可以加载
          let answer_ids = JSON.parse(getStore("answer_ids_"+ccpId));
          if(answer_ids==null){
              answer_ids = [];
          }
          let c =0;
          for(let i=0;i<answer_ids.length;i++){
              if(answer_ids[i]==("answer_"+ccpId+"_"+questionId)){
                  c++;
              }
          }
          if(c==0){
              answer_ids.push("answer_"+ccpId+"_"+questionId);
          }
          setStore("answer_ids_"+ccpId,JSON.stringify(answer_ids));
          setStore("answer_"+ccpId+"_"+questionId,item);
      },
      storeFillBlankAnswer(ccpId,questionId,fillBlankItemCompleted){
          //将答案存到缓存里面，已被开始时候可以加载
          let answer_ids = JSON.parse(getStore("answer_ids_"+ccpId));
          if(answer_ids==null){
              answer_ids = [];
          }
          let c =0;
          for(let i=0;i<answer_ids.length;i++){
              if(answer_ids[i]==("answer_"+ccpId+"_"+questionId)){
                  c++;
              }
          }
          if(c==0){
              answer_ids.push("answer_"+ccpId+"_"+questionId);
          }
          setStore("answer_ids_"+ccpId,JSON.stringify(answer_ids));
          setStore("answer_"+ccpId+"_"+questionId,fillBlankItemCompleted);
      },
      storeSimpleQuestionAnswer(ccpId,questionId,item_A){
          //将答案存到缓存里面，已被开始时候可以加载
          let answer_ids = JSON.parse(getStore("answer_ids_"+ccpId));
          if(answer_ids==null){
              answer_ids = [];
          }
          let c =0;
          for(let i=0;i<answer_ids.length;i++){
              if(answer_ids[i]==("answer_"+ccpId+"_"+questionId)){
                  c++;
              }
          }
          if(c==0){
              answer_ids.push("answer_"+ccpId+"_"+questionId);
          }
          setStore("answer_ids_"+ccpId,JSON.stringify(answer_ids));
          setStore("answer_"+ccpId+"_"+questionId,item_A);
      }
  },
  watch: {
      title: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.title = value;
        this.queryExamManagementsByStudentUserNo(0);
    }

  }
}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .course_child_list {
    .course_child_con {
      background: #fff;
  }
    &.n{
      padding: 0;
      .course_child_con{
        border-bottom: 1px solid #D0D0D0;
        border-top: 1px solid #D0D0D0;
        -webkit-box-shadow: none;
        box-shadow: none;
        padding: 0 10px;
        margin: 0 0 10px 0;
        .course_content_box{
          overflow: hidden;
          .con_right_area{
            float: right;
            li {
              margin-bottom: 0.7rem;
              font-size: 1.2rem;
            }
            .course_title_h1{
              h1 {
                font-size: 1.3rem;
                margin-top: 5px;
              }
              i {
                display: inline-block;
                overflow: hidden;
                max-width: 90%;
                text-overflow: ellipsis;
                white-space: pre-line;
              }
              span {
                display: inline-block;
                margin-top: 0.5rem;
                margin-left: 3px;
              }
          }
            .course_price {
              text-overflow: ellipsis;
              max-width: 200px;
              white-space: nowrap;
              overflow: hidden;
              padding-left: 18px;
              background: url('/wxmobile/img/wxImg/item.png') no-repeat 0 3px;
              background-size: 12px 12px;
              .had_finish {
                margin-left: 15px;
                font-weight: bold;
                &:before {
                  content: '已完成';
                  display: inline-block;
                  width: 40px;
                  height: 16px;
                  margin-left: 0px;
                  line-height: 17px;
                  background: green;
                  font-size: 1rem;
                  color: #fff;
                  border-radius: 3px;
                  text-align: center;
                  position: relative;
                  top: 0px;
                }
              }
            }
          }
      }
    }
  }
  }
  .no_data {
    text-align: center;
    line-height: 2rem;
    font-size: 1rem;
    color: #666;
  }
  .loading-enter-active, .loading-leave-active {
    transition: opacity 1s
  }
  .loading-enter, .loading-leave-active {
    opacity: 0
  }
  .btn_box {
    float: right;
    width: 68px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }
  .cancel_btn_box {
    float: left;
    width: 68px;
    background-color: red;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }
  .continue_btn_box {
    float: right;
    width: 68px;
    background-color: green;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }
</style>
