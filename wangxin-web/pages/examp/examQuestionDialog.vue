<template>
  <div class="" :class="{on: open}">
    <div class="mask" @click="close()"></div>
    <div class="modal_panel image_panel" id="image_panel_importance">
      <div class="modal_head">
        <span class="fl">编辑</span>
        <a href="javascript:" @click="close()" class="close iconfont">关闭</a>
      </div>
      <div class="modal_body">
        <div class="upload_box clearfix">
          <span>{{dialogTitle}}。<span style="color: red">【注：内容不要出现<>等html标记元素，如&ltdiv&gt等，系统会自动过滤】</span></span>
          <div class="fr">
            <span></span>
          </div>
        </div>
        <div id="questionTitle"></div>
      </div>
      <div class="modal_foot">
        <button class="solid_btn" @click="ok()">确定</button>
        <button class="hollow_btn" @click="close()">取消</button>
      </div>
    </div>
  </div>
</template>
<script>
    import {uploadPic} from '~/api/upload.js'
    export default {
        props: {
            content: {
                type: [Object, String],
                default: null
            },
            open: {
                type: Boolean,
                default: true
            },
            data: {
                type: [Object, String],
                default: null
            },
            type: {
                // 1问题图片，2问题答案，3习题图片，4习题答案
                type: [String, Number],
                default: 1
            }
        },
        data() {
            return {
                dialogTitle:this.content.dialogTitle,
                dialogType:this.content.dialogType,
                editorContent:this.content.editorContent
            }
        },
        methods: {
            close() {
                console.log("txt:::::"+this.editorQuestionTitle.txt.html())
                this.$emit('hidefun', event);
            },
            ok() {
                console.log("txt:::::"+this.editorQuestionTitle.txt.html())
               // this.$emit("testfun",this.editorContent)
                //this.editorContent = this.editorContent.substring(3,this.editorContent.length-4);
                this.$emit('hidefun', event);
                if('questionTitle'==this.dialogType) {
                    this.$emit("questionTitleFun", this.editorContent);
                }
                if('itemA'==this.dialogType) {
                    this.$emit("itemAFun", this.editorContent);
                }
                if('itemB'==this.dialogType) {
                    this.$emit("itemBFun", this.editorContent);
                }
                if('itemC'==this.dialogType) {
                    this.$emit("itemCFun", this.editorContent);
                }
                if('itemD'==this.dialogType) {
                    this.$emit("itemDFun", this.editorContent);
                }
                if('itemE'==this.dialogType) {
                    this.$emit("itemEFun", this.editorContent);
                }
                if('itemF'==this.dialogType) {
                    this.$emit("itemFFun", this.editorContent);
                }
                if('itemG'==this.dialogType) {
                    this.$emit("itemGFun", this.editorContent);
                }
                if('itemH'==this.dialogType) {
                    this.$emit("itemHFun", this.editorContent);
                }
                if('analysis'==this.dialogType) {
                    this.$emit("analysisFun", this.editorContent);
                }
                if('customLabel'==this.dialogType) {
                    this.$emit("customLabelFun", this.editorContent);
                }
            },
            // 编辑器上传图片
            editorUpload (files, insert) {
                console.log(files)
                let file = files[0];
                let param = new FormData();
                param.append('picFile', file, file.name);
                this.$nuxt.$loading.start()
                uploadPic(param, function (int) {
                    console.log(int)
                }).then(res => {
                    this.$nuxt.$loading.finish()
                    console.log(res)
                    console.log('load=======')
                    if (res.code === 200) {
                        let imgUrl = res.data
                        insert(imgUrl)
                    } else {
                        this.$msgBox({
                            content: res.msg,
                            isShowCancelBtn: false
                        })
                    }
                }).catch(() => {
                    this.$msgBox({
                        content: '上传图片出错，请稍后重试',
                        isShowCancelBtn: false
                    })
                })
            }

        },
        mounted() {
            let EE = require('wangeditor')
            this.editorQuestionTitle = new EE('#questionTitle')
            this.editorQuestionTitle.customConfig.uploadImgMaxLength = 1
            this.editorQuestionTitle.customConfig.customUploadImg = this.editorUpload
            this.editorQuestionTitle.customConfig.onchange = (html) => {
                console.log(html);
                this.editorContent = html
            }


            this.editorQuestionTitle.create();

            this.editorQuestionTitle.txt.html(this.editorContent);
        }
    }
</script>
<style lang="scss" rel="stylesheet/scss">
  #image_panel_importance {
    width: 720px;
    margin-left: -360px;
  }

  .image_panel {
    width: 720px;
    margin-left: -360px;

    .modal_body {
      overflow: hidden;
    }

    .upload_box {
      line-height: 36px;
      padding: 10px 20px;
      border-bottom: 1px solid #e7e7eb;

      span {
        margin-right: 12px;
        color: #666;
      }
    }

    .modal_foot {
      border-top: 1px solid #e7e7eb;
      padding: 20px;
      text-align: center;
    }

    .image_list {
      overflow: hidden;
      height: 403px;

      .title {
        line-height: 30px;
        padding: 0 20px;
        font-size: 14px;
        background-color: #eee;
      }

      .left_list {
        float: left;
        width: 187px;
        border-right: 2px solid #ddd;
      }

      .right_list {
        float: right;
        width: 531px;
      }

      ul {
        height: 373px;
        overflow: auto;
      }

      .icon {
        text-align: center;
        line-height: 80px;
        font-size: 50px;
        color: #999;
      }

      .img {
        position: relative;
        float: left;
        margin-left: 16px;
        margin-top: 16px;
        width: 153px;
        border: 1px solid #e7e7eb;

        .oper {
          display: none;
        }

        .gb {
          position: absolute;
          right: -10px;
          top: -10px;
          font-size: 18px;
          cursor: pointer;
          color: #666;
        }

        img {
          display: block;
          height: 80px;
        }

        a {
          display: block;
          text-align: center;
          line-height: 30px;
          border-top: 1px solid #e7e7eb;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }

        .sel {
          color: #eee;
          margin: 0 3px;
        }

        &:hover {
          border-color: #ed7d03;

          .oper {
            display: block;
          }

          .icon {
            color: #ed7d03;
          }
        }
      }

      .tip {
        text-align: center;
        font-size: 16px;
        line-height: 60px;
      }

      .progress, .tip, .oper {
        position: absolute;
        left: 0;
        top: 0;
        right: 0;
        z-index: 9;
        height: 80px;

        .entity {
          height: 100%;
          background-color: rgba(0, 204, 0, 0.59);
        }
      }
    }
  }

  .max_img_panel {
    position: relative;
    z-index: 1000;

    .img_panel {
      display: table;
      position: fixed;
      z-index: 999;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
    }

    .img_box {
      text-align: center;
      display: table-cell;
      vertical-align: middle;
    }

    .img {
      display: inline-block;
      position: relative;
      max-width: 80%;

      i {
        position: absolute;
        right: 0;
        top: 0;
        z-index: 9;
        font-size: 30px;
        cursor: pointer;

        &:hover {
          color: #e00;
        }
      }
    }
  }
</style>
