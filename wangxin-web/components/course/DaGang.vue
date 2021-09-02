<!-- 课程大纲列表 -->
<template>
  <div class="sy_body">
    <div class="title"><span></span>课程大纲</div>
    <div class="chapter_info" v-for="(one, index) in list" :key="index">
      <div class="chapter_name">
        <span>第{{index + 1}}章&nbsp;&nbsp;</span>{{one.chapterName}}
      </div>
      <div class="lesson_info" v-for="(two, num) in one.lessonList" :key="num">
        <div class="lesson_top" @click="videoPlay(two)" :class="{on : selectedLessonId == two.id}">
          <!--<div class="lesson_video" :class="{no_v: !two.videoVid}"></div>-->
          <div class="lesson_video" :class="{no_v: false}">◆</div>
          <span class="lesson_num">第{{num+1}}讲</span>
          <!--<span v-if="!two.videoVid" class="no_video">(未更新)</span>
          <span v-if="true" class="no_video">(未更新)</span>-->
          <span v-if="two.isFree==1" class="c_blue">[免费]</span>
          {{two.lessonName}}
          <span v-if="two.lessonType==1" class="c_red">[直播,开始时间{{two.liveBroadcastBeginTime}},时长{{two.liveBroadcastPlanLength}}分钟]</span>
          <span v-if="two.lessonType==3" class="c_red">[回放]</span>
          <!--<span class="video_time fr" v-if="two.videoVid">{{two.videoLength}}分钟</span>-->
          <span class="video_time fr" v-if="false">{{two.videoLength}}分钟</span>
        </div>
        <!-- <a :href="two.accessoryInfoDTOList[0].acUrl" v-if="two.accessoryInfoDTOList && userInfo && two.accessoryInfoDTOList.length && !minVideo">下载课件</a> -->
        <a href="javascript:" @click="downDoc2(two)" v-if="two.isDoc">下载课件</a>
      </div>
    </div>
  </div>
</template>

<script>
    import {queryCourseDocUrlByLessonId} from '~/api/account/course.js'
    import {listByCourseId} from '~/api/course.js'
    import VueCookie from 'vue-cookie'
    import course from "../../pages/account/teacher/course";


    export default {
  props: {
    list: {
      type: Array,
      default:function(){
         return [];
      }
    }
  },
  data (){
    return {
        selectedLessonId: ''
    }
  },
  mounted(){
      this.selectedLessonId = this.$route.query.selectedLessonId;
      if(this.selectedLessonId){
          this.videoPlay({id : this.selectedLessonId});
      }
  },
  methods: {
      // 下载附件
      downDoc2 (item) {
          queryCourseDocUrlByLessonId({id: item.id}).then(res => {
              res = res.data
              if (res.code === 200 ) {
                  console.log("id:::::===>"+res);
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
      setSelectedLessonId (lessonId) {
        this.selectedLessonId = lessonId;
    } ,
      videoPlayOnNewPage(data){
          //let routerJump = this.$router.resolve({name: 'view-courseDetail', query: {courseId: this.$route.query.courseId,selectedLessonId: data.id,isWatchVideo:1}})
          //window.open(routerJump.href,'_blank');
          this.$router.push({name: 'view-courseDetail', query: {courseId: this.$route.query.courseId,selectedLessonId: data.id,isWatchVideo:1}})
      },
    videoPlay (data) {
        if (!VueCookie.get("wangxinToken")) {
            this.$msgBox({
                content: '请先登录'
            }).then(res => {
                this.$store.dispatch('REDIRECT_LOGIN');
            }).catch(() => {
            })
            return false;
        }

        /*
        listByCourseId({courseId: data.courseId}).then(res => {
            res = res.data
            if (res.code === 200 ) {
                console.log("==9999=>"+res.data.list);
                let courseUserList = res.data.list || [];
                if(courseUserList.length<1){
                    this.$msgBox({
                        content: '你没有该课程权限'
                    }).then(res => {
                    }).catch(() => {
                    })
                    return false;
                }

            } else {
                if (res.code > 300 && res.code < 400) {
                    this.$msgBox({
                        content: '登录超时、异常或者被异地登录，请重新登录',
                        isShowCancelBtn: false
                    }).then(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                    }).catch(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
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
*/
        this.$emit("dagangToWatchVideoEcho",data.id);
        console.log("====>>>"+ JSON.stringify(data))
        this.$emit('playfunc', data)
    }
  }
}
</script>

<style lang="scss" rel="stylesheet/scss">
  .sy_body{
    .title {
      border-left: 3px solid #000;
      padding-left: 12px;
      font-size: 16px;
      color: #000;
      font-weight: 700;
      margin-bottom: 25px;
    }
  }
  .chapter_info {
    width: 839px;
    margin: 0 auto;
    font-size: 14px;
    border-bottom: 1px solid #ccc;
    margin-bottom: 30px;
    padding-bottom: 20px;
    &:last-child {
      border: none;
    }
    .chapter_name {
      font-size: 14px;
      font-weight: 700;
      color: #333;
      padding-bottom: 10px;
    }
  }
  .lesson_info {
    position: relative;
    a {
      display: inline-block;
      font-size: 12px;
      width: 66px;
      height: 26px;
      text-align: center;
      line-height: 26px;
      color: #999;
      position: absolute;
      right: 0px;
      top: 10px;
      border: 1px solid rgb(153, 153, 153);
      border-radius: 0px;
      &:hover {
        color: rgb(213, 20, 35);
        text-decoration: none;
        border: 1px solid rgb(213, 20, 35);
        cursor: pointer;
      }
    }
  }
  .lesson_top {
    width: 627px;
    padding-left: 20px;
    height: 46px;
    line-height: 46px;
    position: relative;
    padding-right: 104px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    .video_time {
      position: absolute;
      right: 0;
    }
    .lesson_num {
      margin-right: 6px;
    }
    .lesson_video {
      display: inline-block;
      width: 21px;
      height: 21px;
      position: relative;
      //top: 6px;
      margin-right: 10px;
      background-size: 21px 21px;
      &.no_v {
      }
    }
    &:hover, &.on {
      cursor: pointer;
      background: rgb(242, 242, 242);
      color: blue;
      .lesson_video {
        //background: url(../../assets/image/video.png) center center;
        &.no_v {
          //background: url(../../assets/image/no_video.svg) no-repeat center center;
        }
      }
    }
  }
</style>
