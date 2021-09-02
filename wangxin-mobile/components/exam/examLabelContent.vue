<template>
  <div>
    <!--
    <header-top :name="this.name" :back="this.back" :className="this.className" :backgroundColor="this.backgroundColor"></header-top>
    -->
    <div class="container full">
      <!-- 选项卡 -->
      <ul class="label_tabs">

          <li v-for="(items, index) in tabArr" :key="index"><a @click="changeActive(index)" :class="{on: changeActivated === index}"
          style="font-size: 16px">{{items}}</a></li>
      </ul>
      <div class="xiangqing" v-if="status === 0" style="margin-left:10px;margin-top:20px;position: relative;margin-left:10px;overflow-y:scroll; overflow-x:scroll;height: calc(100% - 200px)">
        <div style="font-size: 16px;">试题共{{this.courseDetail.examQuestionCourseCount}}道</div>
        <ul class="chapter_lesson">
          <li v-for="(item, index) in this.courseDetail.chapterList" :key="index">
            <span style="font-size: 18px;font-weight:bold" :id="item.id | fil" name="chapterName_exam" @click="clickChapterExam(item.id,item.chapterName,2)">
              第{{index+1}}章
            {{item.chapterName}}(试题{{item.examQuestionChapterCount}}道)
            </span>
            <ul>
              <li v-for="(item2, index2) in item.lessonList" :key="index2" >
                <p :id="item2.id | fil" name="perioidName_exam" style="margin-left: 10px;font-size: 16px;"  @click="clickLessonExam(item2.id,item2.lessonName,3)">
                  第{{index2+1}}课时
                  {{item2.lessonName}}
                  <span v-if="isOpenDoExercisesDataSync==1">({{item2.examQuestionLessonCountHadFinished}}/{{item2.examQuestionLessonCount}})</span>
                  <span v-if="isOpenDoExercisesDataSync==0">({{item2.examQuestionLessonCount| hadDone(item2.id)}})</span>
                </p>
              </li>
            </ul>
          </li>
        </ul>
        <div v-show="confirmWhetherToContinue" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999998; filter: alpha(opacity=60); opacity: 0.5 !important;">
          <!--该DIV为遮罩层-->
        </div>
        <div style="position:fixed; *position:absolute; width:280px; height:160px; top:50%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 999999;border-radius:5px;"
        v-show="confirmWhetherToContinue">
          <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
            <span style="font-size: 16px;font-weight: bold">提 示</span>
          </div>
          <div style="box-shadow: 3px 3px 2px #888888;text-align: left;right:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
            <span style="font-size: 16px;font-weight: bold" @click="close()">&nbsp;&nbsp;X&nbsp;&nbsp;</span>
          </div>
          <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
            <span style="font-size: 16px;font-weight: bold">警告：如果点击"重新开始",<br/>将清空本节历史练习记录。</span>
          </div>
          <div style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;left: 20px;width: 120px;height: 30px;border: 1px #333333;background-color: greenyellow;border-radius:3px;">
            <span style="font-size: 12px;font-weight: bold" @click="continueAnswerQuestion()">继续做题</span>
          </div>
          <div style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;right: 20px;width: 60px;height: 30px;border: 1px #333333;background-color: red;border-radius:3px;">
            <span style="font-size: 12px;font-weight: bold" @click="restartAnswerQuestion()">重新开始</span>
          </div>
        </div>
      </div>
      <no-content :content="noContent" v-show="isNoContent"></no-content>
    </div>
    <!--
		<transition name="loading">
			<loading v-show="showLoading"></loading>
		</transition>-->
    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" :toUrlParams="toUrlParams" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
  </div>
</template>
<script>
import headerTop from '~/components/common/header.vue'
import {mapMutations} from 'vuex'
import { mapState } from 'vuex'

//import loading from '~/components/common/loading.vue'
import {getStore,removeStore,setStore} from '~/store/examStore.js'
import noContent from '~/components/common/no_content/no_content.vue'

import {findCourseDetailHadLogin} from '~/api/index'
import {queryExamQuestions,findDoExercisesDataByCcpId} from '~/api/exam'


