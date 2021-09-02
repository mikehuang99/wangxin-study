<template>
  <div  align="center">
    <div style="border: 2px solid red;width: 550px;margin-top: 100px;" align="center">
      <div style="width: 100%;width: 540px;height: 35px;padding-top: 10px">
        <span style="font-size: 30px;font-weight: bold">学习证明</span>
      </div>
      <div style="margin-top: 30px">
        <hr>
      </div>
      <div style="width: 500px;padding-left: 0px;margin-top: 20px">
        <div style="float: left;">
          <div>
            <div style="display: inline-block;">
              <div style="float:left;display: inline-block;;width: 120px;height:30px;text-align:left">
                <span style="font-weight: bold;font-size: 20px">姓&#12288;&#12288;名</span>
              </div>
              <div style="display: inline-block;;width: 256px;height:30px;text-align:left">
                <span style="font-size: 14px">{{studyProof.realName}}</span>
              </div>
              <!--
              <div style="display: inline-block;;width: 120px;height:30px;text-align:left">
                <span style="font-weight: bold;font-size: 20px">性别</span>
              </div>
              <div style="display: inline-block;;width: 64px;height:30px;text-align:left">
                <span style="font-size: 14px">男</span>
              </div>-->
            </div>
          </div>
          <div>
            <div style="display: inline-block;;">
              <div style="display: inline-block;;width: 120px;height:30px;text-align:left">
                <span style="font-weight: bold;font-size: 20px">性&#12288;&#12288;别</span>
              </div>
              <div style="display: inline-block;width: 256px;height:30px;text-align:left">
                <span style="font-size: 14px" v-if="studyProof.sex==1">男</span>
                <span style="font-size: 14px" v-if="studyProof.sex==2">女</span>
                <span style="font-size: 14px" v-if="studyProof.sex==3">保密</span>
              </div>
            </div>
          </div>
          <div>
            <div style="display: inline-block;;">
              <div style="display: inline-block;;width: 120px;height:30px;text-align:left">
                <span style="font-weight: bold;font-size: 20px">年&#12288;&#12288;龄</span>
              </div>
              <div style="display: inline-block;width: 256px;height:30px;text-align:left">
                <span style="font-size: 14px">{{studyProof.age}}</span>
              </div>
            </div>
          </div>
          <div>
            <div style="display: inline-block;;">
              <div style="display: inline-block;;width: 120px;height:30px;text-align:left">
                <span style="font-weight: bold;font-size: 20px">手机号码</span>
              </div>
              <div style="display: inline-block;width: 256px;height:30px;text-align:left">
                <span style="font-size: 14px">{{studyProof.mobile}}</span>
              </div>
            </div>
          </div>
            <div>
              <div style="display: inline-block;;">
                <div style="display: inline-block;width: 120px;height:30px;text-align:left">
                  <span style="font-weight: bold;font-size: 20px">身份证号</span>
                </div>
                <div style="display: inline-block;width: 256px;height:30px;text-align:left">
                  <span style="font-size: 14px">{{studyProof.identityCardNo}}</span>
                </div>
              </div>
          </div>
        </div>
        <div style="float: right;width: 100px;height: 130px">
          <div style="height:100%" class="imgClass">
            <img :src="studyProof.headImgUrl"/>
          </div>
        </div>
      </div>
      <div style="margin-top: 213px">
          <hr>
      </div>
      <div style="width: 100%;width: 520px;height: 35px;margin-top: 5px">
        <span style="font-size: 20px;font-weight: bold">学习情况</span>
      </div>
      <div style="margin-top: 5px">
        <hr>
      </div>
      <div style="width: 100%;width: 520px;margin-top: 5px" v-for="(studyProofCourseVo,indexStudyProofCourseVo) in studyProof.studyProofCourseVos" :key="studyProofCourseVo.id">
        <div>
          <div style="float:left;">
            <div style="width:350px;font-size: 15px;overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">
              {{studyProofCourseVo.courseName}}
            </div>
          </div>
          <div style="float: right">
            <span style="font-size: 15px;">完成{{studyProofCourseVo.totalStudyProgress}}%</span>
          </div>
        </div>
        <br/>
        <hr>
      </div>
      <div>
        <div  style="float: left;width: 60px;height: 80px;margin-left:20px;margin-top: 40px">
          <div style="height:60px">
            <canvas id="qrCodeImg"></canvas>
            <!--
            <img src="https://atest888.oss-cn-hangzhou.aliyuncs.com/course/6f5188582fd344bbaa3166fd153f8e11.png"/>
            -->
          </div>
        </div>
        <div style="margin-left: 200px">
          <canvas id="canvas" width="200" height="200">
          </canvas>
        </div>
      </div>
      <div style="margin-left: 300px">
        <span style="font-size: 20px">{{studyProof.proofDate}}</span>
      </div>
    </div>
    <div style="margin-bottom: 40px">

    </div>
  </div>
</template>

