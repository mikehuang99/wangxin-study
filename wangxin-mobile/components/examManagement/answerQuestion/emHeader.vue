<template>
  <header class="header" :class="[this.backgroundColor ? 'fff' : '' ]">
    <a href="javascript:" @click="go()" class="header_back_icon_white"></a>
    <div style="width: 50px;height: 90%;padding-top:0px;position: absolute;left: 40px;border:0px dashed #000;text-align: center;">
      <span style="font-weight: bold;font-size: 14px">{{continuestartindex}}/{{totalquestionscount}}</span>
    </div>
    <div style="width: 140px;height: 100%;padding-top:0px;position: absolute;left: 100px;border:0px dashed #000;text-align: left;">
      <span v-if="isReview==1" style="font-size: 5px;color: white">{{this.time}}</span>
      <span v-if="isReview!=1" style="font-size: 5px;color: white">剩{{this.openExamTime}}</span>
    </div>
    <!--
    <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 190px;border:1px dashed #000;text-align: center;">
      <span v-if="!isFavorited" style="font-size: 16px;color: white;text-decoration :underline" @click="cacheFavorites(1)">收藏</span>
      <span v-if="isFavorited" style="font-size: 16px;color: white;text-decoration :underline" @click="cacheFavorites(0)">取消收藏</span>
    </div>
    <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 280px;border:1px dashed #000;text-align: center;">
      <span style="font-size: 16px;color: white;text-decoration :underline" @click="note()">笔记</span>
    </div>-->
    <div v-if="isReview!=1">
      <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 190px;border:0px dashed #000;text-align: center;">
        <span style="font-size: 16px;color: white;text-decoration :underline" @click="openReTakeAnExaminationDialog()">重新考试</span>
      </div>
      <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 280px;border:0px dashed #000;text-align: center;">
        <span v-if="showHandInExamPaperButton" style="font-size: 16px;color: white;text-decoration :underline" @click="handInExamPaper()">去交卷</span>
        <span v-if="!showHandInExamPaperButton" style="font-size: 16px;color: white;text-decoration :underline" @click="continueAnswer()">继续作答</span>
      </div>
    </div>
    <div v-if="isReview==1">
      <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 280px;border:0px dashed #000;text-align: center;">
        <span v-if="showHandInExamPaperButton" style="font-size: 16px;color: white;text-decoration :underline" @click="handInExamPaper()">题目列表</span>
        <span v-if="!showHandInExamPaperButton" style="font-size: 16px;color: white;text-decoration :underline" @click="continueAnswer()">返回题目</span>
      </div>
    </div>
    <div v-show="isReTakeAnExamination" style="background: gray; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%;  z-index: 999998; filter: alpha(opacity=60); opacity: 0.5 !important;">
      <!--该DIV为遮罩层-->
    </div>
    <div style="position:fixed; *position:absolute; width:280px; height:160px; top:50%; left:25%; margin:-50px 0 0 -50px;border: 1px red;background-color:#708090;z-index: 999999;border-radius:5px;"
         v-show="isReTakeAnExamination">
      <div style="box-shadow: 3px 3px 2px #888888;text-align: left;left:10px;line-height: 30px;position:absolute;top:3px;width: auto;height: 30px;border: 1px #333333;background-color:#E6E6FA;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">提示</span>
      </div>
      <div style="text-align: center;line-height: 30px;position:absolute;top:40px;width: 100%;height: 30px;border: 1px #333333;border-radius:3px;">
        <span style="font-size: 16px;font-weight: bold">是否重新考试？<br/>点击"确定"将清空已做答案</span>
      </div>
      <div @click="closeReTakeAnExaminationDialog()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;left: 20px;width: 120px;height: 30px;border: 1px #333333;background-color: greenyellow;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >取消</span>
      </div>
      <div @click="reTakeAnExamination()" style="box-shadow: 3px 3px 2px #888888;text-align: center;line-height: 30px;position:absolute;bottom:10px;right: 20px;width: 60px;height: 30px;border: 1px #333333;background-color: red;border-radius:3px;">
        <span style="font-size: 12px;font-weight: bold" >确定</span>
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

  </header>
