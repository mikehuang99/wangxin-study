<template>
  <div class="box" id="box" ref='slide' @touchstart='touchStart' @touchmove='touchMove' @touchend='touchEnd' :style="con">

    <single-choice v-if="hackReset" v-show="singleChoice" :ccpId="ccpId" @setNextQuestion="(val)=>nextQuestion=val" :singleChoiceContent="singleChoiceContent"></single-choice>
    <multi-choice v-if="hackReset"  v-show="multiChoice" :ccpId="ccpId" @setNextQuestion="(val)=>nextQuestion=val" :multiChoiceContent="multichoicecontent"></multi-choice>
    <judge-question v-if="hackReset" v-show="judgeQuestion" :ccpId="ccpId" @setNextQuestion="(val)=>nextQuestion=val" :judgeQuestionContent="judgequestioncontent"></judge-question>
    <fill-blank v-if="hackReset" v-show="fillBlank" :ccpId="ccpId" @setNextQuestion="(val)=>nextQuestion=val" :fillBlankContent="fillBlankContent"></fill-blank>
    <simple-question v-if="hackReset" v-show="simpleQuestion" :ccpId="ccpId" @setNextQuestion="(val)=>nextQuestion=val" :simpleQuestionContent="simpleQuestionContent"></simple-question>

    <div style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: -1000; filter: alpha(opacity=60); opacity: 0.0 !important;">
      <!--该DIV实为遮罩层,填充可以让整个页面【内容还没填满页面的时候】左滑右滑-->
    </div>

    <div v-show="isLast" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999998; filter: alpha(opacity=60); opacity: 0.5 !important;">
      <!--该DIV为遮罩层-->
    </div>
    <div style="position:fixed; *position:absolute; width:280px; height:160px; top:50%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 999999;border-radius:5px;"
         v-show="isLast">
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">提示</span>
      </div>
      <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">已经是最后一道试题，是否返回？</span>
      </div>
      <div @click="closeDialog()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;left: 20px;width: 120px;height: 30px;border: 1px #333333;background-color: greenyellow;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >关闭</span>
      </div>
      <div @click="goBack()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;right: 20px;width: 60px;height: 30px;border: 1px #333333;background-color: red;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >确定</span>
      </div>
    </div>

    <div v-show="isNote" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999998; filter: alpha(opacity=60); opacity: 0.5 !important;">
      <!--该DIV为遮罩层-->
    </div>
    <div style="position:fixed; *position:absolute; width:280px; height:250px; top:30%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 999999;border-radius:5px;"
         v-show="isNote">
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">笔记{{this.noteTip}}</span>
      </div>
      <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
            <textarea id="noteContent" rows="10" cols="30" style="min-height: 150px;min-width: 247px;max-height: 150px; max-width: 247px;">
            </textarea>
      </div>
      <div  @click="closeNoteDialog()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;left: 20px;width: 120px;height: 30px;border: 1px #333333;background-color: greenyellow;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold">关闭</span>
      </div>
      <div  @click="cacheNoteContent()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;right: 20px;width: 60px;height: 30px;border: 1px #333333;background-color: red;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold">确定</span>
      </div>
    </div>

  </div>
</template>
<script>
import headerTop from '~/components/examManagement/reviewEmAnswerQuestion/reviewEmHeader.vue'
import singleChoice from '~/components/examManagement/reviewEmAnswerQuestion/reviewEmSingleChoice.vue'
import multiChoice from '~/components/examManagement/reviewEmAnswerQuestion/reviewEmMultiChoice.vue'
import judgeQuestion from '~/components/examManagement/reviewEmAnswerQuestion/reviewEmJudgeQuestion.vue'
import fillBlank from '~/components/examManagement/reviewEmAnswerQuestion/reviewEmFillBlank.vue'
import simpleQuestion from '~/components/examManagement/reviewEmAnswerQuestion/reviewEmSimpleQuestion.vue'
import footertabs from '~/components/index/footer/Footer.vue'
import { mapState, mapMutations } from 'vuex'
//import {getStore,removeStore,setStore} from '~/store/examStore.js'
import {getStore,removeStore,setStore} from '~/store/common.js'
//import { queryNoteContentByQuestionId,cacheNoteExamQuestion,queryFavoritesExamQuestions,delFavoritesExamQuestion,cacheFavoritesExamQuestion,cacheErrorExamQuestion,delErrorExamQuestion,delHighErrorExamQuestion,cacheHighErrorExamQuestion } from '../../ajax/getData'

