<template>
  <div class="wx_banner">
    <div class="item">
      <img :src="imgsList[currentIndex]">
    </div>
    <div class="page" v-if="this.imgsList.length > 1">
      <ul>
        <li @click="goPage(preIndex)">&lt;</li>
        <li v-for="(item,index) in imgsList" @click="goPage(index)" :key="item" :class="{'current':currentIndex == index}">
          <!--{{index+1}}-->
          █
        </li>
        <li @click="goPage(nextIndex)">&gt;</li>
      </ul>
    </div>
  </div>
</template>

<script>
    import {findAdverts} from '~/api/index'
    export default {
        name: "Banner",
        data() {
            return{
                //imgsList:["http://atest888.oss-cn-hangzhou.aliyuncs.com/course/0a612fcfbe854421acd054af16055acf.jpg","http://atest888.oss-cn-hangzhou.aliyuncs.com/course/c2884f8a27074215b9577f00924e39df.jpg"],
                imgsList:[],
                currentIndex: 0,   //默认显示图片索引
                timer: null    //定时器
            }
        },
        computed: {
            // eslint-disable-next-line vue/return-in-computed-property
            cancleTimerTask() {
                //取消定时任务
                clearInterval(this.timer);
            },
            //上一张
            preIndex() {
                if(this.currentIndex == 0) {
                    return this.imgsList.length - 1;
                }else{
                    return this.currentIndex - 1;
                }
            },
            //下一张
            nextIndex() {
                if(this.currentIndex == this.imgsList.length - 1) {
                    return 0;
                }else {
                    return this.currentIndex + 1;
                }
            }
        },
        methods: {
            goPage(index) {
                this.currentIndex = index;
            },
            //定时器
            runTimer() {
                this.timer = setInterval(() => {
                    this.goPage(this.nextIndex)
                }, 3000)
            },
            findAdverts() {
                findAdverts().then(res => {
                    if (res.data.code === 200) {
                        let list = res.data.data.list || [];
                        for(let i=0;i<list.length;i++){
                            this.imgsList.push(list[i].advertImg);
                        }
                    } else {
                        console.log('88999！')
                    }
                });
            }
        },
        mounted() {
            this.findAdverts();
            this.runTimer();
        }
    }
</script>

<style scoped>
  * {
    margin: 0;
    padding: 0;
  }
  ul li {
    list-style: none;
    float: left;
    width: 30px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    cursor: pointer;
    color: rgba(255,255,255,.8);
    font-size: 14px;
  }
  .wx_banner {
    width: 100%!important;
    height: 140px!important;
    margin: 0 auto;
    position: relative;
    margin-top: 0px;
  }
  .wx_banner .page {
    background: rgba(0,0,0,.5);
    position: absolute;
    right: 0;
    bottom: 0;
    width: 100%;
  }
  .wx_banner .page ul {
    float: left;
  }
  .wx_banner img {
    width: 100%;
    height: 140px!important;
    display: block;
  }

  .current {
    color: red;
  }

</style>
