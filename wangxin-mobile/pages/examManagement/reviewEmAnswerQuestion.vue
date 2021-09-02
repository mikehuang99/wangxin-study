<template>
  <div class="box">

    <header-top ref="header" :questionId="questionId" :continuestartindex="continuestartindex" :totalquestionscount="totalquestionscount"></header-top>
    <answer-question-content @setRightCount="(val)=>rightCount=val"
                             @setErrorCount="(val)=>errorCount=val"
                             @setContinueStartIndex="(val)=>continuestartindex=val"
                             @setTotalQuestionsCount="(val)=>totalquestionscount=val"
                             :ccpId="ccpId"
                             ref="aqc"
    ></answer-question-content>
    <footer-btn :rightCount="rightCount" :errorCount="errorCount" :totalquestionscount="totalquestionscount"></footer-btn>
  </div>
</template>
<script>
    import headerTop from '~/components/examManagement/answerQuestion/emHeader.vue'
    import answerQuestionContent from './reviewEmAnswerQuestionContent.vue'
    import footerBtn from '~/components/examManagement/answerQuestion/emFooterBtn.vue'

    import {examPaperViewByExamManagementIdAndExamPaper,viewExamPaperContentByExamManagementIdAndExamPaperId} from '~/api/examManagement'

    import {setStore} from '~/store/common.js'
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
                examPaperId: ''

            }
        },
        computed: {

        },
        created(){

        },
        mounted(){


            this.examManagementId = this.$route.query.examManagementId;
            this.examPaperId = this.$route.query.examPaperId;



            this.ccpId = this.examPaperId;


            this.viewExamPaperContentByExamManagementIdAndExamPaperId();
        },
        methods: {
            setQuestionId(questionId){
                this.questionId = questionId;
            },
            note(){
                this.$refs.aqc.note();
            },
            goQuestion(lastOrNext){
                this.$refs.aqc.goQuestion(lastOrNext);
            },
            viewExamPaperContentByExamManagementIdAndExamPaperId () {
                viewExamPaperContentByExamManagementIdAndExamPaperId({
                    "id":this.examManagementId,"examPaperId":this.examPaperId
                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        setStore("examQuestions_"+this.examPaperId,res.data.examQuestionVos);
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
