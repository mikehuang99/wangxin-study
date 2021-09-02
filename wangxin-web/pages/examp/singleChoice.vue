<template>
  <div class="">
    <y-header></y-header>
    <div class="container account_cont">
      <y-side :type="'stgl'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: tab == 1}" @click="clicktab(1)">单选题</a>
          <a class="tab" :class="{on: tab == 2}" @click="clicktab(2)">多选题</a>
          <a class="tab" :class="{on: tab == 3}" @click="clicktab(3)">判断题</a>
          <a class="tab" :class="{on: tab == 4}" @click="clicktab(4)">填空题</a>
          <a class="tab" :class="{on: tab == 5}" @click="clicktab(5)">简答题</a>
          <nuxt-link :to="{name:'examp'}" class="fr solid_btn">返回课程列表</nuxt-link>
        </ul>
        <div class="main_cont form">
          <div style="width: 100%;height: 30px;">
            <input type="file"  @change="getFile($event)">
            <button type="primary" @click="submit($event)">导入单选题</button>
            <span href="" style="text-decoration: underline;color:#4169E1;cursor:pointer" @click="examQuestionImportTemplate">下载单选题导入模版</span>
          </div>
          <hr>
          <form action="" @submit="examQuestionSave">
            <div class="form_group">
              <div class="label">试题类型</div>
              <div class="form_ctl">
                <select v-model="obj.questionType" name="" id="" class="form_input_min" disabled="disabled">
                  <option value="">--请选择--</option>
                  <option v-for="st in questionTypes" :checked="obj.questionType == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">课程分类</div>
              <div class="form_ctl">
                <select v-model="obj.subjectId1" @change="changeCate1" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate1" :checked="obj.subjectId1 == cate.id" :key="cate.id" :value="cate.id"
                          v-html="cate.subjectName"></option>
                </select>
                <select name="" v-model="obj.subjectId2" @change="changeCate2" id="" v-if="cate2 && cate2.length"
                        class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate2" :key="cate.id" :value="cate.id" v-html="cate.subjectName"></option>
                </select>
                <select name="" v-model="obj.subjectId3" id="" v-if="cate3 && cate3.length" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="cate in cate3" :key="cate.id" :value="cate.id">{{cate.subjectName}}</option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">题干</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.questionTitle" class="form_input" @click="openDialog('questionTitle','题干')" placeholder="请输入题干">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项A</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemA" class="form_input" @click="openDialog('itemA','选项A')" placeholder="请输入选项A">
                <strong>是否答案：</strong><input  type="radio" id="itemAA" value="A" v-model="obj.answer"  class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项B</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemB" class="form_input" @click="openDialog('itemB','选项B')" placeholder="请输入选项B">
                <strong>是否答案：</strong><input  type="radio" id="itemBA" value="B" v-model="obj.answer" class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项C</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemC" class="form_input" @click="openDialog('itemC','选项C')" placeholder="请输入选项C">
                <strong>是否答案：</strong><input  type="radio" id="itemCA" value="C" v-model="obj.answer" class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项D</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemD" class="form_input" @click="openDialog('itemD','选项D')" placeholder="请输入选项D">
                <strong>是否答案：</strong><input type="radio" id="itemDA" value="D" v-model="obj.answer" class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项E</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemE" class="form_input" @click="openDialog('itemE','选项E')" placeholder="请输入选项E">
                <strong>是否答案：</strong><input type="radio" id="itemEA" value="E" v-model="obj.answer" class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项F</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemF" class="form_input" @click="openDialog('itemF','选项F')" placeholder="请输入选项F">
                <strong>是否答案：</strong><input type="radio" id="itemFA" value="F" v-model="obj.answer" class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项G</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemG" class="form_input" @click="openDialog('itemG','选项G')" placeholder="请输入选项G">
                <strong>是否答案：</strong><input type="radio" id="itemGA" value="G" v-model="obj.answer" class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">选项H</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.itemH" class="form_input" @click="openDialog('itemH','选项H')" placeholder="请输入选项H">
                <strong>是否答案：</strong><input  type="radio" id="itemHA" value="H" v-model="obj.answer" class="form_input_min_a">
              </div>
            </div>
            <div class="form_group">
              <div class="label">难度</div>
              <div class="form_ctl">
                <select v-model="obj.level" name="" id="" class="form_input_min">
                  <option value="">--请选择--</option>
                  <option v-for="st in levels" :checked="obj.level == st.id" :key="st.id" :value="st.id"
                          v-html="st.name"></option>
                </select>
              </div>
            </div>
            <div class="form_group">
              <div class="label">解析</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.analysis" class="form_input" @click="openDialog('analysis','解析')" placeholder="请输入解析">
              </div>
            </div>
            <div class="form_group">
              <div class="label">自定义标签</div>
              <div class="form_ctl">
                <input type="text" v-model="obj.customLabel" class="form_input" @click="openDialog('customLabel','自定义标签')" placeholder="请输入自定义标签,便于搜索">
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
             @questionTitleFun="(val)=>obj.questionTitle=val"
             @itemAFun="(val)=>obj.itemA=val"
             @itemBFun="(val)=>obj.itemB=val"
             @itemCFun="(val)=>obj.itemC=val"
             @itemDFun="(val)=>obj.itemD=val"
             @itemEFun="(val)=>obj.itemE=val"
             @itemFFun="(val)=>obj.itemF=val"
             @itemGFun="(val)=>obj.itemF=val"
             @itemHFun="(val)=>obj.itemF=val"
             @analysisFun="(val)=>obj.analysis=val"
             @customLabelFun="(val)=>obj.customLabel=val"
             ></y-video>
  </div>
