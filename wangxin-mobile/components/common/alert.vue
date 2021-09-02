<template>
  <div class="alert_contain">
    <div class="alert_top">
      {{alertTitle}}
    </div>
    <span class="alert_content">
       {{alertContent}}
      <br/>
      <span v-if="timeOutFlag">{{timeOutTxt}}</span>
    </span>
    <div class="alert_bottom">
      <span class="btn_box" @click="closeAlert">确定</span>
    </div>
  </div>
</template>

<script>
    export default {
        props: {
            alertTitle: {
                type: [Object, String],
                default: ''
            },
            alertContent: {
                type: [Object, String],
                default: ''
            },
            timeOutFlag: {//timeOutFlag和toUrl必须配合使用，当timeOutFlag为真的时候，跳转到toUrl页面
                type: [Object, Boolean],
                default: false
            },
            toUrl: {
                type: [Object, String],
                default: ''
            },
            toUrlParams: {
                type: Array,
                default: () => []
                // 也可以default: function () { return [] }
            }
        },
        name: "alert",
        data () {
            return {
                alert: '',
                timeOutTxt: ''
            }
        },
        mounted(){
            this.timeOut();
        },
        methods: {
            closeAlert(){
                if(this.timeOutFlag){
                    this.$router.push({path: this.toUrl, query:this.toUrlParams})
                }else {
                    this.$emit('closeAlert');
                }
            },
            timeOut () {
                let maxSeconds = 5;
                let timeOutJob = setInterval(() => {
                    if (maxSeconds <= 0) {
                        clearInterval(timeOutJob);
                    } else {
                        maxSeconds--;
                        this.timeOutTxt = maxSeconds + 's后将关闭本提示';
                        if(this.timeOutFlag){
                            this.timeOutTxt = this.timeOutTxt + '并跳转';
                        }
                        if(this.timeOutFlag && maxSeconds==0){
                                this.$router.push({path: this.toUrl, query: this.toUrlParams})
                        }
                    }
                }, 1000)
            },
        }
    }
</script>

<style scoped>
  .alert_contain{
    border-radius: 20px;
    background-color: lightgrey;
    width: 100%;
    height: 200px;
    position: fixed;
    left: 0px;
    top: 30%;
    overflow-y: auto;
    padding-left: 17px;
    padding-right: 17px;
    z-index: 9999999999;
  }
  .alert_top{
    text-align: center;
    font-size: 16px;
    font-weight: bold;
  }
  .alert_content{
    text-align: center;
    font-size: 16px;
    letter-spacing:1px;
    color: orangered;
  }
  .alert_bottom_finish{
    font-size: 18px;
    font-weight: bold;
    float: left;
    margin-top: 20px;
    bottom: 20px;
  }
  .alert_bottom{
    margin-top: 20px;
    text-align: center;
  }
  .alert_bottom_content{
    text-decoration: underline;
    font-size: 18px;
    font-weight: bold;
  }
  .btn_box {
    border-radius: 20px;
    width: 100px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    margin: 4px 2px;
    cursor: pointer;
  }
</style>
