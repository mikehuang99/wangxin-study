<template>
  <div>
    <div v-if="isReview!=1">
      <div class="my-grid-container">
        <div :data-id="item.id" :key="index" v-for="(item, index) in examQuestionsNew">
          <div class="my-grid-item-blue" v-if="item.hadDone == 1" @click="toAnswerQuestion(index)">
            <div class="my-grid-item-block">{{index+1}}</div>
          </div>
          <div class="my-grid-item-grey" v-else @click="toAnswerQuestion(index)">
            <div class="my-grid-item-block">{{index+1}}</div>
          </div>
        </div>
      </div>
      <div>
        <div style="height:30px;display: inline-block;margin-top: 10px">
          <div style="height:30px;display: inline-block;float: left;width: 120px;margin-left: 20px">
            <div style="display: inline-block;float:left;width: 20px;height: 20px;border: solid 1px deepskyblue;background-color: deepskyblue"></div>
            <div style="display: inline-block;font-size: 15px;font-weight: bold">蓝色为已做</div>
          </div>
          <div style="height:30px;display: inline-block;float: right;margin-left: 100px">
            <div style="display: inline-block;float:left;width: 20px;height: 20px;border: solid 1px grey;background-color: grey"></div>
            <div style="display: inline-block;font-size: 15px;font-weight: bold">灰色为未做</div>
          </div>
        </div>
      </div>
      <div style="text-align: center">
        <div class="hand_in_exam_btn_box" @click="openHandInExamPaperDialog">
          <span style="font-weight: bold;font-size: 18px" v-if="handInExamPaperStatus==0">交卷</span>
          <span style="font-weight: bold;font-size: 18px" v-if="handInExamPaperStatus==1">交卷中...</span>
          <span style="font-weight: bold;font-size: 18px" v-if="handInExamPaperStatus==2">已经交卷</span>
        </div>
      </div>

      <div v-show="isHandInExamPaper" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999998; filter: alpha(opacity=60); opacity: 0.5 !important;">
        <!--该DIV为遮罩层-->
      </div>
      <div style="position:fixed; *position:absolute; width:280px; height:160px; top:50%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 999999;border-radius:5px;"
           v-show="isHandInExamPaper">
        <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
          <span style="font-size: 16px;font-weight: bold">提示</span>
        </div>
        <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
          <span style="font-size: 16px;font-weight: bold">是否交卷？<br/>点击"确定"将交卷</span>
        </div>
        <div @click="closeHandInExamPaperDialog()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;left: 20px;width: 120px;height: 30px;border: 1px #333333;background-color: greenyellow;border-radius:3px;">
          <span style="font-size: 12px;font-weight: bold" >取消</span>
        </div>
        <div @click="handInExamPaper()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;right: 20px;width: 60px;height: 30px;border: 1px #333333;background-color: red;border-radius:3px;">
          <span style="font-size: 12px;font-weight: bold" >确定</span>
        </div>
      </div>
    </div>

    <div v-if="isReview==1">
      <div class="my-grid-container">
        <div :data-id="item.id" :key="index" v-for="(item, index) in examQuestionsNew">
          <div class="my-grid-item-green" v-if="item.rightOrErrorFlag == 1" @click="toAnswerQuestion(index)">
            <div class="my-grid-item-block">{{index+1}}</div>
          </div>
          <div class="my-grid-item-red" v-if="item.rightOrErrorFlag == 2" @click="toAnswerQuestion(index)">
            <div class="my-grid-item-block">{{index+1}}</div>
          </div>
          <div class="my-grid-item-grey" v-if="item.rightOrErrorFlag == 0" @click="toAnswerQuestion(index)">
            <div class="my-grid-item-block">{{index+1}}</div>
          </div>
        </div>
      </div>
      <div>
        <div style="height:30px;display: inline-block;margin-top: 10px">
          <div style="height:30px;display: inline-block;float: left;width: 120px;margin-left: 20px">
            <div style="display: inline-block;float:left;width: 20px;height: 20px;border: solid 1px green;background-color: green"></div>
            <div style="display: inline-block;font-size: 15px;font-weight: bold">绿色为做对</div>
          </div>
          <div style="height:30px;display: inline-block;float: left;width: 120px">
            <div style="display: inline-block;float:left;width: 20px;height: 20px;border: solid 1px red;background-color: red"></div>
            <div style="display: inline-block;font-size: 15px;font-weight: bold">红色为做错</div>
          </div>
          <div style="height:30px;display: inline-block;">
            <div style="display: inline-block;float:left;width: 20px;height: 20px;border: solid 1px grey;background-color: grey"></div>
            <div style="display: inline-block;font-size: 15px;font-weight: bold">灰色为未知</div>
          </div>
        </div>
      </div>
    </div>

    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" :toUrlParams="toUrlParams" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
  </div>
