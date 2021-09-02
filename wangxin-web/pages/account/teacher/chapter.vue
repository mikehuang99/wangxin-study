<template>
  <div class="">
    <y-header></y-header>
    <div class="container account_cont">
      <y-side :type="side"></y-side>
      <div class="main_box">
        <div class="step_panel">
          <div class="step on">1.填写课程信息</div>
          <div class="step on">2.填写章节</div>
          <div class="step">3.填写课时</div>
        </div>
        <div class="main_cont course_lesson">
          <div style="font-size: 14px;border:1px solid #000">
            注意：以<span style="color: red">课程、章节</span>为维度绑定的试题<span style="color: red">不能用于练习</span>，只可以用于组卷，以<span style="color: red">课时</span>为维度绑定的试题<span style="color: red">才可以用于练习</span>。
          </div>
          <div class="tip">可<span class="c_red">"拖动"</span>名称调整先后顺序</div>
          <table class="table">
            <thead>
              <tr>
                <th width="80">序号</th>
                <th>章节名称</th>
                <th>试题管理[章节]</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in chapterList" v-dragging="{ item: item, list: chapterList }" :key="item.chapterNo" v-if="item.extendField1 != 'true'">
                <td style="font-size: 16px">第{{ index + 1 }}章</td>
                <td class="name">{{item.chapterName}}</td>
                <td class="operate">
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId:newData.courseId,chapterId: item.id,bindType:'2',isRelate:'0'  }}" class="text_link">绑定试题</router-link>
                  </span>
                  <br>
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId:newData.courseId,chapterId: item.id,bindType:'2',isRelate:'1' }}" class="text_link">查看试题</router-link>
                  </span>
                  <br>
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId:newData.courseId,chapterId: item.id,bindType:'2',isRelate:'1' }}" class="text_link">解绑试题</router-link>
                  </span>
                </td>
                <td class="operate">
                  <a href="javascript:" class="text_link" @click="edit(index)">修改</a><br>
                  <router-link :to="'/account/teacher/lesson?no=' +item.id+ '&i='+ (index +1)" class="text_link">
                    课时管理
                    <span v-if="item.lessonNum" class="c_red">({{item.lessonNum}})</span>
                  </router-link><br>
                  <a href="javascript:" @click="del(item.id)" class="text_link">删除</a>
                </td>
              </tr>
              <tr v-else>
                <td>第{{ index + 1 }}讲</td>
                <td class="name"><input type="text" v-model="item.chapterName" class="form_input" placeholder="请输入课时名称"></td>
                <td> - </td>
                <td class="operate">
                  <button @click="updatas(item)" class="solid_btn" :disabled="btnDab">保存</button>
                </td>
              </tr>
              <tr>
                <td>第{{num}}讲</td>
                <td class="name"><input type="text" v-model="newData.chapterName" class="form_input" placeholder="请输入课时名称"></td>
                <td> - </td>
                <td class="operate">
                  <button @click="addLesson" class="solid_btn" :disabled="btnDab">保存</button>
                </td>
              </tr>
            </tbody>
          </table>
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
import {teacherFreshChapterSave,teacherFreshChapterList, teacherFreshChapterUpdate, teacherFreshChapterDelete, teacherFreshChapterSort} from '~/api/account/course.js'
export default {
  head () {
    return {
      title: '添加课程章节'
    }
  },
  data () {
    return {
      showModal: false,
      modalData: null,
      side: 'kcgl',
      num: 1,
      btnDab: false,
      chapterList: [],
      newData: {
        'chapterName': '',
        'courseId': 0,
        'isFree': 0
      }
    }
  },
  methods: {
    addLesson () {
      if (this.newData.chapterName === '') {
        this.$msgBox({
          content: '请输入课时名称',
          isShowCancelBtn: false
        })
        return false;
      }
      this.btnDab = true;
      this.newData.sort = this.num;
        teacherFreshChapterSave(this.newData).then(res => {
        res = res.data;
        // console.log(res)
      this.btnDab = false;
        if (res.code === 200) {
          this.chapterList.push(res.data)
          this.num = this.chapterList.length + 1;
          this.newData.chapterName = '';
        } else {
          if (res.code >= 300 && res.code < 400) {
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
              content: res.msg,
              isShowCancelBtn: false
            }).then(() => {
                this.$store.dispatch('REDIRECT_LOGIN', res.code)
            }).catch(() => {
              this.$store.dispatch('REDIRECT_LOGIN', res.code)
            })
          } else {
            this.$msgBox({
              content: res.msg,
              isShowCancelBtn: false
            }).catch(() => {})
          }
        }
      }).catch(() => {
        this.btnDab = false;
        this.$msgBox({
          content: '添加失败',
          isShowCancelBtn: false
        })
      })
    },
    // 修改例题
    edit (no) {
      let arr = this.chapterList
      arr[no].extendField1 = 'true';
      this.chapterList = Object.assign([], arr);
    },
    // 更新例题
    updatas (data) {
      if (data.chapterName === '') {
        this.$msgBox({
          content: '请输入课时名称',
          isShowCancelBtn: false
        }).catch(() => {})
        return
      }
      this.btnDab = true;
      data.lessonDesc = '';
        teacherFreshChapterUpdate(data).then(res => {
        // console.log(res)
        this.btnDab = false;
        this.getChapterList();
      }).catch(() => {
        this.btnDab = false;
        this.$msgBox({
          content: '修改失败',
          isShowCancelBtn: false
        })
      })
    },
    // 删除例题
    del (no) {
      let that = this;
      this.$msgBox({
        content: '确认删除该章节?'
      }).then(async (val) => {
          teacherFreshChapterDelete({id: no}).then(res => {
          res = res.data;
          // console.log(res)
          if (res.code === 200) {
            this.$msgBox({
              content: '删除成功',
              isShowCancelBtn: false
            })
            that.getChapterList();
          } else {
            if (res.code >= 300 && res.code < 400) {
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
                content: res.msg,
                isShowCancelBtn: false
              }).then(() => {
                this.$store.dispatch('REDIRECT_LOGIN', res.code)
              }).catch(() => {
                this.$store.dispatch('REDIRECT_LOGIN', res.code)
              })
            } else {
              this.$msgBox({
                content: res.msg,
                isShowCancelBtn: false
              }).catch(() => {})
            }
          }
        })
      }).catch(() => {
        //this.$msgBox({
        //  content: '删除失败',
        //  isShowCancelBtn: false
        //})
        })
    },
    // 保存排序
    saveSort () {
      // console.log(this.chapterList)
      let sort = new Array();
      for (var i = 0; i < this.chapterList.length; i++) {
        sort.push(this.chapterList[i].id)
      }
        teacherFreshChapterSort({chapterIdList: sort}).then(res => {
        res = res.data
        // console.log(res)
        if (res.code === 200) {
        } else {
          this.$msgBox({
            content: res.msg,
            isShowCancelBtn: false
          })
        }
      }).catch(() => {
        this.$msgBox({
          content: '排序保存失败',
          isShowCancelBtn: false
        })
      })
    },
    getChapterList () {
      // courseId
        teacherFreshChapterList({
        courseId: this.newData.courseId
      }).then(res => {
        res = res.data;
        // console.log(res)
        if (res.code === 200) {
          this.chapterList = res.data.list || [];
          this.num = this.chapterList.length + 1;
        } else {
          if (res.code >= 300 && res.code < 400) {
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
              content: res.msg,
              isShowCancelBtn: false
            }).then(() => {
                this.$store.dispatch('REDIRECT_LOGIN', res.code)
            }).catch(() => {
              this.$store.dispatch('REDIRECT_LOGIN', res.code)
            })
          } else {
            this.$msgBox({
              content: res.msg,
              isShowCancelBtn: false
            }).catch(() => {})
          }
        }
      }).catch(msg => {
        this.$msgBox({
          content: '加载数据失败，请稍后刷新再试！！',
          isShowCancelBtn: false
        })
      })
    }
  },
  mounted () {
    this.newData.courseId = this.$route.query.no;
    this.num = this.chapterList.length + 1;
    this.getChapterList();

    this.$dragging.$off("dragend");//使用拖动的时候一定要清除原先的事件，否则越拖执行次数就会累积。并且影响其他页面的拖动
    this.$dragging.$on('dragend', () => {
            this.saveSort();
    })
  },
  components: {
    YHeader,
    YFooter,
    YSide
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
@import '~/assets/css/account.scss';
.table th {//覆盖account.scss的表头属性
  line-height: 40px !important;
  color: #333 !important;
  background-color: lightskyblue !important;
}
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
      font-size: 14px;
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
        background-color: cornflowerblue;
        &:after{
          background-color: cornflowerblue;
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
    .cont_btn {
      width: 280px;
      display: block;
      margin:20px auto 0;
    }
  }
  .dragging{
    color: #ec7e00;
  }
</style>
