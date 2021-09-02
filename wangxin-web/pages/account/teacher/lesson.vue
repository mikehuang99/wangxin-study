<template>
  <div>
    <y-header></y-header>
    <div class="person_body clearfix">
      <y-side :type="'kcgl'"></y-side>
      <div class="person_content">
        <ul class="person_title clearfix">
          <li>第{{num}}章</li>
        </ul>
        <br/>
        <div style="font-size: 14px;border:1px solid #000">
          注意：以<span style="color: red">课程、章节</span>为维度绑定的试题<span style="color: red">不能用于练习</span>，只可以用于组卷，以<span style="color: red">课时</span>为维度绑定的试题<span style="color: red">才可以用于练习</span>。
        </div>
        <div class="tip">可<span class="c_red">"拖动"</span>名称调整先后顺序</div>
        <div class="person_info ">
          <table class="table ">
            <thead>
              <tr>
                <th>序号</th>
                <th>课时名称</th>
                <th>是否免费</th>
                <th>课程类型</th>
                <th>视频/直播</th>
                <!--
                <th>直播</th>-->
                <th>课件</th>
                <th>状态</th>
                <th>试题管理[课时]</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in list" :key="item.id" v-if="item.lessonDesc != 'true'"  v-dragging="{ item: item, list: list, group: 'color' }">
                <td>{{index + 1}}</td>
                <td class="name"><span :title="item.lessonName">{{item.lessonName| cutTile}}</span></td>
                <td>
                  <span v-if="item.isFree == 1" class="c_green">免费</span>
                  <span v-if="item.isFree == 0"  class="c_red">收费</span>
                </td>
                <td>
                  <div>
                    <select v-model="item.lessonType" @change="changeLessonType(item.id,item.lessonType)" name="" id="" class="form_input_min_min">
                      <option value="">-请选择-</option>
                      <option v-for="st in lessonTypes" :checked="item.lessonType == st.id" :key="st.id" :value="st.id"
                              v-html="st.name"></option>
                    </select>
                  </div>
                </td>
                <td class="c_green">
                  <button v-if="item.lessonType !== 1" @click="openVideo(item.id,2)" :class="{on: 1}" class="blue_btn">视频管理</button>
                  <button v-if="item.lessonType ===1" @click="openLiveBroadcast(item.id,item.liveBroadcastBeginTime,item.liveBroadcastPlanLength,item.liveBroadcastHlsAddress,2)" :class="{on: 1}" class="blue_btn">直播管理</button>
                </td>
                <!--
                <td class="c_green"><button v-if="item.lessonType ===1" @click="openLiveBroadcast(item.id,item.liveBroadcastBeginTime,item.liveBroadcastPlanLength,item.liveBroadcastHlsAddress,2)" :class="{on: item.videoNum}" class="gray_btn">直播管理<i class="num">{{item.videoNum}}</i></button></td>
                -->
                <td class="c_green">
                  <a v-if="item.isDoc" :href="item.docUrl">{{item.docName}}</a>
                  <span v-else>暂无附件</span>
                </td>
                <td class="c_green" v-if="item.applyStatus">已审核</td>
                <td class="c_blue" v-else>待审核</td>
                <td class="operate">
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId:item.courseId,chapterId:obj.chapterId,lessonId: item.id,bindType:'3',isRelate:'0'  }}" class="text_link">绑定试题</router-link>
                  </span>
                  <br>
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId:item.courseId,chapterId:obj.chapterId,lessonId: item.id,bindType:'3',isRelate:'1' }}" class="text_link">查看试题</router-link>
                  </span>
                  <br>
                  <span>
                    <router-link :to="{ name: 'examp-bindExamQuestion', query: { courseId:item.courseId,chapterId:obj.chapterId,lessonId: item.id,bindType:'3',isRelate:'1' }}" class="text_link">解绑试题</router-link>
                  </span>
                </td>
                <td class="operate">
                  <a href="javascript:void(0)" @click="edit1(index)" class="text_link">修改</a><br>
                  <a href="javascript:void(0)" @click="del(item.id)" class="text_link">删除</a>
                </td>
              </tr>
              <tr v-else>
                <td>{{index + 1}}
                </td>
                <td class="name"><input type="text" v-model="item.lessonName" class="form_input" placeholder="请输入课时名称"></td>
                <td><input type="checkbox" v-model="item.isFree"></td>
                <td> - </td>
                <td> - </td>
                <td><y-upload :upOk="isUp" @rtnUrl="getUrl" :isresource="true" /></td>
                <td> - </td>
                <td> - </td>
                <td class="operate">
                  <button class="solid_btn b_red" @click="updatas(item)" :disabled="solidBtn">保存</button>
                </td>
              </tr>
              <tr>
                <td>
                  {{sort}}
                </td>
                <td class="name"><input type="text" v-model="obj.lessonName" class="form_input" placeholder="请输入课时名称"></td>
                <td><input type="checkbox" v-model="obj.isFree" value="0"></td>
                <td> - </td>
                <td> - </td>
                <td><y-upload :upOk="isUp" @rtnUrl="getUrl" :isresource="true" /></td>
                <td> - </td>
                <td> - </td>
                <td class="operate">
                  <button class="solid_btn b_red" @click="addPraxis" :disabled="solidBtn">保存</button>
                </td>
              </tr>
            </tbody>
          </table>
          <a href="javascript:" @click="$router.back(-1)" class="cont_btn solid_btn b_red">完成返回</a>
        </div>
      </div>
    </div>
    <y-footer></y-footer>
    <y-video v-if="showVideo" :open="showVideo" :data="videoData" :type="videoType" @hidefun="hideModal()"></y-video>
    <live-broadcast v-if="showLiveBroadcast"
                    :open="showLiveBroadcast"
                    :lessonId="this.videoData.lessonId"
                    :liveBroadcastBeginTime="this.videoData.liveBroadcastBeginTime"
                    :liveBroadcastPlanLength="this.videoData.liveBroadcastPlanLength"
                    :liveBroadcastHlsAddress="this.videoData.liveBroadcastHlsAddress"
                    :data="videoData"
                    :type="videoType"
                    @hidefun="hideLiveBroadcast()"></live-broadcast>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YFooter from '~/components/common/Footer'
