<template>
  <div class="middle_nav_zone" id="middle_nav_zone">
			<ul class="middle_nav_link">
        <li v-for="(value, index) in indexMiddleNavs" :key="index">
        <nuxt-link v-if="value.mmnUrlType === 1"  :to="value.mmnUrl" :data-id="value.id">
						<img width="40" :src="value.mmnIco" alt="" />
					</nuxt-link>
          <a v-if="value.mmnUrlType === 2" :href="value.mmnUrl">
            <img width="40" :src="value.mmnIco" alt="" />
          </a>
          <p style="font-weight: bold;font-size: 14px">{{value.mmnName}}</p>
        </li>
			</ul>
		</div>
</template>
<script>
import {findIndexMiddleNavs} from '~/api/index'

export default {
    data() {
        return {
            indexMiddleNavs: []
        }
    },
  computed: {

  },
  methods: {
      findIndexMiddleNavs() {
          findIndexMiddleNavs({}).then(res => {
              if (res.data.code === 200) {
                  this.indexMiddleNavs = res.data.data.list || [];
              } else {
                  console.log('请求错误')
              }
          });
      }
  },
    mounted() {
        this.findIndexMiddleNavs();
    }
}
</script>
<style lang="sass">
  .middle_nav_zone
     position: relative
     .menu_link
        overflow: hidden
        position: absolute
        left: 50%
        bottom: 6px
        margin-left: -12px
        li
          width: 6px
          height: 6px
          background: url(/wxmobile/img/banner03.png) no-repeat 0 0
          float: left
          margin: 0 6px 0 0
          &.on
            background-image: url(/wxmobile/img/banner01.png)
  .middle_nav_link
    man-height: 16rem
    overflow: hidden
    background: #fff
    padding: 1.3rem 0 0 0
    li
      width: 25%
      float: left
      text-align: center
      margin: 0 0 1.3rem 0
      line-height: 1.5rem
      a
        display: block
      p
        font-size: 1rem
        color: #484848
        margin: 7px 0 0 0
</style>
