<template>
  <div class="course_detail_header right_bar" @touchmove.prevent>
		<div class="activeImg end_active"></div>
		<div class="course_detail_header_banner">
      <!--
      <video id="media" v-if="true" :poster=this.courseDetail.courseLogo :src=videoSrc controls="controls" controlsList="nodownload"
      autoplay="autoplay">
        your browser does not support the video tag
      </video>-->
      <!--
      <div class="win_video">
        <video  id="my-video" class="video-js vjs-default-skin vjs-big-play-centered"
                controls autoplay controlsList="nodownload" preload="auto"  poster="../img/wxImg/play.png">
          <source src="" type="application/x-mpegURL">
        </video>
      </div>-->
      <!---->
      <div v-if="requestError" id="text" style="position: absolute; text-align: center;left:0px; right:0; top:100px; bottom:0;font-size:18px;font-weight:bold;color:rgba(255, 0, 0,0.8);margin: auto;z-index:3000000">
        <p @click="playA" style="text-underline: orangered">请求错误，请点击这里重新加载</p>
      </div>
      <div class="win_video">
        <video  id="my-video" class="video-js vjs-default-skin vjs-big-play-centered"
                controls autoplay controlsList="nodownload" preload="auto"  :poster=this.courseDetail.courseLogo>
          <source src="" type="application/x-mpegURL">
        </video>
      </div>
    </div>
    <span v-if="voi == 'no'" style="color: #ef4040;font-size: 14px">[该课时没有视频]</span>

    <!--
    <div @click="playA">点击这里刷新</div>
    --->

  </div>
