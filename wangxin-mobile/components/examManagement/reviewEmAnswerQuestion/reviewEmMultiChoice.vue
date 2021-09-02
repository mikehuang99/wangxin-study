<template>
  <div  class="multi_class" style="padding-top: 44px;height: 100%;margin-left: 10px;margin-right: 10px">
    <div style="color: #333333;font-weight: bold;font-size: 16px">
      【多选题】
    </div>
    <div id="question_title_and_item">
    <div class="container" >
      <span class="multi_questionTitle"
            style="font-size: 16px;padding: 0px;height: auto;width: 100%;color:  #353c41;word-wrap: break-word;word-break: normal"
            v-html="multiChoiceContent.questionTitle"></span>
    </div>
    <ul>
      <li style="word-wrap:break-word;color: #35495E" v-show="multiChoiceContent.itemA!=null">
        <div class="multi_item" id="multi_A" name="multi_item" :class="{on:true}" @click="clickItem('A')">
          <span v-html="multiChoiceContent.itemA"></span>
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E"  v-show="multiChoiceContent.itemB!=null">
        <div class="multi_item" id="multi_B" name="multi_item" :class="{on:true}"  @click="clickItem('B')">
          <span v-html="multiChoiceContent.itemB"></span>
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E" v-show="multiChoiceContent.itemC!=null">
        <div class="multi_item" id="multi_C" name="multi_item" :class="{on:true}" @click="clickItem('C')">
          <span v-html="multiChoiceContent.itemC"></span>
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E" v-show="multiChoiceContent.itemD!=null">
        <div class="multi_item" id="multi_D" name="multi_item" :class="{on:true}" @click="clickItem('D')">
          <span v-html="multiChoiceContent.itemD"></span>
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E" v-show="multiChoiceContent.itemE!=null">
        <div class="multi_item" id="multi_E" name="multi_item" :class="{on:true}" @click="clickItem('E')">
          <span v-html="multiChoiceContent.itemE"></span>
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E" v-show="multiChoiceContent.itemF!=null">
        <div class="multi_item" id="multi_F" name="multi_item" :class="{on:true}" @click="clickItem('F')">
          <span v-html="multiChoiceContent.itemF"></span>
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E" v-show="multiChoiceContent.itemG!=null">
        <div class="multi_item" id="multi_G" name="multi_item" :class="{on:true}" @click="clickItem('G')">
          <span v-html="multiChoiceContent.itemG"></span>
        </div>
      </li>
      <li style="word-wrap:break-word;color: #35495E" v-show="multiChoiceContent.itemH!=null">
        <div class="multi_item" id="multi_H" name="multi_item" :class="{on:true}" @click="clickItem('H')">
          <span v-html="multiChoiceContent.itemH"></span>
        </div>
      </li>
    </ul>
    </div>
    <hr>
      <div style="text-align: center; ">
        <span @click="submitAnswer" id="submitAnswer" style="font-weight: bold;font-size: 25px;background: deepskyblue;border-radius: 5px;box-shadow: 2px 2px 2px #888888;letter-spacing: 10px">提交答案</span>
      </div>
    <div v-show="showAnswer">
      <hr>
      <div>
        <span style="font-weight: bold;font-size: 20px">正确答案：</span>
        <span style="font-size: 18px">
            <span v-html="multiChoiceContent.answer"></span>
          </span>
      </div>
      <hr>
      <div>
        <span style="font-weight: bold;font-size: 20px">难度：</span>
        <span style="font-size: 18px" v-show="multiChoiceContent.level == 1">*</span>
        <span style="font-size: 18px" v-show="multiChoiceContent.level == 2">**</span>
        <span style="font-size: 18px" v-show="multiChoiceContent.level == 3">***</span>
        <span style="font-size: 18px" v-show="multiChoiceContent.level == 4">****</span>
        <span style="font-size: 18px" v-show="multiChoiceContent.level == 5">*****</span>
      </div>
      <hr>
      <div>
        <span style="font-weight: bold;font-size: 20px">解析：</span>
        <span style="font-size: 18px">
            <span v-html="multiChoiceContent.analysis">
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
        name: "multiChoice",
        props: ['questionId','multiChoiceContent','ccpId'],
        data(){
            return{
                showAnswer: '',
                picked: '',
                onFlag:true,
                rightFlag:false,
                errorFlag:false,
                questionTitle: '题干:当心：类名的第一个字符不能运用数字！它无法在 Mozilla 或 Firefox 中起感召。' +
                    '<img src="https://atest888.oss-cn-hangzhou.aliyuncs.com/course/06fcc9daf6584549b37ccd4be163c1ae.jpg">' +
                    '在下面的 HTML 代码中，h1 与 p 元素都有 center 类。这象征着二者都将性能 ".center" 决定器中的划定。\n',
                selectedAnswer: []
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
                    this.changeImgCss('multi_class');
                }, delayedArr[d]);
            }
            setTimeout(() => {
                if(this.multiChoiceContent.itemA!=null) {
                    this.multiChoiceContent.itemA = 'A.' + this.multiChoiceContent.itemA
                }
                if(this.multiChoiceContent.itemB!=null) {
                    this.multiChoiceContent.itemB = 'B.' + this.multiChoiceContent.itemB
                }
                if(this.multiChoiceContent.itemC!=null) {
                    this.multiChoiceContent.itemC = 'C.' + this.multiChoiceContent.itemC
                }
                if(this.multiChoiceContent.itemD!=null) {
                    this.multiChoiceContent.itemD = 'D.' + this.multiChoiceContent.itemD
                }
                if(this.multiChoiceContent.itemE!=null) {
                    this.multiChoiceContent.itemE = 'E.' + this.multiChoiceContent.itemE
                }
                if(this.multiChoiceContent.itemF!=null) {
                    this.multiChoiceContent.itemF = 'F.' + this.multiChoiceContent.itemF
                }
                if(this.multiChoiceContent.itemG!=null) {
                    this.multiChoiceContent.itemG = 'G.' + this.multiChoiceContent.itemG
                }
                if(this.multiChoiceContent.itemH!=null) {
                    this.multiChoiceContent.itemH = 'H.' + this.multiChoiceContent.itemH
                }
            }, 50)
            setTimeout(() => { // 两秒恢复为true
                let titleLength = document.getElementsByClassName('multi_item').length;
                for (let k = 0; k < titleLength; k++) {
                    let p = document.getElementsByClassName('multi_item').item(k).getElementsByTagName('p');
                    for (let i = 0; i < p.length; i++) {
                        p[i].style = "display: inline";
                    }
                }
            }, 80)

            setTimeout(() => {
                let questionTitleLength2 = document.getElementsByClassName('multi_item').length;
                for (let kk = 0; kk < questionTitleLength2; kk++) {
                    let imgs2 = document.getElementsByClassName('multi_item').item(kk).getElementsByTagName('img');
                    for (let ii = 0; ii < imgs2.length; ii++) {
                        imgs2[ii].style = "width:auto;height:auto;max-width: 100%;max-height: 100%";
                    }
                }
            }, 5)

          //回填答案
            let lastAnswer = getStore("answer_"+this.ccpId+"_"+this.multiChoiceContent.id);
            if(lastAnswer!=null){
                console.log("lastAnswer:"+(lastAnswer == this.multiChoiceContent.answer))
                let a = this.multiChoiceContent.answer;
                console.log("真正答案："+a)
                let b=a.split("");       //分割字符串a为数组dub
                b.sort();              //数组b升序排序（系统自带的方法）
                console.log("真正答案b.length："+b.length)
                let la = JSON.parse(lastAnswer);
                console.log("la:"+la)
                console.log("两次答案是否相等:"+(la.toString() === b.toString()))
                console.log("JSON.stringify(lastAnswer):"+lastAnswer)
                if(la.toString() === b.toString()){
                    //答案正确
                    document.getElementById("question_title_and_item").style="background-color: green";
                }else {
                    //答案错误
                    document.getElementById("question_title_and_item").style="background-color: #FFC1C1";
                }
                for(let eee=0;eee<la.length;eee++){
                    document.getElementById("multi_"+la[eee]).style = 'background: grey url(\'/wxmobile/img/icon_red_ok.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                }
                this.showAnswer = true;

            }



        },
        methods:{
            submitAnswer(){
                let rightCount =0;
                let errorCount =0;
                //console.log("selectedAnswer:"+JSON.stringify(this.selectedAnswer))
                if(this.selectedAnswer.length==0){//未选择答案,直接返回
                    return;
                }
                for(let i=0;i<this.selectedAnswer.length;i++){
                     let rAnswer  = this.multiChoiceContent.answer;
                     if(rAnswer.indexOf(this.selectedAnswer[i])!=-1){
                         rightCount = rightCount+1;
                     }else{
                         errorCount = errorCount+1;
                     }
                }
                document.getElementById("submitAnswer").style.background = "#C0C0C0";
                document.getElementById("submitAnswer").style.boxShadow = "2px 2px 2px #C0C0C0";
                setTimeout(() => { // 两秒恢复为true
                    document.getElementById("submitAnswer").style.background = "deepskyblue";
                    document.getElementById("submitAnswer").style.boxShadow = "2px 2px 2px #888888";
                }, 500);


                let a = this.multiChoiceContent.answer;
                let b=a.split("");       //分割字符串a为数组dub

                this.storeAnswer();

                if(rightCount==this.selectedAnswer.length&&errorCount==0&&rightCount==b.length){
                    //答案正确
                    document.getElementById("question_title_and_item").style="background-color: green";
                    setTimeout(() => {
                        this.$emit('setNextQuestion',true);
                    }, 500)
                    this.storeRightOrErrorCount('right');
                    this.$parent.hadleErrorAndHighErrorEollection('right',this.multiChoiceContent.id);
                }else {
                    //答案错误
                    document.getElementById("question_title_and_item").style="background-color: #FFC1C1";
                    this.storeRightOrErrorCount('error');
                    this.showAnswer = true;
                    this.$parent.cacheErrorExamQuestion(this.ccpId,this.multiChoiceContent.id);
                }

            },
            clickItem(item){

                let n=0;
                for(let i=0;i<this.selectedAnswer.length;i++){
                    if(this.selectedAnswer[i]==item){
                        n++;
                        this.selectedAnswer.splice(i, 1);//从起始位置删除一个
                    }
                }
                if(n==0) {
                    document.getElementById("multi_"+item).style = 'background: grey url(\'/wxmobile/img/icon_red_ok.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                    this.selectedAnswer.push(item);
                }else{
                    document.getElementById("multi_"+item).style = 'background: grey url(\'/wxmobile/img/select_not.png\')no-repeat 0.8rem 0.8rem;background-size: 1.8rem';
                }

            },
            storeRightOrErrorCount(rightOrError){
                if('right'==rightOrError) {
                    //正确就先再缓存里面增加正确的，再从缓存删除错误
                    let answerRightArray = JSON.parse(getStore("answer_right_"+this.ccpId));
                    if(answerRightArray!=null){
                        let n=0;
                        for(let i=0;i<answerRightArray.length;i++){
                            if(answerRightArray[i]==this.multiChoiceContent.id){
                                n++;
                            }
                        }
                        if(n==0){
                            answerRightArray.push(this.multiChoiceContent.id);
                        }
                    }else{
                        answerRightArray = [];
                        answerRightArray.push(this.multiChoiceContent.id);
                    }

                    let answerErrorArray = JSON.parse(getStore("answer_error_"+this.ccpId));
                    if(answerErrorArray!=null){
                        for(let i=0;i<answerErrorArray.length;i++){
                            if(answerErrorArray[i]==this.multiChoiceContent.id){
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
                            if(answerErrorArray[i]==this.multiChoiceContent.id){
                                n++;
                            }
                        }
                        if(n==0) {
                            answerErrorArray.push(this.multiChoiceContent.id);
                        }
                    }else{
                        answerErrorArray = [];
                        answerErrorArray.push(this.multiChoiceContent.id);
                    }

                    let answerRightArray = JSON.parse(getStore("answer_right_"+this.ccpId));
                    if(answerRightArray!=null){
                        for(let i=0;i<answerRightArray.length;i++){
                            if(answerRightArray[i]==this.multiChoiceContent.id){
                                answerRightArray.splice(i,1);
                            }
                        }
                    }
                    setStore("answer_right_"+this.ccpId,JSON.stringify(answerRightArray));
                    setStore("answer_error_"+this.ccpId,JSON.stringify(answerErrorArray));
                }
            },
            storeAnswer(){
                //将答案存到缓存里面，已被开始时候可以加载
                let answer_ids = JSON.parse(getStore("answer_ids_"+this.ccpId));
                if(answer_ids==null){
                    answer_ids = [];
                }
                let c =0;
                for(let i=0;i<answer_ids.length;i++){
                    if(answer_ids[i]==("answer_"+this.ccpId+"_"+this.multiChoiceContent.id)){
                        c++;
                    }
                }
                if(c==0){
                    answer_ids.push("answer_"+this.ccpId+"_"+this.multiChoiceContent.id);
                }

                this.selectedAnswer = [];
                //找出已选答案
                for(let i=0;i<document.getElementsByClassName("multi_item").length;i++){
                    let indexOf = document.getElementsByClassName("multi_item")[i].style.background.indexOf("icon_red_ok.png");
                    if(indexOf!=-1){
                        let itemId = document.getElementsByClassName("multi_item")[i].id;
                        let itemSelected = itemId.replace(/multi_/, "");
                        this.selectedAnswer.push(itemSelected);
                    }
                }


                this.selectedAnswer.sort();   //数组升序排序（系统自带的方法）

                setStore("answer_ids_"+this.ccpId,JSON.stringify(answer_ids));
                setStore("answer_"+this.ccpId+"_"+this.multiChoiceContent.id,this.selectedAnswer);
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
  .multi_item
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
    //.multi_questionTitle
      //font-size: 16px
      //padding: 0px
      //height: auto
      //width: 100%
      //color:  #353c41
      //word-wrap: break-word
      //word-break: normal
</style>