</template>
<script>
    import {getStore,removeStore,setStore} from '~/store/common.js'
    import {queryFavoritesExamQuestions,cacheFavoritesExamQuestion,
        delFavoritesExamQuestion} from '~/api/exam'
    import {keepSession,examManagementViewByStudentUserNo} from '~/api/examManagement'
    import { logout } from '~/store/common'
    export default {
  props: ['questionId','continuestartindex', 'totalquestionscount', 'name', 'className', 'backgroundColor', 'back'],
    data(){
      return{
          isReTakeAnExamination:false,
          isHandInExamPaper:false,
          div1Flag: false,
          div2Flag: false,
          time:'',
          favoritesStatus: '0',
          favoritesStatusName : '收藏',
          courseDetail:{
              id:''
          },
          isFavorited: false,
          ccpId:'',
          keepSessionVar: '',
          showHandInExamPaperButton: true,
          isReview: 0,
          examManagementObj: '',
          openExamTime: ''
      }
    },
    mounted(){
        clearTimeout(this.keepSessionVar);
        this.keepSession();

        this.courseDetail.id = this.$route.query.courseId;
        this.ccpId = this.$route.query.id;
        this.examManagementId = this.$route.query.examManagementId;

        this.isReview = this.$route.query.isReview;

        if(this.isReview!=1) {
            this.examManagementViewByStudentUserNo();
        }

        ///这里是获得登录时候的时间，用来和动态的时间做差来求时长
        let s = new Date();
        this.clockon(s);

        setTimeout(() => {
            this.isHadFavorited();
        }, 500)


    },
  methods: {
      openExamformate (openExamTime) {
          if(openExamTime>=10){
              return openExamTime
          }else{
              return `0${openExamTime}`
          }
      },
      //变更剩余时间
      changeLeftExamTime() {
          this.openExamTimeInterval = setInterval(() => {
              this.openExamTimeDown();
          }, 500);
          return;
      },
      openExamTimeDown () {

          const endTime = new Date(Date.parse(this.examManagementObj.endTime))
          const nowTime = new Date();
          let leftTime = parseInt((endTime.getTime()-nowTime.getTime())/1000)
          let d = parseInt(leftTime/(24*60*60))
          let h = this.openExamformate(parseInt(leftTime/(60*60)%24))
          let m = this.openExamformate(parseInt(leftTime/60%60))
          let s = this.openExamformate(parseInt(leftTime%60))
          if(leftTime <= 0){
              //alert("倒计时结束");
              //this.openExamTime = "0天0小时0分0秒,正在加载试题，即将进入考试...";
              this.toForceHandInPaper();
              clearInterval(this.openExamTimeInterval);

              return;
          }
          if(d>0){
              this.openExamTime = `大于${d}天`
          }else{
              this.openExamTime = `${h}时${m}分${s}秒`
          }
          //this.openExamTime = `${d}天${h}小时${m}分${s}秒`
      },
      examManagementViewByStudentUserNo(){
          let that = this;
          examManagementViewByStudentUserNo({id: this.examManagementId}).then(res => {
              if (res.data.code == 200) {
                  this.examManagementObj = res.data.data;
                  this.changeLeftExamTime();
                  /*
                  if(examManagementObj.isAllowRepeateHandInExamPaper == 0) {
                      if (item.isFinishUserTotalScore == 1 || item.isFinishUserTotalScore == 2) {
                          this.isShowAlert = true;
                          this.alertTitle = "提示";
                          this.alertContent = "已经参加过考试。该考试不允许重复参加";
                          return;
                      }
                  }*/
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
      setShowHandInExamPaperButton(flag){
          this.showHandInExamPaperButton = flag;
      },
      toForceHandInPaper(){
          this.$parent.toForceHandInPaper();
      },
      continueAnswer(){
          this.showHandInExamPaperButton = true;
          this.$parent.toOpenEmAnswerQuestionPool(false);
      },
      closeHandInExamPaperDialog(){
          this.isHandInExamPaper = false;
      },
      openHandInExamPaperDialog(){
          this.isHandInExamPaper = true;
      },
      closeReTakeAnExaminationDialog(){
          this.isReTakeAnExamination = false;
      },
      openReTakeAnExaminationDialog(){
          this.isReTakeAnExamination = true;
      },
      keepSession(){
          keepSession({}).then(res => {
              if (res.data.code == 200) {
                  let that = this;
                  let timeOut = Math.ceil(Math.random()*300000);//300秒之内的随机数
                  that.keepSessionVar = setTimeout(function(){ that.keepSession()}, timeOut);
              } else if (res.data.code > 300 && res.data.code < 400) {
                  if(res.data.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              }else{
                  let that = this;
                  let timeOut = Math.ceil(Math.random()*300000);//300秒之内的随机数
                  that.keepSessionVar = setTimeout(function(){ that.keepSession()}, timeOut);
              }
          }).catch(() => {
              let that = this;
              let timeOut = Math.ceil(Math.random()*300000);//300秒之内的随机数
              that.keepSessionVar = setTimeout(function(){ that.keepSession()}, timeOut);
          })

      },
      isHadFavorited(){
          let countF =0;
          let favoritesArray = JSON.parse(getStore("favorites_"+this.courseDetail.id));
          if(favoritesArray!=null) {
              for (let i = 0; i < favoritesArray.length; i++) {
                  if (favoritesArray[i] == this.questionId) {
                      countF++;
                  }
              }
              if(countF==0){
                  this.isFavorited = false;
              }else{
                  this.isFavorited = true;
              }
          }
      },
      note(){
          this.$parent.note();
      },
      //重新考试
      reTakeAnExamination(){
          this.$parent.reTakeAnExamination();
      },
      handInExamPaper(){
        //交卷
          //this.isHandInExamPaper = false;
          this.showHandInExamPaperButton = false;
          this.$parent.toOpenEmAnswerQuestionPool(true);

      },
      /*
      setFavoritesStatus(favoritesStatus){
          if (favoritesStatus == '0'){
              this.favoritesStatusName = '收藏';
          }else {
              this.favoritesStatus = '1';
              this.favoritesStatusName = '取消收藏';
          }
      },
      reversalFavoritesStatus() {
          console.log("this.favoritesStatus===>:"+this.favoritesStatus)
          if (this.favoritesStatus == '0'){
              this.favoritesStatus = '1';
              this.favoritesStatusName = '取消收藏';
          }else {
             this.favoritesStatus = '0';
             this.favoritesStatusName = '收藏';
          }
          console.log("this.favoritesStatusName===>:"+this.favoritesStatusName)

      },*/
      /*
      cacheFavorites() {
          if(this.favoritesStatus == '0') {
              this.$parent.cacheFavorites(this.favoritesStatus);
          }else{
              this.$parent.cacheFavorites(this.favoritesStatus);
          }
      },*/
      cacheFavorites(flag) {
         if(flag==1){
             //保存收藏
             cacheFavoritesExamQuestion({ccpId: this.courseDetail.id,questionId:this.questionId}).then(res => {
                 res = res.data;
                 if (res.code === 200 ) {
                     //
                     let countF =0;
                     let favoritesArray = JSON.parse(getStore("favorites_"+this.courseDetail.id));
                     if(favoritesArray!=null) {
                         for (let i = 0; i < favoritesArray.length; i++) {
                             if (favoritesArray[i] == this.questionId) {
                                 countF++;
                             }
                         }
                         if(countF==0){
                             favoritesArray.push(this.questionId);
                         }
                     }else {
                         favoritesArray = [];
                         favoritesArray.push(this.questionId);
                     }
                     setStore("favorites_"+this.courseDetail.id,JSON.stringify(favoritesArray));

                     this.isHadFavorited();
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
                     return;
                 }
             }).catch(error => {
                 //reject(error)
                 console.log(error);
                 return;
             })
         }
         if(flag==0){
             //取消收藏
             delFavoritesExamQuestion({ccpId: this.courseDetail.id,questionId:this.questionId}).then(res => {
                 res = res.data;
                 if (res.code === 200 ) {
                     let favoritesArray = JSON.parse(getStore("favorites_"+this.courseDetail.id));
                     if(favoritesArray!=null) {
                         for (let i = 0; i < favoritesArray.length; i++) {
                             if (favoritesArray[i] == this.questionId) {
                                 favoritesArray.splice(i, 1);
                             }
                         }
                     }
                     setStore("favorites_"+this.courseDetail.id,JSON.stringify(favoritesArray));

                     if(this.ccpId.indexOf("FAVORITES_COLLECTION_")!=-1) {
                         this.continueStartIndex = getStore("continueStartIndex_"+this.ccpId);
                         this.continueStartIndex = Number(this.continueStartIndex)-1;
                         setStore("continueStartIndex_"+this.ccpId,this.continueStartIndex);
                     }

                     this.isHadFavorited();
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
                     return;
                 }
             }).catch(error => {
                 //reject(error)
                 console.log(error);
                 return;
             })
         }
      },
    go () {
      this.$router.go(-1)
    },
    div1 () {
        console.log("div1====")
        if(this.div1Flag==false) {
            this.div1Flag = true;
            this.div2Flag = false;
        }else{
            this.div1Flag = false;
        }
    },
    div2() {
        console.log("div2====")
        if(this.div2Flag==false) {
            this.div2Flag = true;
            this.div1Flag = false;
        }else{
            this.div2Flag = false;
        }
    },
      clockon(s) {
          var thistime = new Date();
          //时间差
          let diff = new Date();
          diff.setTime(Math.abs(thistime.getTime() - s.getTime()));
          let timediff = diff.getTime();
          let hos = Math.floor(timediff / (1000 * 60 * 60));
          let mins = Math.floor(timediff / (1000 * 60));
          let secs = Math.floor(timediff / 1000);

          //如果不取余的话，秒数是一直上升的，所以在达到一个60的时候就取余就可以解决这个问题了
          if (secs > 59) {
              secs = secs % 60;
          }
          if (mins > 59) {
              mins = mins % 60;
          }
          if (eval(secs) < 10) {
              secs = "0" + secs;
          }
          if (eval(mins) < 10) {
              mins = "0" + mins;
          }
          if (eval(hos) < 10) {
              hos = "0" + hos;
          }
          this.time = hos + ":" + mins + ":" + secs;
          setTimeout(() => { // 两秒恢复为true
              this.clockon(s)
          }, 200)
      }

  },
    watch: {
        questionId(newVal,oldVal) {
            this.isHadFavorited();
        }
    },
}
</script>
<style lang="sass" scoped>
@import '~static/css/style.sass'
header
	width: 100%
	height: 44px
	text-align: center
	padding-top: 5px
	position: fixed
	left: 0
	top: 0
	z-index: 19881121
	span
  // color: #fff
  font-size: 1.6rem
  line-height: 38px
</style>
