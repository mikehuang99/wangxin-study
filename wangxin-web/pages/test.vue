<template>
  <div>
   <div>
     <button @click="test" value="666"></button>
     <p>{{dataf}}</p>
     <input type="text" class="form_input" placeholder="请输入昵称" v-model="dataf">
   </div>
    <y-video v-if="showa" :open="showVideo" :content="content" @hidefun="hideModal()" @testfun="(val)=>dataf=val"></y-video>
  </div>
</template>
<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import YSide from '~/components/account/Side'
    import YVideo from './testdialog'
    import YUpload from '~/components/account/Upload'
    export default {
        data () {
            return {
                showa:false,
                showVideo:false,
                content:null,
            }
        },
        computed: {

        },
        methods: {
            test(){
              this.showa=true;
              this.showVideo=true;
              this.content = {
                    testNo: 888,
                    testId: this.dataf
              };
            },
            hideModal: function () {
                this.showa = false;
                this.showVideo = false;

            }

        },
        mounted () {

        },
        components: {
            YHeader,
            YFooter,
            YVideo,
            YUpload,
            YSide
        }
    }
</script>
<style lang="scss" rel="stylesheet/scss">
  @import '~/assets/css/account.scss';


</style>
