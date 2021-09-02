<template>
    <div class="xieyi_contain">
      <div class="xieyi_top">
        用户协议
      </div>
      <span class="xieyi_content">
      1．特别提示
      　　1．1　_________媒体（以下简称_________）同意按照本协议的规定及其不时发布的操作规则提供基于互联网的相关服务（以下称网络服务），为获得网络服务，服务使用人（以下称用户）应当同意本协议的全部条款并按照页面上的提示完成全部的注册程序。用户在进行注册程序过程中点击同意按钮即表示用户完全接受本协议项下的全部条款。

      　　2．服务内容
      　　2．1　_________网络服务的具体内容由_________根据实际情况提供，例如论坛（BBS）、聊天室、电子邮件、发表新闻评论等。_________保留随时变更、中断或终止部分或全部网络服务的权利。
      　　2．2　_________在提供网络服务时，可能会对部分网络服务（例如电子邮件）的用户收取一定的费用。在此情况下，_________会在相关页面上做明确的提示。如用户拒绝支付该等费用，则不能使用相关的网络服务。
      　　2．3　用户理解，_________仅提供相关的网络服务，除此之外与相关网络服务有关的设备（如电脑、调制解调器及其他与接入互联网有关的装置）及所需的费用（如为接入互联网而支付的电话费及上网费）均应由用户自行负担。

      　　3．使用规则
      　　3．1　用户在申请使用_________网络服务时，必须向_________提供准确的个人资料，如个人资料有任何变动，必须及时更新。
        </span>
      <span class="xieyi_bottom_finish">全文完</span>
      <div class="xieyi_bottom">
        <span class="xieyi_bottom_content" @click="close">点击这里关闭窗口</span>
      </div>
    </div>
</template>

<script>
    export default {
        name: "xieyi",
        data () {
            return {
                xieyi: ''
            }
        },
        methods: {
            close(){
                this.$parent.isShowXieyi = false;
            }
        }
    }
</script>

<style scoped>
.xieyi_contain{
  background-color: aliceblue;
  width: 100%;
  height: 400px;
  position: fixed;
  left: 0px;
  top: 150px;
  overflow-y: auto;
  padding-left: 17px;
  padding-right: 17px;
  z-index: 2000;
}
.xieyi_top{
  text-align: center;
  font-size: 16px;
  font-weight: bold;
}
.xieyi_content{
  font-size: 16px;
  letter-spacing:1px;
}
.xieyi_bottom_finish{
  font-size: 18px;
  font-weight: bold;
  float: left;
  margin-top: 20px;
}
.xieyi_bottom{
  margin-top: 20px;
  text-align: center;
}
.xieyi_bottom_content{
  text-decoration: underline;
  font-size: 18px;
  font-weight: bold;
}
</style>
