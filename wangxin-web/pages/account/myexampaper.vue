<template>
  <div class="">
    <y-header></y-header>
    <div class="container account_cont">
      <y-side :type="'myexammanagement'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: tab == 1}" @click="clicktab(1)">评卷</a>
          <!--
          <nuxt-link :to="{name:'examp-examManagement-examObjectList',query:{ examManagementId: this.$route.query.examManagementId }}" class="fr solid_btn">返回考试用户列表</nuxt-link>
          -->

          <router-link :to="{ name: 'account-myexammanagement', query: { }}"
                       class="fr solid_btn">返回我的考试</router-link>
        </ul>
        <div class="main_cont form">
          <div style="font-size: 18px;text-align: center;color: red;display: inline">
            本次考试总得分：<span style="font-weight: bold;font-size: 20px">{{examManagementUser.userTotalScore}}分</span>
            <div style="display: inline;float: right" v-if="examManagementUser.isFinishUserTotalScore==1">状态：已经完成评分</div>
            <div style="display: inline;float: right" v-if="examManagementUser.isFinishUserTotalScore==2">状态：等待教师评分</div>
          </div>
          <br/>
          <br/>
          <div  v-for="(item, index) in examQuestionsList">
            <div style="margin-left: 20px" >
              【第{{index+=1}}题】难度:{{item.level | levelToLevelDesc}},分值:{{item.score}}分,
              <span style="font-weight: bold;font-size: 16px;color: red">得分:
                <span v-if="item.questionType==1||item.questionType==2||item.questionType==3||item.questionType==4">{{item.myScore}}</span>
                <span v-if="item.questionType==5"><input type="text" v-model="item.myScore" disabled="disabled"></span>
                分
              </span>
            </div>
            <div   style="margin-left: 20px" v-if="item.questionType == 1">
              <div :class="item.isRight=='1'?'answer_right':'answer_error'">
              <div style="font-weight: bold;font-size: 18px">➤单选题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div  v-if="item.itemA" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="A" id="singleChoiceItemA" disabled="disabled" v-if="item.itemA">
                <label for="singleChoiceItemA" v-if="item.itemA">A.<span v-html="item.itemA"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemB" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="B" id="singleChoiceItemB" disabled="disabled"  v-if="item.itemB">
                <label for="singleChoiceItemB" v-if="item.itemB" >B.<span v-html="item.itemB"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemC" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="C" id="singleChoiceItemC" disabled="disabled" v-if="item.itemC">
                <label for="singleChoiceItemC" v-if="item.itemC" >C.<span v-html="item.itemC"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemD" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="D" id="singleChoiceItemD" disabled="disabled" v-if="item.itemD">
                <label for="singleChoiceItemD" v-if="item.itemD" >D.<span v-html="item.itemD"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemE" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="E" id="singleChoiceItemE" disabled="disabled" v-if="item.itemE">
                <label for="singleChoiceItemE" v-if="item.itemE" >E.<span v-html="item.itemE"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemF" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="F" id="singleChoiceItemF" disabled="disabled" v-if="item.itemF">
                <label for="singleChoiceItemF" v-if="item.itemF" >B.<span v-html="item.itemF"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemG" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="G" id="singleChoiceItemG" disabled="disabled" v-if="item.itemG">
                <label for="singleChoiceItemG" v-if="item.itemG" >G.<span v-html="item.itemG"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemH" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="H" id="singleChoiceItemH" disabled="disabled" v-if="item.itemH">
                <label for="singleChoiceItemH" v-if="item.itemH" >H.<span v-html="item.itemH"></span></label>
              </div>
              <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightAnswer">
                <div style="display: inline">正确答案：</div>
                <div v-html="item.rightAnswer" style="display: inline"></div>
              </div>
            </div>
            <div  v-if="item.questionType == 2" style="margin-left: 20px">
              <div :class="item.isRight=='1'?'answer_right':'answer_error'">
              <div style="font-weight: bold;font-size: 20px">➤多选题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px">{{item.questionTitle}}</span></div>
              <br/>
              <div  v-if="item.itemB" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="A" id="multiChoiceItemA" disabled="disabled" v-if="item.itemA">
                <label for="multiChoiceItemA" v-if="item.itemA">A.<span v-html="item.itemA"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemB" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="B" id="multiChoiceItemB" disabled="disabled" v-if="item.itemB">
                <label for="multiChoiceItemB" v-if="item.itemB">B.<span v-html="item.itemB"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemC" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="C" id="multiChoiceItemC" disabled="disabled" v-if="item.itemC">
                <label for="multiChoiceItemC" v-if="item.itemC">C.<span v-html="item.itemC"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemD" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="D" id="multiChoiceItemD" disabled="disabled" v-if="item.itemD">
                <label for="multiChoiceItemA" v-if="item.itemD">D.<span v-html="item.itemD"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemE" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="E" id="multiChoiceItemE" disabled="disabled" v-if="item.itemE">
                <label for="multiChoiceItemE" v-if="item.itemE">E.<span v-html="item.itemE"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemF" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="F" id="multiChoiceItemF" disabled="disabled" v-if="item.itemF">
                <label for="multiChoiceItemF" v-if="item.itemF">F.<span v-html="item.itemF"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemG" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="G" id="multiChoiceItemG" disabled="disabled" v-if="item.itemG">
                <label for="multiChoiceItemA" v-if="item.itemG">G.<span v-html="item.itemG"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemH" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="H" id="multiChoiceItemH" disabled="disabled" v-if="item.itemH">
                <label for="multiChoiceItemH" v-if="item.itemH">H.<span v-html="item.itemH"></span></label>
                <br/>
              </div>
              <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightAnswer">
                <div style="display: inline">正确答案：</div>
                <div v-html="item.rightAnswer" style="display: inline"></div>
              </div>
            </div>
            <div  v-if="item.questionType == 3" style="margin-left: 20px">
              <div :class="item.isRight=='1'?'answer_right':'answer_error'">
              <div style="font-weight: bold;font-size: 20px">➤判断题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="1" id="judgeQuestionRight" disabled="disabled" >
                <label for="judgeQuestionRight" >正确</label>
                <br/>
              </div>
              <div style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="0" id="judgeQuestionError" disabled="disabled" >
                <label for="judgeQuestionError">错误</label>
                <br/>
              </div>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightAnswer">
                <div style="display: inline">正确答案：</div>
                <div v-if="item.rightAnswer==1" style="display: inline">正确</div>
                <div v-if="item.rightAnswer==0" style="display: inline">错误</div>
              </div>
            </div>
            <div  v-if="item.questionType == 4" style="margin-left: 20px">
              <div :class="item.isRight=='1'?'answer_right':'answer_error'">
              <div style="font-weight: bold;font-size: 20px">➤填空题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemA">
                <div style="float: left; padding-top: 5px">第一个空:</div>
                <div><input type="text" v-model="item.itemA"  id="fillBlankItemA" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemB">
                <div style="float: left">第二个空:</div>
                <div><input type="text" v-model="item.itemB"  id="fillBlankItemB" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemC">
                <div style="float: left">第三个空:</div>
                <div><input type="text" v-model="item.itemC"  id="fillBlankItemC" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemD">
                <div style="float: left">第四个空:</div>
                <div><input type="text" v-model="item.itemD"  id="fillBlankItemD" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemE">
                <div style="float: left">第五个空:</div>
                <div><input type="text" v-model="item.itemE"  id="fillBlankItemE" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemF">
                <div style="float: left">第六个空:</div>
                <div><input type="text" v-model="item.itemF"  id="fillBlankItemF" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemG">
                <div style="float: left">第七个空:</div>
                <div><input type="text" v-model="item.itemG"  id="fillBlankItemG" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.rightItemH">
                <div style="float: left">第八个空:</div>
                <div><input type="text" v-model="item.itemH"  id="fillBlankItemH" disabled="disabled" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemA">
                <div style="display: inline">第一个空正确答案：</div>
                <div v-html="item.rightItemA" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemB">
                <div style="display: inline">第二个空正确答案：</div>
                <div v-html="item.rightItemB" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemC">
                <div style="display: inline">第三个空正确答案：</div>
                <div v-html="item.rightItemC" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemD">
                <div style="display: inline">第四个空正确答案：</div>
                <div v-html="item.rightItemD" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemE">
                <div style="display: inline">第五个空正确答案：</div>
                <div v-html="item.rightItemE" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemF">
                <div style="display: inline">第六个空正确答案：</div>
                <div v-html="item.rightItemF" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemG">
                <div style="display: inline">第七个空正确答案：</div>
                <div v-html="item.rightItemG" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.rightItemH">
                <div style="display: inline">第八个空正确答案：</div>
                <div v-html="item.rightItemH" style="display: inline"></div>
                <br/>
              </div>
            </div>
            <div  v-if="item.questionType == 5" style="margin-left: 20px">
              <div style="font-weight: bold;font-size: 20px">➤简答题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div style="display:inline;">
                <div style="float: left; padding-top: 5px">简答题学生的答案:</div>
                <textarea v-model="item.itemA" placeholder="" disabled="disabled" style="width:100%;height:300px;max-width:100%;max-height: 300px"></textarea>
              </div>
              <br/>
              <div style="display:inline;" v-if="item.rightItemA">
                <div style="float: left; padding-top: 5px">简答题参考答案:</div>
                <textarea v-model="item.rightItemA" placeholder="" disabled="disabled" style="width:100%;height:300px;max-width:100%;max-height: 300px"></textarea>
              </div>
              <br/>
            </div>

            <div style="font-size: 18px;display: inline;margin-left: 20px" v-if="item.analysis">
              <div style="display: inline">解析：</div>
              <div v-html="item.analysis" style="display: inline;font-size: 12px"></div>
            </div>
            <hr>
          </div>
          <div style="text-align: center;font-size: 20px;font-weight: bold">全卷完</div>
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

    import {viewExamPaperContentByExamPaperIdAllowStudent,viewExamManagementUserByExamManagementIdAndExamPaperId} from '~/api/account/course.js'

    export default {
        data() {
            return {
                examManagementUser:'',
                btnDis:false,
                tab: 1,
                pageCurrent: '1',
                examQuestionsList:[{"modifiedTime":1606146515659,"questionTitle":"正在加载中...","subjectId1":"1080744766018883585","level":"1","subjectId2":"1080752583811469314","userNo":"2018112015051635","sort":0,"analysis":"正在加载中...","itemD":"正在加载中...","itemC":"正在加载中...","itemB":"正在加载中...","itemA":"正在加载中...","answer":"正在加载中...","createTime":1606146515659,"validValue":1,"customLabel":"正在加载中...","id":"2533ea4587a84ac4ab1e24f3534abaae","questionType":"0"}],//试题
                pda: {
                    courseId: '',
                    chapterId: '',
                    lessonId: ''
                },
                status: [{
                    "id": "0",
                    "name": "停用"
                }, {
                    "id": "1",
                    "name": "启用"
                }],
                examPaperAnswerResultGloble: []

            }
        },
        filters:{
            percent(val,val2){
                return val/val2*100;
            },
            levelToLevelDesc(val){
                if(val==1){
                    return '非常容易';
                }
                if(val==2){
                    return '容易';
                }
                if(val==3){
                    return '中等';
                }
                if(val==4){
                    return '难';
                }
                if(val==5){
                    return '非常难';
                }
            },
        },
        watch:{

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
            viewExamManagementUserByExamManagementIdAndExamPaperId(){
                let that = this;
                viewExamManagementUserByExamManagementIdAndExamPaperId({examManagementId: this.$route.query.examManagementId,examPaperId: this.$route.query.examPaperId,examUserNo: this.$route.query.examUserNo}).then(res => {
                    if (res.data.code == 200) {
                        that.examManagementUser = res.data.data;
                        /*
                        if(that.examManagementUser.isFinishUserTotalScore==1){
                            this.$msgBox({
                                content: '系统已经完成评分，无须人工评分',
                                isShowCancelBtn: false
                            })
                            return false;
                        }*/
                        let examPaperAnswerResult = JSON.parse(that.examManagementUser.examPaperAnswer)||[];
                        this.examPaperAnswerResultGloble = examPaperAnswerResult;
                        for(let count=0;count<examPaperAnswerResult.length;count++){
                            for(let n=0;n<that.examQuestionsList.length;n++){
                                if(examPaperAnswerResult[count]==null){
                                    continue;
                                }
                                if(examPaperAnswerResult[count].id==this.examQuestionsList[n].id){
                                    that.examQuestionsList[n].isRight = examPaperAnswerResult[count].isRight;
                                    that.examQuestionsList[n].myScore = examPaperAnswerResult[count].myScore;
                                    if(that.examQuestionsList[n].questionType==1||that.examQuestionsList[n].questionType==3) {
                                        that.examQuestionsList[n].rightAnswer = that.examQuestionsList[n].answer;
                                        that.examQuestionsList[n].answer = examPaperAnswerResult[count].answer;
                                    }
                                    if(that.examQuestionsList[n].questionType==2){
                                        that.examQuestionsList[n].rightAnswer = that.examQuestionsList[n].answer;
                                        if(examPaperAnswerResult[count].answer!=null){
                                            let answerArray = examPaperAnswerResult[count].answer.split("");
                                            that.examQuestionsList[n].answerArray = answerArray;
                                        }
                                    }
                                    if(that.examQuestionsList[n].questionType==4) {
                                        that.examQuestionsList[n].rightItemA = that.examQuestionsList[n].itemA;
                                        that.examQuestionsList[n].rightItemB = that.examQuestionsList[n].itemB;
                                        that.examQuestionsList[n].rightItemC = that.examQuestionsList[n].itemC;
                                        that.examQuestionsList[n].rightItemD = that.examQuestionsList[n].itemD;
                                        that.examQuestionsList[n].rightItemE = that.examQuestionsList[n].itemE;
                                        that.examQuestionsList[n].rightItemF = that.examQuestionsList[n].itemF;
                                        that.examQuestionsList[n].rightItemG = that.examQuestionsList[n].itemG;
                                        that.examQuestionsList[n].itemA = examPaperAnswerResult[count].fillBlankItemAAnswer;
                                        that.examQuestionsList[n].itemB = examPaperAnswerResult[count].fillBlankItemBAnswer;
                                        that.examQuestionsList[n].itemC = examPaperAnswerResult[count].fillBlankItemCAnswer;
                                        that.examQuestionsList[n].itemD = examPaperAnswerResult[count].fillBlankItemDAnswer;
                                        that.examQuestionsList[n].itemE = examPaperAnswerResult[count].fillBlankItemEAnswer;
                                        that.examQuestionsList[n].itemF = examPaperAnswerResult[count].fillBlankItemFAnswer;
                                        that.examQuestionsList[n].itemG = examPaperAnswerResult[count].fillBlankItemGAnswer;
                                        that.examQuestionsList[n].itemH = examPaperAnswerResult[count].fillBlankItemHAnswer;
                                    }
                                    if(that.examQuestionsList[n].questionType==5) {
                                        that.examQuestionsList[n].rightItemA = that.examQuestionsList[n].itemA;
                                        that.examQuestionsList[n].itemA = examPaperAnswerResult[count].simpleQuestionItemAAnswer;
                                    }
                                }
                            }
                        }
                    } else if (res.data.code >= 300 && res.data.code <= 400) {
                        this.$store.dispatch('REDIRECT_LOGIN')
                    } else {
                        this.$msgBox({
                            content: '查看考试与用户关系失败：'+res.data.msg,
                            isShowCancelBtn: false
                        })
                        return false;
                    }
                }).catch(error => {
                    this.$msgBox({
                        content: '查看考试与用户关系异常:'+error,
                        isShowCancelBtn: false
                    })
                    return false;
                })
            /*
            catch(() => {
                    this.$msgBox({
                        content: '查看考试与用户关系异常',
                        isShowCancelBtn: false
                    })
                    return false;
                })*/
            },
            viewExamPaperContentByExamPaperIdAllowTeacher(){
                let that = this;
                viewExamPaperContentByExamPaperIdAllowStudent({examPaperId: this.$route.query.examPaperId}).then(res => {
                    if (res.data.code == 200) {
                        let examPaper = res.data.data;
                        this.examQuestionsList = examPaper.examQuestionVos||[];

                        if(this.examQuestionsList.length<=0){
                            this.$msgBox({
                                content: '试卷试题数量为0，不能进行考试',
                                isShowCancelBtn: false
                            })
                            return;
                        }


                        for(let i=0;i<this.examQuestionsList.length;i++){
                            if(this.examQuestionsList[i].questionType=='2'){
                                if(this.examQuestionsList[i].answer!=null){
                                    let answerArray = this.examQuestionsList[i].answer.split("");
                                    this.examQuestionsList[i].answerArray = answerArray;
                                }
                            }
                        }
                    } else if (res.data.code >= 300 && res.data.code <= 400) {
                        this.$store.dispatch('REDIRECT_LOGIN')
                    } else {
                        this.$msgBox({
                            content: '获取试卷信息失败：'+res.data.msg,
                            isShowCancelBtn: false
                        })
                        return false;
                    }
                }).catch(() => {
                    this.$msgBox({
                        content: '获取试卷信息异常',
                        isShowCancelBtn: false
                    })
                    return false;
                })
            }
        },
        // 页面打开
        mounted: function () {
            this.viewExamPaperContentByExamPaperIdAllowTeacher();
            setTimeout(() => {
                this.viewExamManagementUserByExamManagementIdAndExamPaperId();
            }, 1000);
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

  .epc_form_input_fill_blank {
    height: 34px;
    border: 1px solid #e7e7e7;
    width: 278px;
    text-indent: 1em;
  }
  .answer_error {
    background-color: #FFD39B
  }
  .answer_right {
    background-color: #98F898
  }
  .solid_mark_btn{
    display: inline-block;
    line-height: 52px;
    height: 52px;
    width: 100%;
    border-radius: 0px;
    background-color: red;
    color: #fff;
    border: 1px solid red;
    text-align: center;
    &:hover{
      background-color:orangered;
      color: #fff;
      cursor: pointer;
    }
    &:disabled{
      background-color: #bbb;
      border-color: #aaa;
      color: #888;
    }
    &.go_live {
      width: 280px;
      border-radius: 6px;
      margin: 20px auto 0;
      display: block;
      line-height: 40px;
      height: 40px;
      font-size: 14px;
    }
  }
</style>


