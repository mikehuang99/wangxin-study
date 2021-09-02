<template>
  <div class="icon_link_zone" id="icon_link_zone">
			<ul class="icon_link_eqt">
				<li v-for="(value, index) in indexClass" :key="index" @click="goQuestion(value.typeId, value.className,value.classImg)">
          <!--
					<nuxt-link to="/coursehome" :data-id="value.classPid">
						<img width="40" :src="value.showClassImg" alt="" />
						<p>{{value.showClassName}}</p>
					</nuxt-link>-->
          <img width="40" :src="value.classImg" alt="" />
          <p>{{value.className}}</p>
				</li>
			</ul>
    <div v-show="confirmWhetherToContinue" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999998; filter: alpha(opacity=60); opacity: 0.5 !important;">
      <!--该DIV为遮罩层-->
    </div>
    <div style="position:fixed; *position:absolute; width:280px; height:160px; top:50%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 999999;border-radius:5px;"
         v-show="confirmWhetherToContinue">
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">[{{className}}] 提 示</span>
      </div>
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;right:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold" @click="close()">&nbsp;&nbsp;X&nbsp;&nbsp;</span>
      </div>
      <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">警告：如果点击"重新开始",<br/>将清空本节历史练习记录。</span>
      </div>
      <div @click="continueAnswerQuestion()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;left: 20px;width: 120px;height: 30px;border: 1px #333333;background-color: greenyellow;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >继续做题</span>
      </div>
      <div @click="restartAnswerQuestion()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;right: 20px;width: 60px;height: 30px;border: 1px #333333;background-color: red;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >重新开始</span>
      </div>
    </div>

    <div v-show="noQuestions" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999999; filter: alpha(opacity=60); opacity: 0.5 !important;">
      <!--该DIV为遮罩层-->
    </div>
    <div style="position:fixed; *position:absolute; width:280px; height:160px; top:50%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 1999999;border-radius:5px;"
         v-show="noQuestions">
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">[{{className}}] 提 示</span>
      </div>
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;right:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold" @click="closeWarn()">&nbsp;&nbsp;X&nbsp;&nbsp;</span>
      </div>
      <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">没有试题</span>
      </div>
    </div>
		</div>
</template>
<script>
import {mapState, mapMutations} from 'vuex'
//import {queryNoteExamQuestions,queryHighErrorExamQuestions, queryFavoritesExamQuestions,queryErrorExamQuestions } from '../../../ajax/getData'
//import {getStore,removeStore,setStore} from '../../../config/common.js'

import {queryNotYetDoneExamQuestions,queryExamQuestions,queryErrorExamQuestions,cacheErrorExamQuestion,delErrorExamQuestion,queryHighErrorExamQuestions,cacheHighErrorExamQuestion,delHighErrorExamQuestion,queryFavoritesExamQuestions,cacheFavoritesExamQuestion,
    delFavoritesExamQuestion,queryNoteExamQuestions,queryNoteContentByQuestionId,cacheNoteExamQuestion,delNoteExamQuestion} from '~/api/exam'
import { logout } from '~/store/common'
import {getStore,removeStore,setStore} from '~/store/examStore.js'
import { setStore as setLocalStore} from '~/store/common.js'

import {findCourseDetailHadLogin} from '~/api/index'
import VueCookie from 'vue-cookie'
import {viewSystemConfig} from '~/api/system'
import {findDoExercisesDataByCcpId} from '~/api/exam'


