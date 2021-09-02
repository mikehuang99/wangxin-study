<template>
  <div class="">
    <y-header></y-header>
    <div class="container account_cont">
      <y-side :type="'sjgl'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: tab == 1}" @click="clicktab(1)">操作试卷</a>
          <nuxt-link :to="{name:'examp-paper'}" class="fr solid_btn">返回试卷列表</nuxt-link>
        </ul>
        <div class="main_cont form">
          <form action="" @submit="examPaperSave">
            <div class="form_group">
              <div class="label">试卷标题</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.title" class="form_input" placeholder="请输入试卷标题">
              </div>
            </div>
            <div class="form_group">
              <div class="label">试卷副标题</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.subtitle" class="form_input" placeholder="请输入试卷副标题">
              </div>
            </div>
            <div class="form_group">
              <div class="label">课程</div>
              <div class="form_ctl">
                <select v-model="obj.courseId" @change="changeCcpCate1" name="" id="" class="form_input_min" v-if="!this.$route.query.no">
                  <option value="">--请选择课程--</option>
                  <option v-for="cate in ccpCate1" :checked="obj.courseId == cate.id" :key="cate.id" :value="cate.id"
                          v-html="cate.name"></option>
                </select>
                <select v-model="obj.courseId" @change="changeCcpCate1" name="" id="" disabled="disabled" class="form_input_min" v-if="this.$route.query.no">
                  <option value="">--请选择课程--</option>
                  <option v-for="cate in ccpCate1" :checked="obj.courseId == cate.id" :key="cate.id" :value="cate.id"
                          v-html="cate.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">限时</div>
              <div class="form_ctl">
                <input type="number" v-model.number="obj.limitTime" class="form_input" placeholder="请输入限时值">分钟
              </div>
            </div>
            <div class="form_group">
              <div class="label">总分</div>
              <div class="form_ctl">
                <input type="number" v-model.number="obj.totalScore" class="form_input" placeholder="请输入总分">
              </div>
            </div>
            <div class="form_group">
              <div class="label">及格分</div>
              <div class="form_ctl">
                <input type="number" v-model.number="obj.passScore" class="form_input" placeholder="请输入及格分">
              </div>
            </div>
            <div class="form_group">
              <div class="label">难度</div>
              <div class="form_ctl">
                <select v-model="obj.level" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in levels" :checked="obj.level == st.id"  :key="st.id" :value="st.id"
                          v-html="st.name" style="background-color: red"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">组卷人</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.author" class="form_input" placeholder="请输入组卷人">
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
              <div class="label">试卷简介</div>
              <div class="form_ctl">
                <div id="examPaperIntroduce"></div>
              </div>
            </div>
            <div class="form_group">
              <div class="label">注意事项</div>
              <div class="form_ctl">
                <div id="attentionPoints"></div>
              </div>
            </div>
            <div class="form_group">
              <div class="label">出题范围</div>
              <div class="form_ctl">
                <input type="radio" id="A" value="1" v-model="obj.areaType" class="form_input_min_a">从当前课程选题
                <input type="radio" id="B" value="2" v-model="obj.areaType" class="form_input_min_a">从当前课程、章节选题
                <input type="radio" id="C" value="3" v-model="obj.areaType" class="form_input_min_a">从当前课程、章节、课时选题
                <input type="radio" id="D" value="4" v-model="obj.areaType" class="form_input_min_a" @click="getDetailByChapterList">按章节比例出题<span style="color: red">[必须先选择课程]</span>
              </div>
            </div>
            <div class="form_group">
              <div class="label">&nbsp;</div>
              <div class="form_ctl">
                <div v-if="obj.areaType !=4" style="border:1px dashed #bbbbbb;width: 500px;height: auto">
                  单选题:<input type="number" v-model.number="obj.singleChoiceCount">道试题,计<input type="number" v-model.number="obj.singleChoiceScore">分,占总分{{obj.singleChoiceScore | percent(this.obj.totalScore)}}%<br/>
                  多选题:<input type="number" v-model.number="obj.multiChoiceCount">道试题,计<input type="number" v-model.number="obj.multiChoiceScore">分,占总分{{obj.multiChoiceScore | percent(this.obj.totalScore)}}%<br/>
                  判断题:<input type="number" v-model.number="obj.judgeCount">道试题,计<input type="number" v-model.number="obj.judgeScore">分,占总分{{obj.judgeScore | percent(this.obj.totalScore)}}%<br/>
                  填空题:<input type="number" v-model.number="obj.fillBlankCount">道试题,计<input type="number" v-model.number="obj.fillBlankScore">分,占总分{{obj.fillBlankScore | percent(this.obj.totalScore)}}%<br/>
                  简答题:<input type="number" v-model.number="obj.simpleCount">道试题,计<input type="number" v-model.number="obj.simpleScore">分,占总分{{obj.simpleScore | percent(this.obj.totalScore)}}%<br/>
                </div>
                <div v-if="obj.areaType == 4" style="border:1px dashed #bbbbbb;width: 500px;height: auto">
                  单选题:<input type="number" v-model.number="obj.singleChoiceCount" >道试题,计<input type="number" v-model.number="obj.singleChoiceScore">分,占总分{{obj.singleChoiceScore | percent(this.obj.totalScore)}}%<br/>
                  多选题:<input type="number" v-model.number="obj.multiChoiceCount">道试题,计<input type="number" v-model.number="obj.multiChoiceScore">分,占总分{{obj.multiChoiceScore | percent(this.obj.totalScore)}}%<br/>
                  判断题:<input type="number" v-model.number="obj.judgeCount">道试题,计<input type="number" v-model.number="obj.judgeScore">分,占总分{{obj.judgeScore | percent(this.obj.totalScore)}}%<br/>
                  填空题:<input type="number" v-model.number="obj.fillBlankCount">道试题,计<input type="number" v-model.number="obj.fillBlankScore">分,占总分{{obj.fillBlankScore | percent(this.obj.totalScore)}}%<br/>
                  简答题:<input type="number" v-model.number="obj.simpleCount">道试题,计<input type="number" v-model.number="obj.simpleScore">分,占总分{{obj.simpleScore | percent(this.obj.totalScore)}}%<br/>
                </div>
                <div style="height: 20px" v-if="obj.areaType == 4"></div>
                <div v-for="(item,index) in obj.detailByChapterList" :key="item.id" v-if="obj.areaType == 4"
                     style="border:1px dashed #bbbbbb;width: auto;height: auto">
                  <div style="color: #1E1E1E">第{{index+1}}章 {{item.chapterName}}</div>
                  单选题:<input input type="number" v-model.number="item.singleChoiceCount" style="width: 30px">道,
                  多选题:<input input type="number" v-model.number="item.multiChoiceCount" style="width: 30px">道,
                  判断题:<input input type="number" v-model.number="item.judgeCount" style="width: 30px">道,
                  填空题:<input input type="number" v-model.number="item.fillBlankCount" style="width: 30px">道,
                  简答题:<input input type="number" v-model.number="item.simpleCount" style="width: 30px">道<br/>
                  <div style="height: 5px"></div>
                </div>
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
    import {uploadPic} from '~/api/upload.js'
    import {infiniteCcpCate} from '~/api/account/course.js'
    import {examPaperSave,examPaperUpdate,examPaperView,findDetailByChapterList} from '~/api/account/course.js'

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
                obj: {
                    title: '',
                    subtitle: '',
                    limitTime: '',
                    totalScore: '',
                    passScore: '',
                    level: '',
                    author: '',
                    validValue: '',
                    introduce: '',
                    attentionPoints: '',
                    areaType:'',
                    courseId: '',
                    singleChoiceCount:'',
                    multiChoiceCount:'',
                    judgeCount:'',
                    fillBlankCount:'',
                    simpleCount:'',
                    singleChoiceScore:'',
                    multiChoiceScore:'',
                    judgeScore:'',
                    fillBlankScore:'',
                    simpleScore:'',
                    detailByChapterList:[{
                        "id": "1",
                        "singleChoiceCount": "25",
                        "multiChoiceCount": "35",
                        "judgeCount": "20",
                        "fillBlankCount": "10",
                        "simpleCount": "19"
                    }, {
                        "id": "2",
                        "singleChoiceCount": "18",
                        "multiChoiceCount": "35",
                        "judgeCount": "20",
                        "fillBlankCount": "10",
                        "simpleCount": "19"
                    }, {
                        "id": "3",
                        "singleChoiceCount": "45",
                        "multiChoiceCount": "55",
                        "judgeCount": "28",
                        "fillBlankCount": "18",
                        "simpleCount": "19"
                    }, {
                        "id": "4",
                        "singleChoiceCount": "30",
                        "multiChoiceCount": "10",
                        "judgeCount": "18",
                        "fillBlankCount": "10",
                        "simpleCount": "19"
                    }, {
                        "id": "5",
                        "singleChoiceCount": "16",
                        "multiChoiceCount": "38",
                        "judgeCount": "29",
                        "fillBlankCount": "16",
                        "simpleCount": "12"
                    }]
                },
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
                detailByChapter: [{
                    "id": "1",
                    "singleChoiceCount": "25",
                    "multiChoiceCount": "35",
                    "judgeCount": "20",
                    "fillBlankCount": "10",
                    "simpleCount": "19"
                }, {
                    "id": "2",
                    "singleChoiceCount": "18",
                    "multiChoiceCount": "35",
                    "judgeCount": "20",
                    "fillBlankCount": "10",
                    "simpleCount": "19"
                }, {
                    "id": "3",
                    "singleChoiceCount": "45",
                    "multiChoiceCount": "55",
                    "judgeCount": "28",
                    "fillBlankCount": "18",
                    "simpleCount": "19"
                }, {
                    "id": "4",
                    "singleChoiceCount": "30",
                    "multiChoiceCount": "10",
                    "judgeCount": "18",
                    "fillBlankCount": "10",
                    "simpleCount": "19"
                }, {
                    "id": "5",
                    "singleChoiceCount": "16",
                    "multiChoiceCount": "38",
                    "judgeCount": "29",
                    "fillBlankCount": "16",
                    "simpleCount": "12"
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
        filters:{
            percent(val,val2){
                return val/val2*100;
            }
        },
        watch:{
            obj:{
                handler(newVal,oldVal) {
                    if (this.obj.areaType == 4) {
                        var scc = 0;
                        var mcc = 0;
                        var jc = 0;
                        var fbc = 0;
                        var sc = 0;
                        for (var i = 0; i < this.obj.detailByChapterList.length; i++) {
                            scc = this.obj.detailByChapterList[i].singleChoiceCount - (-scc);
                            mcc = this.obj.detailByChapterList[i].multiChoiceCount - (-mcc);
                            jc = this.obj.detailByChapterList[i].judgeCount - (-jc);
                            fbc = this.obj.detailByChapterList[i].fillBlankCount - (-fbc);
                            sc = this.obj.detailByChapterList[i].simpleCount - (-sc);

                        }
                        this.obj.singleChoiceCount = scc;
                        this.obj.multiChoiceCount = mcc;
                        this.obj.judgeCount = jc;
                        this.obj.fillBlankCount = fbc;
                        this.obj.simpleCount = sc;
                  }
                },
                deep:true //true 深度监听
            }
        },
        // 页面打开完毕
        methods: {
            clicktab: function (int) {
                this.tab = int;
                this.pageCurrent = 1;
                if (this.tab == 1) {
                    this.$router.push({path: 'examPaperSet'})
                }
            },
            changeCcpCate1() {
                this.getDetailByChapterList();

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
            examPaperSave(e) {
                e.preventDefault();
                // console.log(this.obj)


                if (!this.obj.title) {
                    this.$msgBox({
                        content: '请输入试卷标题',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.subtitle) {
                    this.$msgBox({
                        content: '请输入试卷副标题',
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
                if (!this.obj.limitTime) {
                    this.$msgBox({
                        content: '请输入限时',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.totalScore) {
                    this.$msgBox({
                        content: '请输入总分',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.passScore) {
                    this.$msgBox({
                        content: '请输入及格分',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.level) {
                    this.$msgBox({
                        content: '请输入难度',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.author) {
                    this.$msgBox({
                        content: '请输入组卷人',
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
                this.obj.introduce = this.editorExamPaperIntroduce.txt.html();
                if (!this.obj.introduce) {
                    this.$msgBox({
                        content: '试题简介',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                this.obj.attentionPoints = this.editorAttentionPoints.txt.html();
                if (!this.obj.attentionPoints) {
                    this.$msgBox({
                        content: '注意事项',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.areaType) {
                    this.$msgBox({
                        content: '请选择出题范围',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                /*
                if (!this.obj.singleChoiceCount) {
                    this.$msgBox({
                        content: '请填写单选题数量',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.multiChoiceCount) {
                    this.$msgBox({
                        content: '请填写多选题数量',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.judgeCount) {
                    this.$msgBox({
                        content: '请填写判断题数量',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.fillBlankCount) {
                    this.$msgBox({
                        content: '请填写填空题数量',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.simpleCount) {
                    this.$msgBox({
                        content: '请填写简答题数量',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.singleChoiceScore) {
                    this.$msgBox({
                        content: '请填写单选题分数',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.multiChoiceScore) {
                    this.$msgBox({
                        content: '请填写多选题分数',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.judgeScore) {
                    this.$msgBox({
                        content: '请填写判断题分数',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.fillBlankScore) {
                    this.$msgBox({
                        content: '请填写填空题分数',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.simpleScore) {
                    this.$msgBox({
                        content: '请填写简答题分数',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.simpleScore) {
                    this.$msgBox({
                        content: '请填写简答题分数',
                        isShowCancelBtn: false
                    })
                    return false;
                }*/

                this.btnDis = true;
                if (this.obj.id) {
                    console.log(this.obj)
                    examPaperUpdate(this.obj).then(res => {
                        this.btnDis = false;
                        res = res.data;
                        if (res.code === 200) {
                            this.$msgBox({
                                content: '修改成功',
                                isShowCancelBtn: false
                            }).then(async (val) => {
                                this.$router.push({name: 'examp-paper', query: {no: res.data.id}})
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
                    examPaperSave(this.obj).then(res => {
                        res = res.data;
                        this.btnDis = false;
                        if (res.code === 200) {
                            this.$msgBox({
                                content: '保存成功',
                                isShowCancelBtn: false
                            }).then(async (val) => {
                                this.$router.push({path: '/examp/paper', query: {no: res.data.id}})
                            })
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
                                }).catch(() => {
                                })
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
            // 获取试题
            getExamPaper() {
                this.load = true;
                examPaperView({id: this.$route.query.no}).then(res => {
                    console.log(res.data)
                    this.load = false;
                    if (res.data.code === 200) {
                        let courseData = res.data.data;
                        this.editorExamPaperIntroduce.txt.html(courseData.introduce);
                        this.editorAttentionPoints.txt.html(courseData.attentionPoints);
                        this.obj = courseData;
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
                }).catch(() => {
                    this.load = false;
                    this.$msgBox({
                        content: '加载数据失败，请稍后刷新再试！！',
                        isShowCancelBtn: false
                    })
                })
            },
            getDetailByChapterList(e){
                if(this.$route.query.no){
                    return;
                }
                this.load = true;
                findDetailByChapterList({courseId: this.obj.courseId}).then(res => {
                    console.log(res.data)
                    this.load = false;
                    if (res.data.code === 200) {
                        let courseData = res.data.data;
                        this.obj.detailByChapterList = courseData.detailByChapterList;
                        console.log("======>>>>>"+courseData.detailByChapterList);
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
        mounted: function () {
            let EE = require('wangeditor')
            this.editorExamPaperIntroduce = new EE('#examPaperIntroduce')
            this.editorExamPaperIntroduce.customConfig.uploadImgMaxLength = 1
            this.editorExamPaperIntroduce.customConfig.customUploadImg = this.editorUpload
            this.editorExamPaperIntroduce.customConfig.onchange = (html) => {
                console.log(html);
                this.editorExamPaperIntroduceContent = html
            }
            this.editorExamPaperIntroduce.create();
            this.editorExamPaperIntroduce.txt.html(this.editorExamPaperIntroduceContent);

            let EEE = require('wangeditor')
            this.editorAttentionPoints = new EEE('#attentionPoints')
            this.editorAttentionPoints.customConfig.uploadImgMaxLength = 1
            this.editorAttentionPoints.customConfig.customUploadImg = this.editorUpload
            this.editorAttentionPoints.customConfig.onchange = (html) => {
                console.log(html);
                this.editorAttentionPointsContent = html
            }
            this.editorAttentionPoints.create();
            this.editorAttentionPoints.txt.html(this.editorAttentionPointsContent);


            if (this.$route.query.no) {
                this.getExamPaper();
            }

            // 课程Ccp分类数据
            infiniteCcpCate().then(res => {
                // console.log(res.data)
                if (res.data.code === 200) {
                    this.ccpCate1 = res.data.data.courseCcpList;
                    //if (this.obj.subjectId1) {
                    this.changeCcpCate1();
                    //}
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

</style>


