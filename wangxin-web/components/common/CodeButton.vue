<template>
  <button class="yzm_btn" type="button" :disabled="disabled" @click="getCode">{{txt}}</button>
</template>
<script>
import {getMobileCode} from '~/api/user.js'
export default {
  props: {
    mobile: {
      // 手机号
      type: [String, Number],
      default: 1
    },
      randomKey: {
        //随机字符串
          type: [Object, String],
          default: 1
      },
      verifyCode: {
          // 图片验证码
          type: [Object, String],
          default: 1
      }
  },
  data () {
    return {
      txt: '获取验证码',
      disabled: false
    }
  },
  computed: {
    // ...mapState(['clientData'])
  },
  methods: {
    getCode (event) {
        if(!this.randomKey){
            this.$msgBox({
                content: '随机字符串为空，程序异常',
                isShowCancelBtn: false
            })
            return false;
        }
      if (!/^1[2|3|4|5|6|7|8|9][0-9]\d{8}$/.test(parseInt(this.mobile))) {
              this.$msgBox({
          content: '请输入正确手机号码',
          isShowCancelBtn: false
        })
        return false;
      }
      if(!this.verifyCode){
          this.$msgBox({
              content: '请输入图片验证码',
              isShowCancelBtn: false
          })
          return false;
      }
      this.$emit('cb', event);
      getMobileCode({
        clientId: this.$store.state.clientData.id,
        mobile: this.mobile,
          verifyCode: this.verifyCode,
          randomKey: this.randomKey
      }).then(res => {
        if (res.data.code === 200) {
          this.timeOut();
        } else {
          this.$msgBox({
            content: res.data.msg,
            isShowCancelBtn: false
          })
        }
      })
    },
    timeOut () {
      this.disabled = true;
      let num = 60;
      this.txt = num;
      let nt = setInterval(() => {
        if (num <= 0) {
          clearInterval(nt);
          this.txt = '重新获取';
          this.disabled = false;
        } else {
          num--;
          this.txt = num + 's';
        }
      }, 1000)
    }
  }
}
</script>
<style lang="scss" scoped>
  button{
    line-height: 22px;
    padding: 0;
    width: 100px;
    background-color: blue;
    border: 1px solid blue;
    color: #fff;
    border-radius: 0px;
    &:disabled{
      background-color: #ccc;
      border-color: #ddd;
      color: #aaa;
    }
  }
</style>
