<template>
  <div  class="judge_class" style="padding-top: 44px;height: 100%;margin-left: 10px;margin-right: 10px">
    <div style="color: #333333;font-weight: bold;font-size: 16px">
      【判断题】
    </div>
    <div class="container" >
      <span class="judge_questionTitle"
            style="font-size: 16px;padding: 0px;height: auto;width: 100%;color:  #353c41;word-wrap: break-word;word-break: normal"
            v-html="judgeQuestionContent.questionTitle"></span>
    </div>
    <ul>
      <li style="word-wrap:break-word;color: #35495E">
        <div class="judge_item" id="judge_1" name="judge_item" :class="{on:true}" @click="clickItem(1)">
          对
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E" >
        <div class="judge_item" id="judge_0" name="judge_item" :class="{on:true}"  @click="clickItem(0)">
          错
        </div>
      </li>
    </ul>
    <div v-show="showAnswer">
    <hr>
      <div>
        <span style="font-weight: bold;font-size: 20px">正确答案：</span>
        <span style="font-size: 18px" v-show="judgeQuestionContent.answer == 1">对</span>
        <span style="font-size: 18px" v-show="judgeQuestionContent.answer == 0">错</span>
      </div>
      <hr>
      <div>
        <span style="font-weight: bold;font-size: 20px">难度：</span>
        <span style="font-size: 18px" v-show="judgeQuestionContent.level == 1">*</span>
        <span style="font-size: 18px" v-show="judgeQuestionContent.level == 2">**</span>
        <span style="font-size: 18px" v-show="judgeQuestionContent.level == 3">***</span>
        <span style="font-size: 18px" v-show="judgeQuestionContent.level == 4">****</span>
        <span style="font-size: 18px" v-show="judgeQuestionContent.level == 5">*****</span>
      </div>
      <hr>
      <div>
        <span style="font-weight: bold;font-size: 20px">解析：</span>
        <span style="font-size: 18px">
            <span v-html="judgeQuestionContent.analysis">
            </span>
          </span>
      </div>
    </div>
  </div>
</template>

