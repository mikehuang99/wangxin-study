<template>
  <div class="">
    <y-header></y-header>
    <div class="container account_cont">
      <y-side :type="'ksgl'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: tab == 1}" @click="clicktab(1)">新增/修改考试</a>
          <nuxt-link :to="{name:'examp-examManagement'}" class="fr solid_btn">返回考试管理列表</nuxt-link>
        </ul>
        <div class="main_cont form">
          <form action="" @submit="examManagementSave">
            <div class="form_group">
              <div class="label">考试主题</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.title" class="form_input" placeholder="请输入考试主题">
              </div>
            </div>
            <div class="form_group">
              <div class="label">考试简介</div>
              <div class="form_ctl">
                <div id="examManagementIntroduce"></div>
              </div>
            </div>
            <div class="form_group">
              <div class="label">课程</div>
              <div class="form_ctl">
                <select v-model="obj.courseId" @change="getExamPaperByCourseId" name="" id="" class="form_input_min">
                  <option value="">--请选择课程--</option>
                  <option v-for="cate in ccpCate1" :checked="obj.courseId == cate.id" :key="cate.id" :value="cate.id"
                          v-html="cate.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">试卷</div>
              <div class="form_ctl">
                <select v-model="obj.examPaperId" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in this.examPapers" :checked="obj.examPaperId == st.id"  :key="st.id" :value="st.id"
                          v-html="st.title"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">参加考试对象类型</div>
              <div class="form_ctl">
                <input type="radio" value ="1" v-model="obj.examObjectType"   class="form_input_min_a">全体人员<br/>
                <input type="radio" value ="2" v-model="obj.examObjectType"   class="form_input_min_a">学员分组<br/>
                <input type="radio" value ="3" v-model="obj.examObjectType"   class="form_input_min_a">全体学员中拥有该课程的学员<br/>
                <input type="radio" value ="4" v-model="obj.examObjectType"   class="form_input_min_a">分组学员中拥有该课程的学员
              </div>
            </div>
            <div class="form_group" v-if="obj.examObjectType==2||obj.examObjectType==4">
              <div class="label">参加考试对象组</div>
              <div class="form_ctl">
                <input type="input" v-model.number="obj.studentGroupNames"  @click="openExamObjectDialog" class="form_input" placeholder="请输入考试对象组">
              </div>
            </div>
            <div class="form_group">
              <div class="label">考试类型</div>
              <div class="form_ctl">
                <select v-model="obj.examType" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in examTypes" :checked="obj.examType == st.id"  :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group" v-if="obj.examType==1||obj.examType==2">
              <div class="label">考试开始时间</div>
              <div class="form_ctl_date">
                <myDatepicker v-model="obj.startTime" :date="startTime" :option="multiOption" :limit="limit"></myDatepicker>
              </div>
            </div>
            <div class="form_group" v-if="obj.examType==2">
              <div class="label">考试结束时间</div>
              <div class="form_ctl_date">
                <myDatepicker v-model="obj.endTime" :date="endTime" :option="multiOption" :limit="limit"></myDatepicker>
              </div>
            </div>
            <div class="form_group">
              <div class="label">是否允许切换窗口</div>
              <div class="form_ctl">
                <select v-model="obj.isAllowSwitchWindow" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in isAllowSwitchWindowStatus" :checked="obj.isAllowSwitchWindow == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">是否允许选择</div>
              <div class="form_ctl">
                <select v-model="obj.isAllowSelect" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in isAllowSelectStatus" :checked="obj.isAllowSelect == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">是否允许右键</div>
              <div class="form_ctl">
                <select v-model="obj.isAllowRightClick" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in isAllowRightClickStatus" :checked="obj.isAllowRightClick == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">是否允许重复交卷</div>
              <div class="form_ctl">
                <select v-model="obj.isAllowRepeateHandInExamPaper" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in isAllowRepeateHandInExamPaperStatus" :checked="obj.isAllowRepeateHandInExamPaper == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
                <span>(如果允许重复交卷，用户重新考试的时候可以再次交卷，适用于可以多次考试的场景，例如模拟考试)</span>
              </div>
            </div>
            <div class="form_group">
              <div class="label">状态</div>
              <div class="form_ctl">
                <select v-model="obj.validValue" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in status" :checked="obj.validValue == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">序号</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.sort" class="form_input" placeholder="请输入序号">
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
    <y-video v-if="showa" :open="showVideo" :content="content" @hidefun="hideModal()"
             @slectedGroupIdsFun="(val)=>this.obj.studentGroupIds=val"
             @slectedGroupNamesFun="(val)=>this.obj.studentGroupNames=val"
             @testfun="(val)=>dataf=val"></y-video>
  </div>
