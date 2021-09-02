<template>
  <div class="box">

    <header-top ref="headRef"  :questionId="questionId" :continuestartindex="continuestartindex" :totalquestionscount="totalquestionscount"></header-top>
    <answer-question-content v-if="!isOpenEmAnswerQuestionPool" @setRightCount="(val)=>rightCount=val"
                             @setErrorCount="(val)=>errorCount=val"
                             @setContinueStartIndex="(val)=>continuestartindex=val"
                             @setTotalQuestionsCount="(val)=>totalquestionscount=val"
                             :ccpId="ccpId"
                             ref="aqc"
    ></answer-question-content>
    <em-answer-question-pool ref="aqp" v-if="isOpenEmAnswerQuestionPool"></em-answer-question-pool>
    <footer-btn :rightCount="rightCount" :errorCount="errorCount" :totalquestionscount="totalquestionscount"></footer-btn>
  </div>
</template>
<script>
    import headerTop from '~/components/examManagement/answerQuestion/emHeader.vue'
    import answerQuestionContent from './emAnswerQuestionContent.vue'
    import emAnswerQuestionPool from './emAnswerQuestionPool.vue'

    import footerBtn from '~/components/examManagement/answerQuestion/emFooterBtn.vue'

    import {examPaperViewByExamManagementIdAndExamPaper,viewExamPaperContentByExamManagementIdAndExamPaperId} from '~/api/examManagement'

    import {setStore,getStore,removeStore} from '~/store/common.js'
    import { logout } from '~/store/common'

    import { mapState, mapMutations } from 'vuex'
    export default {
        data(){
            return{
                questionId:'',
                ccpId: '',
                rightCount: '',
                errorCount: '',
                continuestartindex: '',
                totalquestionscount: '',
                test:'99',
                videooasid:'',
                playvideo:false,
                playimg:true,
                lessonname: '',

                startX: 0, //开始触摸的位置
                endX: 0, //结束触摸的位置
                disX: 0, //移动距离
                lastX: 0, //上一次X坐标
                lastY: 0, //上一次Y坐标
                con:{
                    translateX: 'translateX(0px)'

                },
                courseDetail: {
                    id:''
                },
                examManagementId: '',
                examPaperId: '',

                isOpenEmAnswerQuestionPool: false

            }
        },
        computed: {

        },
        created(){

        },
        mounted(){


            this.examManagementId = this.$route.query.examManagementId;
            this.examPaperId = this.$route.query.examPaperId;



            this.ccpId = this.$route.query.ccpId;

            //this.viewExamPaperContentByExamManagementIdAndExamPaperId();

        },
        methods: {
            setShowHandInExamPaperButton(flag){
                this.$refs.headRef.setShowHandInExamPaperButton(flag);
                this.isOpenEmAnswerQuestionPool = false;
            },
            /*
            toOpenEmAnswerQuestionPool(){
                this.isOpenEmAnswerQuestionPool = true;
            },*/

            toForceHandInPaper(){
                this.isOpenEmAnswerQuestionPool = true;
                this.$refs.aqp.handInExamPaper();
            },
            toOpenEmAnswerQuestionPool(val){
                this.isOpenEmAnswerQuestionPool = val;
            },
            setQuestionId(questionId){
                this.questionId = questionId;
            },
            note(){
                this.$refs.aqc.note();
            },
            reTakeAnExamination(){
                //清除上次的答案缓存开始
                let answer_ids = JSON.parse(getStore("answer_ids_"+this.ccpId));
                if(answer_ids!=null){
                    for(let i=0;i<answer_ids.length;i++){
                        removeStore(answer_ids[i]);
                    }
                }
                removeStore("answer_ids_"+this.ccpId);
                //removeStore("answer_right_"+this.ccpId);
                //removeStore("answer_error_"+this.ccpId);
                //清除上次的答案缓存结束

                //设置索引为0，直接调用继续学习
                setStore("continueStartIndex_"+this.ccpId,0);

                this.reViewExamPaperContentByExamManagementIdAndExamPaperId();

                //this.$router.push({path: '/examManagement/emAnswerQuestion', query: {examManagementId: this.examManagementId,examPaperId:this.examPaperId,id:this.examPaperId}});

            },
            goQuestion(lastOrNext){
                this.$refs.aqc.goQuestion(lastOrNext);
            },
            reViewExamPaperContentByExamManagementIdAndExamPaperId () {
                viewExamPaperContentByExamManagementIdAndExamPaperId({
                    "id":this.examManagementId,"examPaperId":this.examPaperId
                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        let examQuestionVos = res.data.examQuestionVos || [];
                        if(examQuestionVos.length==0){
                            alert("没有试题");
                            return;
                        }
                        setStore("examQuestions_"+this.examManagementId,res.data.examQuestionVos);

                        window.location.reload();

                    } else if (res.code > 300 && res.code < 400) {
                        if(res.code === 304){
                            console.log("该账号被异地登录");
                            logout();
                            this.$router.push({path: '/login', query: {}});
                        }else {
                            console.log("登录超时、异常或者被异地登录，请重新登录")
                            logout();
                            this.$router.push({path: '/login', query: {}});
                        }
                    }else{
                        console.log("系统错误");
                    }
                }).catch(() => {
                })
            }
        },
        components: {
            headerTop,
            answerQuestionContent,
            emAnswerQuestionPool,
            footerBtn
        }
    }
</script>
<style lang="sass" scoped>

  .box
    margin: 0
      padding: 0
    padding-bottom: 50px
      width: 100%
      height: 100%
      background: yellow
      position: absolute

</style>
