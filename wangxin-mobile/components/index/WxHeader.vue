<template>
  <header id="header">
		<nuxt-link v-show="this.isNotlogin" to="/login" class="head_login">登录</nuxt-link>
    <nuxt-link v-show="this.isLogin" to="/my/myInfo" class="head_login">{{realName|fil}}</nuxt-link>
		<div class="input_pad word">
			<input type="text" @click="search()" v-model="courseName" class="index_search" placeholder="搜索：课程名称">
		</div>
	</header>
</template>
<script>
import {getStore} from '../../store/common.js'
import VueCookie from 'vue-cookie'

export default {
  data () {
    return {
      isNotlogin: true,
      isLogin: false,
      realName : '',
        courseName: '',
        userInfo: ''

    }
  },
    filters:{
        fil(value){
            return value.substring(0,6);
        }
    },
  methods: {
    search () {
      this.$router.push({path: '/courseAllList', query: {courseName: this.courseName}});
    }
  },
  mounted () {
      this.userInfo= JSON.parse(VueCookie.get("wangxinMobileUserInfo"));
    if (this.userInfo) {
      this.isNotlogin = false
      this.isLogin = true
      this.realName = this.userInfo.realName;
    }
  }
}
</script>
<style lang="sass">
	header
		padding-top: 5px
		.input_pad
			&.word
				margin-left: 10px
				margin-right: 110px
			margin: 5px 70px 0 88px
		.index_search
			width: 100%
			height: 25px
			font-size: 12px
			line-height: 25px
			color: #fff
			border-radius: 10px
			background: url(/wxmobile/img/index_header_search.png) no-repeat 7px center #a20407
			background-size: 14px 14px
			&::-webkit-input-placeholder
				color: #fff
			:-moz-placeholder
				color: #fff
			::-moz-placeholder
				color: #fff
			:-ms-input-placeholder
				color: #fff
			padding: 5px 0 5px 26px
		.head_login
			width: 90px
			height: 33px
			line-height: 33px
			text-align: right
			font-size: 15px
			color: #fff
			float: right
			margin: 2px 13px 0 0
			&.login
				background: url(/wxmobile/img/index_login.png) no-repeat 14px center
				background-size: 12px 15px
</style>
