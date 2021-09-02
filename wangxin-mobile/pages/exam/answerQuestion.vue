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
    import headerTop from '~/components/exam/answerQuestion/header.vue'
    import answerQuestionContent from './answerQuestionContent.vue'
    import footerBtn from '~/components/exam/answerQuestion/footerBtn.vue'


    import {setStore as setLocalStore} from '~/store/common.js'


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
                }

            }
        },
        computed: {
            /*
            ...mapState([
                'courseDetail'
            ])*/
        },
        created(){

        },
        mounted(){
            this.ccpId = this.$route.query.id;




        },
        methods: {
            setQuestionId(questionId){
                this.questionId = questionId;
            },
            note(){
                this.$refs.aqc.note();
            },
            /*
            setFavoritesStatus(favoritesStatus){
                this.$refs.header.setFavoritesStatus(favoritesStatus);
            },*/
            /*
            reversalFavoritesStatus(){
                this.$refs.header.reversalFavoritesStatus();
            },
            cacheFavorites(favoritesStatus){
                console.log("收藏........");
                this.$refs.aqc.cacheFavorites(favoritesStatus);
            },*/
            goQuestion(lastOrNext){
                this.$refs.aqc.goQuestion(lastOrNext);
            },
            ...mapMutations([
                'COURSE_DETAIL'
            ]),


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
