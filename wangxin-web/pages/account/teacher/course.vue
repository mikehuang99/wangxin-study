<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'kcgl'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: tab == 3}" @click="clicktab(3)">全部课程</a>
          <a class="tab" :class="{on: tab == 1}" @click="clicktab(1)">已经审核</a>
          <a class="tab" :class="{on: tab == 0}" @click="clicktab(0)">等待审核</a>
          <a class="tab" :class="{on: tab == 2}" @click="clicktab(2)">审核失败</a>
          <nuxt-link :to="{name:'account-teacher-add'}" class="fr solid_btn">新增课程</nuxt-link>
        </ul>
        <br/>
        <div style="font-size: 14px;border:1px solid #000">
          注意：以<span style="color: red">课程、章节</span>为维度绑定的试题<span style="color: red">不能用于练习</span>，只可以用于组卷，以<span style="color: red">课时</span>为维度绑定的试题<span style="color: red">才可以用于练习</span>。
        </div>
        <br/>
        <div style="margin-left: 20px">
          <div style="float:left;margin-right: 10px;font-size: 18px;font-weight: bold">课程名称:</div>
          <div style="float: left;">
            <input type="text" v-model="pda.courseName" style="height: 25px;width: 500px" placeholder="请输入课程名称">
          </div>
          <div @click="queryCourseList" v-if="queryState==false" class="fr solid_btn">
            查询
          </div>
          <div v-if="queryState==true" class="fr solid_btn">
            查询中...
          </div>
        </div>
        <br/>
        <br/>
        <div class="main_cont">
          <div class="notdata" v-if="notdata">
            ★暂时没有数据
          </div>
          <table class="course_table table" v-else>
            <thead>
              <tr>
                <th>课程名称</th>
                <th>是否付费</th>
                <th>价格</th>
                <th>状态</th>
                <th>上下架</th>
                <th>试题管理[课程]</th>
                <th>试卷管理</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in courseList" :key="item.courseNo">
                <td class="name"><img class="icon" :src="item.courseLogo" alt="">
                  <p class="fr txt">{{item.courseName}}</p></td>
                <td v-if="item.isFree"><span style="background-color: #05d005;font-size: 18px;color: white">免费</span></td>
                <td v-else><span style="background-color: #CA9E70;font-size: 18px;color: white">付费</span></td>
                <td class="c_red"><span style="text-decoration:line-through;font-size: 12px ">原:¥{{item.courseOriginal}}</span><br/><span style="font-size: 16px">现:¥{{item.courseDiscount}}</span></td>
                <td class="c_green" v-if="item.applyStatus === 1"><span style="background-color: #05d005;font-size: 12px;color: white">已经审核</span></td>
                <td class="c_red" v-else-if="item.applyStatus === 2"><span style="background-color: #CA9E70;font-size: 18px;color: white">审核失败</span></td>
                <td class="c_blue" v-else><span style="background-color: cornflowerblue;font-size: 12px;color: white">等待审核</span></td>
                <td v-if="item.isPutaway"><button class="gray_btn" @click="putaWay(item)">课程下架</button></td>
                <td v-else><button class="blue_btn" @click="putaWay(item)">课程上架</button></td>
                <td class="operate">
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId: item.id,bindType:'1',isRelate:'0'  }}" class="text_link">绑定试题</router-link>
                  </span>
                  <br>
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId: item.id,bindType:'1',isRelate:'1' }}" class="text_link">查看试题</router-link>
                  </span>
                  <br>
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId: item.id,bindType:'1',isRelate:'1' }}" class="text_link">解绑试题</router-link>
                  </span>
                </td>
                <td class="operate">
                  <span>
                    <router-link :to="{ name: 'examp-paper', query: { courseId: item.id}}" class="text_link">试卷管理</router-link>
                  </span>
                </td>
                <td class="operate">
                  <span>
                    <router-link :to="{ name: 'account-teacher-add', query: { no: item.id }}" class="text_link">修改</router-link>
                  </span>
                  <br>
                  <span>
                    <router-link :to="'/account/teacher/chapter?no=' +item.id" class="text_link">章节管理</router-link>
                  </span>
                  <br>
                  <span>
                    <a v-if="item.isDelete" @click="deleteItem(item.id)" href="javascript:" class="text_link">删除</a>
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
          <d-page v-if="pageObj.totalPage > 1" :page="pageObj" @btnClick="getPage"></d-page>
        </div>
      </div>
    </div>
    <y-footer></y-footer>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YFooter from '~/components/common/Footer'
import YSide from '~/components/account/Side'
import DPage from '~/components/Page'
import {teacherFreshCourseList, teacherFreshCourseUpdateCoursePutaway, deleteCourse} from '~/api/account/course.js'
import {getUserInfo} from '~/api/user.js'
import VueCookie from 'vue-cookie'