<script>
    import qrCode from 'qrcode'
    import { viewStudyProof} from '~/api/user.js'

    export default {
        name: "learnProof",
        components: {
            QRCode: qrCode
        },
        data(){
            return {
                id:'',
                studyProof: {
                    "proofUserNo": "",
                    "realName": "加载中......",
                    "sex": '0',
                    "headImgUrl": "",
                    "mobile": '加载中......',
                    "identityCardNo": '加载中......',
                    "age": '加载中......',
                    "proofDate": '加载中......',
                    "mainSealContent": '',
                    "secondSealContent": '',
                    "studyProofCourseVos":[
                        {"id":'0',"courseName":"加载中......","totalStudyProgress":"0"}
                     ]
                }
            }
        },
        mounted(){
            this.id = this.$route.query.id;
            this.drawSeal();
            this.viewStudyProof();
            let that = this;
            setTimeout(function () {
                let currentUrl = window.location.href;
                that.generalQrCodeImg(currentUrl);
            }, 1000);

        },
        methods: {
            viewStudyProof(){
                viewStudyProof({"id":this.id}).then(res => {
                    let result = res.data
                    if (result.code === 200) {
                        this.studyProof = result.data;
                    } else {
                        if (result.code >= 300 && result.code < 400) {
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
                            }).catch(() => {})
                        }
                    }
                }).catch(() => {
                })
            },
            generalQrCodeImg(url){
                let canvas = document.getElementById('qrCodeImg')
                qrCode.toCanvas(canvas, url, function (error) {
                    if (error) console.error(error)
                    console.log('general qrCode success!');
                })
            },
            doPrint(){
                let bdhtml=window.document.body.innerHTML;
                let sprnstr="<!--startprint-->";
                let eprnstr="<!--endprint-->";
                let prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
                prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
                window.document.body.innerHTML=prnhtml;
                window.print();
                location.reload();
            },
            //画五角星
            /**
             * 画一个五角星形状. 该五角星的中心坐标为(sx,sy)。
             * 中心到顶点的距离为radius。、
             * rotate=0时一个顶点在对称轴上，rotate:绕对称轴旋转rotate弧度，即旋转度
             */
             draw5star(sx, sy, radius, color, rotato,canvasContext) {
                canvasContext.save();
                canvasContext.fillStyle = color;
                canvasContext.translate(sx, sy);//移动到坐标原点
                canvasContext.rotate(Math.PI + rotato);//旋转
                canvasContext.beginPath();//创建路径
                let x = Math.sin(0);
                let y = Math.cos(0);
                let dig = Math.PI / 5 * 4;
                for (let i = 0; i < 5; i++) {//画五角星的五条边
                    let x = Math.sin(i * dig);
                    let y = Math.cos(i * dig);
                    canvasContext.lineTo(x * radius, y * radius);
                }
                canvasContext.closePath();
                canvasContext.stroke();
                canvasContext.fill();
                canvasContext.restore();
            },
            drawSeal(){
                let canvas = document.getElementById("canvas");
                let canvasContext = canvas.getContext('2d');
                let secondSealContent = "证明专用章";
                let mainSealContent = "XX市第一中学";

                // 画印章边框
                let width = canvas.width / 2;
                let height = canvas.height / 2;
                canvasContext.lineWidth = 5;
                canvasContext.strokeStyle = "#FF0000";
                canvasContext.beginPath();
                canvasContext.arc(width, height, 90, 0, Math.PI * 2);//宽、高、半径
                canvasContext.stroke();

                //画五角星
                this.draw5star(width,height,25,"#FF0000",0,canvasContext);

                // 画主印章名称(大圆的那个印章名称)
                canvasContext.font = '20px 宋体';
                canvasContext.textBaseline = 'middle';//设置文本的垂直对齐方式
                canvasContext.textAlign = 'center'; //设置文本的水平对对齐方式
                canvasContext.lineWidth=1;
                canvasContext.strokeStyle = '#FF0000';
                canvasContext.strokeText(secondSealContent,width,height+60);

                // 画印章内横着的那行字内容
                canvasContext.translate(width,height);// 平衡移动到该位置,
                canvasContext.font = '23px 宋体'
                let  count = mainSealContent.length;// 多少字数
                let  angle = 4*Math.PI/(3*(count - 1));// 字间的角度
                let chars = mainSealContent.split("");
                let c;
                for (let i = 0; i < count; i++) {
                    c = chars[i];// 需要画的字符
                    if (i == 0) {
                        canvasContext.rotate(5 * Math.PI / 6);
                    } else{
                        canvasContext.rotate(angle);
                    }
                    canvasContext.save();
                    canvasContext.translate(70, 0);// 平衡移动到该位置,此时字和x轴垂直，主印章名称和最外圈的距离
                    canvasContext.rotate(Math.PI / 2);// 旋转90度,让字和x轴平衡
                    canvasContext.strokeText(c, 0, 0);// 该点为字的中心点
                    canvasContext.restore();
                }
            }
        }
    }
</script>

<style scoped>
  .imgClass img{
    width:100%;
    height:100%;
  }
</style>
