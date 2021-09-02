<template>
  <div class="detail_video">
    <div class="video_header clearfix">
      <ul class="header_left clearfix" v-if="userInfo">
        <router-link :to="{name: 'index'}">
          <li class="return_btn">
            <img class="return_img" src="~/assets/image/back.png" alt="">
          </li>
        </router-link>
        <li class="vider_title">
          {{courseInfo.courseName}}<button type="primary" v-if="selectedLessonId" plain @click="copyVideoUrl()">复制本视频地址分享给他人</button>
        </li>
      </ul>
      <ul class="header_right clearfix">
        <li v-if="userInfo.roleType === 2"><nuxt-link :to="{name: 'account-teacher-course'}" class="left_col">教师平台</nuxt-link></li>
        <li><nuxt-link :to="{name: 'account-mycourse'}" class="left_col">我的课程</nuxt-link></li>
        <li>
          <nuxt-link :to="{name: 'account'}" :class="{left_col: true, c_gold: isVip}">{{userInfo.mobile}}</nuxt-link>
        </li>
       <!--  <li v-else>
          <a href="javascript:" @click="login" class="left_col">登录</a>
          <nuxt-link to="login?tab=2" class="pd_0">注册</nuxt-link>
        </li> -->
        <!-- <li><a href="#">退出</a></li> -->
      </ul>
    </div>
    <div class="video_body">
      <div class="video_content clearfix" :class="{show_panel: cateType}">
        <div class="win_box">
          <!--
          <div class="video_win" id="player" ref="videobox" :style="'background-image:url('+courseInfo.courseLogo+')'">
          </div>-->


          <!--
          <video v-if="lessonType == 0|| lessonType == 3" class="video_win" controls autoplay controlsList="nodownload"
                 :src=videoSrc></video>-->


          <!--
          <video id="my-video" class="video-js vjs-default-skin" controls preload="auto" poster="../assets/video.png">
            <source src="http://tx.wangxinvip.com/live/wangxin222.m3u8" type="application/x-mpegURL">
          </video>-->


          <video  id="my-video" class="video-js vjs-default-skin vjs-big-play-centered"
                 controls autoplay controlsList="nodownload" preload="auto"  poster="../assets/video.png">
            <source src="" type="application/x-mpegURL">
          </video>
          <!--
          <span class="iconfont close_video" v-if="showTop" @click="stopVideo">关闭</span>-->
        </div>
        <div class="video_info">
          <a href="javascript:" @click="changeTab(1)" :class="{on: cateType == 1}">
            <p>章节</p>
          </a>
          <a href="javascript:" @click="changeTab(2)" :class="{on: cateType == 2}">
            <p>课件</p>
          </a>
        </div>
        <div class="cate_panel">
          <div  v-if="cateType == 1">
            <dl v-for="(one, index) in courseInfo.chapterList" :key="index">
              <dt>第{{index + 1}}章：{{one.chapterName}}</dt>
              <dd v-for="(two, num) in one.lessonList" :key="num" :class="{on : selectedLessonId == two.id}" @click="videoPlay(two)" :id=two.id>
                <span>第{{num + 1}}讲：</span>
                <span style="color: red" v-if="two.lessonType==1">[直播{{two.liveBroadcastBeginTime}}]</span>
                <span style="color: red" v-if="two.lessonType==3">[回放]</span>
                {{two.lessonName}}
                <!--<span class="no_video2" v-if="!two.videoVid">(未更新)</span>-->
                <span class="c_blue" v-if="two.isFree">(免费)</span>
              </dd>
            </dl>
          </div>
          <div v-if="cateType == 2">
            <dl v-for="(one, index) in courseInfo.chapterList" :key="index">
              <dt>{{one.chapterName}}</dt>
              <dd v-for="(two, num) in one.lessonList" :key="num" v-if="two.isDoc" @click="downDoc(two)">
                <a href="javascript:" >{{two.docName}}</a>
              </dd>
            </dl>
          </div>
        </div>
      </div>
    </div>
    <div style="text-align: center" v-if="false">直播加载数据错误，请稍后重新刷新</div>
    <div style="text-align: center" v-if="liveIsWaitData">直播正在等待数据</div>
  </div>
</template>
<script>
import videojs from 'video.js'
import {userStudyLogSave} from '~/api/user.js'