</template>
<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import YSide from '~/components/account/Side'
    import DUpload from '~/components/account/Upload'
    import YVideo from './examQuestionDialog'
    import {uploadPic} from '~/api/upload.js'
    import {examQuestionImportTemplate,examQuestionImport,examQuestionSave,examQuestionUpdate,examQuestionView,infiniteCate, accessoryList} from '~/api/account/course.js'

    export default {
        data() {
            return {
                btnDis: false,
                sideTy: 'kcgl',
                edit: true,
                tab: 1,
                cate1: [],
                cate2: false,
                cate3: false,
                change1: false,
                change2: false,
                tags: [],
                obj: {
                    id: '',
                    subjectId1: '',
                    subjectId2: '',
                    subjectId3: '',
                    questionTitle: '',
                    questionType: '1',
                    itemA: '',
                    itemB: '',
                    itemC: '',
                    itemD: '',
                    itemE: '',
                    itemF: '',
                    itemG: '',
                    itemH: '',
                    answer:'',
                    analysis: '',
                    customLabel: '',
                    level:'',
                    validValue:''
                },
                subjectTypesObj: {
                    id: "1"
                },
                status: [{
                    "id": "0",
                    "name": "停用"
                }, {
                    "id": "1",
                    "name": "启用"
                }],
                questionTypes: [{
                    "id": "1",
                    "name": "单选题"
                },{
                    "id": "2",
                    "name": "多选题"
                },{
                    "id": "3",
                    "name": "判断题"
                },{
                    "id": "4",
                    "name": "填空题"
                },{
                    "id": "5",
                    "name": "简答题"
                }],
                levelObj: {
                    id: "3"
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
                showa: false,
                showVideo: false,
                content: null,
                editorContent:null,
                is_display_e:false
            }
        },
        // 页面打开完毕
        methods: {
            //methods中的方法
            examQuestionImportTemplate(){
                examQuestionImportTemplate(this.obj).then(res => {
                    if(res.status===200) {
                        const link = document.createElement('a')
                        let blob = new Blob([res.data], {type: 'application/vnd.ms-excel'})
                        link.style.display = 'none'
                        link.href = URL.createObjectURL(blob)

                        // link.download = res.headers['content-disposition'] //下载后文件名
                        //link.download = data.fileName //下载的文件名
                        console.log("content-disposition:" + res.headers['content-disposition'])


                        link.download = decodeURI(res.headers['filename']) //下载的文件名

                        document.body.appendChild(link)
                        link.click()
                        document.body.removeChild(link)
                    }else{
                        this.$msgBox({
                            content: '未知错误',
                            isShowCancelBtn: false
                        })
                        return false;
                    }
                }).catch(() => {
                    this.$msgBox({
                        content: '未知错误',
                        isShowCancelBtn: false
                    })
                    return false;
                })
            },
            //excel导入开始
            getFile: function (event) {
                this.file = event.target.files[0];
                this.file.name
                console.log(this.file);
            },
            submit: function (event) {
                if(this.file == null){
                    this.$msgBox({
                        content: '文件为空,请选择文件进行导入',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                //阻止元素发生默认的行为
                event.preventDefault();
                let formData = new FormData();
                formData.append("file", this.file);
                examQuestionImport(formData).then(res => {
                    res = res.data;
                    this.btnDis = false;
                    if (res.code === 200) {
                        this.$msgBox({
                            content: res.data,
                            isShowCancelBtn: false
                        })
                        return false;
                    } else {
                        this.$msgBox({
                            content: '未知错误',
                            isShowCancelBtn: false
                        })
                        return false;
                    }
                }).catch(error => {
                    let code = error.request.status;
                    this.load = false;
                    this.$msgBox({
                        content: '未知错误',
                        isShowCancelBtn: false
                    })
                    return;
                })
            },
            //excel导入结束
            openDialog(dialogType,dialogTitle) {
                if('questionTitle'==dialogType){
                    console.log("9999======>"+this.obj.questionTitle);
                    this.editorContent = this.obj.questionTitle;
                }
                if('itemA'==dialogType){
                    this.editorContent = this.obj.itemA;
                }
                if('itemB'==dialogType){
                    this.editorContent = this.obj.itemB;
                }
                if('itemC'==dialogType){
                    this.editorContent = this.obj.itemC;
                }
                if('itemD'==dialogType){
                    this.editorContent = this.obj.itemD;
                }
                if('itemE'==dialogType){
                    this.editorContent = this.obj.itemE;
                }
                if('itemF'==dialogType){
                    this.editorContent = this.obj.itemF;
                }
                if('itemG'==dialogType){
                    this.editorContent = this.obj.itemG;
                }
                if('itemH'==dialogType){
                    this.editorContent = this.obj.itemH;
                }
                if('analysis'==dialogType){
                    this.editorContent = this.obj.analysis;
                }
                if('customLabel'==dialogType){
                    this.editorContent = this.obj.customLabel;
                }
                console.log("333");
                this.showa = true;
                this.showVideo = true;
                this.content = {
                    testNo: 888,
                    testId: this.dataf,
                    dialogType: dialogType,
                    dialogTitle:dialogTitle,
                    editorContent:this.editorContent
                };
            },
            hideModal: function () {
                this.showa = false;
                this.showVideo = false;

            },
            setUrl(res) {
                this.obj.courseLogo = res.url;
                // console.log(res)
            },
            examQuestionSave(e) {
                e.preventDefault();
                if (!this.obj.questionTitle) {
                    this.$msgBox({
                        content: '请输入题干',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.itemA) {
                    this.$msgBox({
                        content: '至少输入一个选项',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if(this.obj.level!=1&&this.obj.level!=2&&this.obj.level!=3&&this.obj.level!=4&&this.obj.level!=5){
                    this.$msgBox({
                        content: '请选择难度',
                        isShowCancelBtn: false
                    })
                    return false;
                }
                if (!this.obj.answer) {
                    this.$msgBox({
                        content: '请选择答案',
                        isShowCancelBtn: false
                    })
                    return false;
                }

                if(this.obj.id){
                    examQuestionUpdate(this.obj).then(res => {
                        res = res.data;
                        this.btnDis = false;
                        if (res.code === 200) {
                            this.$msgBox({
                                content: '保存成功',
                                isShowCancelBtn: false
                            }).then(async (val) => {
                                this.$router.push({path: '/examp?examQuestionId=99888', query: {no: res.data.id}})
                            })
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
                }else{
                    examQuestionSave(this.obj).then(res => {
                        res = res.data;
                        this.btnDis = false;
                        if (res.code === 200) {
                            this.$msgBox({
                                content: '保存成功',
                                isShowCancelBtn: false
                            }).then(async (val) => {
                                this.$router.push({path: '/examp?examQuestionId=99888', query: {no: res.data.id}})
                            })
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
            changeCate1() {
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
            changeCate2() {
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
            getExamQuestion() {
                this.load = true;
                examQuestionView({id: this.$route.query.no}).then(res => {
                    console.log(res.data)
                    this.load = false;
                    if (res.data.code === 200) {
                        let courseData = res.data.data;
                        //this.editor2.txt.html(courseData.introduce);
                        this.obj = courseData;
                        if (this.cate1.length) {
                            this.changeCate1();
                        }
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
            clicktab: function (int) {
                this.tab = int;
                this.pageCurrent = 1;
                if (this.tab == 1) {
                    this.$router.push({path: 'singleChoice'})
                } else if (this.tab == 2) {
                    this.$router.push({path: 'multiChoice'})
                }else if(this.tab==3){
                    this.$router.push({path: 'judgeQuestion'})
                }else if(this.tab==4){
                    this.$router.push({path: 'fillBlank'})
                }else if(this.tab==5){
                    this.$router.push({path: 'simpleQuestion'})
                }
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

            if (!(this.$route.query.t === 'paper')) {
                let E = require('wangeditor')
                this.editor2 = new E('#courseInfo')
                this.editor2.customConfig.uploadImgMaxLength = 1
                this.editor2.customConfig.customUploadImg = this.editorUpload
                this.editor2.create();
            }



            // 根据是否有编号判断添加或编辑试题
            if (this.$route.query.no) {
                this.getExamQuestion();
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
            YSide,
            YVideo
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
    text-align:center;
    border:1px solid #a1a1a1;
    padding:10px 10px;
    background:#dddddd;
    width:80px;
    border-radius:5px;
    -moz-border-radius:5px; /* 老的 Firefox */
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    cursor:pointer;
  }

</style>


