<template>
  <div class="course_detail">
    <y-watch-video  v-if="isWatchVideo==1" :courseInfo="courseInfo" @playfunc="videoPlay" @watchVideoToDagangEcho="watchVideoToDagangEcho"  ref="watchVideo"></y-watch-video>
    <y-display v-else :courseInfo="courseInfo" :isPay="isPay" :continueBuy="continueBuy" :longTerm="longTerm" @goStudy="goStudy" ref="display"></y-display>

    <div class=" detail_info detail_box clearfix">
      <div class="layout_left">
        <ul class="course_tab clearfix">
          <li :class="{on: tab == 'info'}"><a href="javascript:" @click="tab = 'info'">课程介绍</a></li>
          <li :class="{on: tab == 'big'}"><a href="javascript:" @click="tab = 'big'">课程章节</a></li>
        </ul>
        <div class="content_info"  v-if="tab == 'info'">
          <div class="introduce" v-html="courseInfo.introduce"></div>
          <!--
          <y-syllabus @playfunc="videoPlay" :list="courseInfo.chapterList" :nowNo="nowLessonNo"></y-syllabus>-->
        </div>
        <div class="content_info"  v-if="tab == 'big'">
          <da-gang @playfunc="videoPlay" :list="courseInfo.chapterList"  ref="dagang"></da-gang>
        </div>
      </div>
      <div class="layout_right">
        <div class="teacher_info clearfix">
          <span class="head">教师简介</span>
          <div class="teacher_msg">
            <div class="teacher_msg_right">
              <img class="teacher_phone" v-if="teacherInfo.headImgUrl" :src="teacherInfo.headImgUrl" alt="">
              <img class="teacher_phone" v-else src="~/assets/image/friend.jpg" alt="">
              <div class="teacher_name">
                {{teacherInfo.teacherName}}
              </div>
              <div v-html="teacherInfo.introduce" class="info_box"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <y-footer></y-footer>
  </div>
</template>
<script>
    import YDisplay from '~/components/course/Display'
    import YFooter from '~/components/common/Footer'
    import DaGang from '~/components/course/DaGang'
    import YWatchVideo from '~/components/course/WatchVideo'
    import {courseDetail, userCourseDetail} from '~/api/course.js'
    import VueCookie from 'vue-cookie'
    import QRCode from 'qrcode'

    export default {
        components: {
            DaGang,
            YFooter,
            YDisplay,
            YWatchVideo,
            QRCode
        },
        head () {
            return {
                title: '课程详情'
            }
        },
        data () {
            return {
                tab: 'big',
                nowLessonNo: '',  //当前播放章节
                courseInfo:{'teacher':{'teacherName':''}},
                teacherInfo : '',

                isPay: false,
                continueBuy: false,
                longTerm:false,//长期有效
                isWatchVideo:0,
                isLogin: false,
                qrcode: '',
                qr: '',
                selectedLessonId: ''

            }
        },
        watch:{

        },
        methods: {
            goStudy(){
                this.isWatchVideo=1;
            },
            queryCourseDetail(){
                let tk = VueCookie.get("wangxinToken");
                let result = new Object();
                if (this.isLogin==true) {
                    userCourseDetail({courseId: this.$route.query.courseId}, tk).then(res => {
                        if (res.data.code == 200) {
                            result.courseInfo = res.data.data;
                            this.courseInfo = res.data.data;
                            result.teacherInfo = res.data.data.teacher;
                            this.teacherInfo = res.data.data.teacher;
                            result.isbuy = false;
                            result.isLogin = false;

                            if(this.courseInfo.isPay==1){
                                if(this.courseInfo.validTerm=='9999-12-31 23:59'){
                                    this.longTerm = true;
                                }
                                this.continueBuy = true;
                                this.isPay = true;
                            }

                        } else if (res.data.code >= 300 && res.data.code <= 400) {
                            if(res.data.code == 304){
                                this.$msgBox({
                                    content: '该账号被异地登录',
                                    isShowCancelBtn: false
                                }).then(() => {
                                    this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
                                }).catch(() => {
                                    this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
                                })
                            }
                            this.$store.dispatch('REDIRECT_LOGIN')
                        } else {
                            result.courseInfo = null;
                            this.courseInfo = null;

                        }
                    }).catch(() => {
                        this.load = false;
                        this.$msgBox({
                            content: '加载数据失败，请稍后刷新再试！！',
                            isShowCancelBtn: false
                        })
                    })
                }else{
                    courseDetail({courseId: this.$route.query.courseId}).then(res => {
                        if (res.data.code == 200) {
                            result.courseInfo = res.data.data;
                            this.courseInfo = res.data.data;
                            result.teacherInfo = res.data.data.teacher;
                            this.teacherInfo = res.data.data.teacher;
                            result.isbuy = false;
                            result.isLogin = false;
                        } else if (res.data.code >= 300 && res.data.code <= 400) {
                            if(res.data.code == 304){
                                this.$msgBox({
                                    content: '该账号被异地登录',
                                    isShowCancelBtn: false
                                }).then(() => {
                                    this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
                                }).catch(() => {
                                    this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
                                })
                            }
                            this.$store.dispatch('REDIRECT_LOGIN')
                        } else {
                            result.courseInfo = null;
                            this.courseInfo = null;
                        }
                    }).catch(() => {
                        this.load = false;
                        this.$msgBox({
                            content: '加载数据失败，请稍后刷新再试！！',
                            isShowCancelBtn: false
                        })
                    })
                }
                return result;
            },
            videoPlay (data) {
                this.selectedLessonId = data.id;
                this.isWatchVideo = 1;
                setTimeout(() => {
                    this.$refs.watchVideo.videoPlay(data);
                }, 1000)

            },
            //播放列表窗口点击回显到课程大纲
            watchVideoToDagangEcho(lessonId){
                setTimeout(() => {
                    //如果右侧课程列表未点开，会报方法undefined异常
                    this.$refs.dagang.setSelectedLessonId(lessonId);
                }, 500)
            },
            dagangToWatchVideoEcho(lessonId){
                //如果课程大纲未点开，会报方法undefined异常
                setTimeout(() => {
                    this.$refs.watchVideo.setSelectedLessonId(lessonId);
                }, 500)
            }
        },
        mounted () {
            if(VueCookie.get("wangxinToken")==''||VueCookie.get("wangxinToken")==null){
                this.isLogin = false;
            }else{
                this.isLogin = true;
            }

            this.isWatchVideo = this.$route.query.isWatchVideo;
            this.queryCourseDetail();
            console.log(this.courseInfo);

        }
    }
