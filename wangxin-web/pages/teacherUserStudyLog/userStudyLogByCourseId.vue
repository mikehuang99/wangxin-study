<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'teacherUserStudyLog'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on" >学习记录</a>
          <div @click="back" class="fr solid_btn">返回</div>
        </ul>
        <div class="main_cont form">
          <span style="font-size: 16px;font-weight: bold">用户姓名:{{realName}}</span><br/>
          <span>课程名称:{{courseName}}</span><br/>
          <span>提示:本学习记录只记录点播/录播的学习记录，直播、回放的不会记录</span><br/>
          <form action="">
            <div class="form_group">
              <div class="label" v-if="this.pda.bindType==1">课程:</div>
              <div class="label" v-if="this.pda.bindType==2">章节:</div>
              <div class="label" v-if="this.pda.bindType==3">课时:</div>
              <div class="form_ctl">
                <div class="label">课程:</div>
                <select v-model="pda.courseId"@change="changeCcpCate1"  name="" id="" class="form_input_min">
                  <option value="">--请选择课程--</option>
                  <option v-for="cate in ccpCate1" :checked="pda.courseId == cate.id" :key="cate.id" :value="cate.id"
                          v-html="cate.name"></option>
                </select>
                <select name="" v-model="pda.chapterId" @change="changeCcpCate2"  id="" v-if="ccpCate2 && ccpCate2.length"
                        class="form_input_min">
                  <option value="">--请选择章节--</option>
                  <option v-for="cate in ccpCate2" :key="cate.id" :value="cate.id" v-html="cate.name"></option>
                </select>
                <select name="" v-model="pda.lessonId"  id=""  class="form_input_min" v-if="ccpCate3 && ccpCate3.length">
                  <option value="">--请选择课时--</option>
                  <option v-for="cate in ccpCate3" :key="cate.id" :value="cate.id">{{cate.name}}</option>
                </select>
              </div>
            </div>
            <hr/>
            <div class="form_group">
                <div class="label">进度>=:</div>
                <div class="form_ctl_search">
                  <input type="text" v-model="pda.studyProgressGreaterThan" class="form_input_mid" placeholder="请输入0到100数字，不要输入%">
                </div>
                <div class="label">进度<=:</div>
                <div class="form_ctl_search">
                  <input type="text" v-model="pda.studyProgressLessThan" class="form_input_mid" placeholder="请输入0到100数字，不要输入%">
                </div>
              </div>
            <div class="form_group">
              <div @click="getUserStudyLogList(true)" v-if="queryState==false" class="fr solid_btn">
                查询
              </div>
              <div v-if="queryState==true" class="fr solid_btn">
                查询中...
              </div>
            </div>
          </form>
          <div class="main_cont">
            <div class="notdata" v-if="notdata">
              ★暂时没有数据
            </div>
            <table class="exam_management_list_table table" v-else>
              <thead>
              <tr>
                <!--
                <th><input type="checkbox" @click="allSelect()"></th>
                -->
                <th>序号</th>
                <!--
                <th>课程</th>-->
                <th>课程</th>
                <th>章节</th>
                <th>课时</th>
                <th>学习进度</th>
                <th>最近学习时间</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item,index) in courseList" :key="item.id">
                <!--
                <td><input type="checkbox" :value="item.id" v-model="pda.ids"></td>
                -->
                <td>{{index=index+1}}</td>
                <td><label :title="item.courseName" class="test_a">{{item.courseName | cutTile}}</label></td>
                <td><label :title="item.chapterName" class="test_a">{{item.chapterName | cutTile}}</label></td>
                <td><label :title="item.lessonName" class="test_a">{{item.lessonName | cutTile}}</label></td>
                <td>{{item.studyProgress}}%</td>
                <td>{{item.modifiedTime}}</td>
              </tr>
              </tbody>
            </table>
            <d-page v-if="pageObj.totalPage > 1" :page="pageObj" @btnClick="getPage"></d-page>
            <div v-if="queryState==true" style="text-align:center;color: red">
              正在刷新中...
            </div>
          </div>
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
    import {infiniteCate, infiniteCcpCate,examManagementDelete} from '~/api/account/course.js'
    import {userStudyLogListByTeacherUserNo} from '~/api/account/user.js'

    export default {
        name: "question",

        data() {
            return {
                isAllSelect:false,
                chapterIds:[],
                lessonIds:[],
                subject: "",
                answer: "",
                ccpCate1:[],
                ccpCate2:false,
                ccpCate3:false,
                cate1: [],
                cate2: false,
                cate3: false,
                queryState:false,
                obj: {
                    "id": '',
                    "createTime": '',
                    "modifiedTime": '',
                    "validValue": '',
                    "sort": '',
                    "title": '',
                    "introduce": '',
                    "examObjectType": '',
                    "studentGroupIds": '',
                    "examType": '',
                    "startTime": '',
                    "endTime": '',
                    "isAllowSwitchWindow": '',
                    "isShowConfirmButton": '',
                    "isAllowRightClick": '',
                    "courseName":'',
                    "examPaperTitle":''
                },
                notdata: true,
                courseList: [],
                realName: '',
                pda: {
                    ids:[],
                    courseId:'',
                    studyUserNo:'',


                    studyProgressGreaterThan:'',
                    studyProgressLessThan:'',
                    title:'',
                    examType:'',
                    introduce:'',
                    level:'',
                    validValue:'',
                    examPaperTitle:'',
                    pageCurrent: 1,
                    pageSize: 20
                },
                pageObj: {
                    pageCurrent: '',
                    pageSize: '',
                    totalCount: '',
                    totalPage: ''
                },
                questionTypes: [{
                    "id": "1",
                    "name": "单选题"
                }, {
                    "id": "2",
                    "name": "多选题"
                }, {
                    "id": "3",
                    "name": "判断题"
                }, {
                    "id": "4",
                    "name": "填空题"
                }, {
                    "id": "5",
                    "name": "简答题"
                }],
                examTypes:[{
                    "id":"1",
                    "name":"定时考试"
                },{
                    "id":"2",
                    "name":"限时考试"
                }],
                levels: [{
                    "id": "5",
                    "name": "非常难"
                }, {
                    "id": "4",
                    "name": "较难"
                }, {
                    "id": "3",
                    "name": "中等"
                }, {
                    "id": "2",
                    "name": "容易"
                }, {
                    "id": "1",
                    "name": "非常容易"
                }],
                status: [{
                    "id": "0",
                    "name": "停用"
                }, {
                    "id": "1",
                    "name": "启用"
                }]

            }
        },
        components: {
            YHeader,
            YFooter,
            YSide,
            DPage
        },
        filters: {
            cutTile(value) {
                if (!value) return ''
                if (value.length > 14) {
                    return value.slice(0, 14) + '...'
                }
                return value
            },
            combine(val,val2){
                return val+"-"+val2;
            }
        },
        methods: {
            back(){
                this.$router.go(-1);//返回上一层
            },
            allSelect(){
                if(this.isAllSelect==false){
                    //全选
                    for(var i=0;i<this.courseList.length;i++){
                        //this.ids[i]=this.courseList[i].id;
                        this.pda.ids.push(this.courseList[i].id)
                    }
                }else {
                    //取消全选
                    this.pda.ids = [];
                }
                this.isAllSelect = !this.isAllSelect;
            },
            getPage: function (int) {
                this.pageCurrent = int;
                this.getUserStudyLogList(false);
            },
            changeCcpCate1() {
                this.pda.chapterId = '';
                this.pda.lessonId = '';
                this.changeCcpCate2();


                if (this.pda.chapterId == 0) {
                    this.pda.chapterId = ''
                }
                if (this.pda.lessonId == 0) {
                    this.pda.lessonId = ''
                }
                //if (!this.$route.query.no) {
                //    this.pda.chapterId = '';
                //    this.pda.lessonId = '';
                //    this.ccpCate2 = [];
                //    this.ccpCate3 = [];
                //}
                if (this.change1) {
                    this.pda.chapterId = '';
                    this.pda.lessonId = '';
                    this.ccpCate2 = [];
                    this.ccpCate3 = [];
                }
                let c1 = this.pda.courseId;
                let that = this;
                if (c1 === '') {
                    that.ccpCate2 = false;
                    that.ccpCate3 = false;
                } else {
                    let catels = that.ccpCate1;
                    catels.map(function (item, i) {
                        if (item.id === c1) {
                            that.ccpCate2 = item.twoList;
                            that.ccpCate3 = false;
                        }
                    })
                   // this.changeCcpCate2();
                }
                if (this.$route.query.no) {
                    this.change1 = true
                }
                // console.log(this.obj)
            },
            changeCcpCate2() {
                //if (!this.$route.query.no) {
                //    this.pda.lessonId = '';
                //    this.ccpCate3 = [];
                //}

                this.pda.lessonId = '';


                if (this.change2) {
                    this.pda.lessonId = '';
                    this.ccpCate3 = [];
                }
                let c2 = this.pda.chapterId;
                let that = this;
                if (c2 === '') {
                    that.ccpCate3 = false;
                } else {
                    let catels = that.ccpCate2;
                    catels.map(function (item, i) {
                        if (item.id === c2) {
                            if (item.threeList && item.threeList.length > 0) {
                                that.ccpCate3 = item.threeList;
                            } else {
                                that.ccpCate3 = false;
                            }
                        }
                    })
                }
                if (this.$route.query.no) {
                    this.change2 = true
                }
                // console.log(this.cate2)
            },
            changeCate1() {
                if (this.pda.subjectId2 == 0) {
                    this.pda.subjectId2 = ''
                }
                if (this.pda.subjectId3 == 0) {
                    this.pda.subjectId3 = ''
                }
                if (!this.$route.query.no) {
                    this.pda.subjectId2 = '';
                    this.pda.subjectId3 = '';
                    this.cate2 = [];
                    this.cate3 = [];
                }
                if (this.change1) {
                    this.pda.subjectId2 = '';
                    this.pda.subjectId3 = '';
                    this.cate2 = [];
                    this.cate3 = [];
                }
                let c1 = this.pda.subjectId1;
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
            changeCate2() {
                if (!this.$route.query.no) {
                    this.pda.subjectId3 = '';
                    this.cate3 = [];
                }
                if (this.change2) {
                    this.pda.subjectId3 = '';
                    this.cate3 = [];
                }
                let c2 = this.pda.subjectId2;
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
            //isStartFromFirstPage，是为了用户停留在非第一页的情况下，点击查询而查询不到数据，设置该值为false表示从第一页开始查
            getUserStudyLogList(isStartFromFirstPage) {
                this.queryState = true;
                let astat = this.tab === 3 ? '' : this.tab;
                this.pda.applyStatus = astat;
                if(isStartFromFirstPage){
                    this.pda.pageCurrent = 1;
                }else {
                    this.pda.pageCurrent = this.pageCurrent;
                }
                userStudyLogListByTeacherUserNo(this.pda).then(res => {
                    let result = res.data
                    console.log(result)
                    console.log('course----')
                    if (result.code === 200 && result.data.list.length > 0) {
                        this.courseList = result.data.list;
                        this.notdata = false;
                        this.pageObj = result.data;
                    } else if (result.code > 300 && result.code < 400) {
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
                    this.queryState = false;
                }).catch(msg => {
                    this.queryState = false;
                    this.$msgBox({
                        content: '加载数据失败，请稍后刷新再试！！',
                        isShowCancelBtn: false
                    })
                })
            },
            deleteExamManagementList(e){
                e.preventDefault();
                if (this.pda.ids.length == 0){
                        this.$msgBox({
                        content: '没有选中考试管理项',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                this.$msgBox({
                    content: '确认删除选中的考试管理吗？'
                }).then(() => {
                    examManagementDelete(this.pda).then(res => {
                        let result = res.data
                        if (result.code === 200) {
                            this.$msgBox({
                                content: '删除成功',
                                isShowCancelBtn: false
                            })
                            this.getUserStudyLogList();
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
            }

        },
        // 页面打开
        mounted() {
            //this.getCourseList();
            //this.pda.bindType = this.$route.query.bindType;
            //this.pda.isRelate = this.$route.query.isRelate;


            //this.pda.courseId = this.$route.query.courseId;
            //this.pda.chapterId = this.$route.query.chapterId;
            //this.pda.lessonId = this.$route.query.lessonId;
            //console.log("获得的courseId:"+this.pda.courseId);
            //console.log("chapterId:"+this.pda.chapterId);
            this.pda.courseId = this.$route.query.courseId;

            this.pda.studyUserNo = this.$route.query.studyUserNo;

            this.realName = this.$route.query.realName;
            this.courseName = this.$route.query.courseName;

            this.getUserStudyLogList(false);
            //this.$msgBox({
            //  content: this.$route.query.exampId,
            // isShowCancelBtn: false
            //})




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

            // 课程Ccp分类数据
            infiniteCcpCate().then(res => {
                // console.log(res.data)
                if (res.data.code === 200) {
                    this.ccpCate1 = res.data.data.courseCcpList;
                    //if (this.obj.subjectId1) {
                    this.changeCcpCate1();
                    //}
                }
            });

        }
    }

</script>

<style lang="scss" rel="stylesheet/scss">
  @import '~/assets/css/account.scss';

  .exam_management_list_table {

    .name {
      width: 400px;

      .txt {
        width: 190px;
        padding-top: 12px;
        font-size: 14px;
      }
    }

    .icon_min {
      width: 35px;
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
  .form_input_mid {
    text-indent: 1em;
    width: 230px;
    height: 34px;
    margin-right: 10px;
    border: 1px solid #e7e7e7;
  }

  .test_a {
    text-align: center;
  }

  .btn_box {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
  }
</style>
