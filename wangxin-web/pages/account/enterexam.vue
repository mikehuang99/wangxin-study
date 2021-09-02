<template>
  <div class="index_page">
       <!--@contextmenu.prevent="forbidRightClick()">-->
    <y-header :active="'index'"></y-header>
    <div class="enter_exam_list">
      <div id="enter_exam_head">
        <div style="display:inline;">
          <div style="float: left;width:25%;border: 0px solid red;text-align: left">{{ nowDate }} {{ nowTime }}</div>
          <div style="border: 0px solid red;text-align: left;font-size: 28px;font-weight: bold;line-height: 35px">{{examManagementObj.title}}</div>
        </div>
        <br/>
        <span style="text-align: center;font-size: 25px;font-weight: bold;line-height: 30px">{{examPaperObj.title}}</span>
        <br/>
        <span style="text-align: center;font-size: 20px;line-height: 25px">{{examPaperObj.subtitle}}</span>
        <br/>
        <div>
          <div style="float: left">
              开考时间:{{this.examManagementObj.startTime}}  时长:{{this.examManagementObj.limitTime}}分钟
            <span v-if="isOpenExam==false" style="font-size: 18px;font-weight: bold;color: blue">离开考还有:{{openExamTime}}</span>
            <span v-if="isOpenExam==true" style="font-size: 18px;font-weight: bold;color: red">距离考试结束还有{{finishExamTime}}</span>
            <span v-if="isHadViewExamPager==1">正在获取试卷......</span>
          </div>
          <div style="float: right">
            <button @click="showExamManagementIntroduce()" value="考试简介">
              <span v-if="!isShowExamManagementIntroduce">考试简介⇊⇊</span><span v-if="isShowExamManagementIntroduce">考试简介⇈⇈</span>
            </button>
            <button @click="showExamPageIntroduce()" value="试卷简介">
              <span v-if="!isShowExamPageIntroduce">试卷简介⇊⇊</span><span v-if="isShowExamPageIntroduce">试卷简介⇈⇈</span>
            </button>
          </div>
        </div>
      </div>
      <div id="enter_exam_paper_introduce" v-if="isShowExamPageIntroduce">
        <span v-html="examPaperObj.introduce"></span>
      </div>
      <div id="enter_exam_management_introduce" v-if="isShowExamManagementIntroduce">
        <span v-html="examManagementObj.introduce"></span>
      </div>
      <div id="enter_exam_content">
        <div id="enter_exam_left">
          <div>
            注意事项(可以滚动向下看):
            <span v-html="examPaperObj.attentionPoints"></span>
          </div>
        </div>
        <div id="enter_exam_c">
          <!--题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)-->
          <div v-if="this.examQuestionsList[this.currentExamQuestionIndex].questionType == 0">
            <div>试卷尚未装载</div>
          </div>
          <div style="margin-left: 20px">【第{{this.currentExamQuestionIndex+1}}题】</div>
          <div  v-if="this.examQuestionsList[this.currentExamQuestionIndex].questionType == 1" style="margin-left: 20px">
            <div style="font-weight: bold;font-size: 20px">➤单选题</div>
            <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="this.examQuestionsList[this.currentExamQuestionIndex].questionTitle"></span></div>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="A" id="singleChoiceItemA" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemA">
            <label for="singleChoiceItemA" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemA">A.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemA"></span></label>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="B" id="singleChoiceItemB" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemB">
            <label for="singleChoiceItemB" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemB">B.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemB"></span></label>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="C" id="singleChoiceItemC" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemC">
            <label for="singleChoiceItemC" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemC">C.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemC"></span></label>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="D" id="singleChoiceItemD" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemD">
            <label for="singleChoiceItemD" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemD">D.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemD"></span></label>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="E" id="singleChoiceItemE" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemE">
            <label for="singleChoiceItemE" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemE">E.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemE"></span></label>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="F" id="singleChoiceItemF" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemF">
            <label for="singleChoiceItemF" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemF">F.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemF"></span></label>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="G" id="singleChoiceItemG" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemG">
            <label for="singleChoiceItemG" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemG">G.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemG"></span></label>
            <br/>
            <input type="radio" v-model="singleChoiceAnswer" value="H" id="singleChoiceItemH" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemH">
            <label for="singleChoiceItemH" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemH">H.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemH"></span></label>
          </div>
          <div  v-if="this.examQuestionsList[this.currentExamQuestionIndex].questionType == 2" style="margin-left: 20px">
            <div style="font-weight: bold;font-size: 20px">➤多选题</div>
            <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px">{{this.examQuestionsList[this.currentExamQuestionIndex].questionTitle}}</span></div>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="A" id="multiChoiceItemA" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemA">
            <label for="multiChoiceItemA" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemA">A.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemA"></span></label>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="B" id="multiChoiceItemB" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemB">
            <label for="multiChoiceItemB" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemB">B.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemB"></span></label>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="C" id="multiChoiceItemC" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemC">
            <label for="multiChoiceItemC" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemC">C.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemC"></span></label>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="D" id="multiChoiceItemD" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemD">
            <label for="multiChoiceItemD" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemD">D.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemD"></span></label>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="E" id="multiChoiceItemE" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemE">
            <label for="multiChoiceItemE" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemE">E.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemE"></span></label>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="F" id="multiChoiceItemF" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemF">
            <label for="multiChoiceItemF" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemF">F.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemF"></span></label>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="G" id="multiChoiceItemG" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemG">
            <label for="multiChoiceItemG" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemG">G.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemG"></span></label>
            <br/>
            <input type="checkbox" v-model="multiChoiceAnswer" value="H" id="multiChoiceItemH" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemH">
            <label for="multiChoiceItemH" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemH">H.<span v-html="this.examQuestionsList[this.currentExamQuestionIndex].itemH"></span></label>
          </div>
          <div  v-if="this.examQuestionsList[this.currentExamQuestionIndex].questionType == 3" style="margin-left: 20px">
            <div style="font-weight: bold;font-size: 20px">➤判断题</div>
            <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="this.examQuestionsList[this.currentExamQuestionIndex].questionTitle"></span></div>
            <br/>
            <input type="radio" v-model="judgeQuestionAnswer" value="1" id="judgeQuestionRight">
            <label for="judgeQuestionRight" >正确</label>
            <br/>
            <input type="radio" v-model="judgeQuestionAnswer" value="0" id="judgeQuestionError">
            <label for="judgeQuestionError">错误</label>
          </div>
          <div  v-if="this.examQuestionsList[this.currentExamQuestionIndex].questionType == 4" style="margin-left: 20px">
            <div style="font-weight: bold;font-size: 20px">➤填空题</div>
            <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="this.examQuestionsList[this.currentExamQuestionIndex].questionTitle"></span></div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemA">
              <div style="float: left; padding-top: 5px">第一个空:</div>
              <div><input type="text" v-model="fillBlankItemAAnswer"  id="fillBlankItemA" class="form_input_fill_blank"></div>
            </div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemB">
              <div style="float: left">第二个空:</div>
              <div><input type="text" v-model="fillBlankItemBAnswer"  id="fillBlankItemB" class="form_input_fill_blank"></div>
            </div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemC">
              <div style="float: left">第三个空:</div>
              <div><input type="text" v-model="fillBlankItemCAnswer"  id="fillBlankItemC" class="form_input_fill_blank"></div>
            </div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemD">
              <div style="float: left">第四个空:</div>
              <div><input type="text" v-model="fillBlankItemDAnswer"  id="fillBlankItemD" class="form_input_fill_blank"></div>
            </div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemE">
              <div style="float: left">第五个空:</div>
              <div><input type="text" v-model="fillBlankItemEAnswer"  id="fillBlankItemE" class="form_input_fill_blank"></div>
            </div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemF">
              <div style="float: left">第六个空:</div>
              <div><input type="text" v-model="fillBlankItemFAnswer"  id="fillBlankItemF" class="form_input_fill_blank"></div>
            </div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemG">
              <div style="float: left">第七个空:</div>
              <div><input type="text" v-model="fillBlankItemFAnswer"  id="fillBlankItemG" class="form_input_fill_blank"></div>
            </div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemH">
              <div style="float: left">第八个空:</div>
              <div><input type="text" v-model="fillBlankItemHAnswer"  id="fillBlankItemH" class="form_input_fill_blank"></div>
            </div>
            <br/>
          </div>
          <div  v-if="this.examQuestionsList[this.currentExamQuestionIndex].questionType == 5" style="margin-left: 20px">
            <div style="font-weight: bold;font-size: 20px">➤简答题</div>
            <div><span style="font-weight: bold;font-size: 20px">☆题干：</span><span style="font-size: 16px" v-html="this.examQuestionsList[this.currentExamQuestionIndex].questionTitle"></span></div>
            <br/>
            <div style="display:inline;" v-if="this.examQuestionsList[this.currentExamQuestionIndex].itemA">
              <div style="float: left; padding-top: 5px">简答题作答:</div>
              <textarea v-model="simpleQuestionItemAAnswer" placeholder="" style="width:100%;height:300px;max-width:100%;max-height: 300px"></textarea>
              <!--
              <div><input type="text" v-model="simpleQuestionItemAAnswer"  id="simpleQuestionItemA" class="form_input_fill_blank"></div>
              -->
            </div>
            <br/>
          </div>
          <div style="position: absolute;bottom: 60px;z-index: 9999"><span class="last_question" @click="lastQuestion()">上一题</span><span class="next_question" @click="nextQuestion()">下一题</span></div>
        </div>
        <div id="enter_exam_right">
          <div style="text-align: center;height: 40px;background-color:dodgerblue;padding: 10px;cursor:pointer " @click="handInExamPaper">
            <span style="font-weight: bold;font-size: 18px" v-if="handInExamPaperStatus==0">交卷</span>
            <span style="font-weight: bold;font-size: 18px" v-if="handInExamPaperStatus==1">交卷中...</span>
            <span style="font-weight: bold;font-size: 18px" v-if="handInExamPaperStatus==2">已经交卷</span>
          </div>
          <div style="display:inline;" v-for="(item, index) in examQuestionsList" :key="index">
            <div id="column1" @click="changeCurrentExamQuestionIndex(index)" v-if="hadDoneExamQuestionsList[index]&&currentExamQuestionIndex!=index" style="background-color: deepskyblue;border:1px solid black;float:left;width:12.5%;cursor: pointer;">{{index+1}}</div>
            <div id="column2" @click="changeCurrentExamQuestionIndex(index)" v-if="!hadDoneExamQuestionsList[index]&&currentExamQuestionIndex!=index" style="background-color: lightgrey;border:1px solid black;float:left;width:12.5%;cursor: pointer;">{{index+1}}</div>
            <div id="column3" @click="changeCurrentExamQuestionIndex(index)" v-if="currentExamQuestionIndex==index" style="background-color: red;border:1px solid black;float:left;width:12.5%;cursor: pointer;">{{index+1}}</div>

          </div>
        </div>
      </div>
      <div id="enter_exam_foot">
        <span>提示：右侧蓝色点表示已做试题，灰色点表示未做试题，红色点表示正在做的试题。点击数字可以前往对应的试题。</span>
      </div>
    </div>
    <y-footer></y-footer>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YBanner from '~/components/Banner'
