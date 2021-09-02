<template>
  <div class="">
    <y-header></y-header>
    <div class="container account_cont">
      <y-side :type="sideTy"></y-side>
      <div class="main_box">
        <div class="" v-if="!obj.id">
          <div class="step_panel">
            <div class="step on">1.填写课程信息</div>
            <div class="step">2.上传课程内容</div>
            <div class="step">3.提交等待审核</div>
          </div>
        </div>
        <div class="main_cont form">
          <form action="" @submit="courseSave">
            <div class="form_group">
              <div class="label">课程名称</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.courseName" class="form_input" placeholder="请输入课程名称">
              </div>
            </div>
            <div class="form_group">
              <div class="label">课程分类</div>
              <div class="form_ctl">
                <select v-model="obj.subjectId1" @change="changeCate1" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate1" :checked="obj.subjectId1 == cate.id" :key="cate.id" :value="cate.id" v-html="cate.subjectName"></option>
                </select>
                <select name="" v-model="obj.subjectId2" @change="changeCate2" id="" v-if="cate2 && cate2.length" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate2" :key="cate.id" :value="cate.id" v-html="cate.subjectName"></option>
                </select>
                <select name="" v-model="obj.subjectId3" id="" v-if="cate3 && cate3.length" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate3" :key="cate.id" :value="cate.id">{{cate.subjectName}}</option>
                </select>
              </div>
            </div>
            <div class="form_group" v-if="!(this.$route.query.t === 'paper')">
              <div class="label">课程介绍</div>
              <div class="form_ctl">
                <div id="courseInfo"></div>
              </div>
            </div>
            <div class="form_group">
              <div class="label">课程类型</div>
              <div class="form_ctl form_ctl_radio">
                <input name="ct" v-model="obj.courseType" type="radio" value="0" id="ct1">
                <label for="ct1">点播</label>
                <input name="ct" v-model="obj.courseType" type="radio" value="1" id="ct2">
                <label for="ct2">直播</label>
                <input name="ct" v-model="obj.courseType" type="radio" value="2" id="ct3">
                <label for="ct3">点播+直播</label>
              </div>
            </div>
            <div class="form_group">
              <div class="label">是否免费</div>
              <div class="form_ctl form_ctl_radio">
                <input name="sex" v-model="obj.isFree" type="radio" value="0" id="sex1">
                <label for="sex1">付费</label>
                <input name="sex" v-model="obj.isFree" type="radio" value="1" id="sex2">
                <label for="sex2">免费</label>
              </div>
            </div>
            <div class="form_group" v-if="obj.isFree == 0">
              <div class="label">课程售价</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.courseOriginal" class="form_input_min" placeholder="请输入价格"><span>元</span>
              </div>
            </div>
            <div class="form_group" v-if="obj.isFree == 0">
              <div class="label">课程优惠价</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.courseDiscount" class="form_input_min" placeholder="请输入课程优惠价"><span>元【以该价为课程实际结算价，必须填写】</span>
              </div>
            </div>
            <div class="form_group">
              <div class="label">初始化学习人数</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.studyCount" class="form_input_min" placeholder="请输入初始化学习人数"><span>人</span>
              </div>
            </div>
            <div class="form_group">
              <div class="label">初始化购买人数</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.buyCount" class="form_input_min" placeholder="请输入初始化购买人数"><span>人</span>
              </div>
            </div>
            <div class="form_group">
              <div class="label">计费周期</div>
              <div class="form_ctl form_ctl_radio">
                <input name="ctt" v-model="obj.costTimeType" type="radio" value="0" id="ctt1">
                <label for="ctt1">长期</label>
                <input name="ctt" v-model="obj.costTimeType" type="radio" value="1" id="ctt2">
                <label for="ctt2">按天</label>
                <input name="ctt" v-model="obj.costTimeType" type="radio" value="2" id="ctt3">
                <label for="ctt3">按周</label>
                <input name="ctt" v-model="obj.costTimeType" type="radio" value="3" id="ctt4">
                <label for="ctt4">按月</label>
                <input name="ctt" v-model="obj.costTimeType" type="radio" value="4" id="ctt5">
                <label for="ctt5">按年</label>
              </div>
            </div>
            <div class="form_group" v-if="!(this.$route.query.t === 'paper')">
              <div class="label">上传封面</div>
              <div class="form_ctl upload_ctl">
                <input type="hidden" v-model="obj.courseLogo">
                <div class="preview">
                  <img :src="obj.courseLogo" alt="" v-if="obj.courseLogo">
                </div>
                <d-upload @rtnUrl="setUrl" :btntxt="'上传图片'" :accept="'image/png,image/gif,image/jpeg'"></d-upload>
              </div>
            </div>
            <div class="form_group">
              <div class="label">&nbsp;</div>
              <div class="form_ctl">
                <button class="submit_btn" :disabled="btnDis">确认</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <y-footer class=""></y-footer>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YFooter from '~/components/common/Footer'