</template>
<script>
    import videojs from 'video.js'
    import {listMyCourse} from '~/api/course'
    import {userStudyLogSave} from '~/api/user'


    import VueCookie from 'vue-cookie'

    import { logout } from '~/store/common'

    export default {
    props: ['courseDetail','voi','playvideo','playimg'],
    data () {
      return {
        isDisplayBar: false,
          oas:'',
          isExeTime:false,
          media: '',
          videoSrc: '',
          //courseDetail: {
              //courseLogo: "https://atest888.oss-cn-hangzhou.aliyuncs.com/course/45f6c1c0dc5a46d28da1cf138f46f0a5.jpg"
             // courseLogo: ""
         // },
          selectedLessonId: '',
          videoPlayObject: '',
          processArray: new Array(101),
          processArraySend: [],
          currentUrl:'',
          liveIsWaitData: '',
          requestError: false
      }
    },
    computed: {

    },
    watch:{
        lessonname(newVal,oldVal){
            console.log("====lessonname===="+newVal)
        },
      voi(newVal,oldVal){
          console.log("newVal:"+newVal);
          console.log("oldVal:"+oldVal);
          if(newVal == 'no'){
              console.log("停止")
              this.media.pause();
              //this.media.pause();
          }else{
              this.videoSrc = newVal;
              console.log("进来了")
              this.media.play();
             // if(this.media.paused) {
               //   this.media.play();
             // }
          }

      }
    },
    mounted(){

      /*
        var media = document.getElementById("media");
        this.media = media;

        media.oncanplay = function(){
            console.log("准备就绪");

        };
        //监听播放开始
        media.addEventListener('play',function(){
            console.log("开始播放");
        });

        //监听播放结束
        media.addEventListener('pause',function(){
            console.log("播放暂停");
        });

        //监听播放结束
        media.addEventListener('ended',function(){
            console.log("播放结束");
        });


        //使用事件监听方式捕捉事件， 此事件可作为实时监测video 播放状态
        media.addEventListener("timeupdate",function(){
            var timeDisplay;
            //用秒数来显示当前播放进度
            timeDisplay = Math.floor(media.currentTime);
            //console.log(Math.floor(media.currentTime))

            //当视频播放到 4s的时候做处理
            if(timeDisplay == 4){
                //处理代码
            }
        },false);
*/



    },
        beforeDestroy() {
            if (this.videoPlayObject) {
                this.videoPlayObject.dispose();
            }
        },
  methods: {
      updateLessonId(lessonId){
          this.selectedLessonId = lessonId;
          this.processArraySend = [];
      },
      aaa(){
         console.log("ppp---");
      },
      playA(videoSrc){
          this.requestError = false;
          let that = this;
          that.openVideoJs();
          that.videoSrc = videoSrc;
          that.videoPlayObject.src(that.videoSrc);
          that.videoPlayObject.play(); //播放控制


      },

    go () {
      this.$router.go(-1)
    },
    gobar () {
      this.isDisplayBar = !this.isDisplayBar
      document.body.scrollTop = 0
    },
      openVideoJs(){
          let that = this;
          this.videoPlayObject =videojs('my-video', {
              bigPlayButton: false,
              textTrackDisplay: false,
              posterImage: true,
              errorDisplay: false,
              controlBar: true
          }, function () {
              //this.play();
              this.on('suspend', function() {//延迟下载
                  console.log("延迟下载")
              });
              this.on('loadstart', function() { //客户端开始请求数据
                  console.log("客户端开始请求数据")
              });
              this.on('progress', function() {//客户端正在请求数据
                  console.log("客户端正在请求数据")
              });
              this.on('abort', function() {//客户端主动终止下载（不是因为错误引起）
                  console.log("客户端主动终止下载")
              });
              this.on('error', function() {//请求数据时遇到错误
                  console.log("请求数据时遇到错误");
                  that.requestError = true;
                  //setTimeout("alert('请求数据时遇到错误，请稍后刷新页面重试')", 1000 )
              });
              this.on('stalled', function() {//网速失速
                  console.log("网速失速")
              });
              this.on('play', function() {//开始播放
                  console.log("开始播放")
              });
              this.on('pause', function() {//暂停
                  console.log("暂停")
              });
              this.on('loadedmetadata', function() {//成功获取资源长度
                  console.log("成功获取资源长度")
              });
              this.on('loadeddata', function() {//渲染播放画面
                  console.log("渲染播放画面")
              });
              this.on('waiting', function() {//等待数据，并非错误
                  console.log("等待数据")
                  this.liveIsWaitData = true;
              });
              this.on('playing', function() {//开始回放
                  console.log("开始回放")
              });
              this.on('canplay', function() {//可以播放，但中途可能因为加载而暂停
                  console.log("可以播放，但中途可能因为加载而暂停")
              });
              this.on('canplaythrough', function() { //可以播放，歌曲全部加载完毕
                  console.log("可以播放，歌曲全部加载完毕")
              });
              this.on('seeking', function() { //寻找中
                  console.log("寻找中")
              });
              this.on('seeked', function() {//寻找完毕
                  console.log("寻找完毕");
                  //清除已经发送的学习记录列表，让其可以重新发送，以免记漏
                  that.processArraySend = [];
              });
              /*
              this.on('timeupdate', function() {//播放时间改变
                  console.log("播放时间改变")
              });*/
              this.on('timeupdate', function() {//播放时间改变
                  console.log("播放时间改变");
                  let currentTime = parseInt(this.currentTime()); //当前时间
                  console.log("当前播放时间："+currentTime);
                  let duration = this.duration(); //视频时长
                  let percentNum = (currentTime / duration * 100).toFixed(0);
                  let percent = percentNum + "%";
                  console.log("ssdfsd:"+(5%2));

                  console.log("播放刻度:"+percent);//当直播的时候，播放刻度永远是0


                  that.processArray[percentNum] = percentNum;
                  console.log("percentNum%5 = " + (parseInt(percentNum)%5));

                  if(parseInt(percentNum)!=0&&parseInt(percentNum)%5==0) {
                      if(that.processArraySend[percentNum]!=percentNum) {
                          that.processArraySend[percentNum] = percentNum;
                          userStudyLogSave({
                              'lessonId': that.selectedLessonId,
                              'studyProgessDetails': that.processArray.toString()
                          }).then(res => {
                              res = res.data;
                              if (res.code === 200) {

                              }  else if (res.code > 300 && res.code < 400) {
                                  if(res.code === 304){
                                      console.log("该账号被异地登录");
                                      logout();
                                      this.$router.push({path: '/login', query: {}});
                                  }else {
                                      console.log("登录超时、异常或者被异地登录，请重新登录")
                                      logout();
                                      this.$router.push({path: '/login', query: {}});
                                  }
                              } else {
                                  console.log("保存学习记录失败")
                              }
                          }).catch(() => {
                          })
                      }
                  }

              });
              this.on('ended', function() {//播放结束
                  console.log("播放结束")
              });
              this.on('ratechange', function() {//播放速率改变
                  console.log("播放速率改变")
              });
              this.on('durationchange', function() {//资源长度改变
                  console.log("资源长度改变")
              });
              this.on('volumechange', function() {//音量改变
                  console.log("音量改变")
              });
          })
      }
  },
  components: {
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
// 课程详情区域
.course_detail_header {
  // height: 150px
  //margin-top: 44px;
  padding: 0;
  margin: 0;
  position: relative;
  }
  // 图片盛放容器
.course_detail_header .course_detail_header_banner{
    position: relative;
    height: 0;
    padding-top: 62.5%;
    background: #fff;
    overflow: hidden;
    position: relative;
    }
.course_detail_header .course_detail_header_banner .win_video {
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
}
.vjs-poster {
  width: 100%;
  height: 80% !important;
  //background: url(/wxmobile/img/index_login.png) no-repeat center center !important;
}
.video-js {
  height: 100%;
  width: 100%;
}
</style>