import YFooter from '~/components/common/Footer'
import RightTap from '~/components/common/RightTap'
import DPage from '~/components/Page'
import {examManagementViewByStudentUserNo,examPaperViewByExamManagementIdAndExamPaper,viewExamPaperContentByExamManagementIdAndExamPaperId,handInExamPaper,keepSession} from '~/api/account/course.js'
import { setStore, getStore, removeStore } from '~/utils/storage'
import VueCookie from 'vue-cookie'

export default {
  components: {
    YHeader,
    YBanner,
    YFooter,
    RightTap,
    DPage
  },
  data () {
    return {
        singleChoiceAnswer: '',
        multiChoiceAnswer: [],
        judgeQuestionAnswer:'',
        fillBlankItemAAnswer:'',
        fillBlankItemBAnswer:'',
        fillBlankItemCAnswer:'',
        fillBlankItemDAnswer:'',
        fillBlankItemEAnswer:'',
        fillBlankItemFAnswer:'',
        fillBlankItemGAnswer:'',
        fillBlankItemHAnswer:'',
        simpleQuestionItemAAnswer:'',


        examQuestionsList:[{"modifiedTime":1606146515659,"questionTitle":"正在加载中...","subjectId1":"1080744766018883585","level":"1","subjectId2":"1080752583811469314","userNo":"2018112015051635","sort":0,"analysis":"正在加载中...","itemD":"正在加载中...","itemC":"正在加载中...","itemB":"正在加载中...","itemA":"正在加载中...","answer":"正在加载中...","createTime":1606146515659,"validValue":1,"customLabel":"正在加载中...","id":"2533ea4587a84ac4ab1e24f3534abaae","questionType":"0"}],//试题
        hadDoneExamQuestionsList:[],

        currentExamQuestionIndex: 0,//当前做的试题的索引

        isShowExamPageIntroduce: false,
        isShowExamManagementIntroduce: false,
        displayId: '',
        newsTypeList: [],
        pageObj : {
            list: [],
            pageCurrent: '1',
            pageSize: '',
            totalCount: '',
            totalPage: ''
        },
        obj : {
            pageCurrent: 1,
            pageSize: 20
        },
        notdata: false,
        loading: false,
        examManagementObj: '',
        examPaperObj: '',
        examManagementId: '',
        examPaperId: '',

        isOpenExam: false,//是否已经开启考试
        isHadViewExamPager: 0,//是否已经获取试卷,0未获取，1正在获取，2获取成功，3获取失败，请刷新重试

        nowDate:null,    //存放年月日变量
        nowTime:null,   //存放时分秒变量
        timer: "",           //定义一个定时器的变量
        currentTime: new Date(),     // 获取当前时间

        handInExamPaperStatus: 0,//0未交卷，1交卷中，2已经交卷

        openExamTime : '',
        openExamFlag : false,
        openExamEndTime: new Date(new Date().getTime()+1000000000),

        finishExamTime : '',
        finishExamFlag : false,
        finishExamEndTime: new Date(new Date().getTime()+100000000),

        leaveThePageCount: 0,

        handInExamPaperTimeOut: 0,
        keepSessionTimeOut:0,

        examHadEnd: false,

        getExamPaperContentViewTimeOutVar: '',
        handInExamPaperTimeOutVar: '',
        keepSessionVar: ''



    }
  },
  methods: {
      changeCurrentExamQuestionIndex(index){
          this.setAnswer();
          this.currentExamQuestionIndex= index;
          this.resetAnswer();
          this.echoAnswer();
      },
      lastQuestion(){//上一题
          this.setAnswer();
          //this.getAnswer(this.examQuestionsList[this.currentExamQuestionIndex].questionType);
          if(this.currentExamQuestionIndex<=0){
              this.$msgBox({
                  content: '前面没有题了',
                  isShowCancelBtn: false
              })
              return;
          }else{
              this.currentExamQuestionIndex = this.currentExamQuestionIndex -1;
          }
          this.resetAnswer();
          this.echoAnswer();

      },
      nextQuestion(){//下一题
          this.setAnswer();
          //this.getAnswer(this.examQuestionsList[this.currentExamQuestionIndex].questionType);
          if(this.currentExamQuestionIndex<(this.examQuestionsList.length-1)) {
              this.currentExamQuestionIndex = this.currentExamQuestionIndex + 1;
          }else{
              this.$msgBox({
                  content: '已经最后一题',
                  isShowCancelBtn: false
              })
              return;
          }
          this.resetAnswer();
          this.echoAnswer();
      },

      setAnswer(){
          if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 1){
              if(this.singleChoiceAnswer) {
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex] = {'id': '', 'questionType': '', 'answer': ''};
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].id = this.examQuestionsList[this.currentExamQuestionIndex].id;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].answer = this.singleChoiceAnswer;
              }
          }
          if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 2){
              if(this.multiChoiceAnswer.length!=0) {
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex] = {'id': '', 'questionType': '','answer': '', 'originalAnswer':''};
                  let multiChoiceAnswerStr = '';
                  for (let iii = 0; iii < this.multiChoiceAnswer.length; iii++) {
                      multiChoiceAnswerStr = multiChoiceAnswerStr + this.multiChoiceAnswer[iii];
                  }
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].id = this.examQuestionsList[this.currentExamQuestionIndex].id;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].answer = multiChoiceAnswerStr;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].originalAnswer = this.multiChoiceAnswer;
              }
          }
          if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 3){
              if(this.judgeQuestionAnswer) {
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex] = {'id': '', 'questionType': '','answer': ''};
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].id = this.examQuestionsList[this.currentExamQuestionIndex].id;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].answer = this.judgeQuestionAnswer;
              }
          }
          if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 4){
              if(this.fillBlankItemAAnswer||this.fillBlankItemBAnswer||this.fillBlankItemCAnswer||this.fillBlankItemDAnswer||
                  this.fillBlankItemEAnswer||this.fillBlankItemFAnswer||this.fillBlankItemGAnswer||this.fillBlankItemHAnswer) {
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex] = {
                      'id': '',
                      'questionType': '',
                      'fillBlankItemAAnswer': '',
                      'fillBlankItemBAnswer': '',
                      'fillBlankItemCAnswer': '',
                      'fillBlankItemDAnswer': '',
                      'fillBlankItemEAnswer': '',
                      'fillBlankItemFAnswer': '',
                      'fillBlankItemGAnswer': '',
                      'fillBlankItemHAnswer': ''
                  };
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].id = this.examQuestionsList[this.currentExamQuestionIndex].id;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemAAnswer = this.fillBlankItemAAnswer;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemBAnswer = this.fillBlankItemBAnswer;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemCAnswer = this.fillBlankItemCAnswer;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemDAnswer = this.fillBlankItemDAnswer;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemEAnswer = this.fillBlankItemEAnswer;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemFAnswer = this.fillBlankItemFAnswer;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemGAnswer = this.fillBlankItemGAnswer;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemHAnswer = this.fillBlankItemHAnswer;
              }
          }
          if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 5){
              if(this.simpleQuestionItemAAnswer) {
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex] = {
                      'id': '',
                      'questionType': '',
                      'simpleQuestionItemAAnswer': ''
                  };
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].id = this.examQuestionsList[this.currentExamQuestionIndex].id;
                  this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].simpleQuestionItemAAnswer = this.simpleQuestionItemAAnswer;
              }
          }
          this.userInfo = JSON.parse(VueCookie.get("wangxinUserInfo"));
          setStore('exam_'+this.userInfo.id+"_"+this.examPaperId, this.hadDoneExamQuestionsList);
      },
      resetAnswer(){
          this.singleChoiceAnswer = '';
          this.multiChoiceAnswer = [];
          this.judgeQuestionAnswer = '';
          this.fillBlankItemAAnswer = '';
          this.fillBlankItemBAnswer = '';
          this.fillBlankItemCAnswer = '';
          this.fillBlankItemDAnswer = '';
          this.fillBlankItemEAnswer = '';
          this.fillBlankItemFAnswer = '';
          this.fillBlankItemGAnswer = '';
          this.fillBlankItemHAnswer = '';
          this.simpleQuestionItemAAnswer = ''
      },
      //回显答案
      echoAnswer(){
          //alert(typeof this.hadDoneExamQuestionsList[this.currentExamQuestionIndex] == "undefined");
          if(typeof this.hadDoneExamQuestionsList[this.currentExamQuestionIndex] != "undefined"){
              if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 1){
                  this.singleChoiceAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].answer;
              }
              if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 2){
                  this.multiChoiceAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].originalAnswer;
              }
              if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 3){
                  this.judgeQuestionAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].answer;
              }
              if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 4){
                  this.fillBlankItemAAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemAAnswer;
                  this.fillBlankItemBAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemBAnswer;
                  this.fillBlankItemCAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemCAnswer;
                  this.fillBlankItemDAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemDAnswer;
                  this.fillBlankItemEAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemEAnswer;
                  this.fillBlankItemFAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemFAnswer;
                  this.fillBlankItemGAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemGAnswer;
                  this.fillBlankItemHAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].fillBlankItemHAnswer;
              }
              if(this.examQuestionsList[this.currentExamQuestionIndex].questionType == 5){
                  this.simpleQuestionItemAAnswer = this.hadDoneExamQuestionsList[this.currentExamQuestionIndex].simpleQuestionItemAAnswer;
              }
              return;
          }
          return;
          this.singleChoiceAnswer = '';
          this.multiChoiceAnswer = [];
          this.judgeQuestionAnswer = '';
          this.fillBlankItemAAnswer = '';
          this.fillBlankItemBAnswer = '';
          this.fillBlankItemCAnswer = '';
          this.fillBlankItemDAnswer = '';
          this.fillBlankItemEAnswer = '';
          this.fillBlankItemFAnswer = '';
          this.fillBlankItemGAnswer = '';
          this.fillBlankItemHAnswer = '';
          this.simpleQuestionItemAAnswer = ''
      },
      getAnswer(type){
          if(type==1){
            alert(this.singleChoiceAnswer);
          }
          if(type==2){
              let multiChoiceAnswerStr ='';

              for(let iii=0;iii<this.multiChoiceAnswer.length;iii++){
                  multiChoiceAnswerStr = multiChoiceAnswerStr + this.multiChoiceAnswer[iii];
              }

              alert(multiChoiceAnswerStr);
          }
          if(type==3){
              alert(this.judgeQuestionAnswer);
          }
      },
      finishExamTimeDown () {
          const endTime = new Date(this.finishExamEndTime)
          const nowTime = new Date();
          let leftTime = parseInt((endTime.getTime()-nowTime.getTime())/1000);
          let d = parseInt(leftTime/(24*60*60))
          let h = this.finishExamformate(parseInt(leftTime/(60*60)%24))
          let m = this.finishExamformate(parseInt(leftTime/60%60))
          let s = this.finishExamformate(parseInt(leftTime%60))
          if(leftTime <= 0){
              this.finishExamFlag = true
              //alert("考试结束了");
              //这里应该交卷了
              //this.handInExamPaperStatus = 1;
              //let that = this;
              //setTimeout(function(){that.handInExamPaperStatus = 2 }, 5000);
              if(this.examHadEnd==false){//防止重新打开页面重复交卷
                this.okHandInExam();
              }
          }
          this.finishExamTime = `${d}天${h}小时${m}分${s}秒`
      },
      finishExamformate (finishExamTime) {
          if(finishExamTime>=10){
              return finishExamTime
          }else{
              return `0${finishExamTime}`
          }
      },
      openExamTimeDown () {
          const endTime = new Date(this.openExamEndTime)
          const nowTime = new Date();
          let leftTime = parseInt((endTime.getTime()-nowTime.getTime())/1000)
          let d = parseInt(leftTime/(24*60*60))
          let h = this.openExamformate(parseInt(leftTime/(60*60)%24))
          let m = this.openExamformate(parseInt(leftTime/60%60))
          let s = this.openExamformate(parseInt(leftTime%60))
          if(leftTime <= 0){
              this.openExamFlag = true;
              this.isOpenExam = true;
              //alert("倒计时结束");
              //这里开始获取试卷。。。。。。。。
              let that = this;
              let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
              this.getExamPaperContentViewTimeOutVar = setTimeout(function(){ that.getExamPaperContentView()}, timeOut);
          }
          this.openExamTime = `${d}天${h}小时${m}分${s}秒`
      },
      openExamformate (openExamTime) {
          if(openExamTime>=10){
              return openExamTime
          }else{
              return `0${openExamTime}`
          }
      },
      showExamPageIntroduce(){
          if(this.isShowExamPageIntroduce){
              this.isShowExamPageIntroduce = false;
          }else{
              this.isShowExamPageIntroduce = true;
          }
      },
      showExamManagementIntroduce(){
          if(this.isShowExamManagementIntroduce){
              this.isShowExamManagementIntroduce = false;
          }else{
              this.isShowExamManagementIntroduce = true;
          }
      },
      getTime(){
          const date = new Date();
          const year = date.getFullYear();
          const month = date.getMonth() + 1;
          const day = date.getDate();
          const hour= date.getHours();
          const minute = date.getMinutes();
          const second = date.getSeconds();
          const str = ''
          if(this.hour>12) {
              this.hour -= 12;
              this.str = " 下午";
          }else{
              this.str = " 上午";
          }
          this.month=check(month);
          this.day=check(day);
          this.hour=check(hour);
          this.minute=check(minute);
          this.second=check(second);
          function check(i){
              const num = (i<10)?("0"+i) : i;
              return num;
          }
          this.nowDate = year + "年" + this.month + "月" + this.day+"日";
          this.nowTime = this.hour + ":" + this.minute + ":" + this.second + this.str;
      },
      forbidRightClick(){
        alert('禁止右键');
      },
      toNews(id){
          this.$router.push({path: '/news/newsDetail', query: {id: id}})
      },
      getPage (int) {
          window.scrollTo(0, 0);
          console.log("点击的页数："+int);
          this.obj.pageCurrent = int;
          //this.pageObj.pageCurrent = int;
          this.getNewsList();
      },
      getExamPaperContentView(){
          if(this.isHadViewExamPager==2){//如果试卷获取成功，退出该方法
              return;
          }
          let that = this;
          this.isHadViewExamPager =1;
          if(this.$route.query.examManagementId==null){//防止离开页面后，还在不断执行该方法
              return;
          }
          viewExamPaperContentByExamManagementIdAndExamPaperId({id: this.$route.query.examManagementId,examPaperId: this.examPaperId}).then(res => {
              if (res.data.code == 200) {

                  this.isHadViewExamPager =2;

                  let examPaper = res.data.data;
                  this.examPaperObj = examPaper;

                  console.log("===有试卷内容的===>"+JSON.stringify(examPaper));

                  this.examQuestionsList = examPaper.examQuestionVos||[];

                  console.log("=9999试卷内容的===>"+JSON.stringify(this.examQuestionsList[0].questionTitle));


                  if(this.examQuestionsList.length<=0){
                      this.$msgBox({
                          content: '试卷试题数量为0，不能进行考试',
                          isShowCancelBtn: false
                      })
                      return;
                  }

                  if(this.examPaperObj.buildStatus!==2){
                      this.$msgBox({
                          content: '还没有试卷，不能进行考试',
                          isShowCancelBtn: false
                      })
                      return;
                  }

              } else if (res.data.code >= 300 && res.data.code <= 400) {
                  this.$store.dispatch('REDIRECT_LOGIN')
              } else {
                  console.log("失败了");
                  let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
                  this.getExamPaperContentViewTimeOutVar = setTimeout(function(){ that.getExamPaperContentView()}, timeOut);
              }
          }).catch(() => {
              //this.load = false;
              //this.$msgBox({
              //    content: '加载数据失败，请稍后刷新再试！！',
              //    isShowCancelBtn: false
              //})
              console.log("失败，再次获取。。。。");
              let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
              this.getExamPaperContentViewTimeOutVar = setTimeout(function(){ that.getExamPaperContentView()}, timeOut);
          })
      },
      handInExamPaper(){
          if(this.handInExamPaperStatus==2){
              this.$msgBox({
                  content: "已经交卷，不需要重复交卷。",
                  isShowCancelBtn: false
              })
              return;
          }

          this.$msgBox({
              content: '确定交卷吗？',
              isShowCancelBtn: true
          }).then(() => {
              this.okHandInExam();
          }).catch(() => {
              //点击取消，什么也不做
          });



      },
      //确定交卷
      okHandInExam(){
          this.handInExamPaperStatus = 1;
          let that = this;
          handInExamPaper({userExamPaperAnswerList:this.hadDoneExamQuestionsList,examManagementId: this.examManagementObj.id,examPaperId: this.examPaperId}).then(res => {
              if (res.data.code == 200) {
                  let result = res.data.data;
                  console.log("===交卷后返回内容===>"+JSON.stringify(result));
                  this.handInExamPaperStatus = 2;
              } else if (res.data.code == 609) {//不能重复交卷
                  this.handInExamPaperStatus = 2;
                  this.$msgBox({
                      content: res.data.msg,
                      isShowCancelBtn: false
                  })
                  return;
              }else if (res.data.code >= 300 && res.data.code <= 400) {
                  this.$store.dispatch('REDIRECT_LOGIN')
              } else {
                  console.log("交卷失败了，应用程序有误");
                  let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
                  that.handInExamPaperTimeOut = that.handInExamPaperTimeOut+timeOut;
                  console.log("间隔时长："+that.handInExamPaperTimeOut);
                  this.handInExamPaperTimeOutVar = setTimeout(function(){ that.handInExamPaper()}, that.handInExamPaperTimeOut);
              }
          }).catch(() => {
              console.log("失败，可能是网络问题，再次交卷。。。。");
              let timeOut = Math.ceil(Math.random()*5000);//5秒之内的随机数
              that.handInExamPaperTimeOut = that.handInExamPaperTimeOut+timeOut;
              this.handInExamPaperTimeOutVar = setTimeout(function(){ that.handInExamPaper()}, that.handInExamPaperTimeOut);
          })
      },
      keepSession(){
          keepSession({}).then(res => {
              if (res.data.code == 200) {
                  let that = this;
                  let timeOut = Math.ceil(Math.random()*300000);//300秒之内的随机数
                  that.keepSessionVar = setTimeout(function(){ that.keepSession()}, timeOut);
              }else if (res.data.code >= 300 && res.data.code <= 400) {
                  let that = this;
                  that.$store.dispatch('REDIRECT_LOGIN')
              } else {
                  let that = this;
                  let timeOut = Math.ceil(Math.random()*300000);//300秒之内的随机数
                  this.keepSessionVar = setTimeout(function(){ that.keepSession()}, timeOut);
              }
          }).catch(() => {
              let that = this;
              let timeOut = Math.ceil(Math.random()*300000);//300秒之内的随机数
              that.keepSessionVar = setTimeout(function(){ that.keepSession()}, timeOut);
          })
      },
      getExamPaperView(){
          examPaperViewByExamManagementIdAndExamPaper({id: this.$route.query.examManagementId,examPaperId: this.examPaperId}).then(res => {
              if (res.data.code == 200) {
                  let examPaper = res.data.data;
                  this.examPaperObj = examPaper;

                  console.log("===没有试卷内容的===>"+JSON.stringify(examPaper));

              } else if (res.data.code >= 300 && res.data.code <= 400) {
                  this.$store.dispatch('REDIRECT_LOGIN')
              } else {
                  console.log("========>");
              }
          }).catch(() => {
              this.load = false;
              this.$msgBox({
                  content: '加载数据失败，请稍后刷新再试！！',
                  isShowCancelBtn: false
              })
          })
      },
      getExamManagement(){
          examManagementViewByStudentUserNo({id: this.$route.query.examManagementId}).then(res => {
              if (res.data.code == 200) {
                  let examManagement = res.data.data;
                  this.examManagementObj = examManagement;
                  this.examPaperId = examManagement.examPaperId;
                  this.examManagementId = examManagement.id;
                  this.openExamEndTime = this.examManagementObj.startTime;
                  this.finishExamEndTime = this.examManagementObj.endTime;

                  //时间戳比较
                  let startTime=Date.parse(this.openExamEndTime);
                  let endTime=Date.parse(this.finishExamEndTime);
                  let nowTime = new Date().getTime();
                  console.log("endTime==>"+endTime);
                  console.log("nowTime===>"+nowTime);
                  if(endTime<nowTime){
                      this.$msgBox({
                          content: '该考试已经结束！！',
                          isShowCancelBtn: false
                      })

                      clearTimeout(this.getExamPaperContentViewTimeOutVar);
                      clearTimeout(this.handInExamPaperTimeOutVar);
                      clearTimeout(this.keepSessionVar)

                      this.examHadEnd = true;
                      this.$router.push({path: '/account/myexammanagement', query: {}})

                  }

                  console.log("===开考 结束考===>"+JSON.stringify(examManagement));
                  this.getExamPaperView();
                  if(this.examManagementObj.isAllowSwitchWindow == '0') {
                      document.addEventListener('visibilitychange', this.handleVisiable);// 监听页面离开事件
                  }
                      this.$nextTick(() => {
                          if(this.examManagementObj.isAllowSelect == '0') {
                              // 禁用选择
                              document.onselectstart = new Function("event.returnValue=false");
                          }
                          if(this.examManagementObj.isAllowRightClick == '0') {
                              // 禁用右键
                              document.oncontextmenu = new Function("event.returnValue=false");
                          }
                      });


              } else if (res.data.code >= 300 && res.data.code <= 400) {
                  this.$store.dispatch('REDIRECT_LOGIN')
              } else {
                  this.$msgBox({
                      content: res.data.msg,
                      isShowCancelBtn: false
                  }).then(res => {
                      this.$router.push({path: '/account/myexammanagement', query: {}})
                  }).catch(() => {
                  })
                  return;
              }
          }).catch(() => {
              this.load = false;
              this.$msgBox({
                  content: '加载数据失败，请稍后刷新再试！！',
                  isShowCancelBtn: false
              })
          })
      },
      handleVisiable(e) { // 监听页面离开事件
          if (e.target.visibilityState === 'visible') {
              //window.location.href = 'http://www.baidu.com'
              this.leaveThePageCount = this.leaveThePageCount + 1;
              this.$msgBox({
                  content: '你已经离开页面' + this.leaveThePageCount + '次,离开3次将自动交卷',
                  isShowCancelBtn: false
              })
          }
      }
  },
  mounted () {
      this.keepSession();
      this.getExamManagement();
      let openExamTime = setInterval(()=>{
          if(this.openExamFlag == true){
              clearInterval(openExamTime)
          }
          this.openExamTimeDown()
      },500);

      let finishExamTime = setInterval(()=>{
          if(this.finishExamFlag == true){
              clearInterval(finishExamTime)
          }
          this.finishExamTimeDown()
      },500);
  },
    destroyed() {
        document.removeEventListener('visibilitychange', this.handleVisiable);// 监听页面离开事件
    },
    beforeDestroy() {
        if (this.timer) {
            clearInterval(this.timer); // 在Vue实例销毁前，清除定时器
        }


    },
    created() {
        this.timer = setInterval(this.getTime, 1000);
    }
}
</script>
<style lang="scss" rel="stylesheet/scss">
  .form_input_fill_blank {
    height: 34px;
    border: 1px solid #e7e7e7;
    width: 278px;
    text-indent: 1em;
  }
  .last_question {
    background-color: blue;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
  }
  .next_question {
    background-color: blue;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 150px;
    cursor: pointer;
  }
  .seletedNewsTypeId{
    background-Color:deepskyblue!important
  }
  .bb{
    background-Color:lightgrey;
    height:100px;
    width:50px;
    line-height:50px;
    display:inline;
    word-break:break-all;
    //word-wrap:break-word;
    font-size: 20px;
    margin-right: 30px;
    margin-left: 10px;
    box-shadow:0px 0px 7px #000;
    cursor:pointer;
    white-space:nowrap;//加入这个就不会一行的文字换行显示了
  }
  .cc{
    background-Color:deepskyblue;
    box-shadow:0px 0px 7px #000;
    height:100px;
    width:50px;
    line-height:50px;
    display:inline;
    word-break:break-all;
    //word-wrap:break-word;
    font-size: 20px;
    margin-right: 30px;
    margin-left: 10px;
    cursor:pointer;
    white-space:nowrap;//加入这个就不会一行的文字换行显示了
  }
