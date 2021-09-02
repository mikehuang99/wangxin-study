
<template>
  <transition name='index'>
    <div class="body">
      <wx-header></wx-header>
      <Banner></Banner>
      <News></News>
      <MiddleNav></MiddleNav>
      <TopicZone></TopicZone>
      <Copyright></Copyright>
      <Footer></Footer>
    </div>
  </transition>
</template>
<script>
import wxHeader from '~/components/index/WxHeader.vue'
import Banner from '~/components/index/banner/Banner.vue'
import News from '~/components/index/news/News.vue'
import MiddleNav from '~/components/index/nav/MiddleNav.vue'
import TopicZone from '~/components/index/topic/TopicZone.vue'
import Copyright from '~/components/index/copyright/Copyright.vue'
import Footer from '~/components/index/footer/Footer.vue'
import {findWebsite} from '~/api/index'


import {mapState, mapMutations} from 'vuex'

export default {
    data(){
        return{
          title:"",
          newsList:[{"name":"dfdfd","id":"2222","createTime":"2022-09-09"},
              {"name":"2223323","id":"23232","createTime":"2022-09-10"}],
            website: '',
            faviconIcoUrl: ''
        }
    },
  computed: {

  },
  mounted () {
        this.getWebsite();
  },
  methods: {
    ...mapMutations([
      'COURSE_TYPE'
    ]),
      carouselHandler(response){

      },
      getWebsite() {
          findWebsite().then(res => {
              if (res.data.code === 200) {
                  this.website = res.data.data;
                  this.title = this.website.websiteTitle;
                  this.faviconIcoUrl = this.website.faviconIcoUrl;
              } else {
                  console.log('findWebsite请求错误');
              }
          });
      },
  },
  head () {
    return {
      title: this.title,
        link: [
            { rel: 'icon', type: 'image/x-icon', href: this.faviconIcoUrl }
        ]
    }
  },
  components: {
      Footer,
      TopicZone,
      MiddleNav,
      News,
      wxHeader,
      Banner,
      Copyright
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .body{
    background: #eee;
    padding-top: 44px;
    padding-bottom: 70px;
  }
    html{
      width: 100%;
      height: 100%;
      font-size: 62.5%;
      color: #474747;
  }
    body{
      font-family: 'Microsoft YaHei', 'Hiragino Sans GB', 'Arail';
      padding-top: 44px;
      padding-bottom: 6rem;
      -webkit-box-sizing: border-box;
      margin: 0;
  }
    body.gray{
      background: #eeeeee;
  }
    button,input,textarea{
      border: none;
      -webkit-outline: none;
      outline: none;
      -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
      -webkit-appearance: none;
      border-radius: 0;
      padding: 0;
  }
    .fl{
      float: left;
  }
    .fr{
      float: right;
  }
    a{
      color: #474747;
      text-decoration: none;
    }
  a:hover{
    text-decoration: none;
  }
  a:focus{
    text-decoration: none;
  }
  a:active{
    text-decoration: none;
  }

    ul,p,h1,h2,h3,h4,h5,h6{
      padding: 0;
      margin: 0;
  }
    li{
      list-style: none;
  }
    em,i{
      font-style: normal;
  }
    img{
      vertical-align: middle;
  }
    .index-enter-active, .index-leave-active{
      transition: opacity 0.2s;
  }
    .index-enter, .index-leave-active{
      opacity: 0;
  }
</style>