export default {
  data () {
    return {
      queryState: false,
      side: 'kcgl',
      tab: 3,
      notdata: true,
      pageCurrent: 1,
      courseList: [],
      pda: {
        courseSubject: 1,
          applyStatus: '',
        teacherUserNo: '',
          courseName:'',
        pageCurrent: 1,
        pageSize: 20
      },
      pageObj: {
        pageCurrent: '',
        pageSize: '',
        totalCount: '',
        totalPage: ''
      }
    }
  },
  computed: {
    webInfo () {
      return this.$store.state.webInfo
    }
  },
  methods: {
    // 删除课程
    deleteItem (id) {
      this.$msgBox({
        content: '确认删除该课程？'
      }).then(() => {
        deleteCourse({id}).then(res => {
          let result = res.data
          if (result.code === 200) {
            this.$msgBox({
              content: '删除成功',
              isShowCancelBtn: false
            })
            this.getCourseList()
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
                content: '登录超时、异常或者被异地登录，请重新登录',
                isShowCancelBtn: false
              }).then(() => {
                this.$store.dispatch('REDIRECT_LOGIN', result.code)
              }).catch(() => {
                this.$store.dispatch('REDIRECT_LOGIN', result.code)
              })
            } else {
              this.$msgBox({
                content: result.msg,
                isShowCancelBtn: false
              }).catch(() => {})
            }
          }
        }).catch(() => {
        })
      }).catch(() => {
        // console.log('取消')
      })
    },
    getPage: function (int) {
      this.pageCurrent = int;
      this.getCourseList();
    },
    // 课程上下架
    putaWay (item) {
      // console.log(item)
      let isp = item.isPutaway ? 0 : 1;
      let txt = '确定下架该课程';
      if (isp) {
        txt = '确定上架该课程?'
      }
      this.$msgBox({
        content: txt
      }).then(res => {
          teacherFreshCourseUpdateCoursePutaway({
          id: item.id,
          isPutaway: isp
        }).then(res => {
          let result = res.data
          console.log(result)
          if (result.code === 200) {
            item.isPutaway = isp;
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
                content: '登录超时、异常或者被异地登录，请重新登录',
                isShowCancelBtn: false
              }).then(() => {
                this.$store.dispatch('REDIRECT_LOGIN', result.code)
              }).catch(() => {
                this.$store.dispatch('REDIRECT_LOGIN', result.code)
              })
            } else {
              this.$msgBox({
                content: result.msg,
                isShowCancelBtn: false
              }).catch(() => {})
            }
          }
        }).catch(msg => {
        this.$msgBox({
          content: '操作失败',
          isShowCancelBtn: false
        })
      })
      })
    },
    clicktab: function (int) {
      this.tab = int;
      this.pageCurrent = 1;
      this.getCourseList();
    },
      queryCourseList(){
          this.queryState = true;
        this.pageCurrent = 1;
        this.getCourseList();

      },
    getCourseList () {
      let astat = this.tab === 3 ? '' : this.tab;
      this.pda.applyStatus = astat;
      this.pda.pageCurrent = this.pageCurrent;
      console.log(this.pda)
        teacherFreshCourseList(this.pda).then(res => {
            this.queryState = false;
        let result = res.data;
        if (result.code === 200 && result.data.list.length > 0) {
          this.courseList = result.data.list;
          this.notdata = false;
          this.pageObj = result.data;
        } else if (result.code > 300 && result.code <400) {
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
            content: '登录超时、异常或者被异地登录，请重新登录',
            isShowCancelBtn: false
          }).then(() => {
            this.$store.dispatch('REDIRECT_LOGIN', result.code)
          }).catch(() => {
            this.$store.dispatch('REDIRECT_LOGIN', result.code)
          })
        } else {
          this.notdata = true;
          this.pageObj.totalPage = 0;
        }
      }).catch(msg => {
            this.queryState = false;
        this.$msgBox({
          content: '加载数据失败，请稍后刷新再试！！',
          isShowCancelBtn: false
        })
      })
    }
  },
  mounted () {
    // console.log('课程列表')
    this.getCourseList();
    try {
        this.pda.teacherUserNo = JSON.parse(VueCookie.get("wangxinUserInfo")).userNo;
    }catch (e) {
        this.$router.push({name: 'index'});
    }
  },
  created () {
  },
  components: {
    YHeader,
    YFooter,
    YSide,
    DPage
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
@import '~/assets/css/account.scss';
a:hover {  color : blue ; }
.table th {//覆盖account.scss的表头属性
  line-height: 40px !important;
  color: #333 !important;
  background-color: lightskyblue !important;
}
  .course_table{
    .name{
      width: 380px;
      .txt{
        width: 175px;
        padding-top: 12px;
        font-size: 14px;
      }
    }
    .icon{
      width: 175px;
      vertical-align: middle;
    }
  }
.form_ctl_search {
  margin-left: 12px;
  float: left;
  width: 200px;
  position: relative;
  z-index: 9;
}
</style>