.index_page {
  .clearfix:before, .clearfix:after {
    content: "";
    display: table;
  }
  .clearfix:after {
    clear: both;
  }
  .i_content {
    background: lightgrey;
    &:nth-child(2n) {
      background: white;
    }
    .i_topic {
      width: 1200px;
      margin: 0 auto;
      padding: 50px 0 40px 0;
    }
  }
  .topic_header {
    .col_block {
      display: inline-block;
      width: 24px;
      height: 24px;
      background: blue;
      position: relative;
      top: 4px;
    }
    .big_text {
      // text-align: center;
      font-size: 24px;
    }
    .small_text {
      // text-align: center;
      font-size: 18px;
      color: blue;
      font-weight: bold;
      margin-top: 10px;
    }
    .link_text:hover {
      text-decoration: none;
      color: #333;
    }
  }
  .topic_body {
    margin-top: 25px;
  }
  .enter_exam_list {
    position: relative;
    height: 100%;
    top: 0px;
    font-size: 16px;
    width: 1020px;
    left: 50%;
    z-index: 10;
    margin-left: -510px;
    border: 1px solid #bbbbbb;
  }
  .news_list_item {
    margin-left: 10px;
    a{
      line-height: 28px;
    }
    a:hover{
      color:red;
      font-weight:bolder;
      cursor:pointer;
    }
    /*
    :before {
        content: "◉";
        color: blue;
    }*/
  }


  #enter_exam_head{
    height:150px;
    background-color:lightgrey;
    margin:0;
    overflow:scroll;
    text-align: center;
  }
  #enter_exam_paper_introduce{
    height:300px;
    background-color:white;
    margin:0;
    overflow-y:auto;
    overflow-x:auto;
    scrollbar-base-color:#ff6600;
    border: 1px solid gainsboro;
  }
  #enter_exam_management_introduce{
    height:300px;
    background-color:white;
    margin:0;
    overflow-y:auto;
    overflow-x:auto;
    scrollbar-base-color:#ff6600;
    border: 1px solid gainsboro;
  }
  #enter_exam_content{
    background:#CCCCCC;
    height:500px;
    margin:0;
    border: 1px solid gainsboro;
  }
  #enter_exam_left{
    width:15%;
    height:500px;
    background:#CCCC66;
    float:left;
    margin:0;
    overflow:scroll;
  }
  #enter_exam_c{
    width:60%;
    height:500px;
    background:white;
    margin:0;
    float:left;
    overflow:scroll;
    word-wrap: break-word;
    word-break: normal;
  }
  #enter_exam_right{
    width:25%;
    height:500px;
    background:whitesmoke;
    float:right;
    margin:0;
    overflow:scroll;
  }
  #enter_exam_foot{
    background: lightgrey;
    height:40px;
    margin:0;
    padding: 10px;
  }
}
</style>
