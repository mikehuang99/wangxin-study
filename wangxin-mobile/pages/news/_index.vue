<template>
  <div>
    <top-header :headerName="this.headerName" :headerNameClass="this.headerNameClass"></top-header>
    <div class="container">
      <div class="newsTitle">{{newsTitle}}</div>
      <div class="newsTime">发布日期:{{newsTime | fil}}</div>
      <span class="newsContent" v-html="newsContent">
      </span>
      <div style="color: red;font-weight: bold">全文完</div>
    </div>
  </div>
</template>
<script>
    import topHeader from '~/components/common/header.vue'

    import {findNewsById} from '~/api/index'

    export default {
        data() {
            return {
                headerName: '信息详情',
                headerNameClass: 1,
                val: '返回',
                newsTitle:'',
                newsContent:'',
                newsTime:''
            }
        },
        mounted () {
            let id= this.$route.query.id

            this.findNewsById(id);
            /*
            let newsRes =  axios.get('/mobileApi/newsView?id='+ id +'&timeStamp='+new Date().getTime());
            let newsTitle = newsRes.id;*/
            //console.log("newsTitle:"+newsTitle)
            /*
            axios.get('/mobileApi/newsView?id='+ id +'&timeStamp='+new Date().getTime()).then(res => {
                console.log("=====:"+res.data.data.newsTitle)
                this.newsTitle = res.data.data.newsTitle;
                this.newsContent = res.data.data.newsContent;
                this.newsTime = res.data.data.createTime;
            })*/

        },
        filters:{
            fil(value){
                return value.substring(0,10);
            }
        },
        components: {
            topHeader
        },
        methods: {
            findNewsById(id) {
                findNewsById({"id":id}).then(res => {
                    if (res.data.code === 200) {
                        //this.newsList = res.data.data.list || [];
                        this.newsTitle = res.data.data.newsTitle;
                        this.newsContent = res.data.data.newsContent;
                        this.newsTime = res.data.data.createTime;
                    } else {
                        console.log('88999！')
                    }
                });
            }
        }
    }
</script>
<style lang="sass" scoped>
  @import '~static/css/style.sass'
  .container
    padding: 10px 5px 15px 5px

  .newsTitle
      height: 5rem
      background: #E5E5E5
      color: #353c41
      font-weight: bold
      padding-top: 5rem
      padding-left: 1rem
      line-height: 1.5rem
      font-size: 1.7rem
      text-align: left
      word-wrap: break-word
      word-break: normal
  .newsTime
    height: 2rem
    background: #E5E5E5
    color: #C0C0C0
    padding-top: 5rem
    padding-left: 1rem
    line-height: 1.5rem
    font-size: 1.7rem
    text-align: left
    word-wrap: break-word
    word-break: normal
  .newsContent
      padding: 0px
      height: auto
      color:  #353c41
      font-size: 1.5rem
      line-height: 1.5rem
      padding-left: 1rem
      word-wrap: break-word
      word-break: normal
      text-indent: 3rem

      img
        width: auto
        height: auto
        max-width: 100%
        max-height: 100%

  .btn_padding
    margin-top: 1rem
    width: 100%
    padding: 0 1rem

  .info_box_case
    float: right
    font-size: 1.1rem
    margin-right: 1rem
    margin-top: 0.8rem

    i
      color: $theme_color
</style>
