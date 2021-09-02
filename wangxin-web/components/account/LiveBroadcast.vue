<template>
  <div class="" :class="{on: open}">
    <div class="mask" @click="close()">关闭</div>
    <div class="modal_panel image_panel" id="image_panel_importance">
      <div class="modal_head">
        <span class="fl">直播管理</span>
        <a href="javascript:" @click="close()" class="close iconfont">关闭</a>
      </div>
      <div class="modal_body" style="margin-left: 30px;">
        <br/>
        <div class="form_group">
          <div class="label" style="display: inline;font-size: 20px">直播开始时间:</div>
          <div class="form_ctl_date" style="display: inline;margin-left: 20px">
            <myDatepicker  v-model="liveBroadcastBeginTime"  :date="liveBroadcastBeginTimeVar" :option="multiOption" :limit="limit"></myDatepicker>
          </div>
        </div>
        <br/>
        <div class="form_group">
          <div style="display: inline;font-size: 20px">直播计划时长:</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text" v-model="liveBroadcastPlanLength" class="form_input_min" placeholder="请输入时长"><span>分钟</span>

          </div>
        </div>
        <br/>
        <div class="form_group">
          <div style="display: inline;font-size: 20px">HLS播放地址:</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text"  v-model="liveBroadcastHlsAddress" class="form_input_min" style="width: 300px" placeholder="HLS播放地址"><span></span>
          </div>
        </div>
        <br/>
        <div class="form_group">
          <div style="display: inline;font-size: 20px">OBS推流地址:</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text"  v-model="liveBroadcastObsStreamAddress" class="form_input_min" style="width: 300px" placeholder="OBS推流地址"><span></span>
          </div>
        </div>
        <br/>
        <div class="form_group">
          <div style="display: inline;font-size: 20px">直播OBS推流名称:</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text"  v-model="liveBroadcastObsStreamName" class="form_input_min" style="width: 300px" placeholder="直播OBS推流名称"><span></span>
          </div>
        </div>
        <!--
        <div class="form_group">
          <div style="display: inline;font-size: 28px">RTMP播放地址(选填):</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text" class="form_input_min" placeholder="RTMP播放地址"><span></span>
          </div>
        </div>
        <div class="form_group">
          <div style="display: inline;font-size: 28px">推流地址(选填):</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text" class="form_input_min" placeholder="推流地址"><span>分钟</span>
          </div>
        </div>-->
      </div>
      <div class="modal_foot">
        <button class="solid_btn" @click="submit">确定</button>
        <button class="hollow_btn" @click="close()">取消</button>
      </div>
    </div>
  </div>