import YSide from '~/components/account/Side'
import DUpload from '~/components/account/Upload'
import {infiniteCate, teacherFreshCourseSave,teacherFreshCourseView, teacherFreshCourseUpdate, accessoryList} from '~/api/account/course.js'
import {uploadPic} from '~/api/upload.js'

export default {
  data () {
    return {
      btnDis: false,
      sideTy: 'kcgl',
      edit: true,
      cate1: [],
      cate2: false,
      cate3: false,
      change1: false,
      change2: false,
      tags: [],
      obj: {
        subjectId1: '',
        subjectId2: '',
        subjectId3: '',
        isFree: 0,
        courseSubject: '1',
        courseLogo: '',
        introduce: '',
        courseName: '',
        courseOriginal: '',
        costTimeType: 0,
          courseType: 0
      }
    }
  },
  // 页面打开完毕
  methods: {
    setUrl (res) {
      this.obj.courseLogo = res.url;
      // console.log(res)
    },
    courseSave (e) {
      e.preventDefault();
      // console.log(this.obj)
      this.obj.introduce = this.editor2.txt.html();
      if (!this.obj.introduce) {
        this.$msgBox({
          content: '请输入课程介绍',
          isShowCancelBtn: false
        })
        return false;
      }
      if (!this.obj.courseName) {
        this.$msgBox({
          content: '请输入课程名称',
          isShowCancelBtn: false
        })
        return false;
      }
      if (!this.obj.subjectId1 && !this.obj.id) {
        this.$msgBox({
          content: '请选择课程分类',
          isShowCancelBtn: false
        })
        return false;
      }
      if (parseInt(this.obj.isFree) !== 1) {
        if (!this.obj.courseOriginal) {
          this.$msgBox({
            content: '请输入课程售价',
            isShowCancelBtn: false
          })
          return false;
        }
        if (this.obj.courseOriginal <= 0) {
          this.$msgBox({
            content: '请输入正确的课程原价',
            isShowCancelBtn: false
          })
          return false;
        }
        if (!this.obj.courseDiscount) {
            this.$msgBox({
                content: '请输入课程优惠价',
                isShowCancelBtn: false
            })
            return false;
        }
        if (this.obj.courseDiscount <= 0) {
            this.$msgBox({
                content: '请输入正确的课程优惠价',
                isShowCancelBtn: false
            })
            return false;
        }
      } else {
        this.obj.courseOriginal = 0;
      }
      if (!this.obj.courseLogo && !(this.$route.query.t === 'paper')) {
        this.$msgBox({
          content: '请上传课程封面',
          isShowCancelBtn: false
        })
        return false;
      }
      // console.log(tx)

      this.btnDis = true;
      if (this.obj.id) {
        console.log("---->"+this.obj.costTimeType)
        teacherFreshCourseUpdate(this.obj).then(res => {
          this.btnDis = false;
          res = res.data;
          if (res.code === 200) {
            this.$msgBox({
              content: '修改成功',
              isShowCancelBtn: false
            }).then(async (val) => {
              this.$router.push({name: 'account-teacher-course', query: {no: res.data.id}})
            })
          } else {
            this.$msgBox({
              content: res.msg,
              isShowCancelBtn: false
            })
          }
        }).catch(() => {
          this.btnDis = false;
          this.$msgBox({
            content: '修改失败,请稍后再试',
            isShowCancelBtn: false
          })
        })
      } else {
        // console.log(this.obj)
          teacherFreshCourseSave(this.obj).then(res => {
          res = res.data;
          this.btnDis = false;
          if (res.code === 200) {
            this.$msgBox({
              content: '保存成功',
              isShowCancelBtn: false
            }).then(async (val) => {
              this.$router.push({path: '/account/teacher/chapter', query: {no: res.data.id}})
            })
          } else {
            if (result.code >= 300 && result.code < 400) {
                if(result.code == 304){
                    this.$msgBox({
                        content: '该账号被异地登录',
                        isShowCancelBtn: false
                    }).then(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                    }).catch(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                    })
                }
              this.$msgBox({
                content: res.msg,
                isShowCancelBtn: false
              }).then(() => {
                this.$store.dispatch('REDIRECT_LOGIN', result.code)
              }).catch(() => {
                this.$store.dispatch('REDIRECT_LOGIN', result.code)
              })
            } else {
              this.$msgBox({
                content: res.msg,
                isShowCancelBtn: false
              }).catch(() => {})
            }
          }
        }).catch(() => {
          this.btnDis = false;
          this.$msgBox({
            content: '保存失败,请稍后再试',
            isShowCancelBtn: false
          })
        })
      }
    },
    changeCate1 () {
      if (this.obj.subjectId2 == 0) {
        this.obj.subjectId2 = ''
      }
      if (this.obj.subjectId3 == 0) {
        this.obj.subjectId3 = ''
      }
      if (!this.$route.query.no) {
        this.obj.subjectId2 = '';
        this.obj.subjectId3 = '';
        this.cate2 = [];
        this.cate3 = [];
      }
      if (this.change1) {
        this.obj.subjectId2 = '';
        this.obj.subjectId3 = '';
        this.cate2 = [];
        this.cate3 = [];
      }
      let c1 = this.obj.subjectId1;
      let that = this;
      if (c1 === '') {
        that.cate2 = false;
        that.cate3 = false;
      } else {
        let catels = that.cate1;
        catels.map(function (item, i) {
          if (item.id === c1) {
            that.cate2 = item.twoList;
            that.cate3 = false;
          }
        })
        this.changeCate2();
      }
      if (this.$route.query.no) {
        this.change1 = true
      }
      // console.log(this.obj)
    },
    changeCate2 () {
      if (!this.$route.query.no) {
        this.obj.subjectId3 = '';
        this.cate3 = [];
      }
      if (this.change2) {
        this.obj.subjectId3 = '';
        this.cate3 = [];
      }
      let c2 = this.obj.subjectId2;
      let that = this;
      if (c2 === '') {
        that.cate3 = false;
      } else {
        let catels = that.cate2;
        catels.map(function (item, i) {
          if (item.id === c2) {
            if (item.threeList && item.threeList.length > 0) {
              that.cate3 = item.threeList;
            } else {
              that.cate3 = false;
            }
          }
        })
      }
      if (this.$route.query.no) {
        this.change2 = true
      }
      // console.log(this.cate2)
    },
    // 获取课程信息
    getCourse () {
      this.load = true;
        teacherFreshCourseView({id: this.$route.query.no}).then(res => {
        console.log(res.data)
        this.load = false;
        if (res.data.code === 200) {
          let courseData = res.data.data;
          this.editor2.txt.html(courseData.introduce);
          this.obj = courseData;
          if (this.cate1.length) {
            this.changeCate1();
          }
        } else {
          if (res.code >= 300 && res.code < 400) {
              if(res.code == 304){
                  this.$msgBox({
                      content: '该账号被异地登录',
                      isShowCancelBtn: false
                  }).then(() => {
                      this.$store.dispatch('REDIRECT_LOGIN', result.code)
                  }).catch(() => {
                      this.$store.dispatch('REDIRECT_LOGIN', result.code)
                  })
              }
            this.$msgBox({
              content: res.msg,
              isShowCancelBtn: false
            }).then(() => {
              this.$store.dispatch('REDIRECT_LOGIN', result.code)
            }).catch(() => {
              this.$store.dispatch('REDIRECT_LOGIN', result.code)
            })
          } else {
            this.$msgBox({
              content: res.msg,
              isShowCancelBtn: false
            }).catch(() => {})
          }
        }
      }).catch(() => {
        this.load = false;
        this.$msgBox({
          content: '加载数据失败，请稍后刷新再试！！',
          isShowCancelBtn: false
        })
      })
    },
      // 编辑器上传图片
      editorUpload(files, insert) {
          console.log(files)
          let file = files[0];
          let param = new FormData();
          param.append('picFile', file, file.name);
          this.$nuxt.$loading.start()
          uploadPic(param, function (int) {
              console.log(int)
          }).then(res => {
              this.$nuxt.$loading.finish()
              console.log(res)
              console.log('load=======')
              if (res.code === 200) {
                  let imgUrl = res.data
                  insert(imgUrl)
              } else {
                  this.$msgBox({
                      content: res.msg,
                      isShowCancelBtn: false
                  })
              }
          }).catch(() => {
              this.$msgBox({
                  content: '上传图片出错，请稍后重试',
                  isShowCancelBtn: false
              })
          })
      }
  },
  // 页面打开
  mounted () {
    if (!(this.$route.query.t === 'paper')) {
    let E = require('wangeditor')
      this.editor2 = new E('#courseInfo')
        this.editor2.customConfig.uploadImgMaxLength = 1
        this.editor2.customConfig.customUploadImg = this.editorUpload
      this.editor2.create();
    }

    // 根据是否有课程编号判断添加或编辑
    if (this.$route.query.no) {
      this.getCourse();
    }
    // 课程分类数据
    infiniteCate().then(res => {
      // console.log(res.data)
      if (res.data.code === 200) {
        this.cate1 = res.data.data.courseSubjectList;
        if (this.obj.subjectId1) {
          this.changeCate1();
        }
      }
    })
  },
  components: {
    YHeader,
    YFooter,
    DUpload,
    YSide
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
@import '~/assets/css/account.scss';
  .step_panel{
    overflow: hidden;
    background-color: #eee;
    .step{
      float: left;
      width: 33.33333%;
      text-align: center;
      height: 50px;
      line-height: 50px;
      color: #333;
      font-size: 16px;
      position: relative;
      &:after{
        content: "";
        display: block;
        position: absolute;
        right: -12px;
        top: -5px;
        z-index: 1;
        width: 60px;
        height: 60px;
        box-shadow: 1px 1px 0 #fff;
        transform:rotate(-45deg);
      }
      &.on{
        color: #fff;
        background-color: lightskyblue;
        &:after{
          background-color: lightskyblue;
        }
      }
    }
  }
  .course_lesson{
    label{
      margin-right: 8px;
      color: #333;
    }
    .form_input{
      height: 34px;
      border: 1px solid #e7e7e7;
      width: 278px;
      text-indent: 1em;
    }
    .form_input_min{
      text-indent: 1em;
      width: 130px;
      height: 34px;
      margin-right: 10px;
      border: 1px solid #e7e7e7;
    }
  }
  .upload_ctl{
    .preview{
      width: 200px;
      height: 120px;
      overflow: hidden;
      background-color: #f5f5f5;
      text-align: center;
      margin-bottom: 10px;
      i{
        line-height: 120px;
        font-size: 80px;
        color: #ddd;
      }
    }
  }
  .ac_ctl{
    .item_ac_op{
      line-height: 30px;
    }
  }
</style>
