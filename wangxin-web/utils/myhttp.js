// 处理token异常
export const myHttp = function ({method, params={}, confirm, cancel}) {
  // console.log(method)
  // console.log(params)
  // console.log(this)
  // console.log(confirm)
  // console.log(cancel)
  // console.log('token处理=======')
  return new Promise((resolve, reject) => {
    method(params).then(res => {
      let result = res.data
      if (result.code === 200) {
        resolve(result)
      } else {
        this.notdata = true
        if (result.code > 300 && result.code < 400) {
          if(result.code == 304){
            this.$msgBox({
              content: '该账号被异地登录',
              isShowCancelBtn: false
            }).then(() => {
              this.$store.dispatch('REDIRECT_LOGIN', result.code)
            }).catch(() => {
              this.$store.dispatch('REDIRECT_LOGIN', result.code)
            })
          }
          this.$msgBox({
            content: '登录超时、异常或者被异地登录，请重新登录',
            isShowCancelBtn: false
          }).then(() => {
            this.$store.dispatch('REDIRECT_LOGIN', result.code)
          }).catch(() => {
            this.$store.dispatch('REDIRECT_LOGIN', result.code)
          })
        } else {
          this.$msgBox({
            content: result.msg,
            isShowCancelBtn: false
          }).then(() => {
            if (confirm) {
              confirm()
            }
          }).catch(() => {
            if (cancel) {
              cancel()
            }
          })
        }
      }
    }).catch(error => {
      this.notdata = true
      this.$msgBox({
        content: '系统可能繁忙，请稍后再重试',
        isShowCancelBtn: false
      }).catch(() => {})
      reject(error)
    })
  })
}
