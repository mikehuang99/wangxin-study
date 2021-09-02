<template>
  <div class="scroll-wrap">
    <div class="scroll-content" :style="{ top }" @mouseenter="Stop()" @mouseleave="Up()">
      <p v-for="(item, index) in newsList" :key="index" >
        <img width="30" height="30" src="img/class_art_icon.png" alt="">
        <!--<a :href="item.src">-->
          <!--<a href="/news/index">-->
          <nuxt-link :to="'/news/index?id='+ item.id">
              <span style="font-size:12px;color: #ef4040">{{item.newsTitle}}[{{item.createTime | fil}}]</span>
            </nuxt-link>
          <!--</a>-->
      </p>
    </div>
  </div>
</template>

<script>
    import {findNewsList} from '~/api/index'

    export default {
        name: 'News.vue',
        data () {
            return {
                activeIndex: 0,
                intnum: undefined,
                newsList: []
            }
        },
        filters:{
          fil(value){
              return value.substring(0,10);
          }
        },
        mounted(){
          this.findNewsList();
        },
        computed: {
            top () {
                return -this.activeIndex * 40 + 'px'
            }
        },
        created () {
            this.ScrollUp()
        },
        methods: {
            findNewsList() {
                findNewsList().then(res => {
                    if (res.data.code === 200) {
                        this.newsList = res.data.data.list || [];
                    } else {
                        console.log('88999！')
                    }
                });
            },
            ScrollUp () {
                this.intnum = setInterval(_ => {
                    if (this.activeIndex < this.newsList.length) {
                        this.activeIndex += 1
                    } else {
                        this.activeIndex = 0
                    }
                }, 2000)
            },
            Stop () {
                clearInterval(this.intnum)
            },
            Up () {
                this.ScrollUp()
            }
        }
    }
</script>
<style>
  .scroll-wrap {
    height: 40px;
    overflow: hidden;
  }
  .scroll-content {
    position: relative;
    transition: top 0.5s;
  }
  .scroll-content p {
    line-height: 40px;
    text-align: left;
    margin-left:10px;
  }
</style>