import VueCookie from 'vue-cookie'

import { logout } from '~/store/common'

import {setStore as setLocalStore} from '~/store/common.js'
import {viewSystemConfig} from '~/api/system'

import alert from "~/components/common/alert";


export default {
    props: ['courseDetail'],
  data () {
    return {
        isShowAlert: false,
        timeOutFlag: false,
        toUrl: '',
        alertTitle: '',
        alertContent: '',
        toUrlParams: [],

        ccpId:'',//课程ID、章节ID、课时ID
        type:'3',//1代表课程，2代表章节，3代表课时
        confirmWhetherToContinue:false,
      status: 0,
      tabArr: [
        '继续做题'
      ],
      name: '课程详细信息',
      className: 1,
      // 位置左侧是选中的id
      changeActivated: 0,
      backgroundColor: 1,
      back: 0,
      isNoContent: false,
      // 是否显示加载动画
      showLoading: true,
      listArr: [],
      // 无内容样式
      noContent: {
        class: 'menu',
        name: '你没有订单'
      },
        //courseDetail:{},
        userNo: '',
        isOpenDoExercisesDataSync: 0, //是否开启刷题数据同步,1为开启，0为关闭
        courseId: ''
    }
  },
  mounted () {
      this.userInfo = JSON.parse(VueCookie.get("wangxinMobileUserInfo"));
      if (VueCookie.get("wangxinMobileToken") && this.userInfo) {
          this.userNo = this.userInfo.userNo;
      }else{
          logout();
          this.$router.push({path: '/login', query: {}});
      }

      let systemConfig = JSON.parse(getStore("systemConfig"));
      if(systemConfig==null){
          this.getSystemConfig();
      }else {
          this.isOpenDoExercisesDataSync = systemConfig.isOpenDoExercisesDataSync;
      }



      //this.ccpId = this.$route.query.id;
      this.courseId = this.$route.query.courseId;
    this.getorder()
     // this.findCourseDetail()
  },
  computed: {

  },
    filters:{
      fil(value){
          return value+"_exam";
      },
     hadDone(value,id){
         let continueStartIndex =  getStore("continueStartIndex_"+id);
         if(continueStartIndex==null){
             continueStartIndex=0;
         }
         return continueStartIndex+"/"+value;

         /*
         let rightCount = JSON.parse(getStore("answer_right_"+id))==null?0:JSON.parse(getStore("answer_right_"+id)).length;
         let errorCount = JSON.parse(getStore("answer_error_"+id))==null?0:JSON.parse(getStore("answer_error_"+id)).length;
         let hadDoneCount = rightCount+errorCount;
         return hadDoneCount+"/"+value;*/
     }
    },
  methods: {
    ...mapMutations([
      'ORDERDATA'
    ]),
      closeAlert(){
          this.isShowAlert = false;
      },
      getSystemConfig () {
          viewSystemConfig({

          }).then(res => {
              res = res.data;
              if (res.code === 200) {
                  // console.log("====>"+res.data)
                  setLocalStore("systemConfig", JSON.stringify(res.data));
                  this.isOpenDoExercisesDataSync = res.data.isOpenDoExercisesDataSync;
              } else if (res.code > 300 && res.code < 400) {
                  setLocalStore("lastUrlTemp",window.location.href);
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
          }).catch(() => {
          })
      },

      async restartAnswerQuestion(){
          //清除上次的答案缓存开始
          let answer_ids = JSON.parse(getStore("answer_ids_"+this.ccpId));
          if(answer_ids!=null){
              for(let i=0;i<answer_ids.length;i++){
                  removeStore(answer_ids[i]);
              }
          }
          removeStore("answer_ids_"+this.ccpId);

          removeStore("answer_right_"+this.ccpId);
          removeStore("answer_error_"+this.ccpId);
          //清除上次的答案缓存结束

          //清除索引直接调用继续做题
          setStore("continueStartIndex_"+this.ccpId,0);
          this.continueAnswerQuestion();

          /*
          let data = await queryExamQuestions(this.ccpId, this.type);
          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              setStore("examQuestions_"+this.ccpId,data);
              setStore("continueStartIndex_"+this.ccpId,0);
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      async continueAnswerQuestion(){

          queryExamQuestions({ccpId: this.ccpId,type: this.type}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  let data = res.data.list || [];
                  if(data.length==0){
                      this.isShowAlert = true;
                      this.alertTitle = "提示";
                      this.alertContent = "没有题目可以练习";
                      return;
                  }
                  console.log("data.examQuestions.length:"+data.length);
                  setLocalStore("examQuestions_"+this.ccpId,data);
                  let startIndex = getStore("continueStartIndex_"+this.ccpId);
                  console.log("startIndex:"+startIndex);
                  console.log("startIndex == null"+ (startIndex == null))
                  if(startIndex == null){
                      setLocalStore("continueStartIndex_"+this.ccpId,0);
                  }
                  //this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId:this.courseDetail.id}});
                  this.findDoExercisesDataByCcpId();
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
              }else if (res.code === 408){
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = res.msg + "，即将跳转...请稍后";
                  this.timeOutFlag = true;
                  this.toUrl = "/courseDetail/index";
                  this.toUrlParams = {"id":this.courseId,"timeStamp":new Date().getTime()};

                  return;
              }else{
                  console.log("系统错误");
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = res.msg;
                  return;
              }
          }).catch(error => {
              //reject(error)
              console.log(error);
          })

        /*
          let data = await queryExamQuestions(this.ccpId, this.type);
          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              console.log("data.examQuestions.length:"+data.length);
              setStore("examQuestions_"+this.ccpId,data);
              let startIndex = getStore("continueStartIndex_"+this.ccpId);
              console.log("startIndex:"+startIndex);
              console.log("startIndex == null"+ (startIndex == null))
              if(startIndex == null){
                  setStore("continueStartIndex_"+this.ccpId,0);
              }
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      findDoExercisesDataByCcpId(){
          if( this.isOpenDoExercisesDataSync!=1){//是否开启刷题数据同步,1为开启，0为关闭
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId:this.courseDetail.id}});
              return;//不再往下执行
          }
          findDoExercisesDataByCcpId({ccpId: this.ccpId}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  let data = res.data;
                  let dataListArray = JSON.parse(data.dataList);
                  for (let key in dataListArray) {
                      setLocalStore(key,dataListArray[key]);
                  }
                  this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId:this.courseDetail.id}});

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
      close(){
          this.confirmWhetherToContinue = false;
      },
      async clickChapterExam(chapterId,chapterName,type){
          this.ccpId= chapterId;
          this.type= type;
          let x=document.getElementsByName("perioidName_exam");
          for(let i=0;i<x.length;i++){
              x[i].style = "margin-left: 10px;font-size: 16px;";
          }
          let y=document.getElementsByName("chapterName_exam");
          for(let i=0;i<y.length;i++){
              y[i].style = "margin-left: 10px;font-size: 16px;";
          }
          document.getElementById(chapterId+"_exam").style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
          setStore("selected_chapter_id_exam_index",chapterId+"_exam")

          /*
          let data = await queryExamQuestions(chapterId, 2);
          if (data.status) {
          } else {

          }*/

      },
      async clickLessonExam(lessonId,lessonName,type){
          this.confirmWhetherToContinue=true;
          this.ccpId= lessonId;
          this.type= type;
          let x=document.getElementsByName("perioidName_exam");
          for(let i=0;i<x.length;i++){
              x[i].style = "margin-left: 10px;font-size: 16px;";
          }
          let y=document.getElementsByName("chapterName_exam");
          for(let i=0;i<y.length;i++){
              y[i].style = "margin-left: 10px;font-size: 16px;";
          }
          document.getElementById(lessonId+"_exam").style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
          setStore("selected_lesson_id_exam_index",lessonId+"_exam")
      },
      gotop(lessonId,lessonName){
          let x=document.getElementsByName("perioidName");
          for(let i=0;i<x.length;i++){
              x[i].style = "margin-left: 10px;font-size: 16px;";
          }
        document.getElementById(lessonId).style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
        setStore("selected_lesson_id_index",lessonId)
          /*
        axios.get('/mobileApi/findByVideoLocalNumber?lessonId='+ lessonId +'&timeStamp='+new Date().getTime()).then(res => {
            let v = res.data.videoOasUrl;
            this.$emit('setVideoOasUrl',v)
            this.$emit('lessonName',lessonName)

            if(typeof (v) == 'undefined'|| v === null){
                this.$emit('setVideoOasUrl','no');
                this.$emit('playVideo',false)
                this.$emit('playImg',true)
            }else{
                this.$emit('playVideo',true)
                this.$emit('playImg',false)
            }
        })*/
    },
    async getorder () {
      let phone = getStore('user')
        /*
      let data = await getOrderList(phone)
      if (data.status) {
        this.listArr = data.result
      }
      if (JSON.stringify(data.result) === '{}') { // 如果没有数据那就 该咋咋把
        this.isNoContent = true
      }
      this.showLoading = false*/
    },
    async goPayTo (status, item, totalMoney) {
      if (!parseInt(status)) {
        this.$router.push({path: '/order/payOrder', query: this.$route.query.id})
        let phone = getStore('user') // 拿到用户手机号
        let orderArr = []
        item.forEach(function (element) { // 循环当前订单一共有几个课程
          orderArr.push(element.courseOrder) // 每个课程的id放进去
        }, this)
          /*
        let data = await submitOrder(phone, orderArr.join('-'), totalMoney, true) // 提交订单
        data.orderData.orderId = item[0].orderId
        this.ORDERDATA(data.orderData)*/
      }
    },
    async changeActive (index) {
        console.log("===============>>>>"+index)

      this.status = index;
      this.isNoContent = false
        setTimeout(function (){
            if(getStore("selected_lesson_id_index") == null || document.getElementById(getStore("selected_lesson_id_index"))==null){

            }else{
                document.getElementById(getStore("selected_lesson_id_index")).style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
            }
            if(getStore("selected_lesson_id_exam_index") == null || document.getElementById(getStore("selected_lesson_id_exam_index"))==null){

            }else{
                document.getElementById(getStore("selected_lesson_id_exam_index")).style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
            }
            if(getStore("selected_chapter_id_exam_index") == null || document.getElementById(getStore("selected_chapter_id_exam_index"))==null){

            }else{
                document.getElementById(getStore("selected_chapter_id_exam_index")).style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
            }
        }, 200);

      this.changeActivated = index
    }
  },
  components: {
    headerTop,
    noContent,
    //loading
      alert
  }
}
</script>
<style lang="sass" scoped>
@import '~static/css/style.sass'
.container
  margin-bottom: 40px
  .meun
	  margin-top: 10px
		padding-bottom: 0px
  .chapter_lesson
    li
      line-height: 30px
.label_tabs
	overflow: hidden
	border-top: #c7c5c6
	background-color: #fff
	margin: 0px 0 10px 0
	position: relative
	top: 10px
	border-top: 1px solid #ddd
	z-index: 100000
	width: 100%
	li
		width: 25%
		height: 3.5rem
		padding: 0 1rem
		float: left
		a
			height: 100%
			line-height: 3.5rem
			display: block
			text-align: center
			color: #484848
			&.on
				color: red
				border-bottom: 2px solid  red
.tabs_list
	background-color: #fff
	border-top: 1px solid #eee
	border-bottom: 1px solid #eee
	margin: 0px 0 10px 0
	.list_link
		display: block
		.btns
			overflow: hidden
			text-align: right
			padding: 6px 15px 0 0
			margin: 0 0 0 160px
			.cancel
				font-size: 1.2rem
				border: 1px solid #000
				border-color: #ddd
				border-radius: 2px
				display: inline-block
				padding: 5px 12px
    .xiangqing
      padding: 30px 30px 0 0px
      margin: 10px 0 0 10px
      background-color: #ef4040
    .xiangqing_content
      padding: 30px 30px 0 0px
      margin: 100px 0 0 100px
      word-wrap: break-word
      word-break: normal
      font-size: 100px


</style>
