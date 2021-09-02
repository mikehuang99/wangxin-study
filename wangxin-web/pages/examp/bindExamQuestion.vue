<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'stbd'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on" >试题绑定/解绑</a>
        </ul>
        <div class="main_cont form">
          <form action="">
            <div class="form_group">
              <div class="label" v-if="this.pda.bindType==1">课程:</div>
              <div class="label" v-if="this.pda.bindType==2">章节:</div>
              <div class="label" v-if="this.pda.bindType==3">课时:</div>
              <div class="form_ctl">
                <select v-model="pda.courseId"@change="changeCcpCate1" disabled="disabled" name="" id="" class="form_input_min">
                  <option value="">--请选择课程--</option>
                  <option v-for="cate in ccpCate1" :checked="pda.courseId == cate.id" :key="cate.id" :value="cate.id"
                          v-html="cate.name"></option>
                </select>
                <select name="" v-model="pda.chapterId" @change="changeCcpCate2" disabled="disabled" id="" v-if="this.pda.bindType==2||this.pda.bindType==3"
                        class="form_input_min">
                  <option value="">--请选择章节--</option>
                  <option v-for="cate in ccpCate2" :key="cate.id" :value="cate.id" v-html="cate.name"></option>
                </select>
                <select name="" v-model="pda.lessonId" disabled="disabled" id="" v-if="this.pda.bindType==3" class="form_input_min">
                  <option value="">--请选择课时--</option>
                  <option v-for="cate in ccpCate3" :key="cate.id" :value="cate.id">{{cate.name}}</option>
                </select>
              </div>
            </div>
            <hr/>
            <div class="form_group">
              <div class="label">课程分类</div>
              <div class="form_ctl">
                <select v-model="pda.subjectId1" @change="changeCate1" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate1" :checked="pda.subjectId1 == cate.id" :key="cate.id" :value="cate.id"
                          v-html="cate.subjectName"></option>
                </select>
                <select name="" v-model="pda.subjectId2" @change="changeCate2" id="" v-if="cate2 && cate2.length"
                        class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate2" :key="cate.id" :value="cate.id" v-html="cate.subjectName"></option>
                </select>
                <select name="" v-model="pda.subjectId3" id="" v-if="cate3 && cate3.length" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate3" :key="cate.id" :value="cate.id">{{cate.subjectName}}</option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">题型:</div>
              <div class="form_ctl_search">
                <select v-model="pda.questionType" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in questionTypes" :checked="pda.id == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
              <div class="label">难度:</div>
              <div class="form_ctl_search">
                <select v-model="pda.level" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in levels" :checked="pda.level == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">状态:</div>
              <div class="form_ctl_search">
                <select v-model="pda.validValue" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in status" :checked="pda.validValue == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
              <div class="label">题干:</div>
              <div class="form_ctl_search">
                <input type="text" v-model="pda.questionTitle" class="form_input" placeholder="请输入题干">
              </div>
            </div>
            <div class="form_group">
              <div class="label">
                <!--
                <button type="button" @click="saveExamQuestionCourse()" v-if="this.pda.isRelate==0">绑定选中的试题</button>
                <button type="button" @click="deleteExamQuestionCourse()" v-if="this.pda.isRelate==1">解绑选中的试题</button>
                -->
                <div class="btn_box" @click="saveExamQuestionCourse()" v-if="this.pda.isRelate==0">绑定选中的试题</div>
                <div class="btn_box" @click="deleteExamQuestionCourse()" v-if="this.pda.isRelate==1">解绑选中的试题</div>
              </div>
              <div class="form_ctl_search">
                <label class="form_input" ></label>
              </div>
              <div class="label">自定义标签:</div>
              <div class="form_ctl_search">
                <input type="text" v-model="pda.customLabel" class="form_input" placeholder="请输入标签">
              </div>
              <div @click="getExamQuestionCourseList(true)" v-if="queryState==false" class="fr solid_btn">
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
            <table class="course_table table" v-else>
              <thead>
              <tr>
                <th><input type="checkbox" @click="allSelect()"></th>
                <th>序号</th>
                <th>类别</th>
                <th>题型</th>
                <th>题干</th>
                <th>难度</th>
                <th>状态</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item,index) in courseList" :key="item.id">
                <td><input type="checkbox" :value="item.id" v-model="pda.questionIds"></td>
                <td>{{index=index+1}}</td>
                <td>{{item.subjectName1}}<label style="color: red;">|</label>{{item.subjectName2}}<label
                  style="color: red;">|</label>{{item.subjectName3}}
                </td>
                <td v-if="item.questionType==1"><p class="test_a">单选题</p></td>
                <td v-if="item.questionType==2"><p class="test_a">多选题</p></td>
                <td v-if="item.questionType==3"><p class="test_a">判断题</p></td>
                <td v-if="item.questionType==4"><p class="test_a">填空题</p></td>
                <td v-if="item.questionType==5"><p class="test_a">简答题</p></td>
                <td><label :title="item.questionTitle" class="test_a">{{item.questionTitle | cutTile}}</label></td>
                <td v-if="item.level==1"><p class="test_a">非常容易</p></td>
                <td v-if="item.level==2"><p class="test_a">容易</p></td>
                <td v-if="item.level==3"><p class="test_a">中等</p></td>
                <td v-if="item.level==4"><p class="test_a">难</p></td>
                <td v-if="item.level==5"><p class="test_a">非常难</p></td>
                <td class="c_green" v-if="item.validValue === 1">启用</td>
                <td class="c_red" v-else-if="item.validValue === 0">停用</td>
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
    import {infiniteCate, examQuestionCourseList,examQuestionCourseDelete,examQuestionCourseSave,infiniteCcpCate,infiniteAuditCcpCate} from '~/api/account/course.js'
    import VueCookie from 'vue-cookie'

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
                    "analysis": "",
                    "answer": "",
                    "subjectId1": "",
                    "subjectId2": "",
                    "subjectId3": "",
                    "subjectName1": "",
                    "subjectName2": "",
                    "subjectName3": "",
                    "createTime": "",
                    "modifiedTime": "",
                    "id": "",
                    "itemA": "",
                    "itemB": "",
                    "itemC": "",
                    "itemD": "",
                    "itemE": "",
                    "itemF": "",
                    "itemG": "",
                    "itemH": "",
                    "level": "",
                    "questionTitle": "",
                    "questionType": "",
                    "sort": "",
                    "validValue": "",
                    "customLabel": "",
                    "userNo": ""
                },
                notdata: true,
                courseList: [],
                pda: {
                    questionIds:[],
                    bindType:'',
                    isRelate:'',
                    courseId: '',
                    chapterId: '',
                    lessonId: '',
                    subjectId1: '',
                    subjectId2: '',
                    subjectId3: '',
                    questionTitle:'',
                    questionType:'',
                    level:'',
                    validValue:'',
                    customLabel: '',
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
                if (value.length > 80) {
                    return value.slice(0, 80) + '...'
                }
                return value
            },
            combine(val,val2){
                return val+"-"+val2;
            }
        },

        methods: {
            allSelect(){
                if(this.isAllSelect==false){
                    //全选
                    for(var i=0;i<this.courseList.length;i++){
                        //this.questionIds[i]=this.courseList[i].id;
                        this.pda.questionIds.push(this.courseList[i].id)
                    }
                }else {
                    //取消全选
                    this.pda.questionIds = [];
                }
                this.isAllSelect = !this.isAllSelect;
            },
            getPage: function (int) {
                this.pageCurrent = int;
                this.getExamQuestionCourseList(false);
            },
            changeCcpCate1() {
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
                    this.changeCcpCate2();
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
            getExamQuestionCourseList(isStartFromFirstPage) {
                this.queryState = true;
                let astat = this.tab === 3 ? '' : this.tab;
                this.pda.applyStatus = astat;
                if(isStartFromFirstPage){
                    this.pda.pageCurrent = 1;
                }else {
                    this.pda.pageCurrent = this.pageCurrent;
                }
                examQuestionCourseList(this.pda).then(res => {
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
            deleteExamQuestionCourse(id){
                this.$msgBox({
                    content: '确认解绑该试题吗？'
                }).then(() => {
                    examQuestionCourseDelete(this.pda).then(res => {
                        let result = res.data
                        if (result.code === 200) {
                            this.$msgBox({
                                content: '解绑成功',
                                isShowCancelBtn: false
                            })
                            this.pda.questionIds = [];//清空questionIds
                            this.getExamQuestionCourseList();
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
            saveExamQuestionCourse(){
                this.$msgBox({
                    content: '确认绑定这些试题吗？'
                }).then(() => {
                    examQuestionCourseSave(this.pda).then(res => {
                        let result = res.data
                        if (result.code === 200) {
                            this.$msgBox({
                                content: '绑定成功',
                                isShowCancelBtn: false
                            })
                            this.pda.questionIds = [];//清空ID
                            this.getExamQuestionCourseList();
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
            this.pda.bindType = this.$route.query.bindType;
            this.pda.isRelate = this.$route.query.isRelate;


            this.pda.courseId = this.$route.query.courseId;
            this.pda.chapterId = this.$route.query.chapterId;
            this.pda.lessonId = this.$route.query.lessonId;


            this.getExamQuestionCourseList(false);
            //this.$msgBox({
            //  content: this.$route.query.exampId,
            // isShowCancelBtn: false
            //})
            try {
                this.pda.teacherUserNo = JSON.parse(VueCookie.get("wangxinUserInfo")).userNo;
            }catch (e) {
                this.$router.push({name: 'index'});
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

            // 课程Ccp分类数据
            infiniteAuditCcpCate().then(res => {
                // console.log(res.data)
                if (res.data.code === 200) {
                    this.ccpCate1 = res.data.data.courseCcpList;
                    //if (this.obj.subjectId1) {
                        this.changeCcpCate1();
                    //}
                }
            })
        }
    }

</script>

<style lang="scss" rel="stylesheet/scss">
  @import '~/assets/css/account.scss';

  .course_table {
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