export default {
    data(){
      return{
          /*
          indexClass: [
                {"classPid":"1080747124211781634","showClassName":"错题回顾","showClassImg":"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/97ee48528fb24166b7012f4197ec7269.png"},
                {"classPid":"1080744766018883585","showClassName":"我的收藏","showClassImg":"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/20a403e8a2624feab9181031e0c046f0.png"},
                {"classPid":"1080750603160457217","showClassName":"高频错题","showClassImg":"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/007ed911137c47b9a93fc6cce85ac241.png"},
                {"classPid":"1080387521456295937","showClassName":"我的笔记","showClassImg":"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/50d3edbcbf7346768987359160d1405e.png"}
              ],
           */
          indexClass: [
                {"typeId":"error","className":"错题回顾","classImg":"/wxmobile/img/question_icon/error.png"},
                {"typeId":"favorites","className":"我的收藏","classImg":"/wxmobile/img/question_icon/myfavorites.png"},
                {"typeId":"highError","className":"高频错题","classImg":"/wxmobile/img/question_icon/high_error.png"},
                {"typeId":"note","className":"我的笔记","classImg":"/wxmobile/img/question_icon/note.png"}
              ],
          confirmWhetherToContinue:false,
          ccpId: '',
          typeId: '',
          className: '',
          noQuestions:'',
          courseDetail:{
              id:''
          },
          isOpenDoExercisesDataSync: 0 //是否开启刷题数据同步,1为开启，0为关闭

      }
    },
  computed: {

  },
    mounted(){
        let systemConfig = JSON.parse(getStore("systemConfig"));
        if(systemConfig==null){
            this.getSystemConfig();
        }else {
            this.isOpenDoExercisesDataSync = systemConfig.isOpenDoExercisesDataSync;
        }

        //this.ccpId = "ERROR_COLLECTION_"+this.courseDetail.id;
        this.courseDetail.id = this.$route.query.courseId
        this.findCourseDetail();
        console.log("==888888888====>>>>"+this.ccpId);
    },
  methods: {
    ...mapMutations([
      'COURSE_ID', 'COURSE_PARAMS', 'COURSE_SORT','COURSE_TYPE'
    ]),
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

                      console.log("key:"+key);
                      console.log("dataListArray[key]:"+dataListArray[key]);

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
      findCourseDetail(){
          let id = this.$route.query.id;

          this.userInfo = JSON.parse(VueCookie.get("wangxinMobileUserInfo"));
          if (VueCookie.get("wangxinMobileToken") && this.userInfo) {
              //如果返回未登录的信息要跳转到登录页面去
              findCourseDetailHadLogin({id: id}).then(res => {
                  res = res.data;
                  if (res.code === 200 ) {
                      this.courseDetail = res.data;
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
          }else{
              logout();
              this.$router.push({path: '/login', query: {}});
          }
      },
      goQuestion (typeId,className, classImg) {
          this.confirmWhetherToContinue=true;
          this.typeId = typeId;
          this.className = className;
          console.log("typeId:"+typeId);
          console.log("className: "+className);
          console.log("classImg:"+classImg);
    },
      restartAnswerQuestion(){
          if(this.typeId=='error'){
              this.ccpId = "ERROR_COLLECTION_"+this.courseDetail.id;
              this.restartErrorAnswerQuestion();
          }else if(this.typeId=='favorites'){
              this.ccpId = "FAVORITES_COLLECTION_"+this.courseDetail.id;
              this.restartFavoritesAnswerQuestion();
          }else if(this.typeId=='highError'){
              this.ccpId = "HIGH_ERROR_COLLECTION_"+this.courseDetail.id;
              this.restartHighErrorAnswerQuestion();
          }else if(this.typeId=='note'){
              this.ccpId = "NOTE_COLLECTION_"+this.courseDetail.id;
              this.restartNoteAnswerQuestion();
          }
      },
      continueAnswerQuestion(){
            if(this.typeId=='error'){
                this.ccpId = "ERROR_COLLECTION_"+this.courseDetail.id;
                this.continueErrorAnswerQuestion();
            }else if(this.typeId=='favorites'){
                this.ccpId = "FAVORITES_COLLECTION_"+this.courseDetail.id;
                this.continueFavoritesAnswerQuestion();
            }else if(this.typeId=='highError'){
                this.ccpId = "HIGH_ERROR_COLLECTION_"+this.courseDetail.id;
                this.continueHighErrorAnswerQuestion();
            }else if(this.typeId=='note'){
                this.ccpId = "NOTE_COLLECTION_"+this.courseDetail.id;
                this.continueNoteAnswerQuestion();
            }
      },
      async restartFavoritesAnswerQuestion(){
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
/*
          let data = await queryFavoritesExamQuestions(this.courseDetail.id);

          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              setStore("examQuestions_"+this.ccpId,data);
              setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
          let data = "";
          queryFavoritesExamQuestions({ccpId: this.courseDetail.id}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  data = res.data.list;
                  if(data.length==0){
                      this.confirmWhetherToContinue = false;
                      this.noQuestions = true;
                      return;
                  }
                  setStore("examQuestions_"+this.ccpId,data);
                  setStore("continueStartIndex_"+this.ccpId,0);
                  //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转

                  this.findDoExercisesDataByCcpId();

                  //this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId: this.courseDetail.id}});
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
      async continueFavoritesAnswerQuestion(){
          //let data = await queryExamQuestions(this.ccpId, this.type);
          console.log("this.ccpId ======>"+this.ccpId)

          /*
          let data = await queryFavoritesExamQuestions(this.courseDetail.id);

          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              console.log("data.examQuestions.length:"+data.length);
              setStore("examQuestions_"+this.ccpId,data);
              let startIndex = getStore("continueStartIndex_"+this.ccpId);
              if(startIndex == null){
                  setStore("continueStartIndex_"+this.ccpId,0);
              }else if(startIndex >= data.length){
                  setStore("continueStartIndex_"+this.ccpId,data.length-1);
              }

              //setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
          queryFavoritesExamQuestions({ccpId: this.courseDetail.id}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  let data = res.data.list;
                  if(data.length==0){
                      this.confirmWhetherToContinue = false;
                      this.noQuestions = true;
                      return;
                  }
                  console.log("data.examQuestions.length:"+data.length);
                  setStore("examQuestions_"+this.ccpId,data);
                  let startIndex = getStore("continueStartIndex_"+this.ccpId);
                  if(startIndex == null){
                      setStore("continueStartIndex_"+this.ccpId,0);
                  }else if(startIndex >= data.length){
                      setStore("continueStartIndex_"+this.ccpId,data.length-1);
                  }

                  //setStore("continueStartIndex_"+this.ccpId,0);
                  //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转

                  this.findDoExercisesDataByCcpId();

                  //this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId: this.courseDetail.id}});
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
      async restartErrorAnswerQuestion(){
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

          //设置索引为0，直接调用继续学习
          setStore("continueStartIndex_"+this.ccpId,0);
          this.continueErrorAnswerQuestion();

          /*
          let data = await queryErrorExamQuestions(this.courseDetail.id);
          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              setStore("examQuestions_"+this.ccpId,data);
              setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      async continueErrorAnswerQuestion(){
          //let data = await queryExamQuestions(this.ccpId, this.type);
          console.log("this.ccpId ======>"+this.ccpId)

          queryErrorExamQuestions({ccpId: this.courseDetail.id}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  let data = res.data.list;
                  if(data.length==0){
                      this.confirmWhetherToContinue = false;
                      this.noQuestions = true;
                      return;
                  }
                  console.log("data.examQuestions.length:"+data.length);
                  setLocalStore("examQuestions_"+this.ccpId,data);
                  let startIndex = getStore("continueStartIndex_"+this.ccpId);
                  if(startIndex == null){
                      setLocalStore("continueStartIndex_"+this.ccpId,0);
                  }else if(startIndex >= data.length){
                      setLocalStore("continueStartIndex_"+this.ccpId,data.length-1);
                  }
                  let ar = [];
                  setLocalStore("answer_right_"+this.ccpId,ar);

                  //setStore("continueStartIndex_"+this.ccpId,0);
                  //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转

                  this.findDoExercisesDataByCcpId();
                  //this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId: this.courseDetail.id}});
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

          /*
          let data = await queryErrorExamQuestions(this.courseDetail.id);

          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              console.log("data.examQuestions.length:"+data.length);
              setStore("examQuestions_"+this.ccpId,data);
              let startIndex = getStore("continueStartIndex_"+this.ccpId);
              if(startIndex == null){
                  setStore("continueStartIndex_"+this.ccpId,0);
              }else if(startIndex >= data.length){
                  setStore("continueStartIndex_"+this.ccpId,data.length-1);
              }
              let ar = [];
              setStore("answer_right_"+this.ccpId,ar);

              //setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      async restartHighErrorAnswerQuestion(){
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

          //先设置索引为0，直接调用继续做题
          setStore("continueStartIndex_"+this.ccpId,0);
          this.continueHighErrorAnswerQuestion();

          /*
          let data = await queryHighErrorExamQuestions(this.courseDetail.id);
          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              setStore("examQuestions_"+this.ccpId,data);
              setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      async continueHighErrorAnswerQuestion(){
          //let data = await queryExamQuestions(this.ccpId, this.type);
          console.log("this.ccpId ======>"+this.ccpId)
          queryHighErrorExamQuestions({ccpId: this.courseDetail.id}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  let data = res.data.list;
                  if(data.length==0){
                      this.confirmWhetherToContinue = false;
                      this.noQuestions = true;
                      return;
                  }
                  console.log("data.examQuestions.length:"+data.length);
                  setLocalStore("examQuestions_"+this.ccpId,data);
                  let startIndex = getStore("continueStartIndex_"+this.ccpId);
                  if(startIndex == null){
                      setLocalStore("continueStartIndex_"+this.ccpId,0);
                  }else if(startIndex >= data.length){
                      setLocalStore("continueStartIndex_"+this.ccpId,data.length-1);
                  }
                  let ar = [];
                  setLocalStore("answer_right_"+this.ccpId,ar);

                  //setStore("continueStartIndex_"+this.ccpId,0);
                  //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转

                  this.findDoExercisesDataByCcpId();

                  //this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId: this.courseDetail.id}});
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
          /*
          let data = await queryHighErrorExamQuestions(this.courseDetail.id);

          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              console.log("data.examQuestions.length:"+data.length);
              setStore("examQuestions_"+this.ccpId,data);
              let startIndex = getStore("continueStartIndex_"+this.ccpId);
              if(startIndex == null){
                  setStore("continueStartIndex_"+this.ccpId,0);
              }else if(startIndex >= data.length){
                  setStore("continueStartIndex_"+this.ccpId,data.length-1);
              }
              let ar = [];
              setStore("answer_right_"+this.ccpId,ar);

              //setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      async restartNoteAnswerQuestion(){
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

          //先设置索引为0，再直接调用继续做题
          setStore("continueStartIndex_"+this.ccpId,0);
          this.continueNoteAnswerQuestion();

          /*
          let data = await queryNoteExamQuestions(this.courseDetail.id);
          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              setStore("examQuestions_"+this.ccpId,data);
              setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      async continueNoteAnswerQuestion(){
          //let data = await queryExamQuestions(this.ccpId, this.type);
          console.log("this.ccpId ======>"+this.ccpId)

          queryNoteExamQuestions({ccpId: this.courseDetail.id}).then(res => {
              res = res.data;
              if (res.code === 200 ) {
                  let data = res.data.list;
                  if(data.length==0){
                      this.confirmWhetherToContinue = false;
                      this.noQuestions = true;
                      return;
                  }
                  console.log("data.examQuestions.length:"+data.length);
                  setLocalStore("examQuestions_"+this.ccpId,data);
                  let startIndex = getStore("continueStartIndex_"+this.ccpId);
                  if(startIndex == null){
                      setLocalStore("continueStartIndex_"+this.ccpId,0);
                  }else if(startIndex >= data.length){
                      setLocalStore("continueStartIndex_"+this.ccpId,data.length-1);
                  }
                  let ar = [];
                  setLocalStore("answer_right_"+this.ccpId,ar);

                  //setStore("continueStartIndex_"+this.ccpId,0);
                  //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转

                  this.findDoExercisesDataByCcpId();

                  //this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId,courseId: this.courseDetail.id}});
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

          /*
          let data = await queryNoteExamQuestions(this.courseDetail.id);

          if ((data.status&&data.status==500)&&(data.message&&data.message=='token异常')) {
              //this.$router.push({ path: '/' }) // 跳转
              this.$router.push({ path: '/login' }) // 跳转
          } else {
              if(data.length==0){
                  this.confirmWhetherToContinue = false;
                  this.noQuestions = true;
                  return;
              }
              console.log("data.examQuestions.length:"+data.length);
              setStore("examQuestions_"+this.ccpId,data);
              let startIndex = getStore("continueStartIndex_"+this.ccpId);
              if(startIndex == null){
                  setStore("continueStartIndex_"+this.ccpId,0);
              }else if(startIndex >= data.length){
                  setStore("continueStartIndex_"+this.ccpId,data.length-1);
              }
              let ar = [];
              setStore("answer_right_"+this.ccpId,ar);

              //setStore("continueStartIndex_"+this.ccpId,0);
              //this.$router.push({ path: '/exam/answerQuestion?id='+this.ccpId }); //跳转
              this.$router.push({path: '/exam/answerQuestion', query: {id: this.ccpId}});
          }*/
      },
      close(){
          this.confirmWhetherToContinue = false;
      },
      closeWarn(){
          this.noQuestions = false;
      }
  }
}
</script>
<style lang="sass">
  .icon_link_zone
     position: relative
     .meun_link
        overflow: hidden
        position: absolute
        left: 50%
        bottom: 6px
        margin-left: -12px
        li
          width: 6px
          height: 6px
          background: url(/img/banner03.png) no-repeat 0 0
          float: left
          margin: 0 6px 0 0
          &.on
            background-image: url(/img/banner01.png)
  .icon_link_eqt
    man-height: 16rem
    overflow: hidden
    background: #fff
    padding: 1.3rem 0 0 0
    li
      width: 25%
      float: left
      text-align: center
      margin: 0 0 1.3rem 0
      a
        display: block
      p
        font-size: 1rem
        color: #484848
        margin: 7px 0 0 0
</style>