import {queryNotYetDoneExamQuestions,queryExamQuestions,queryErrorExamQuestions,cacheErrorExamQuestion,delErrorExamQuestion,queryHighErrorExamQuestions,cacheHighErrorExamQuestion,delHighErrorExamQuestion,queryFavoritesExamQuestions,cacheFavoritesExamQuestion,
    delFavoritesExamQuestion,queryNoteExamQuestions,queryNoteContentByQuestionId,cacheNoteExamQuestion,delNoteExamQuestion} from '~/api/exam'
import { logout } from '~/store/common'


export default {
    data(){
        return{
            noteTip:'',
            isNote: '',
            rightCount: 0,
            nextQuestion: false,
            isLast: '',
            hackReset: '',
            questionId: '',
            singleChoiceContent: '',
            multichoicecontent: '',
            judgequestioncontent: '',
            fillBlankContent: '',
            simpleQuestionContent: '',
            continueStartIndex: 0,
            singleChoice: false,
            multiChoice: false,
            judgeQuestion: false,
            fillBlank: false,
            simpleQuestion: false,

            test:'99',
            videooasid:'',
            playvideo:false,
            playimg:true,
            lessonname: '',

            startX: 0, //开始触摸的位置
            startY: 0, //开始触摸的位置
            endX: 0, //结束触摸的位置
            endY: 0, //结束触摸的位置
            disX: 0, //移动距离
            disY: 0,
            lastX: 0, //上一次X坐标
            lastY: 0, //上一次Y坐标
            con:{
                translateX: 'translateX(0px)'

            },
            courseDetail: {
                id:''
            },
            ccpId:'',
            slideFlag: false

        }
    },
  computed: {
        /*
    ...mapState([
      'courseDetail'
    ])*/
  },
   // props: ['ccpId'],
    watch: {
        nextQuestion(newName, oldName) {
            if(newName==true) {
                this.answerQuestion('1');
                this.nextQuestion = false;
                this.flush();
            }
        },
        questionId(newValue,oldValue){
            this.$parent.setQuestionId(newValue);
        }
    },
    created(){

    },
    mounted(){

        this.ccpId = this.$route.query.id;
        this.courseDetail.id = this.$route.query.courseId;

        this.queryCacheFavorites();
        setTimeout(() => {
            this.answerQuestion('0');
            this.flush();
        }, 20)

    },
  methods: {
      async queryNoteContentByQuestionId(){
              queryNoteContentByQuestionId({ccpId: this.ccpId,questionId:this.questionId}).then(res => {
                  res = res.data;
                  if (res.code === 200 ) {
                      let data = res.data;
                     // let data = await queryNoteContentByQuestionId(this.courseDetail.id, this.questionId);

                      if(data!='null') {
                          document.getElementById("noteContent").value = data;
                      }else{
                          document.getElementById("noteContent").value = '';
                      }
                  } else if (res.code > 300 && res.code < 400) {
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
                  }
              }).catch(error => {
                  //reject(error)
                  console.log(error);
              })

      },
      async cacheNoteContent(){
         let noteContent = document.getElementById("noteContent").value;
          let data;
          try {
              cacheNoteExamQuestion({ccpId: this.courseDetail.id,questionId:this.questionId,noteContent:noteContent}).then(res => {
                  res = res.data;
                  if (res.code === 200 ) {
                      data = res.data;
                      if(data.noteContent!='null') {
                          document.getElementById("noteContent").value = data.noteContent;
                      }else{
                          document.getElementById("noteContent").value = '';
                      }
                      this.noteTip = '';
                      this.closeNoteDialog();
                  } else if (res.code > 300 && res.code < 400) {
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
                      this.noteTip = '[存储失败]';
                      console.log("系统错误");
                  }
              }).catch(error => {
                  //reject(error)
                  this.noteTip = '[存储失败]';
                  console.log(error);
              })

         }catch (e) {
             this.noteTip = '[存储失败]';
             return;
         }
      },
      note(isNote){
            this.isNote = true;
            this.queryNoteContentByQuestionId();
            console.log("content 中的note");
      },
      /*
      setFavoritesStatus(favoritesStatus){
          this.$parent.setFavoritesStatus(favoritesStatus);
      },*/
      async queryCacheFavorites(){
          console.log("收藏2........");
          queryFavoritesExamQuestions({ccpId: this.courseDetail.id}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  let data = res.data.list;

                  //let data = await queryFavoritesExamQuestions(this.courseDetail.id);
                  let dataArray = [];
                  for(let i=0;i<data.length;i++){
                      dataArray.push(data[i].id);
                  }
                  setStore("favorites_"+this.courseDetail.id,JSON.stringify(dataArray));

                  let data2 = JSON.parse(getStore("favorites_"+this.courseDetail.id));
                  console.log("收藏data2:"+data2);

              } else if (res.code > 300 && res.code < 400) {
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
              }
          }).catch(error => {
              //reject(error)
              console.log(error);
          })

      },
      //处理错误题集和高频错误题集
      async hadleErrorAndHighErrorEollection(rightOrError,questionId){
          //处理错题
          if (this.ccpId.indexOf("ERROR_COLLECTION_") != -1){
              if(rightOrError=='right') {
                  //做对了，先删除错误题集中的
                  //let data = await delErrorExamQuestion(this.courseDetail.id, questionId);
                  delErrorExamQuestion({ccpId: this.courseDetail.id,questionId:questionId}).then(res => {
                      res = res.data;
                      if (res.code === 200 ) {

                      } else if (res.code > 300 && res.code < 400) {
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
                      }
                  }).catch(error => {
                      //reject(error)
                      console.log(error);
                  })
                  //在这里退一，导致绿色不会再刷新下一页了，不过将错就错，就让在"错题回顾"中，即使答对也不进入下一题了
                  let csi = getStore("continueStartIndex_"+this.ccpId)-1;
                  setStore("continueStartIndex_"+this.ccpId, csi);
              }
              if(rightOrError=='error') {
                  //还做错，就加入高频错题
                  //let data = await cacheHighErrorExamQuestion(this.courseDetail.id, questionId);
                  cacheHighErrorExamQuestion({ccpId: this.courseDetail.id,questionId:questionId}).then(res => {
                      res = res.data;
                      if (res.code === 200 ) {

                      } else if (res.code > 300 && res.code < 400) {
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
                      }
                  }).catch(error => {
                      //reject(error)
                      console.log(error);
                  })
              }
          }
          //处理高频试题
          if (this.ccpId.indexOf("HIGH_ERROR_COLLECTION_") != -1){
              if(rightOrError=='right') {
                  //做对了，先删除高频错题集中的
                  //let data = await delHighErrorExamQuestion(this.courseDetail.id, questionId);
                  delHighErrorExamQuestion({ccpId: this.courseDetail.id,questionId:questionId}).then(res => {
                      res = res.data;
                      if (res.code === 200 ) {

                      } else if (res.code > 300 && res.code < 400) {
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
                      }
                  }).catch(error => {
                      //reject(error)
                      console.log(error);
                  })
              }
          }
      },
      async cacheErrorExamQuestion(ccpId,questionId){
          //一个课程的所有错题都是放在一起，所以用courseId
          if(ccpId.indexOf("ERROR_COLLECTION_")==-1) {
              //let data = await cacheErrorExamQuestion(this.courseDetail.id, questionId);
              //如果返回未登录的信息要跳转到登录页面去
              cacheErrorExamQuestion({ccpId: this.courseDetail.id,questionId:questionId}).then(res => {
                  res = res.data;
                  if (res.code === 200 ) {
                      //
                  } else if (res.code > 300 && res.code < 400) {
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
                  }
              }).catch(error => {
                  //reject(error)
                  console.log(error);
              })
          }
      },
      goQuestion(lastOrNext){
          if('last'==lastOrNext){
              this.answerQuestion('2');
              this.flush();
          }
          if('next'==lastOrNext){
              this.answerQuestion('1');
              this.flush();
          }
      },
      flush(){
          //setTimeout(() => {
              this.hackReset = false;
              this.$nextTick(() => {
                  this.hackReset = true;
              });
          //}, 5)
      },
      goBack(){
          this.$router.go(-1);
      },
      closeDialog(){
          this.isLast = false;
      },
      closeNoteDialog(){
         this.isNote = false;
      },
      answerQuestion(status){


          let data = getStore("examQuestions_"+this.ccpId);
          let dataArray = JSON.parse(data) || [];    //转换为array对象
          this.continueStartIndex = getStore("continueStartIndex_"+this.ccpId);

          if(Number(this.continueStartIndex) > dataArray.length){
              this.continueStartIndex = dataArray.length;//对于超过现存数据的索引值，强制等于
          }
          if(Number(this.continueStartIndex) <= dataArray.length){
              if(status=='1') {
                  if(this.continueStartIndex<(dataArray.length-1)) {
                      this.continueStartIndex = Number(this.continueStartIndex) + Number(1);
                  }else{
                      this.isLast = true;
                  }
              }else if(status=='2'){
                  if(this.continueStartIndex>0){
                      this.continueStartIndex = Number(this.continueStartIndex) - Number(1);
                  }
              }else if(status=='0'){
                  this.continueStartIndex = Number(this.continueStartIndex);
              }
              setStore("continueStartIndex_"+this.ccpId,this.continueStartIndex);

              if(Number(this.continueStartIndex) >= dataArray.length){
                  this.continueStartIndex = dataArray.length-1;//保险，因为增加了hadleErrorAndHighErrorEollection这个函数对这个值的操作
              }

              if(this.continueStartIndex<0){
                  this.continueStartIndex = 0;
              }
              if(dataArray[this.continueStartIndex].questionType=='1'){
                  this.allFalse();
                  this.singleChoice=true;
                  this.singleChoiceContent = dataArray[this.continueStartIndex];
                  this.questionId = this.singleChoiceContent.id;
              }else if(dataArray[this.continueStartIndex].questionType=='2'){
                  this.allFalse();
                  this.multiChoice=true;
                  this.multichoicecontent = dataArray[this.continueStartIndex];
                  this.questionId = this.multichoicecontent.id;
              }else if(dataArray[this.continueStartIndex].questionType=='3'){
                  this.allFalse();
                  this.judgeQuestion=true;
                  this.judgequestioncontent = dataArray[this.continueStartIndex];
                  this.questionId = this.judgequestioncontent.id;
              }else if(dataArray[this.continueStartIndex].questionType=='4'){
                  this.allFalse();
                  this.fillBlank=true;
                  this.fillBlankContent = dataArray[this.continueStartIndex];
                  this.questionId = this.fillBlankContent.id;
              }else if(dataArray[this.continueStartIndex].questionType=='5'){
                  this.allFalse();
                  this.simpleQuestion=true;
                  this.simpleQuestionContent = dataArray[this.continueStartIndex];
                  this.questionId = this.simpleQuestionContent.id;
              }
              this.$emit('setContinueStartIndex',Number(this.continueStartIndex)+1);
              this.$emit('setTotalQuestionsCount',dataArray.length);

          }else{
              console.log("结束做题");
          }
      },
      allFalse(){
          this.singleChoice = false;
          this.multiChoice = false;
          this.judgeQuestion =false;
          this.fillBlank = false;
          this.simpleQuestion = false;
      },


               touchStart: function (ev) {
                ev = ev || event;

                if (ev.touches.length == 1) { //tounches类数组，等于1时表示此时有只有一只手指在触摸屏幕
                    this.startX = this.lastX = ev.touches[0].clientX; // 记录开始位置
                    //this.lastY = ev.touches[0].clientX;
                    this.startY = this.lastY = ev.touches[0].clientY; // 记录开始位置

                }
            },
            touchMove: function (ev) {
                //初始化是否滑动试题
                this.slideFlag = false;
                ev = ev || event;
                let slideWidth = document.body.offsetWidth;


                if (ev.touches.length == 1) {
                    // 实时的滑动的距离-起始位置=实时移动的位置
                    this.disX = this.lastX - this.startX;
                    this.disY = this.lastY - this.startY;
                    // 计算两次移动距离Y>X就不启动滑动动画,防止误滑
                    //console.log("ev.touches[0].clientX:"+ev.touches[0].clientX);
                    //console.log("ev.touches[0].clientY:"+ev.touches[0].clientY);
                    var disX = ev.touches[0].clientX - this.lastX
                    var disY = ev.touches[0].clientY - this.lastY

                    if (Math.abs(disX) > Math.abs(disY)) {
                        this.con.translateX = 'translateX(' + this.disX + 'px)';
                    }
                    // 记录一次坐标值
                    this.lastX = ev.touches[0].clientX;
                    this.lastY = ev.touches[0].clientY;
                }
            },
            touchEnd: function (ev) {
                console.log("this.disY"+this.disY);
                console.log("this.disX"+this.disX);

                if(Math.abs(this.disY) > Math.abs(this.disX)){
                    //此种情况是下拉了，不要滑动
                    console.log("不要滑动")
                    return;
                }
                ev = ev || event;
                let slideWidth = document.body.offsetWidth;
                if (ev.changedTouches.length == 1) {
                    let endX = ev.changedTouches[0].clientX;
                    this.disX = endX - this.startX;

                    this.con.translateX = 'translateX(0px)';
                    // 只有滑动大于一半距离才触发
                    if (Math.abs(this.disX) > (slideWidth / 4)) {
                        if (this.disX < 0) {
                            console.log('左滑');
                            this.test = "左滑"+new Date().getTime();

                            this.answerQuestion('1');
                            this.flush();
                        } else {
                            console.log('右滑');

                            this.test = '右滑'+new Date().getTime();
                            this.answerQuestion('2');
                            this.flush();
                        }
                    }
                }
            }
  },
  components: {
    headerTop,
    singleChoice,
    multiChoice,
      judgeQuestion,
      fillBlank,
      simpleQuestion,
    footertabs
  }
}
</script>
<style lang="sass" scoped>

.box
  margin: 0
    padding: 0
  padding-bottom: 50px
    width: 100%
    height: 100%
    background: yellow
    position: absolute

</style>