</script>
<style lang="scss" rel="stylesheet/scss">
  .course_detail{
    .detail_info {
      margin: 20px auto 60px;
      width: 1200px;
    }
    .layout_left {
      width: 920px;
      float: left;
      .info_body {
        margin-bottom: 30px;
      }
    }
    .course_tab{
      padding-left: 30px;
      background: #fff;
      border-radius: 0px;
      margin-bottom: 20px;
      li {
        float: left;
        height: 66px;
        line-height: 66px;
        margin-right: 80px;
        &.on {
          border-bottom: 2px solid blue;
          a {
            color: blue;
          }
        }
        a {
          color: #000;
          font-size: 16px;
          font-weight: 700;
          &:hover {
            text-decoration: none;
            color: blue;
          }
        }
      }
    }
    .content_info{
      padding: 30px;
      background: #fff;
      border-radius: 0px;
      min-height: 400px;
      .title {
        border-left: 3px solid #000;
        padding-left: 12px;
        font-size: 16px;
        color: #000;
        font-weight: 700;
        margin-bottom: 25px;
      }
      .introduce{
        font-size: 14px;
        line-height: 30px;
        color: #333;
        padding-left: 8px;
      }
    }
    .layout_right{
      width: 260px;
      float: right;
    }
    .share_info {
      background: #fff;
      border-radius: 0px;
      position: relative;
      height: 84px;
      border: 1px solid lightgrey;
    }
    .teacher_info {
      background: #fff;
      border-radius: 0px;
      position: relative;
      min-height: 180px;
      .head {
        display: block;
        line-height: 66px;
        height: 66px;
        padding-left: 20px;
        font-size: 16px;
        color: #333;
        border-bottom: 1px solid rgb(228, 228, 228);
        font-weight: bold;
      }
      .teacher_phone {
        width: 46px;
        height: 46px;
        border-radius: 50%;
        background: rgb(228, 228, 228);
        text-align: center;
        line-height: 46px;
        font-size: 13px;
        color: #999;
        float:left;
        margin: 0px 10px 0 10px;
        img {
          width: 46px;
          height: 46px;
          border-radius: 50%;
        }
      }
      .teacher_msg_right {
        width: 235px;
        float: right;
        margin: 12px 15px 12px 12px;
        line-height: 20px;
      }
      .teacher_name {
        font-size: 14px;
        font-weight: 700;
        color: #333;
        margin-bottom: 10px;
      }
    }
  }
</style>