</template>
<script>
import headerTop from '~/components/examManagement/answerQuestion/emHeader.vue'
import {handInExamPaper} from '~/api/examManagement'
import footertabs from '~/components/index/footer/Footer.vue'
import {getStore,removeStore,setStore} from '~/store/common.js'
import Alert from "~/components/common/alert";


import { logout } from '~/store/common'

export default {
    data(){
        return{
            isShowAlert: false,
            timeOutFlag: false,
            toUrl: '',
            alertTitle: '',
            alertContent: '',
            toUrlParams: [],

            examManagementId: '',
            examPaperId: '',
            ccpId: '',
            examQuestions: '',
            examQuestionsNew: [],
            isHandInExamPaper:false,
            hadDoneExamQuestionsList:[],
            handInExamPaperStatus: 0,//0未交卷，1交卷中，2已经交卷
            handInExamPaperTimeOutVar: '',
            isReview: 0,
            autoHandInExamPaperTimeoutVar: ''
        }
    },
  computed: {

  },
    watch: {
        handInExamPaperStatus(val, oldVal){
            if(val==2&&this.isReview!=1){
                let that = this;
                this.autoHandInExamPaperTimeoutVar = setTimeout(function () {
                    that.$router.push({path: '/my/myExamManagement/myExamManagementIndex', query: {}});
                }, 2000);
            }
        }
    },
    created(){

    },
    mounted(){
        this.examManagementId = this.$route.query.examManagementId;
        this.examPaperId = this.$route.query.examPaperId;
        this.ccpId = this.$route.query.ccpId;

        this.isReview = this.$route.query.isReview;

        clearTimeout(this.handInExamPaperTimeOutVar);
        clearTimeout(this.autoHandInExamPaperTimeoutVar);
        this.loadAnswerQuestionPool();


    },
  methods: {
      closeAlert(){
          this.isShowAlert = false;
      },
      toAnswerQuestion(index){
          setStore("continueStartIndex_"+this.ccpId, index);
          this.$parent.setShowHandInExamPaperButton(true);
      },
      closeHandInExamPaperDialog(){
          this.isHandInExamPaper = false;
      },
      openHandInExamPaperDialog(){
          this.isHandInExamPaper = true;
      },
      /*
      handInExamPaper(){
          //交卷
          this.isHandInExamPaper = false;
          //this.$parent.toOpenEmAnswerQuestionPool(true);

      },*/
      //handInExamPaper
      handInExamPaper(){
          this.isHandInExamPaper = false;

          if(this.handInExamPaperStatus ==2 ){
              this.isShowAlert = true;
              this.alertTitle = "提示";
              this.alertContent = "已经交卷，不需要重复交卷。";
              return;
          }

          this.handInExamPaperStatus = 1;
          let that = this;
          handInExamPaper({userExamPaperAnswerList:this.hadDoneExamQuestionsList,examManagementId: this.examManagementId,examPaperId: this.examPaperId}).then(res => {
              if (res.data.code == 200) {
                  let result = res.data.data;
                  this.handInExamPaperStatus = 2;
                  console.log("===交卷后返回内容===>"+JSON.stringify(result));
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = "交卷成功，即将跳转到考试列表...";
                  return;
              } else if (res.data.code == 609) {//不能重复交卷
                  this.handInExamPaperStatus = 2;
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = res.data.msg;
                  return;
              }else if (res.data.code >= 300 && res.data.code <= 400) {
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
                  console.log("交卷失败了，应用程序有误");
                  let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
                  that.handInExamPaperTimeOut = that.handInExamPaperTimeOut+timeOut;
                  console.log("间隔时长："+that.handInExamPaperTimeOut);
                  this.handInExamPaperTimeOutVar = setTimeout(function(){ that.handInExamPaper()}, that.handInExamPaperTimeOut);
              }
          }).catch(() => {
              console.log("失败，可能是网络问题，再次交卷。。。。");
              let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
              that.handInExamPaperTimeOut = that.handInExamPaperTimeOut+timeOut;
              this.handInExamPaperTimeOutVar = setTimeout(function(){ that.handInExamPaper()}, that.handInExamPaperTimeOut);
          })
      },
      loadAnswerQuestionPool(){
          let examQuestions = JSON.parse(getStore("examQuestions_"+this.ccpId))||[];
          this.examQuestions = examQuestions;

          this.hadDoneExamQuestionsList = [];
          let index = 0;
          for(let i=0;i<this.examQuestions.length;i++){
              this.examQuestions[i].rightOrErrorFlag = 0;//初始化该属性
              let isRight = getStore("isRight_"+this.examManagementId+"_"+this.examQuestions[i].id)
              if(isRight == 1){
                  this.examQuestions[i].rightOrErrorFlag = 1;
              }else{
                  this.examQuestions[i].rightOrErrorFlag = 2;
              }
              if(this.examQuestions[i].questionType==5){
                  this.examQuestions[i].rightOrErrorFlag = 0;
              }
              let answer_ids = JSON.parse(getStore("answer_ids_"+this.ccpId));
              if(answer_ids!=null){
                  let count = 0;
                  for(let n=0;n<answer_ids.length;n++){
                      let answer = "answer_"+this.examManagementId+"_"+this.examQuestions[i].id;
                      if(answer==answer_ids[n]){
                          count++;

                          let answerContent = getStore("answer_"+this.examManagementId+"_"+this.examQuestions[i].id);

                          if(this.examQuestions[i].questionType == 1){
                              if(answerContent) {
                                  this.hadDoneExamQuestionsList[index] = {'id': '', 'questionType': '', 'answer': ''};
                                  this.hadDoneExamQuestionsList[index].id = this.examQuestions[i].id;
                                  this.hadDoneExamQuestionsList[index].answer = answerContent;
                                  index = index+ 1;
                                  /*
                                  if(answerContent==this.examQuestions[i].answer){
                                      this.examQuestions[i].rightOrErrorFlag = 1;
                                  }else{
                                      this.examQuestions[i].rightOrErrorFlag = 2;
                                  }*/
                              }
                          }
                          if(this.examQuestions[i].questionType == 2){
                              let multiChoiceAnswer = JSON.parse(answerContent)||[];
                              if(multiChoiceAnswer.length!=0) {
                                  multiChoiceAnswer.sort();//先排一下序
                                  this.hadDoneExamQuestionsList[index] = {'id': '', 'questionType': '','answer': '', 'originalAnswer':''};
                                  let multiChoiceAnswerStr = '';
                                  for (let iii = 0; iii < multiChoiceAnswer.length; iii++) {
                                      multiChoiceAnswerStr = multiChoiceAnswerStr + multiChoiceAnswer[iii];
                                  }
                                  this.hadDoneExamQuestionsList[index].id = this.examQuestions[i].id;
                                  this.hadDoneExamQuestionsList[index].answer = multiChoiceAnswerStr;
                                  this.hadDoneExamQuestionsList[index].originalAnswer = multiChoiceAnswer;
                                  index = index+ 1;

                                  /*
                                  let a = this.examQuestions[i].answer;
                                  let b=a.split("");       //分割字符串a为数组dub
                                  b.sort();              //数组b升序排序（系统自带的方法）
                                  let realMultiChoiceAnswerStr = '';
                                  for(let k=0;k<b.length;k++){
                                      realMultiChoiceAnswerStr = realMultiChoiceAnswerStr + b[k];
                                  }
                                  if(multiChoiceAnswerStr.toString() === realMultiChoiceAnswerStr.toString()){
                                      this.examQuestions[i].rightOrErrorFlag = 1;
                                  }else{
                                      this.examQuestions[i].rightOrErrorFlag = 2;
                                  }*/
                              }
                          }
                          if(this.examQuestions[i].questionType == 3){
                              if(answerContent) {
                                  this.hadDoneExamQuestionsList[index] = {'id': '', 'questionType': '','answer': ''};
                                  this.hadDoneExamQuestionsList[index].id = this.examQuestions[i].id;
                                  this.hadDoneExamQuestionsList[index].answer = answerContent;
                                  index = index+ 1;
                                  /*
                                  if(answerContent==this.examQuestions[i].answer){
                                      this.examQuestions[i].rightOrErrorFlag = 1;
                                  }else{
                                      this.examQuestions[i].rightOrErrorFlag = 2;
                                  }*/
                              }
                          }
                          if(this.examQuestions[i].questionType == 4){
                              let fillBlankAnswer = JSON.parse(answerContent)||[];
                              if(fillBlankAnswer.length!=0) {
                                  this.hadDoneExamQuestionsList[index] = {
                                      'id': '',
                                      'questionType': '',
                                      'fillBlankItemAAnswer': '',
                                      'fillBlankItemBAnswer': '',
                                      'fillBlankItemCAnswer': '',
                                      'fillBlankItemDAnswer': '',
                                      'fillBlankItemEAnswer': '',
                                      'fillBlankItemFAnswer': '',
                                      'fillBlankItemGAnswer': '',
                                      'fillBlankItemHAnswer': ''
                                  };
                                  this.hadDoneExamQuestionsList[index].id = this.examQuestions[i].id;
                                  this.hadDoneExamQuestionsList[index].fillBlankItemAAnswer = fillBlankAnswer[0];
                                  this.hadDoneExamQuestionsList[index].fillBlankItemBAnswer = fillBlankAnswer[1];
                                  this.hadDoneExamQuestionsList[index].fillBlankItemCAnswer = fillBlankAnswer[2];
                                  this.hadDoneExamQuestionsList[index].fillBlankItemDAnswer = fillBlankAnswer[3];
                                  this.hadDoneExamQuestionsList[index].fillBlankItemEAnswer = fillBlankAnswer[4];
                                  this.hadDoneExamQuestionsList[index].fillBlankItemFAnswer = fillBlankAnswer[5];
                                  this.hadDoneExamQuestionsList[index].fillBlankItemGAnswer = fillBlankAnswer[6];
                                  this.hadDoneExamQuestionsList[index].fillBlankItemHAnswer = fillBlankAnswer[7];
                                  index = index+ 1;

                                  /*
                                  let rightCount =0;
                                  let errorCount =0;
                                  if(this.examQuestions[i].itemA){
                                      if(this.examQuestions[i].itemA==fillBlankAnswer[0]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }

                                  if(this.examQuestions[i].itemB){
                                      if(this.examQuestions[i].itemB==fillBlankAnswer[1]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }

                                  if(this.examQuestions[i].itemC){
                                      if(this.examQuestions[i].itemC==fillBlankAnswer[2]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }

                                  if(this.examQuestions[i].itemD){
                                      if(this.examQuestions[i].itemD==fillBlankAnswer[3]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }

                                  if(this.examQuestions[i].itemE){
                                      if(this.examQuestions[i].itemE==fillBlankAnswer[4]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }

                                  if(this.examQuestions[i].itemF){
                                      if(this.examQuestions[i].itemF==fillBlankAnswer[5]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }

                                  if(this.examQuestions[i].itemG){
                                      if(this.examQuestions[i].itemG==fillBlankAnswer[6]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }

                                  if(this.examQuestions[i].itemH){
                                      if(this.examQuestions[i].itemH==fillBlankAnswer[7]){
                                          rightCount = rightCount + 1;
                                      }else{
                                          errorCount = errorCount + 1;
                                      }
                                  }
                                  if(errorCount==0){
                                      //答案正确
                                      this.examQuestions[i].rightOrErrorFlag = 1;
                                  }else {
                                      //答案错误
                                      this.examQuestions[i].rightOrErrorFlag = 2;
                                  }*/
                              }
                          }
                          if(this.examQuestions[i].questionType == 5) {
                              if (answerContent) {
                                  this.hadDoneExamQuestionsList[index] = {
                                      'id': '',
                                      'questionType': '',
                                      'simpleQuestionItemAAnswer': ''
                                  };
                                  this.hadDoneExamQuestionsList[index].id = this.examQuestions[i].id;
                                  this.hadDoneExamQuestionsList[index].simpleQuestionItemAAnswer = answerContent;
                                  index = index+ 1;

                                  //this.examQuestions[i].rightOrErrorFlag = 1;
                              }
                          }
                      }
                  }
                  if(count!=0){
                      this.examQuestions[i].hadDone = 1;
                  }
              }
              this.examQuestionsNew.push(this.examQuestions[i]);
          }
          for(let aa = 0;aa<this.hadDoneExamQuestionsList.length;aa++){
              console.log("已经做的："+this.hadDoneExamQuestionsList[aa].id)
          }
      }
  },
  components: {
    headerTop,
    footertabs,
      Alert
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .my-grid-container {
    margin-top: 44px;
    display: grid;
    //grid-template-columns: auto auto auto;
    background-color: lightgrey;
    padding: 5px;
    grid-template-columns: repeat(auto-fill, 20%);
    grid-template-rows: 40px 40px 40px;
  }
  .my-grid-item-blue {
    //background-color: rgba(255, 255, 255, 0.8);
    background-color: deepskyblue;
    border: 2px solid lightgrey;
    padding: 10px;
    font-size: 12px;
    text-align: center;
  }
  .my-grid-item-green {
    //background-color: rgba(255, 255, 255, 0.8);
    background-color: green;
    border: 2px solid lightgrey;
    padding: 10px;
    font-size: 12px;
    text-align: center;
  }
  .my-grid-item-grey {
    //background-color: rgba(255, 255, 255, 0.8);
    background-color: grey;
    border: 2px solid lightgrey;
    padding: 10px;
    font-size: 12px;
    text-align: center;
  }
  .my-grid-item-red {
    //background-color: rgba(255, 255, 255, 0.8);
    background-color: red;
    border: 2px solid lightgrey;
    padding: 10px;
    font-size: 12px;
    text-align: center;
  }
  .my-grid-item-block {
    font-weight: bold;
  }
  .hand_in_exam_btn_box {
    width: 150px;
    border-radius: 5px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    margin: 4px 2px;
    cursor: pointer;
  }

</style>
