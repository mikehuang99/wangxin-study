<template>
  <div>
    <nuxt/>
  </div>
</template>

<script>
    import VueCookie from 'vue-cookie'



    export default {
  head () {
    return {
      link: [
       // { rel: 'icon', type: 'image/x-icon', href: this.$store.state.webInfo.faviconIcoUrl }
      ]
    }
  },
  created () {
    console.log("每次刷新都加载。。。。。。default.vue")
    if (process.client) {
        console.log("触发初始化...")

      this.$store.commit('INIT_WEB');
      if (!!VueCookie.get("wangxinToken")) {
        if (!JSON.parse(VueCookie.get("wangxinUserInfo")) || VueCookie.get("wangxinToken") != JSON.parse(VueCookie.get("wangxinUserInfo")).token) {
            console.log("再次加载用户")
          this.$store.dispatch('GET_USERINFO');
        }
      }
    }
  },
  mounted () {

  }
}

</script>
<style>
html {
  font-family: 'Source Sans Pro', -apple-system, BlinkMacSystemFont, 'Segoe UI',
    Roboto, 'Helvetica Neue', Arial, sans-serif;
  font-size: 16px;
  word-spacing: 1px;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  box-sizing: border-box;
}

*,
*:before,
*:after {
  box-sizing: border-box;
  margin: 0;
}

.button--green {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #3b8070;
  color: #3b8070;
  text-decoration: none;
  padding: 10px 30px;
}

.button--green:hover {
  color: #fff;
  background-color: #3b8070;
}

.button--grey {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #35495e;
  color: #35495e;
  text-decoration: none;
  padding: 10px 30px;
  margin-left: 15px;
}

.button--grey:hover {
  color: #fff;
  background-color: #35495e;
}
</style>
