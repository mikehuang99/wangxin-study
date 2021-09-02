<template>
  <div class="fillBlank_class" style="padding-top: 44px;height: 100%;margin-left: 10px;margin-right: 10px">
    <div style="color: #333333;font-weight: bold;font-size: 16px">
      【填空题】
    </div>
    <div id="fillBlank_question_title_and_item">
      <div class="container" >
        <span class="fillBlank_questionTitle"
              style="font-size: 16px;padding: 0px;height: auto;width: 100%;color:  #353c41;word-wrap: break-word;word-break: normal"
              v-html="fillBlankContent.questionTitle"></span>
      </div>
      <br/>
      <ul>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px" v-show="fillBlankContent.itemA!=null&&fillBlankContent.itemA!=''">
          空一：<input style="font-size: 20px" id="item_A">
        </li>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px;margin-top: 2px" v-show="fillBlankContent.itemB!=null&&fillBlankContent.itemB!=''">
          空二：<input style="font-size: 20px" id="item_B">
        </li>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px;margin-top: 2px" v-show="fillBlankContent.itemC!=null&&fillBlankContent.itemC!=''">
          空三：<input style="font-size: 20px" id="item_C">
        </li>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px;margin-top: 2px" v-show="fillBlankContent.itemD!=null&&fillBlankContent.itemD!=''">
          空四：<input style="font-size: 20px" id="item_D">
        </li>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px;margin-top: 2px" v-show="fillBlankContent.itemE!=null&&fillBlankContent.itemE!=''">
          空五：<input style="font-size: 20px" id="item_E">
        </li>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px;margin-top: 2px" v-show="fillBlankContent.itemF!=null&&fillBlankContent.itemF!=''">
          空六：<input style="font-size: 20px" id="item_F">
        </li>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px;margin-top: 2px" v-show="fillBlankContent.itemG!=null&&fillBlankContent.itemG!=''">
          空七：<input style="font-size: 20px" id="item_G">
        </li>
        <li style="word-wrap:break-word;color: #35495E;font-size: 20px;background-color: #bbbbbb;border-radius: 10px;margin-top: 2px" v-show="fillBlankContent.itemH!=null&&fillBlankContent.itemH!=''">
          空八：<input style="font-size: 20px" id="item_H">
        </li>
      </ul>
    </div>
    <hr>
    <div style="text-align: center; ">
      <span @click="submitAnswer" id="fillBlank_submitAnswer" style="font-weight: bold;font-size: 25px;background: deepskyblue;border-radius: 5px;box-shadow: 2px 2px 2px #888888;letter-spacing: 10px">提交答案</span>
    </div>
    <div v-show="showAnswer">
    <hr>
    <div>
      <span style="font-weight: bold;font-size: 20px">正确答案：</span><br/>
      <div v-show="fillBlankContent.itemA!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px">空一：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemA" ></span>
      </div>
      <div  v-show="fillBlankContent.itemB!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px">空二：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemB"></span>
      </div>
      <div v-show="fillBlankContent.itemC!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px">空三：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemC"></span>
      </div>
      <div v-show="fillBlankContent.itemD!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px">空四：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemD" ></span>
      </div>
      <div v-show="fillBlankContent.itemE!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px">空五：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemE"></span>
      </div>
      <div v-show="fillBlankContent.itemF!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px" >空六：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemF"></span>
      </div>
      <div v-show="fillBlankContent.itemG!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px">空七：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemG"></span>
      </div>
      <div v-show="fillBlankContent.itemH!=null">
        <span style="font-weight: bold;word-wrap:break-word;font-size: 18px">空八：</span>
        <span style="word-wrap:break-word;font-size: 16px" v-html="fillBlankContent.itemH"></span>
      </div>
    </div>
    <hr>
    <div>
      <span style="font-weight: bold;font-size: 20px">难度：</span>
      <span style="font-size: 18px" v-show="fillBlankContent.level == 1">*</span>
      <span style="font-size: 18px" v-show="fillBlankContent.level == 2">**</span>
      <span style="font-size: 18px" v-show="fillBlankContent.level == 3">***</span>
      <span style="font-size: 18px" v-show="fillBlankContent.level == 4">****</span>
      <span style="font-size: 18px" v-show="fillBlankContent.level == 5">*****</span>
    </div>
    <hr>
    <div>
      <span style="font-weight: bold;font-size: 20px">解析：</span>
      <span style="font-size: 18px">
          <span v-html="fillBlankContent.analysis">
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
        name: "fillBlank",
        props: ['questionId','fillBlankContent','ccpId'],
        data(){
            return{
                showAnswer: false,
                picked: '',
                onFlag:true,
                rightFlag:false,
                errorFlag:false,
                questionTitle: '题干:当心：类名的第一个字符不能运用数字！它无法在 Mozilla 或 Firefox 中起感召。' +
                    '<img src="https://atest888.oss-cn-hangzhou.aliyuncs.com/course/06fcc9daf6584549b37ccd4be163c1ae.jpg">' +
                    '在下面的 HTML 代码中，h1 与 p 元素都有 center 类。这象征着二者都将性能 ".center" 决定器中的划定。\n'
            }
        },
        filters:{

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
                    this.changeImgCss('fillBlank_class');
                }, delayedArr[d]);
            }

            setTimeout(() => { // 两秒恢复为true
                let titleLength = document.getElementsByClassName('fillBlank_item').length;
                for (let k = 0; k < titleLength; k++) {
                    let p = document.getElementsByClassName('fillBlank_item').item(k).getElementsByTagName('p');
                    for (let i = 0; i < p.length; i++) {
                        p[i].style = "display: inline";
                    }
                }
            }, 8)

            setTimeout(() => {
                let questionTitleLength2 = document.getElementsByClassName('fillBlank_item').length;
                for (let kk = 0; kk < questionTitleLength2; kk++) {
                    let imgs2 = document.getElementsByClassName('fillBlank_item').item(kk).getElementsByTagName('img');
                    for (let ii = 0; ii < imgs2.length; ii++) {
                        imgs2[ii].style = "width:auto;height:auto;max-width: 100%;max-height: 100%";
                    }
                }
            }, 5)


            //回填答案开始
            let lastAnswer = getStore("answer_"+this.ccpId+"_"+this.fillBlankContent.id);
            if(lastAnswer!=null){
                let la = JSON.parse(lastAnswer);
                console.log("回填答案1:"+la)
                for(let ccc=0;ccc<la.length;ccc++){
                    if(ccc==0) {
                        document.getElementById("item_A").value = la[ccc];
                    }
                    if(ccc==1) {
                        document.getElementById("item_B").value = la[ccc];
                    }
                    if(ccc==2) {
                        document.getElementById("item_C").value = la[ccc];
                    }
                    if(ccc==3) {
                        document.getElementById("item_D").value = la[ccc];
                    }
                    if(ccc==4) {
                        document.getElementById("item_E").value = la[ccc];
                    }
                    if(ccc==5) {
                        document.getElementById("item_F").value = la[ccc];
                    }
                    if(ccc==6) {
                        document.getElementById("item_G").value = la[ccc];
                    }
                    if(ccc==7) {
                        document.getElementById("item_H").value = la[ccc];
                    }
                }

                let isReview = this.$route.query.isReview;
                if(isReview==1) {
                    let rightCount =0;
                    let errorCount =0;
                    if(this.fillBlankContent.itemA!=null&&this.fillBlankContent.itemA!=''){
                        if(document.getElementById("item_A").value==this.fillBlankContent.itemA){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }

                    if(this.fillBlankContent.itemB!=null&&this.fillBlankContent.itemB!=''){
                        if(document.getElementById("item_B").value==this.fillBlankContent.itemB){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }

                    if(this.fillBlankContent.itemC!=null&&this.fillBlankContent.itemC!=''){
                        if(document.getElementById("item_C").value==this.fillBlankContent.itemC){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }

                    if(this.fillBlankContent.itemD!=null&&this.fillBlankContent.itemD!=''){
                        if(document.getElementById("item_D").value==this.fillBlankContent.itemD){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }

                    if(this.fillBlankContent.itemE!=null&&this.fillBlankContent.itemE!=''){
                        if(document.getElementById("item_E").value==this.fillBlankContent.itemE){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }

                    if(this.fillBlankContent.itemF!=null&&this.fillBlankContent.itemF!=''){
                        if(document.getElementById("item_F").value==this.fillBlankContent.itemF){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }

                    if(this.fillBlankContent.itemG!=null&&this.fillBlankContent.itemG!=''){
                        if(document.getElementById("item_G").value==this.fillBlankContent.itemG){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }

                    if(this.fillBlankContent.itemH!=null&&this.fillBlankContent.itemH!=''){
                        if(document.getElementById("item_H").value==this.fillBlankContent.itemH){
                            rightCount = rightCount + 1;
                        }else{
                            errorCount = errorCount + 1;
                        }
                    }
                    if(errorCount==0){
                        //答案正确
                        document.getElementById("fillBlank_question_title_and_item").style="background-color: green";
                    }else {
                        //答案错误
                        document.getElementById("fillBlank_question_title_and_item").style="background-color: #FFC1C1";
                    }
                    this.showAnswer = true;
                    //回填答案结束
                }
                /*


                 */
            }
        },
        methods:{
            submitAnswer(){
                let rightCount =0;
                let errorCount =0;
                let fillBlankItemCompleted = [];
                if(this.fillBlankContent.itemA!=null){
                    if(document.getElementById("item_A").value==this.fillBlankContent.itemA){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_A").value);
                }

                if(this.fillBlankContent.itemB!=null){
                    if(document.getElementById("item_B").value==this.fillBlankContent.itemB){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_B").value);
                }

                if(this.fillBlankContent.itemC!=null){
                    if(document.getElementById("item_C").value==this.fillBlankContent.itemC){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_C").value);
                }

                if(this.fillBlankContent.itemD!=null){
                    if(document.getElementById("item_D").value==this.fillBlankContent.itemD){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_D").value);
                }

                if(this.fillBlankContent.itemE!=null){
                    if(document.getElementById("item_E").value==this.fillBlankContent.itemE){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_E").value);
                }

                if(this.fillBlankContent.itemF!=null){
                    if(document.getElementById("item_F").value==this.fillBlankContent.itemF){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_F").value);
                }

                if(this.fillBlankContent.itemG!=null){
                    if(document.getElementById("item_G").value==this.fillBlankContent.itemG){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_G").value);
                }

                if(this.fillBlankContent.itemH!=null){
                    if(document.getElementById("item_H").value==this.fillBlankContent.itemH){
                        rightCount = rightCount + 1;
                    }else{
                        errorCount = errorCount + 1;
                    }
                    fillBlankItemCompleted.push(document.getElementById("item_H").value);
                }



                document.getElementById("fillBlank_submitAnswer").style.background = "#C0C0C0";
                document.getElementById("fillBlank_submitAnswer").style.boxShadow = "2px 2px 2px #C0C0C0";
                setTimeout(() => { // 两秒恢复为true
                    document.getElementById("fillBlank_submitAnswer").style.background = "deepskyblue";
                    document.getElementById("fillBlank_submitAnswer").style.boxShadow = "2px 2px 2px #888888";
                }, 500)

                this.storeAnswer(fillBlankItemCompleted);
                setTimeout(() => {
                    this.$emit('setNextQuestion',true);
                }, 500)
                /*
                if(errorCount==0){
                    //答案正确
                    document.getElementById("fillBlank_question_title_and_item").style="background-color: green";
                    setTimeout(() => { // 两秒恢复为true
                        this.$emit('setNextQuestion',true);
                    }, 500)
                    this.storeRightOrErrorCount('right');
                    this.$parent.hadleErrorAndHighErrorEollection('right',this.fillBlankContent.id);
                }else {
                    //答案错误
                    document.getElementById("fillBlank_question_title_and_item").style="background-color: #FFC1C1";
                    this.storeRightOrErrorCount('error');
                    this.showAnswer = true;
                    this.$parent.cacheErrorExamQuestion(this.ccpId,this.fillBlankContent.id);
                }*/

            },
            storeRightOrErrorCount(rightOrError){
                if('right'==rightOrError) {
                    //正确就先再缓存里面增加正确的，再从缓存删除错误
                    let answerRightArray = JSON.parse(getStore("answer_right_"+this.ccpId));
                    if(answerRightArray!=null){
                        let n=0;
                        for(let i=0;i<answerRightArray.length;i++){
                            if(answerRightArray[i]==this.fillBlankContent.id){
                                n++;
                            }
                        }
                        if(n==0){
                            answerRightArray.push(this.fillBlankContent.id);
                        }
                    }else{
                        answerRightArray = [];
                        answerRightArray.push(this.fillBlankContent.id);
                    }

                    let answerErrorArray = JSON.parse(getStore("answer_error_"+this.ccpId));
                    if(answerErrorArray!=null){
                        for(let i=0;i<answerErrorArray.length;i++){
                            if(answerErrorArray[i]==this.fillBlankContent.id){
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
                            if(answerErrorArray[i]==this.fillBlankContent.id){
                                n++;
                            }
                        }
                        if(n==0) {
                            answerErrorArray.push(this.fillBlankContent.id);
                        }
                    }else{
                        answerErrorArray = [];
                        answerErrorArray.push(this.fillBlankContent.id);
                    }

                    let answerRightArray = JSON.parse(getStore("answer_right_"+this.ccpId));
                    if(answerRightArray!=null){
                        for(let i=0;i<answerRightArray.length;i++){
                            if(answerRightArray[i]==this.fillBlankContent.id){
                                answerRightArray.splice(i,1);
                            }
                        }
                    }
                    setStore("answer_right_"+this.ccpId,JSON.stringify(answerRightArray));
                    setStore("answer_error_"+this.ccpId,JSON.stringify(answerErrorArray));
                }
            },
            storeAnswer(fillBlankItemCompleted){
                //将答案存到缓存里面，已被开始时候可以加载
                let answer_ids = JSON.parse(getStore("answer_ids_"+this.ccpId));
                if(answer_ids==null){
                    answer_ids = [];
                }
                let c =0;
                for(let i=0;i<answer_ids.length;i++){
                    if(answer_ids[i]==("answer_"+this.ccpId+"_"+this.fillBlankContent.id)){
                        c++;
                    }
                }
                if(c==0){
                    answer_ids.push("answer_"+this.ccpId+"_"+this.fillBlankContent.id);
                }
                setStore("answer_ids_"+this.ccpId,JSON.stringify(answer_ids));
                setStore("answer_"+this.ccpId+"_"+this.fillBlankContent.id,fillBlankItemCompleted);
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
  .fillBlank_item
    margin: 0.5rem 0
    color: #fff
    line-height: 3.5rem
    padding-left: 3.3rem
    background: grey url("/img/select_not.png")no-repeat 0.8rem 0.8rem
    background-size: 1.8rem
    font-size: 16px
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
    .fillBlank_questionTitle
      font-size: 16px
      padding: 0px
      height: auto
      width: 100%
      color:  #353c41
      word-wrap: break-word
      word-break: normal
</style>
