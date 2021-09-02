<template>
  <div class="" :class="{on: open}">
    <div class="mask" @click="close()"></div>
    <div class="modal_panel image_panel" id="image_panel_importance">
      <div class="modal_head">
        <span class="fl">选择参考对象-分组</span>
        <a href="javascript:" @click="close()" class="close iconfont">关闭</a>
      </div>
      <div class="modal_body">
        <div class="upload_box clearfix">
          <span>分组名称：</span>
          <input type="text" v-model="pda.groupName" style="height: 30px;width: 120px" placeholder="请输入分组名称">
          <div class="fr">
            <div @click="getStudentGroupList"  class="fr solid_btn">
              查询
            </div>
          </div>
        </div>
        <div id="questionTitle" style="height:380px;width:710px;overflow:scroll;background:#EEEEEE;">
          <tr v-for="(item,index) in studentGroupList" :key="item.id">
            <td style="height: 20px">
              <input type="checkbox" :value="item.id" v-model="studentGroupIds" style="margin-left:20px;width:20px;height:20px;">
              <span style="margin-left:5px;width:20px;height:20px;">{{item.groupName}}</span>
            </td>
          </tr>
        </div>
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
    import {studentGroupList} from '~/api/account/course.js'

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
            },
            studentGroupIdsParent: {
                type: [Object,Array],
                default: null
            }
        },
        data() {
            return {
                pda: {
                    pageCurrent: '-1',
                    pageSize: '-1',
                    groupName:''
                },
                aa:this.content.testId,
                editorContent:null,
                studentGroupList:null,
                studentGroupIds:[]
            }
        },
        methods: {
            ok() {
                this.$emit("slectedGroupIdsFun",this.studentGroupIds);
                this.studentGroupNames = [];
                for(let i=0;i<this.studentGroupList.length;i++){
                    for(let k=0;k<this.studentGroupIds.length;k++){
                        if(this.studentGroupIds[k]==this.studentGroupList[i].id){
                            this.studentGroupNames.push(this.studentGroupList[i].groupName);
                        }
                    }
                }
                console.log(this.studentGroupNames)
                this.$emit("slectedGroupNamesFun",this.studentGroupNames);

                this.$emit("testfun",this.studentGroupIds);
                this.$emit('hidefun', event);
            },
            close() {
                this.$emit("testfun",this.editorContent)
                this.$emit('hidefun', event);
            },
            getStudentGroupList() {
                this.pda.pageCurrent = -1;
                this.pda.pageSize = -1;
                studentGroupList(this.pda).then(res => {
                    let result = res.data
                    console.log(result)
                    console.log('course----')
                    if (result.code === 200 ) {
                        if(result.data.list.length==0){
                            this.queryState = false;
                            this.$msgBox({
                                content: '没有数据！！',
                                isShowCancelBtn: false
                            })
                        }
                        this.studentGroupList = result.data.list;

                    } else if (result.code > 300 && result.code < 400) {
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
                        this.notdata = true;
                        this.pageObj.totalPage = 0;
                    }
                    this.queryState = false;
                }).catch(msg => {
                    this.queryState = false;
                    this.$msgBox({
                        content: '加载数据失败，请稍后刷新再试！！',
                        isShowCancelBtn: false
                    })
                })
            }
        },
        mounted() {
            this.getStudentGroupList();

            console.log("=======>>>>>>"+this.content.studentGroupIds.length);

            for(let i=0;i<this.content.studentGroupIds.length;i++){
                this.studentGroupIds.push(this.content.studentGroupIds[i]);
            }
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