import YSide from '~/components/account/Side'
import YVideo from '~/components/account/VideoModal'
import LiveBroadcast from '~/components/account/LiveBroadcast'
import YUpload from '~/components/account/Upload'
import {teacherFreshLessonList, teacherFreshLessonSave, teacherFreshLessonDelete, teacherFreshLessonUpdate,teacherFreshLessonUpdateLessonApplyLessonType, teacherFreshCourseVideoSort } from '~/api/account/course.js'
export default {
  data () {
    return {
      load: false,
      tab: 1,
      num: 1,
      list: [],
      solidBtn: false,
      showVideo: false,
      showLiveBroadcast: false,
      videoType: 1,
      videoData: null,
      courseId:'',
        lessonTypes: [{
            "id": 0,
            "name": "点播"
        }, {
            "id": 1,
            "name": "直播"
        }, {
            "id": 3,
            "name": "回放"
        }],
      obj: {
        isFree: 0,
        chapterNo: 0,
        courseNo: 0,
        teacherUserNo: 0,
        orgNo: '',
        lessonName: ''
        // sort: 0
      },
      sort: 0,
      docUrl: ''
    }
  },
  computed: {
    isUp () {
      if (this.docUrl) {
        return false
      } else {
        return true
      }
    }
  },
    filters: {
        cutTile(value) {
            if (!value) return ''
            if (value.length > 20) {
                return value.slice(0, 20) + '...'
            }
            return value
        }
    },
  methods: {
      changeLessonType(id, lessonType){
          teacherFreshLessonUpdateLessonApplyLessonType({'id':id,'lessonType':lessonType}).then(res => {
              // console.log(res)
              if (res.data.code === 200) {
                  // this.$msgBox({
                  //    content: '更新成功',
                  //    isShowCancelBtn: false
                  //})
              } else {
                  if (res.data.code > 300 && res.data.code < 400) {
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
                      this.$msgBox({
                          content: '登录异常，请重新登录',
                          isShowCancelBtn: false
                      }).then(() => {
                          this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
                      }).catch(() => {
                          this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
                      })
                  }
              }
          }).catch(() => {
              this.$msgBox({
                  content: '更新失败',
                  isShowCancelBtn: false
              })
          })
      },
    // 获取上传附件
    getUrl (file) {
      console.log(file)
      console.log('file======')
      this.docUrl = file.url || ''
      this.obj.docName = file.name || ''
      this.obj.docUrl = file.url || ''
    },
    // 展开视频选择
    openVideo (id, ty) {
      this.videoData = {
        cNo: this.$route.query.no,
        pNo: id
      };
      this.videoType = ty;
      this.showVideo = true;
    },
      // 展开直播管理
      openLiveBroadcast (id,liveBroadcastBeginTime,liveBroadcastPlanLength,liveBroadcastHlsAddress, ty) {
          this.videoData = {
              //cNo: this.$route.query.no,
              //pNo: id,
              lessonId: id,
              liveBroadcastBeginTime: liveBroadcastBeginTime,
              liveBroadcastPlanLength: liveBroadcastPlanLength,
              liveBroadcastHlsAddress: liveBroadcastHlsAddress
          };
          this.videoType = ty;
          this.showLiveBroadcast = true;
      },
    hideModal: function () {
      this.showVideo = false;
      this.chapterList();
    },
    hideLiveBroadcast: function () {
          this.showLiveBroadcast = false;
          this.chapterList();
    },
    // 修改例题
    edit1 (no) {
      let arr = this.list
      arr[no].lessonDesc = 'true';
      this.list = arr;
      // console.log(no)
    },
    // 更新例题
    updatas (data) {
      if (data.lessonName === '') {
        this.$msgBox({
          content: '请输入课时名称',
          isShowCancelBtn: false
        }).catch(() => {})
        return
      }
      this.solidBtn = true;
      data.lessonDesc = '';
      data.isFree = data.isFree ? 1 : 0;
      data.docName = this.obj.docName || data.docName;
      data.docUrl = this.obj.docUrl || data.docUrl;
      if (data.docName) {
        data.isDoc = 1
      }
        teacherFreshLessonUpdate(data).then(res => {
        this.solidBtn = false;
        // console.log(res)
        if (res.data.code === 200) {
          this.obj.docName = ''
          this.obj.docUrl = ''
          this.docUrl = ''
        } else {
          if (res.data.code > 300 && res.data.code < 400) {
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
            this.$msgBox({
              content: '登录异常，请重新登录',
              isShowCancelBtn: false
            }).then(() => {
              this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
            }).catch(() => {
              this.$store.dispatch('REDIRECT_LOGIN', res.data.code)
            })
          }
        }
      }).catch(() => {
        this.solidBtn = false;
        this.$msgBox({
          content: '更新失败',
          isShowCancelBtn: false
        })
      })
    },
    // 删除例题
    del (no) {
      let that = this;
      this.$msgBox({
        content: '你确定要删除该课时吗?'
      }).then(async (val) => {
          teacherFreshLessonDelete({id: no}).then(res => {
          res = res.data
          console.log(res)
          if (res.code === 200) {
            this.$msgBox({
              content: '删除成功',
              isShowCancelBtn: false
            })
            that.chapterList();
          } else {
            if (res.code >= 300 && res.code < 400) {
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
          // console.log(res)
        })
      }).catch(() => {
      })
    },
    // 例题列表
    chapterList () {
        teacherFreshLessonList({
        chapterId: this.$route.query.no
      }).then(res => {
        res = res.data;
        console.log(res)
        if (res.code === 200) {
          this.list = res.data.list || [];
          this.obj.courseNo = res.data.courseNo;
          this.sort = this.list.length + 1;
          // this.setSort();
        } else {
          if (res.code >= 300 && res.code < 400) {
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
        // console.log(res)
      }).catch(() => {
      })
    },
    // 保存排序
    saveSort () {
      // console.log(this.list)
      if (!this.list.length) {
        return
      }
        teacherFreshCourseVideoSort({lessonViewList: this.list}).then(res => {
        res = res.data
        // console.log(res)
        if (res.code === 200) {
          this.isSort = 1;
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
    // 添加例题
    addPraxis () {
      if (!this.obj.lessonName) {
        this.$msgBox({
          content: '课时名称不能为空',
          isShowCancelBtn: false
        })
        return
      }
      this.solidBtn = true;
      // console.log(this.obj)
      this.sort = this.int;
      this.obj.isFree = this.obj.isFree ? 1 : 0;
      console.log(this.obj)
      console.log('aaaaa-----')
        teacherFreshLessonSave(this.obj).then(res => {
        res = res.data;
        this.solidBtn = false;
        if (res.code === 200) {
          this.chapterList();
          this.obj.lessonName = '';
          this.obj.isFree = '';
          this.obj.docName = '';
          this.obj.docUrl = '';
          this.docUrl = ''
        } else {
          if (res.code >= 300 && res.code < 400) {
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
        // console.log(res)
      }).catch(() => {
        this.solidBtn = false;
        this.$msgBox({
          content: '添加失败',
          isShowCancelBtn: false
        })
      })
    },

  },
  mounted () {

    this.num = this.$route.query.i ? this.$route.query.i : 1;
    this.obj.chapterId = this.$route.query.no;


    this.chapterList();

    this.$dragging.$off("dragend");//使用拖动的时候一定要清除原先的事件，否则越拖执行次数就会累积。并且影响其他页面的拖动
    this.$dragging.$on('dragend', ({ value }) => {
      //console.log("----》》"+value.list)
            this.saveSort();

    })
  },
  components: {
      LiveBroadcast,
    YHeader,
    YFooter,
    YVideo,
    YUpload,
    YSide
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
@import '~/assets/css/account.scss';
  .person_body {
    width: 1200px;
    margin: 30px auto 0;
    min-height: 1000px;
    .upload_com {
      .solid_btn {
        display: inline-block;
        height: 25px;
        line-height: 22px;
        background: #fff;
        border-color: #ddd;
        color: #999;
        outline: none;
        &:hover {
          color: blue;
          border-color: blue;
        }
      }
    }
  }
  .person_content {
    width: 1012px;
    float: right;
    background: #fff;
    border-radius: 6px;
  }
  .person_title {
    border-bottom: 1px solid rgb(242, 242, 242);
    li {
      color: #D51423;
      float: left;
      line-height: 60px;
      padding: 0 30px;
      border-bottom: 2px solid #D51423;
      font-size: 14px;
    }
  }
  .person_info {
    padding-bottom: 40px;
  }
  .name{
    .form_input{
      margin: 10px 0;
      height: 34px;
      border: 1px solid #e7e7e7;
      width: 120px;
      text-indent: 1em;
    }
  }
  .cont_btn {
    width: 280px;
    display: block;
    margin:20px auto 0;
  }
.form_input_min_min {
  text-indent: 1em;
  width: 80px;
  height: 34px;
  margin-right: 10px;
  border: 1px solid #e7e7e7;
}
</style>