</template>
<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import YSide from '~/components/account/Side'
    import DUpload from '~/components/account/Upload'
    import YVideo from './examObjectDialog'
    import {uploadPic} from '~/api/upload.js'
    import {infiniteCcpCate} from '~/api/account/course.js'

    import {examManagementSave,examManagementUpdate,examManagementView,examPaperList,examPaperView,findDetailByChapterList} from '~/api/account/course.js'

    import myDatepicker from 'vue-datepicker/vue-datepicker-es6.vue';


    export default {
        data() {
            return {
                btnDis:false,
                editorExamPaperIntroduceContent: '',
                editorAttentionPointsContent: '',
                tab: 1,
                pageCurrent: '1',
                ccpCate1: [],
                ccpCate2: false,
                ccpCate3: false,
                pda: {
                    courseId: '',
                    chapterId: '',
                    lessonId: ''
                },
                studentGroupIds:[],
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
                    "studentGroupNames": '',
                    "examType": '',
                    "startTime": '',
                    "endTime": '',
                    "isAllowSwitchWindow": '',
                    "isShowConfirmButton": '',
                    "isAllowSelect": '',
                    "isAllowRightClick": '',
                    "isAllowRepeateHandInExamPaper": '',
                    "courseName":'',
                    "examPaperTitle":'',
                    "examPaperId":''
                },
                examPapers: null,
                examTypes: [
                    {
                        "id": "1",
                        "name": "定时考试"
                    },
                    {
                        "id": "2",
                        "name": "时段考试"
                    }
                ],
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
                }],
                isAllowSwitchWindowStatus: [{
                    "id": "0",
                    "name": "否"
                }, {
                    "id": "1",
                    "name": "是"
                }],
                isAllowSelectStatus: [{
                    "id": "0",
                    "name": "否"
                }, {
                    "id": "1",
                    "name": "是"
                }],
                isAllowRightClickStatus: [{
                    "id": "0",
                    "name": "否"
                }, {
                    "id": "1",
                    "name": "是"
                }],
                isAllowRepeateHandInExamPaperStatus: [{
                    "id": "0",
                    "name": "否"
                }, {
                    "id": "1",
                    "name": "是"
                }],
                startTime: {  // 相当于变量
                    time: ''
                },
                endTime: {	  // 相当于变量
                    time: ''
                },
                timeOption: {
                    type: 'min',  // day , multi-day
                    week: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                    month: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                    format: 'YYYY-M-D HH:mm', // YYYY-MM-DD 日期
                    inputStyle: {    			// input 样式
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
                    overlayOpacity: 0.5,	// 遮罩透明度
                    placeholder: '请选时间',  // 提示日期
                    dismissible: true  // 默认true  待定
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
                    }],
                showa:false,
                showVideo:false,
                content:null,
                dataf:null
            }
        },
        filters:{
            percent(val,val2){
                return val/val2*100;
            }
        },
        watch:{
            dataf(val) {
                //this.obj.studentGroupIds= this.dataf;

            }

        },
        // 页面打开完毕
        methods: {
            openExamObjectDialog(){
                console.log("=====-----{{"+this.obj.studentGroupIds);

                this.showa=true;
                this.showVideo=true;
                this.content = {
                    testNo: 888,
                    testId: this.dataf,
                    studentGroupIds: this.obj.studentGroupIds
                };
            },
            hideModal: function () {
                this.showa = false;
                this.showVideo = false;

            },
            clicktab: function (int) {
                this.tab = int;
                this.pageCurrent = 1;
                if (this.tab == 1) {
                    this.$router.push({path: 'examPaperSet'})
                }
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
            examManagementSave(e) {
                e.preventDefault();
                // console.log(this.obj)

                if (!this.obj.title) {
                    this.$msgBox({
                        content: '请输入考试主题',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                this.obj.introduce = this.editorExamManagementIntroduce.txt.html();
                if (!this.obj.introduce) {
                    this.$msgBox({
                        content: '考试简介',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.courseId) {
                    this.$msgBox({
                        content: '请选择课程',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.examPaperId) {
                    this.$msgBox({
                        content: '请选择试卷',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.examObjectType) {
                    this.$msgBox({
                        content: '请选择参考考试人员类型',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (this.obj.examObjectType==2&&!this.obj.studentGroupIds) {
                    this.$msgBox({
                        content: '请选择参考考试学员组',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.examType) {
                    this.$msgBox({
                        content: '请选择考试类型',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.startTime) {
                    this.$msgBox({
                        content: '请选择考试开始时间',
                        isShowCancelBtn: false
                    })
                    return false;
                }

                if (!this.obj.isAllowSwitchWindow) {
                    this.$msgBox({
                        content: '请选择是否允许切换窗口',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.isAllowSelect) {
                    this.$msgBox({
                        content: '请选择是否允许选择',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.isAllowRightClick) {
                    this.$msgBox({
                        content: '请选择是否允许右键',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.isAllowRepeateHandInExamPaper) {
                    this.$msgBox({
                        content: '请选择是否允许重复交卷',
                        isShowCancelBtn: false
                    })
                    return false;
                }

                if (!this.obj.validValue) {
                    this.$msgBox({
                        content: '请选择状态',
                        isShowCancelBtn: false
                    })
                    return false;
                }




                this.obj.startTime = this.startTime.time;
                this.obj.endTime = this.endTime.time;

                this.btnDis = true;
                if (this.obj.id) {
                    console.log(this.obj)
                    examManagementUpdate(this.obj).then(res => {
                        this.btnDis = false;
                        res = res.data;
                        if (res.code === 200) {
                            this.$msgBox({
                                content: '修改成功',
                                isShowCancelBtn: false
                            }).then(async (val) => {
                                this.$router.push({name: 'examp-examManagement', query: {no: res.data.id}})
                            })
                        } else {
                            this.$msgBox({
                                content: res.msg,
                                isShowCancelBtn: false
                            })
                        }
                    }).catch(error => {
                        let code = error.request.status;
                        this.load = false;
                        this.$msgBox({
                            content: '加载数据失败，请稍后刷新再试！！',
                            isShowCancelBtn: false
                        })
                    })
                } else {
                    // console.log(this.obj)
                    examManagementSave(this.obj).then(res => {
                        res = res.data;
                        this.btnDis = false;
                        if (res.code === 200) {
                            this.$msgBox({
                                content: '保存成功',
                                isShowCancelBtn: false
                            }).then(async (val) => {
                                this.$router.push({path: '/examp/examManagement', query: {no: res.data.id}})
                            })
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
                                }).catch(() => {
                                })
                            }
                        }
                    }).catch(error => {
                        let code = error.request.status;
                        this.load = false;
                        this.$msgBox({
                            content: '加载数据失败，请稍后刷新再试！！',
                            isShowCancelBtn: false
                        })
                    })
                }
            },
            // 获取考试管理
            getExamManagement() {
                this.load = true;
                examManagementView({id: this.$route.query.no}).then(res => {
                    this.load = false;
                    if (res.data.code === 200) {
                        let courseData = res.data.data;
                        this.startTime.time = courseData.startTime;
                        this.endTime.time = courseData.endTime;
                        //this.editorExamPaperIntroduce.txt.html(courseData.introduce);
                        //this.editorAttentionPoints.txt.html(courseData.attentionPoints);
                        this.obj = courseData;
                        this.editorExamManagementIntroduce.txt.html(courseData.introduce);
                        this.getExamPaper();
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
                            }).catch(() => {
                            })
                        }
                    }
                }).catch(error => {
                    let code = error.request.status;
                    this.load = false;
                    this.$msgBox({
                        content: '加载数据失败，请稍后刷新再试！！',
                        isShowCancelBtn: false
                    })
                })
            },
            // 获取试题
            getExamPaper() {
                this.load = true;
                examPaperView({id: this.obj.examPaperId}).then(res => {
                    console.log(res.data)
                    this.load = false;
                    if (res.data.code === 200) {
                        let courseData = res.data.data;
                        this.obj.courseId = courseData.courseId;
                        this.getExamPaperByCourseId();
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
                            }).catch(() => {
                            })
                        }
                    }
                }).catch(error => {
                    let code = error.request.status;
                    this.load = false;
                    this.$msgBox({
                        content: '加载数据失败，请稍后刷新再试！！',
                        isShowCancelBtn: false
                    })
                })
            },
            getExamPaperByCourseId(e){
                /*
                if(this.$route.query.no){
                    return;
                }*/
                this.load = true;
                examPaperList({courseId: this.obj.courseId,validValue: 1}).then(res => {
                    console.log(res.data)
                    this.load = false;
                    if (res.data.code === 200) {
                         this.examPapers = res.data.data.list;

                        //this.obj = courseData;
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
                            }).catch(() => {
                            })
                        }
                    }
                }).catch(error => {
                    let code = error.request.status;
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
        mounted: function () {
            let EE = require('wangeditor')
            this.editorExamManagementIntroduce = new EE('#examManagementIntroduce')
            this.editorExamManagementIntroduce.customConfig.uploadImgMaxLength = 1
            this.editorExamManagementIntroduce.customConfig.customUploadImg = this.editorUpload
            this.editorExamManagementIntroduce.customConfig.onchange = (html) => {
                console.log(html);
                this.editorExamManagementIntroduceContent = html
            }
            this.editorExamManagementIntroduce.create();
            this.editorExamManagementIntroduce.txt.html(this.editorExamManagementIntroduceContent);




            if (this.$route.query.no) {
                this.getExamManagement();

            }

            // 课程Ccp分类数据
            infiniteCcpCate().then(res => {
                // console.log(res.data)
                if (res.data.code === 200) {
                    this.ccpCate1 = res.data.data.courseCcpList;
                    //if (this.obj.subjectId1) {
                    //this.changeCcpCate1();
                    //}
                }
            })
        },
        components: {
            YHeader,
            YFooter,
            DUpload,
            YSide,
            YVideo,
            myDatepicker
        }
    }
</script>
<style scoped lang="scss" rel="stylesheet/scss">
  @import '~/assets/css/account.scss';

  .step_panel {
    overflow: hidden;
    background-color: #eee;

    .step {
      float: left;
      width: 33.33333%;
      text-align: center;
      height: 50px;
      line-height: 50px;
      color: #333;
      font-size: 14px;
      position: relative;

      &:after {
        content: "";
        display: block;
        position: absolute;
        right: -12px;
        top: -5px;
        z-index: 1;
        width: 60px;
        height: 60px;
        box-shadow: 1px 1px 0 #fff;
        transform: rotate(-45deg);
      }

      &.on {
        color: #fff;
        background-color: #ec7e00;

        &:after {
          background-color: #ec7e00;
        }
      }
    }
  }

  .course_lesson {
    label {
      margin-right: 8px;
      color: #333;
    }

    .form_input {
      height: 34px;
      border: 1px solid #e7e7e7;
      width: 278px;
      text-indent: 1em;
    }

    .form_input_min {
      text-indent: 1em;
      width: 130px;
      height: 34px;
      margin-right: 10px;
      border: 1px solid #e7e7e7;
    }
  }

  .upload_ctl {
    .preview {
      width: 200px;
      height: 120px;
      overflow: hidden;
      background-color: #f5f5f5;
      text-align: center;
      margin-bottom: 10px;

      i {
        line-height: 120px;
        font-size: 80px;
        color: #ddd;
      }
    }
  }

  .ac_ctl {
    .item_ac_op {
      line-height: 30px;
    }
  }

  .form_input_min_a {
    height: 34px;
    border: 1px solid #e7e7e8;
    width: 30px;
    text-indent: 1em;
  }

  .button_a {
    text-align: center;
    border: 1px solid #a1a1a1;
    padding: 10px 10px;
    background: #dddddd;
    width: 80px;
    border-radius: 5px;
    -moz-border-radius: 5px; /* 老的 Firefox */
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    cursor: pointer;
  }

  .form_ctl_date{
    margin-left: 12px;
    float: left;
    width: 700px;
    position: relative;
  }

</style>