import {queryCourseVideoByLessonId,queryCourseDocUrlByLessonId} from '~/api/account/course.js'
import VueCookie from 'vue-cookie'


export default {
  props: {
    courseInfo: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
        liveIsWaitData: false,
      isVip: false,
      showTop: false,
      cateType: 0,
      clientData: this.$store.state.clientData,
      userInfo: '',
        videoSrc: '',
        videoPlayObject: 1,
        lessonType: '',
        selectedLessonId: '',
        processArray: new Array(101),
        processArraySend: [],
        currentUrl:''

    }
  },
  methods: {
    changeTab (int) {
      if (int === this.cateType) {
        this.cateType = 0;
      } else {
        this.cateType = int;
      }
    },
     getCurrentUrl(){
         let originalUrl = window.location.href;

         if(originalUrl.indexOf('selectedLessonId=')!=-1&&originalUrl.indexOf('isWatchVideo=')!=-1){
             originalUrl = originalUrl.replace("&selectedLessonId="+this.$route.query.selectedLessonId,"");
             originalUrl = originalUrl.replace("&isWatchVideo="+this.$route.query.isWatchVideo,"");
         }
         this.currentUrl = originalUrl + '&selectedLessonId=' + this.selectedLessonId + '&isWatchVideo=1';
     },
      copyVideoUrl(){
          if(!this.selectedLessonId){
              this.$msgBox({
                  content: '你还没有观看任何视频',
                  isShowCancelBtn: false
              }).then(() => {
              }).catch(() => {
              })
              return;
          }
          let input = document.createElement("input");   // 直接构建input
          input.value = this.currentUrl;  // 设置内容
          document.body.appendChild(input);    // 添加临时实例
          input.select();   // 选择实例内容
          document.execCommand("Copy");   // 执行复制
          document.body.removeChild(input); // 删除临时实例
          this.$msgBox({
              content: '复制成功',
              isShowCancelBtn: false
          }).then(() => {
          }).catch(() => {
          })
      },
    // 下载附件
      downDoc (item) {
        queryCourseDocUrlByLessonId({id: item.id}).then(res => {
            res = res.data
            if (res.code === 200 ) {
                window.location.href = res.data.docUrl
            } else {
                if (res.code > 300 && res.code < 400) {
                    if(res.code == 304){
                        this.$msgBox({
                            content: '该账号被异地登录',
                            isShowCancelBtn: false
                        }).then(() => {
                            this.$store.dispatch('REDIRECT_LOGIN', res.code)
                        }).catch(() => {
                            this.$store.dispatch('REDIRECT_LOGIN', res.code)
                        })
                    }
                    this.$msgBox({
                        content: '登录超时、异常或者被异地登录，请重新登录',
                        isShowCancelBtn: false
                    }).then(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', res.code)
                    }).catch(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', res.code)
                    })
                }else {
                    if (!res.msg) {
                        this.$msgBox({
                            content: "系统错误",
                            isShowCancelBtn: false
                        }).then(() => {
                        }).catch(() => {
                        })
                    }else{
                        this.$msgBox({
                            content: res.msg,
                            isShowCancelBtn: false
                        }).then(() => {
                        }).catch(() => {
                        })
                    }
                }
            }
        });
    },
     dateFormat(fmt, date) {
      let ret;
      const opt = {
          "Y+": date.getFullYear().toString(),        // 年
          "m+": (date.getMonth() + 1).toString(),     // 月
          "d+": date.getDate().toString(),            // 日
          "H+": date.getHours().toString(),           // 时
          "M+": date.getMinutes().toString(),         // 分
          "S+": date.getSeconds().toString()          // 秒
          // 有其他格式化字符需求可以继续添加，必须转化成字符串
      };
      for (let k in opt) {
          ret = new RegExp("(" + k + ")").exec(fmt);
          if (ret) {
              fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
          };
      };
      return fmt;
    },
      setSelectedLessonId(selectedLessonId){
          this.selectedLessonId = selectedLessonId;
          this.processArraySend = [];
      },
    videoPlay (data) {
      //if (!data.videoVid) {
        if(this.videoPlayObject.paused){  //如果已暂停不管

        }else{ // 已播放点击则暂停
            this.videoPlayObject.pause(); //暂停控制
        }

        this.selectedLessonId = data.id;
        this.$emit('watchVideoToDagangEcho', this.selectedLessonId);

        let that = this;
        queryCourseVideoByLessonId({lessonId: data.id}).then(res => {
            res = res.data
            if (res.code === 200 && res.data.list) {
                if (res.data.list.length > 0) {
                  //alert(res.data.list[0].videoOasUrl);

                    that.lessonType = res.data.list[0].lessonType;

                    //this.src("https://atest888.oss-cn-hangzhou.aliyuncs.com/course/%E6%B5%8B%E8%AF%95%E8%A7%86%E9%A2%91_f5ed473c5fa647f3bf1e875af82b524d.mp4");


                   if(res.data.list[0].lessonType==0||res.data.list[0].lessonType==3){
                       that.videoSrc = res.data.list[0].videoOasUrl;
                   }
                    if(res.data.list[0].lessonType == 1){
                        that.videoSrc = res.data.list[0].liveBroadcastHlsAddress;
                        console.log("直播地址："+this.videoSrc )
                        //this.videoPlayObject.src(this.videoSrc);
                    }

                    that.videoPlayObject.src(this.videoSrc);
                    this.videoPlayObject.play(); //播放控制

                }else{
                    this.$msgBox({
                        content: '查不到数据，数据异常',
                        isShowCancelBtn: false
                    }).then(() => {
                    }).catch(() => {
                    })
                    return;
                }
            } else {
                if (res.code > 300 && res.code < 400) {
                    if(res.code == 304){
                        this.$msgBox({
                            content: '该账号被异地登录',
                            isShowCancelBtn: false
                        }).then(() => {
                            this.$store.dispatch('REDIRECT_LOGIN', res.code)
                        }).catch(() => {
                            this.$store.dispatch('REDIRECT_LOGIN', res.code)
                        })
                    }
                    this.$msgBox({
                        content: '登录超时、异常或者被异地登录，请重新登录',
                        isShowCancelBtn: false
                    }).then(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', res.code)
                    }).catch(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', res.code)
                    })
                }else {
                    if (!res.msg) {
                      this.$msgBox({
                          content: "系统错误",
                          isShowCancelBtn: false
                      }).then(() => {
                      }).catch(() => {
                      })
                  }else{
                        this.$msgBox({
                            content: res.msg,
                            isShowCancelBtn: false
                        }).then(() => {
                        }).catch(() => {
                        })
                   }
                }
            }
        });

        /*
        if (true) {
        this.$msgBox({
          content: '该视频未更新2',
          isShowCancelBtn: false
        }).catch(() => {})
        return false;
      }*/
     // this.$emit('playfunc', data)
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
              this.play();
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
                  //setTimeout("alert('请求数据时遇到错误，请稍后刷新页面重试')", 1000 )
                  that.$msgBox({
                      content: '请求数据时遇到错误，请稍后刷新页面重试',
                      isShowCancelBtn: false
                  }).then(() => {
                  }).catch(() => {})
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
                                    that.$msgBox({
                                        content: '该账号被异地登录',
                                        isShowCancelBtn: false
                                    }).then(() => {
                                        that.$store.dispatch('REDIRECT_LOGIN', res.code)
                                    }).catch(() => {
                                        that.$store.dispatch('REDIRECT_LOGIN', res.code)
                                    })
                                }else {
                                    that.$msgBox({
                                        content: '登录超时、异常或者被异地登录，请重新登录',
                                        isShowCancelBtn: false
                                    }).then(() => {
                                        that.$store.dispatch('REDIRECT_LOGIN', res.code)
                                    }).catch(() => {
                                        that.$store.dispatch('REDIRECT_LOGIN', res.code)
                                    })
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
beforeDestroy() {
    if (this.videoPlayObject) {
        this.videoPlayObject.dispose();
    }
},
    watch:{
      selectedLessonId:function(newVal,oldVal){
            this.getCurrentUrl();
        }
    },
  mounted () {
      this.userInfo= JSON.parse(VueCookie.get("wangxinUserInfo"));
    this.processArray[0] = 0;
    this.processArray[100] = 100;
      this.openVideoJs();

      this.getCurrentUrl();




      /*
          this.videoPlayObject =videojs('my-video', {
                bigPlayButton: false,
                textTrackDisplay: false,
                posterImage: true,
                errorDisplay: false,
                controlBar: true
            }, function () {
                this.play();


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
                    setTimeout("alert('请求数据时遇到错误，请稍后刷新页面重试')", 1000 )
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
                    console.log("寻找完毕")
                });
                this.on('timeupdate', function() {//播放时间改变
                    console.log("播放时间改变")
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
            })*/
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
  .detail_video {
    background: #fff;
  }
  .video_header {
    height: 66px;
    width: 1200px;
    margin: 0 auto;
    li {
      float: left;
      line-height: 66px;
    }
    .header_left {
      float: left;
      a {
        color: #999;
        margin-left: 10px;
        &:hover {
          text-decoration: none;
          color: #333;
        }
      }
      .left_30 {
        margin-left: 30px;
      }
      .return_btn {
        width: 66px;
        height: 66px;
        &:hover {
          background: rgb(245, 245, 245);
        }
      }
      .return_img {
        width: 20px;
        height: 20px;
        margin-left: 23px;
        margin-top: 23px;
      }
      .vider_title {
        font-size: 16px;
        font-weight: 600;
        color: #333;
        margin-left: 20px;
        a:hover {
          color: #d51423;
        }
        .c_red {
          color: #d51423;
        }
      }
    }
    .header_right {
      float: right;
      a {
        padding: 0 10px;
        font-size: 12px;
        color: #333;
        &:hover {
          text-decoration: none;
          color: #D51423;
        }
        &.c_gold {
          color: #CA9E70;
        }
        &.no_left {
          padding-left: 0px;
        }
      }
      .left_col {
        border-left: 1px solid #ccc;
      }
      .pd_0 {
        padding-left: 0px;
      }
      .vip_icon {
        width: 14px;
        height: 13px;
        position: relative;
        left: -10px;
        top: 1px;
        cursor: pointer;
      }
    }
  }
  .video_body {
    background: rgb(51, 51, 51);
    .video_content {
      width: 1200px;
      margin: 0 auto;
      position: relative;
      overflow: hidden;
    }
    .win_box {
      float: left;
      width: 1120px;
      height: 595px;
      margin: 5px 0;
      border-radius: 0px;
      border: 5px solid #000;
    }
    .video_win {
      width: 1110px;
      height: 585px;
      -webkit-background-size: 100%;
      background-size: 100%;
      &.mini_win {
        width: 600px;
        height: 295px;
        position: fixed;
        right: 10px;
        bottom: 30px;
        z-index: 9999;
      }
    }
    .video_info {
      position: relative;
      z-index: 41;
      float: right;
      padding-top: 20px;
      height: 595px;
      background-color: #333;
      a {
        overflow: hidden;
        display: block;
        height: 86px;
        width: 69px;
        color: #ccc;
        &:hover,&.on {
          text-decoration: none;
          background: #000;
        }
      }
      i {
        display: block;
        width: 24px;
        height: 23px;
        margin: 20px 20px 10px 20px;
        font-size: 26px;
      }
      p {
        font-size: 14px;
        text-align: center;
        color: #ccc;
      }
    }
  }
  .cate_panel{
    position: absolute;
    z-index: 11;
    top: 0;
    right: -202px;
    background-color: #000;
    height: 595px;
    width: 300px;
    padding: 20px 30px;
    overflow: auto;
    transition: all .5s;
    z-index: 40;
    &::-webkit-scrollbar {
      width: 4px;
    }
    &::-webkit-scrollbar-thumb {
      border-radius: 0px;
      background: rgba(255, 255, 255, 0.4);
    }
    dt{
      color: #fff;
      font-size: 14px;
      line-height: 18px;
    }
    dd{
      color: #A9A9AB;
      line-height: 26px;
      padding-left: 8px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      i{
        font-size: 14px;
        margin-right: 8px;
      }
      a {
        color: #A9A9AB;
      }
      &:hover, &.on, a:hover {
        color: #D51423;
        background-color: white;
        cursor: pointer;
        text-decoration: none;
      }
    }
    .load_more {
      text-align: center;
      color: #ccc;
    }
  }
  .show_panel{
    .cate_panel{
      right: 69px;
    }
  }
  .no_video {
    color: #999;
  }
  .no_video2 {
    color: #eee;
  }
  .info_box {
    pre {
      white-space: inherit;
    }
  }
  //覆盖video.js样式开始
  .vjs-poster {
    width: 1120px;
    height: 555px !important;
    //background-color: #0099FF !important;
    background: url(../../assets/video.png) no-repeat center center !important;
  }
  .video-js {
    height: 100%;
    width: 100%;
  }
  //覆盖video.js样式开始
</style>
