<template>
  <header class="header" :class="[this.backgroundColor ? 'fff' : '' ]">
    <a href="javascript:" @click="go()" class="header_back_icon_white"></a>
    <div style="width: 50px;height: 90%;padding-top:0px;position: absolute;left: 40px;border:1px dashed #000;text-align: center;">
      <span style="font-weight: bold;font-size: 18px">{{continuestartindex}}/{{totalquestionscount}}</span>
    </div>
    <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 100px;border:1px dashed #000;text-align: center;">
      <span style="font-size: 16px;color: white">{{this.time}}</span>
    </div>
    <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 190px;border:1px dashed #000;text-align: center;">
      <!--
      <span style="font-size: 16px;color: white;text-decoration :underline" @click="cacheFavorites()">{{favoritesStatusName}}</span>-->
      <span v-if="!isFavorited" style="font-size: 16px;color: white;text-decoration :underline" @click="cacheFavorites(1)">收藏</span>
      <span v-if="isFavorited" style="font-size: 16px;color: white;text-decoration :underline" @click="cacheFavorites(0)">取消收藏</span>

    </div>
    <div style="width: 90px;height: 100%;padding-top:0px;position: absolute;left: 280px;border:1px dashed #000;text-align: center;">
      <span style="font-size: 16px;color: white;text-decoration :underline" @click="note()">笔记</span>
    </div>
  </header>
</template>
<script>
    import {getStore,removeStore,setStore} from '~/store/common.js'
    import {queryFavoritesExamQuestions,cacheFavoritesExamQuestion,
        delFavoritesExamQuestion} from '~/api/exam'
    import { logout } from '~/store/common'
    export default {
  props: ['questionId','continuestartindex', 'totalquestionscount', 'name', 'className', 'backgroundColor', 'back'],
    data(){
      return{
          div1Flag: false,
          div2Flag: false,
          time:'',
          favoritesStatus: '0',
          favoritesStatusName : '收藏',
          courseDetail:{
              id:''
          },
          isFavorited: false,
          ccpId:''

      }
    },
    mounted(){

        this.courseDetail.id = this.$route.query.courseId;
        this.ccpId = this.$route.query.id;


        ///这里是获得登录时候的时间，用来和动态的时间做差来求时长
        let s = new Date();
        this.clockon(s);

        setTimeout(() => {
            this.isHadFavorited();
        }, 500)


    },
  methods: {
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