</template>
<script>
import {uploadResVideo} from '~/api/upload.js'
import myDatepicker from 'vue-datepicker/vue-datepicker-es6.vue';
import {teacherFreshCourseVideoUpdate, teacherFreshCourseVideoList,teacherFreshLessonView,updateLessonApplyLiveBroadcast,queryCourseVideoByLessonId} from '~/api/account/course.js'
export default {
    components: {
        myDatepicker
    },
  props: {
    open: {
      type: Boolean,
      default: false
    },
    lessonId: {
        type: [Object, String],
        default: null
    },
    data: {
      type: [Object, String],
      default: null
    },
    type: {
      // 1问题图片，2问题答案，3习题图片，4习题答案
      type: [String, Number],
      default: 1
    }
  },
  data () {
    return {
        liveBroadcastBeginTime: '',
        liveBroadcastPlanLength: '',
        liveBroadcastHlsAddress: '',
        liveBroadcastObsStreamAddress: '',
        liveBroadcastObsStreamName: '',
      flag: true,
      kg: true,
      upbtn: true,
      chapterVideoList: [],
      videoList: [],
      videoType: 'video/mp4,video/avi,video/mpg,video/mpeg,video/ram,video/flv,video/mov,video/asf,video/3gp,video/f4v,video/wmv,video/x-ms-wmv',
      savePic: {
        title: '',
        lessonNo: 0,
        picType: this.type,
        picUrl: ''
      },
      imgsrc: '',
      uploadList: [],
        liveBroadcastBeginTimeVar: {	  // 相当于变量
            time: ''
        },
        multiOption: {
            type: 'min',
            week: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
            month: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            format:"YYYY-M-D HH:mm",
            inputStyle: {
                'display': 'inline-block',
                'padding': '6px',
                'line-height': '22px',
                'width':'160px',
                'font-size': '16px',
                'border': '2px solid #fff',
                'box-shadow': '0 1px 3px 0 rgba(0, 0, 0, 0.2)',
                'border-radius': '2px',
                'color': '#5F5F5F',
                'margin':'0'
            },
            color: {				// 字体颜色
                header: '#35acff',	// 头部
                headerText: '#fff',	// 头部文案
            },
            buttons: {				// button 文案
                ok: '确定',
                cancel: '取消'
            },
            placeholder: '请选时间',
            dismissible: true
        },
        limit: [{
            type: 'weekday',
            available: [1, 2, 3, 4, 5,6,0]
        },
            {
                type: 'fromto',
                from: '2016-02-01',
                to: '2050-02-20'
            }]
    }
  },
  methods: {
    // 提交保存选中视频
    submit () {
        if (this.liveBroadcastBeginTimeVar.time==null) {
            this.$msgBox({
                content: '请输入直播开始时间',
                isShowCancelBtn: false
            })
            return false;
        }
        if (!this.liveBroadcastPlanLength) {
            this.$msgBox({
                content: '请输入直播时长',
                isShowCancelBtn: false
            })
            return false;
        }
        if (!this.liveBroadcastHlsAddress) {
            this.$msgBox({
                content: '请输入直播HLS播放地址',
                isShowCancelBtn: false
            })
            return false;
        }
        if (!this.liveBroadcastObsStreamAddress) {
            this.$msgBox({
                content: '请输入直播OBS推流地址',
                isShowCancelBtn: false
            })
            return false;
        }
        if (!this.liveBroadcastObsStreamName) {
            this.$msgBox({
                content: '请输入直播OBS推流名称',
                isShowCancelBtn: false
            })
            return false;
        }
        updateLessonApplyLiveBroadcast({id: this.lessonId,
            liveBroadcastBeginTime: this.liveBroadcastBeginTimeVar.time,
            liveBroadcastPlanLength: this.liveBroadcastPlanLength,
            liveBroadcastHlsAddress: this.liveBroadcastHlsAddress,
            liveBroadcastObsStreamAddress: this.liveBroadcastObsStreamAddress,
            liveBroadcastObsStreamName: this.liveBroadcastObsStreamName,
            lessonType: 1
        }).then(res => {
        res = res.data;
        console.log(res)
        console.log('save====')
        if (res.code === 200) {
          this.$emit('hidefun', event);
        } else {
          this.$msgBox({
            content: res.msg,
            isShowCancelBtn: false
          })
        }
      }).catch(() => {
        this.$msgBox({
          content: '提交失败',
          isShowCancelBtn: false
        })
      })
      // this.$emit('hidefun', event);
    },
    close () {
      if (this.upbtn) {
        this.$emit('hidefun', event);
      } else {
        this.$msgBox({
          content: '视频正在上传,确定关闭上传窗口吗?'
        }).then(res => {
          this.$emit('hidefun', event);
        })
      }
    },
    btnClick (event) {
      this.$emit('btnClick', event);
    },
    changeTab (int) {
      this.$emit('change', int);
    },
      lessonView () {
          teacherFreshLessonView({id: this.lessonId}).then(res => {
              console.log(res)
              res = res.data
              console.log('lesson===')
              if (res.code === 200) {
                  this.liveBroadcastBeginTimeVar.time = res.data.liveBroadcastBeginTime;
                  this.liveBroadcastPlanLength = res.data.liveBroadcastPlanLength;
                  this.liveBroadcastHlsAddress = res.data.liveBroadcastHlsAddress;

                  this.liveBroadcastObsStreamAddress = res.data.liveBroadcastObsStreamAddress;
                  this.liveBroadcastObsStreamName = res.data.liveBroadcastObsStreamName;

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
                  }
                  this.videoList = [];
              }
          });
      },
      // 获取该例题已选中的图片
    lessonVideoList () {
        queryCourseVideoByLessonId({lessonId: this.data.lessonId}).then(res => {
        res = res.data
            console.log("lesson:==>"+res.data)
            if (res.code === 200 && res.data.list) {
          this.videoList = res.data.list
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
          }
          this.videoList = [];
        }
      });
    },
    getChapterVideo () {
        teacherFreshCourseVideoList({chapterId: this.data.cNo, lessonId: '0'}).then(res => {
        res = res.data;
        console.log(res)
        if (res.code === 200 && res.data.list !== null) {
          this.chapterVideoList = res.data.list;
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
          }
          this.chapterVideoList = [];
        }
      });
    }
  },
  mounted () {
    this.kg = false;
    this.lessonVideoList();
    this.getChapterVideo();
    this.lessonView ();
  },
}
</script>
<style lang="scss" rel="stylesheet/scss">
#image_panel_importance {
  width: 720px;
  margin-left: -360px;
}
.image_panel{
  width: 720px;
  margin-left: -360px;
  .modal_body{
    overflow: hidden;
  }
  .upload_box{
    line-height: 36px;
    padding: 10px 20px;
    border-bottom: 1px solid #e7e7eb;
    span{
      margin-right: 12px;
      color: #666;
    }
  }
  .modal_foot{
    border-top: 1px solid #e7e7eb;
    padding: 20px;
    text-align: center;
  }
  .image_list{
    overflow: hidden;
    height: 403px;
    .title{
      line-height: 30px;
      padding: 0 20px;
      font-size: 14px;
      background-color: #eee;
    }
    .left_list{
      float: left;
      width: 187px;
      border-right: 2px solid #ddd;
    }
    .right_list{
      float: right;
      width: 531px;
    }
    ul{
      height: 373px;
      overflow: auto;
    }
    .icon{
      text-align: center;
      line-height: 80px;
      font-size: 50px;
      color: #999;
    }
    .img{
      position: relative;
      float: left;
      margin-left: 16px;
      margin-top: 16px;
      width: 153px;
      border: 1px solid #e7e7eb;
      .oper{
        display: none;
      }
      .gb{
        position: absolute;
        right: -10px;
        top: -10px;
        font-size: 18px;
        cursor: pointer;
        color: #666;
      }
      img{
        display: block;
        height: 80px;
      }
      a{
        display: block;
        text-align: center;
        line-height: 30px;
        border-top: 1px solid #e7e7eb;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .sel{
        color: #eee;
        margin: 0 3px;
      }
      &:hover{
        border-color: #ed7d03;
        .oper{
          display: block;
        }
        .icon {
          color: #ed7d03;
        }
      }
    }
    .tip{
      text-align: center;
      font-size: 16px;
      line-height: 60px;
    }
    .progress,.tip,.oper{
      position: absolute;
      left: 0;
      top: 0;
      right: 0;
      z-index: 9;
      height: 80px;
      .entity{
        height: 100%;
        background-color: rgba(0, 204, 0, 0.59);
      }
    }
  }
}
.form_input_min{
  text-indent: 1em;
  width: 130px;
  height: 34px;
  margin-right: 10px;
  border: 1px solid #e7e7e7;
}
.form_ctl_date{
  //margin-left: 12px;
  //float: left;
  width: 700px;
  //position: relative;
}
.max_img_panel{
  position: relative;
  z-index: 1000;
  .img_panel{
    display: table;
    position: fixed;
    z-index: 999;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
  }
  .img_box{
    text-align: center;
    display: table-cell;
    vertical-align: middle;
  }
  .img{
    display: inline-block;
    position: relative;
    max-width: 80%;
    i{
      position: absolute;
      right: 0;
      top: 0;
      z-index: 9;
      font-size: 30px;
      cursor: pointer;
      &:hover{
        color: #e00;
      }
    }
  }
}
</style>