<script>
    import {getStore,removeStore,setStore} from '~/store/common.js'
    import { mapState } from 'vuex'
    export default {
        name: "judgeQuestion",
        props: ['questionId','judgeQuestionContent','ccpId'],
        data(){
            return{
                showAnswer: '',
                picked: '',
                onFlag:true,
                rightFlag:false,
                errorFlag:false,
                questionTitle: '题干:当心：类名的第一个字符不能运用数字！它无法在 Mozilla 或 Firefox 中起感召。' +
                    '<img src="https://atest888.oss-cn-hangzhou.aliyuncs.com/course/06fcc9daf6584549b37ccd4be163c1ae.jpg">' +
                    '在下面的 HTML 代码中，h1 与 p 元素都有 center 类。这象征着二者都将性能 ".center" 决定器中的划定。\n'
            }
        },
        computed: {
            ...mapState([
                'courseDetail'
            ])
        },
        mounted() {
            let delayedArr = [10,30,50,100,500,1000,2000,3000,4000,5000];
            for(let d=0;d<delayedArr.length;d++){
                setTimeout(() => {
                    this.changeImgCss('judge_class');
                }, delayedArr[d]);
            }

            setTimeout(() => { // 两秒恢复为true
                let titleLength = document.getElementsByClassName('judge_item').length;
                for (let k = 0; k < titleLength; k++) {
                    let p = document.getElementsByClassName('judge_item').item(k).getElementsByTagName('p');
                    for (let i = 0; i < p.length; i++) {
                        p[i].style = "display: inline";
                    }
                }
            }, 8)

            setTimeout(() => {
                let questionTitleLength2 = document.getElementsByClassName('judge_item').length;
                for (let kk = 0; kk < questionTitleLength2; kk++) {
                    let imgs2 = document.getElementsByClassName('judge_item').item(kk).getElementsByTagName('img');
                    for (let ii = 0; ii < imgs2.length; ii++) {
                        imgs2[ii].style = "width:auto;height:auto;max-width: 100%;max-height: 100%";
                    }
                }
            }, 5)


            //回填答案
            let lastAnswer = getStore("answer_"+this.ccpId+"_"+this.judgeQuestionContent.id);
            if(lastAnswer!=null){
                if(this.judgeQuestionContent.answer == lastAnswer) {
                    document.getElementById("judge_"+lastAnswer).style = 'background: green url(\'/wxmobile/img/icon_red_ok.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                }else {
                    document.getElementById("judge_"+lastAnswer).style = 'background: #FFC1C1 url(\'/wxmobile/img/icon_red_ok.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                }
                this.showAnswer = true;
            }

        },
        methods:{
            clickItem(item){
                let itemLength = document.getElementsByName('judge_item').length;
                for(let i=0;i<itemLength;i++){
                    document.getElementsByName('judge_item')[i].style = 'background: grey url(\'/wxmobile/img/select_not.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                }
                this.storeAnswer(item);
                if(this.judgeQuestionContent.answer == item) {
                    document.getElementById("judge_"+item).style = 'background: green url(\'/wxmobile/img/icon_red_ok.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                    setTimeout(() => {
                        this.$emit('setNextQuestion',true);
                    }, 500)
                    this.storeRightOrErrorCount('right');
                    this.$parent.hadleErrorAndHighErrorEollection('right',this.judgeQuestionContent.id);
                }else {
                    document.getElementById("judge_"+item).style = 'background: #FFC1C1 url(\'/wxmobile/img/icon_red_ok.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                    this.storeRightOrErrorCount('error');
                    this.showAnswer = true;
                    this.$parent.cacheErrorExamQuestion(this.ccpId,this.judgeQuestionContent.id);

                }

            },
            storeRightOrErrorCount(rightOrError){
                if('right'==rightOrError) {
                    //正确就先再缓存里面增加正确的，再从缓存删除错误
                    let answerRightArray = JSON.parse(getStore("answer_right_"+this.ccpId));
                    if(answerRightArray!=null){
                        let n=0;
                        for(let i=0;i<answerRightArray.length;i++){
                            if(answerRightArray[i]==this.judgeQuestionContent.id){
                                n++;
                            }
                        }
                        if(n==0){
                            answerRightArray.push(this.judgeQuestionContent.id);
                        }
                    }else{
                        answerRightArray = [];
                        answerRightArray.push(this.judgeQuestionContent.id);
                    }

                    let answerErrorArray = JSON.parse(getStore("answer_error_"+this.ccpId));
                    if(answerErrorArray!=null){
                        for(let i=0;i<answerErrorArray.length;i++){
                            if(answerErrorArray[i]==this.judgeQuestionContent.id){
                                answerErrorArray.splice(i,1);
                            }
                        }
                    }
                    setStore("answer_right_"+this.ccpId,JSON.stringify(answerRightArray));
                    setStore("answer_error_"+this.ccpId,JSON.stringify(answerErrorArray));
                }else if('error'==rightOrError){
                    //错误就先再缓存里面增加错误的，再从缓存删除正确
                    let answerErrorArray = JSON.parse(getStore("answer_error_"+this.ccpId));
                    if(answerErrorArray!=null){
                        let n=0;
                        for(let i=0;i<answerErrorArray.length;i++){
                            if(answerErrorArray[i]==this.judgeQuestionContent.id){
                                n++;
                            }
                        }
                        if(n==0) {
                            answerErrorArray.push(this.judgeQuestionContent.id);
                        }
                    }else{
                        answerErrorArray = [];
                        answerErrorArray.push(this.judgeQuestionContent.id);
                    }

                    let answerRightArray = JSON.parse(getStore("answer_right_"+this.ccpId));
                    if(answerRightArray!=null){
                        for(let i=0;i<answerRightArray.length;i++){
                            if(answerRightArray[i]==this.judgeQuestionContent.id){
                                answerRightArray.splice(i,1);
                            }
                        }
                    }
                    setStore("answer_right_"+this.ccpId,JSON.stringify(answerRightArray));
                    setStore("answer_error_"+this.ccpId,JSON.stringify(answerErrorArray));
                }
            },
            storeAnswer(item){
                //将答案存到缓存里面，已被开始时候可以加载
                let answer_ids = JSON.parse(getStore("answer_ids_"+this.ccpId));
                if(answer_ids==null){
                    answer_ids = [];
                }
                let c =0;
                for(let i=0;i<answer_ids.length;i++){
                    if(answer_ids[i]==("answer_"+this.ccpId+"_"+this.judgeQuestionContent.id)){
                        c++;
                    }
                }
                if(c==0){
                    answer_ids.push("answer_"+this.ccpId+"_"+this.judgeQuestionContent.id);
                }
                setStore("answer_ids_"+this.ccpId,JSON.stringify(answer_ids));
                setStore("answer_"+this.ccpId+"_"+this.judgeQuestionContent.id,item);
            },
            changeImgCss(className) {
                let questionTitleLength = document.getElementsByClassName(className).length;
                for (let k = 0; k < questionTitleLength; k++) {
                    let imgs = document.getElementsByClassName(className).item(k).getElementsByTagName('img');
                    for (let i = 0; i < imgs.length; i++) {
                        imgs[i].style = "width:auto;height:auto;max-width: 100%;max-height: 100%";
                    }
                }
            }
        }
    }
</script>

<style lang="sass" scoped>
  .judge_item
    margin: 0.5rem 0
    color: #fff
    line-height: 3.5rem
    padding-left: 3.3rem
    background: grey url("/img/select_not.png")no-repeat 0.8rem 0.8rem
    background-size: 1.8rem
    font-size: 16px
    border-radius: 10px
    &.on
      background: grey url("/wxmobile/img/select_not.png")no-repeat 0.8rem 0.8rem
      background-size: 1.8rem
    &.right
      background: green url("/wxmobile/img/icon_red_ok.png")no-repeat 0.8rem 0.8rem
      background-size: 1.8rem
    &.error
      background: #FF8888 url("/wxmobile/img/icon_red_ok.png")no-repeat 0.8rem 0.8rem
      background-size: 1.8rem
    h3
      font-weight: normal
      font-size: 1.3rem
    p
      display: inline
    .container
      padding: 10px 5px 15px 5px
    .judge_questionTitle
      font-size: 16px
      padding: 0px
      height: auto
      width: 100%
      color:  #353c41
      word-wrap: break-word
      word-break: normal
</style>
