<template>
  <div class="">
    <y-header></y-header>
    <div class="container account_cont">
      <y-side :type="'sjgl'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: tab == 1}" @click="clicktab(1)">试卷内容</a>
          <nuxt-link :to="{name:'examp-paper'}" class="fr solid_btn">返回试卷列表</nuxt-link>
        </ul>
        <div class="main_cont form">
          <div  v-for="(item, index) in examQuestionsList">
            <div style="margin-left: 20px">【第{{index+=1}}题】分值:{{item.score}}分,难度:{{item.level | levelToLevelDesc}}</div>
            <div   style="margin-left: 20px" v-if="item.questionType == 1">
              <div style="font-weight: bold;font-size: 18px">➤单选题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div  v-if="item.itemA" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="A" id="singleChoiceItemA" v-if="item.itemA">
                <label for="singleChoiceItemA" v-if="item.itemA">A.<span v-html="item.itemA"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemB" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="B" id="singleChoiceItemB" v-if="item.itemB">
                <label for="singleChoiceItemB" v-if="item.itemB" >B.<span v-html="item.itemB"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemC" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="C" id="singleChoiceItemC" v-if="item.itemC">
                <label for="singleChoiceItemC" v-if="item.itemC" >C.<span v-html="item.itemC"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemD" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="D" id="singleChoiceItemD" v-if="item.itemD">
                <label for="singleChoiceItemD" v-if="item.itemD" >D.<span v-html="item.itemD"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemE" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="E" id="singleChoiceItemE" v-if="item.itemE">
                <label for="singleChoiceItemE" v-if="item.itemE" >E.<span v-html="item.itemE"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemF" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="F" id="singleChoiceItemF" v-if="item.itemF">
                <label for="singleChoiceItemF" v-if="item.itemF" >B.<span v-html="item.itemF"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemG" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="G" id="singleChoiceItemG" v-if="item.itemG">
                <label for="singleChoiceItemG" v-if="item.itemG" >G.<span v-html="item.itemG"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemH" style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="H" id="singleChoiceItemH" v-if="item.itemH">
                <label for="singleChoiceItemH" v-if="item.itemH" >H.<span v-html="item.itemH"></span></label>
              </div>
              <br/>
              <div style="font-size: 18px;display: inline;" v-if="item.answer">
                <div style="display: inline">答案：</div>
                <div v-html="item.answer" style="display: inline"></div>
              </div>
            </div>
            <div  v-if="item.questionType == 2" style="margin-left: 20px">
              <div style="font-weight: bold;font-size: 20px">➤多选题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px">{{item.questionTitle}}</span></div>
              <br/>
              <div  v-if="item.itemB" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="A" id="multiChoiceItemA" v-if="item.itemA">
                <label for="multiChoiceItemA" v-if="item.itemA">A.<span v-html="item.itemA"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemB" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="B" id="multiChoiceItemB" v-if="item.itemB">
                <label for="multiChoiceItemB" v-if="item.itemB">B.<span v-html="item.itemB"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemC" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="C" id="multiChoiceItemC" v-if="item.itemA">
                <label for="multiChoiceItemC" v-if="item.itemC">C.<span v-html="item.itemC"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemD" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="D" id="multiChoiceItemD" v-if="item.itemD">
                <label for="multiChoiceItemA" v-if="item.itemD">D.<span v-html="item.itemD"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemE" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="E" id="multiChoiceItemE" v-if="item.itemE">
                <label for="multiChoiceItemE" v-if="item.itemE">E.<span v-html="item.itemE"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemF" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="F" id="multiChoiceItemF" v-if="item.itemF">
                <label for="multiChoiceItemF" v-if="item.itemF">F.<span v-html="item.itemF"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemG" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="G" id="multiChoiceItemG" v-if="item.itemG">
                <label for="multiChoiceItemA" v-if="item.itemG">G.<span v-html="item.itemG"></span></label>
                <br/>
              </div>
              <div  v-if="item.itemH" style="font-size: 14px">
                <input type="checkbox" v-model="item.answerArray" value="H" id="multiChoiceItemH" v-if="item.itemH">
                <label for="multiChoiceItemH" v-if="item.itemH">H.<span v-html="item.itemH"></span></label>
                <br/>
              </div>
              <br/>
              <div style="font-size: 18px;display: inline;" v-if="item.answer">
                <div style="display: inline">答案：</div>
                <div v-html="item.answer" style="display: inline"></div>
              </div>
            </div>
            <div  v-if="item.questionType == 3" style="margin-left: 20px">
              <div style="font-weight: bold;font-size: 20px">➤判断题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="1" id="judgeQuestionRight">
                <label for="judgeQuestionRight" >正确</label>
                <br/>
              </div>
              <div style="font-size: 14px">
                <input type="radio" v-model="item.answer" value="0" id="judgeQuestionError">
                <label for="judgeQuestionError">错误</label>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.answer">
                <div style="display: inline">答案：</div>
                <div v-if="item.answer==1" style="display: inline">正确</div>
                <div v-if="item.answer==0" style="display: inline">错误</div>
              </div>
            </div>
            <div  v-if="item.questionType == 4" style="margin-left: 20px">
              <div style="font-weight: bold;font-size: 20px">➤填空题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div style="display:inline;font-size: 14px" v-if="item.itemA">
                <div style="float: left; padding-top: 5px">第一个空:</div>
                <div><input type="text" v-model="item.itemA"  id="fillBlankItemA" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.itemB">
                <div style="float: left">第二个空:</div>
                <div><input type="text" v-model="item.itemB"  id="fillBlankItemB" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.itemC">
                <div style="float: left">第三个空:</div>
                <div><input type="text" v-model="item.itemC"  id="fillBlankItemC" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.itemD">
                <div style="float: left">第四个空:</div>
                <div><input type="text" v-model="item.itemD"  id="fillBlankItemD" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.itemE">
                <div style="float: left">第五个空:</div>
                <div><input type="text" v-model="item.itemE"  id="fillBlankItemE" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.itemF">
                <div style="float: left">第六个空:</div>
                <div><input type="text" v-model="item.itemF"  id="fillBlankItemF" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.itemG">
                <div style="float: left">第七个空:</div>
                <div><input type="text" v-model="item.itemG"  id="fillBlankItemG" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="display:inline;font-size: 14px" v-if="item.itemH">
                <div style="float: left">第八个空:</div>
                <div><input type="text" v-model="item.itemH"  id="fillBlankItemH" class="epc_form_input_fill_blank"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemA">
                <div style="display: inline">第一个空答案：</div>
                <div v-html="item.itemA" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemB">
                <div style="display: inline">第二个空答案：</div>
                <div v-html="item.itemB" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemC">
                <div style="display: inline">第三个空答案：</div>
                <div v-html="item.itemC" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemD">
                <div style="display: inline">第四个空答案：</div>
                <div v-html="item.itemD" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemE">
                <div style="display: inline">第五个空答案：</div>
                <div v-html="item.itemE" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemF">
                <div style="display: inline">第六个空答案：</div>
                <div v-html="item.itemF" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemG">
                <div style="display: inline">第七个空答案：</div>
                <div v-html="item.itemG" style="display: inline"></div>
                <br/>
              </div>
              <div style="font-size: 18px;display: inline;" v-if="item.itemH">
                <div style="display: inline">第八个空答案：</div>
                <div v-html="item.itemH" style="display: inline"></div>
                <br/>
              </div>
            </div>
            <div  v-if="item.questionType == 5" style="margin-left: 20px">
              <div style="font-weight: bold;font-size: 20px">➤简答题</div>
              <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="item.questionTitle"></span></div>
              <br/>
              <div style="display:inline;" v-if="item.itemA">
                <div style="float: left; padding-top: 5px">简答题答案:</div>
                <textarea v-model="item.itemA" placeholder="" style="width:100%;height:300px;max-width:100%;max-height: 300px"></textarea>
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

    import {viewExamPaperContentByExamPaperIdAllowTeacher} from '~/api/account/course.js'

    export default {
        data() {
            return {
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
                }]

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
            viewExamPaperContentByExamPaperIdAllowTeacher(){
                let that = this;
                viewExamPaperContentByExamPaperIdAllowTeacher({examPaperId: this.$route.query.no}).then(res => {
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

</style>


