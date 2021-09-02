<template>
  <div class="" :class="{on: open}">
    <div class="mask" @click="close()">关闭</div>
    <div class="modal_panel image_panel" id="image_panel_importance">
      <div class="modal_head">
        <span class="fl">增加学习卡</span>
        <a href="javascript:" @click="close()" class="close iconfont">关闭</a>
      </div>
      <div class="modal_body" style="margin-left: 30px;">
        <br/>
        <div class="form_group">
          <div style="display: inline;font-size: 20px">卡号:</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text" v-model="cardNo" class="form_input_min" placeholder="请输入卡号"><span style="color: red">*</span>
          </div>
        </div>
        <br/>
        <div class="form_group">
          <div style="display: inline;font-size: 20px">卡密:</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            <input type="text"  v-model="cardPsw" class="form_input_min" style="width: 300px" placeholder="请输入卡密"><span style="color: red">*</span>
          </div>
        </div>
        <div class="form_group">
          <div style="display: inline;font-size: 20px;color: red">提示:</div>
          <div class="form_ctl" style="display: inline;margin-left: 20px">
            新增学习卡有可能按照学习卡要求扣减一定的积分，具体参看你学习卡上的说明
          </div>
        </div>
      </div>
      <div class="modal_foot">
        <button class="solid_btn" @click="submit">确定</button>
        <button class="hollow_btn" @click="close()">取消</button>
      </div>
    </div>
  </div>
</template>
<script>
import {bindStudyCardUserNo,checkBeforebindStudyCardUserNo} from '~/api/account/user.js'

export default {
    components: {

    },
  props: {
    open: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      cardNo: '',
      cardPsw: '',
      flag: true,
      kg: true,
      upbtn: true,
      chapterVideoList: [],
      videoList: [],
      uploadList: []
    }
  },
  methods: {
    close () {
        this.$emit('hidefun', event);
    },
    btnClick (event) {
      this.$emit('btnClick', event);
    },
    changeTab (int) {
      this.$emit('change', int);
    },
    submit(){
        checkBeforebindStudyCardUserNo({
            'cardNo': this.cardNo,
            'cardPsw': this.cardPsw
        }).then(res => {
            res = res.data;
            if (res.code === 200) {
                this.$msgBox({
                    content: '一旦新增成功将扣除积分'+res.data.valuePoint+'，确定新增这张学习卡？',
                    isShowCancelBtn: true
                }).then(() => {
                    this.bindStudyCardUserNo();
                }).catch(() => {
                    //点击取消，什么也不做
                });
            } else {
                if (res.code >= 300 && res.code < 400) {
                    if(res.code == 304){
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
                        content: res.msg,
                        isShowCancelBtn: false
                    }).then(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                    }).catch(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                    })
                } else {
                    this.$msgBox({
                        content: res.msg,
                        isShowCancelBtn: false
                    }).catch(() => {})
                }
            }
        }).catch(() => {

        })
    },
    bindStudyCardUserNo(){
        bindStudyCardUserNo({
            'cardNo': this.cardNo,
            'cardPsw': this.cardPsw
        }).then(res => {
            res = res.data;
            if (res.code === 200) {
                this.$emit('hidefun', event);
                this.$emit('getStudyCardList', event);
            } else {
                if (res.code >= 300 && res.code < 400) {
                    if(res.code == 304){
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
                        content: res.msg,
                        isShowCancelBtn: false
                    }).then(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                    }).catch(() => {
                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                    })
                } else {
                    this.$msgBox({
                        content: res.msg,
                        isShowCancelBtn: false
                    }).catch(() => {})
                }
            }
        }).catch(() => {

        })
    }
  },
  mounted () {
    this.kg = false;

  },
}
</script>
<style lang="scss" rel="stylesheet/scss">
#image_panel_importance {
  width: 720px;
  margin-left: -360px;
}
.image_panel{
  width: 720px;
  margin-left: -360px;
  .modal_body{
    overflow: hidden;
  }
  .upload_box{
    line-height: 36px;
    padding: 10px 20px;
    border-bottom: 1px solid #e7e7eb;
    span{
      margin-right: 12px;
      color: #666;
    }
  }
  .modal_foot{
    border-top: 1px solid #e7e7eb;
    padding: 20px;
    text-align: center;
  }
  .image_list{
    overflow: hidden;
    height: 403px;
    .title{
      line-height: 30px;
      padding: 0 20px;
      font-size: 14px;
      background-color: #eee;
    }
    .left_list{
      float: left;
      width: 187px;
      border-right: 2px solid #ddd;
    }
    .right_list{
      float: right;
      width: 531px;
    }
    ul{
      height: 373px;
      overflow: auto;
    }
    .icon{
      text-align: center;
      line-height: 80px;
      font-size: 50px;
      color: #999;
    }
    .img{
      position: relative;
      float: left;
      margin-left: 16px;
      margin-top: 16px;
      width: 153px;
      border: 1px solid #e7e7eb;
      .oper{
        display: none;
      }
      .gb{
        position: absolute;
        right: -10px;
        top: -10px;
        font-size: 18px;
        cursor: pointer;
        color: #666;
      }
      img{
        display: block;
        height: 80px;
      }
      a{
        display: block;
        text-align: center;
        line-height: 30px;
        border-top: 1px solid #e7e7eb;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .sel{
        color: #eee;
        margin: 0 3px;
      }
      &:hover{
        border-color: #ed7d03;
        .oper{
          display: block;
        }
        .icon {
          color: #ed7d03;
        }
      }
    }
    .tip{
      text-align: center;
      font-size: 16px;
      line-height: 60px;
    }
    .progress,.tip,.oper{
      position: absolute;
      left: 0;
      top: 0;
      right: 0;
      z-index: 9;
      height: 80px;
      .entity{
        height: 100%;
        background-color: rgba(0, 204, 0, 0.59);
      }
    }
  }
}
.form_input_min{
  text-indent: 1em;
  width: 130px;
  height: 34px;
  margin-right: 10px;
  border: 1px solid #e7e7e7;
}
.form_ctl_date{
  //margin-left: 12px;
  //float: left;
  width: 700px;
  //position: relative;
}
.max_img_panel{
  position: relative;
  z-index: 1000;
  .img_panel{
    display: table;
    position: fixed;
    z-index: 999;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
  }
  .img_box{
    text-align: center;
    display: table-cell;
    vertical-align: middle;
  }
  .img{
    display: inline-block;
    position: relative;
    max-width: 80%;
    i{
      position: absolute;
      right: 0;
      top: 0;
      z-index: 9;
      font-size: 30px;
      cursor: pointer;
      &:hover{
        color: #e00;
      }
    }
  }
}
</style>
