(window.webpackJsonp=window.webpackJsonp||[]).push([[24],{485:function(t,e,n){var content=n(665);content.__esModule&&(content=content.default),"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,n(51).default)("3fa4efb3",content,!0,{sourceMap:!1})},662:function(t,e,n){var o=n(5);o(o.P,"Array",{fill:n(663)}),n(99)("fill")},663:function(t,e,n){"use strict";var o=n(37),l=n(98),r=n(20);t.exports=function(t){for(var e=o(this),n=r(e.length),c=arguments.length,d=l(c>1?arguments[1]:void 0,n),h=c>2?arguments[2]:void 0,f=void 0===h?n:l(h,n);f>d;)e[d++]=t;return e}},664:function(t,e,n){"use strict";n(485)},665:function(t,e,n){var o=n(50)(!1);o.push([t.i,".imgClass img[data-v-5a05601c]{width:100%;height:100%}",""]),t.exports=o},783:function(t,e,n){"use strict";n.r(e);n(662);var o=n(268),l=n.n(o),r=n(96),c={name:"learnProof",components:{QRCode:l.a},data:function(){return{id:"",studyProof:{proofUserNo:"",realName:"加载中......",sex:"0",headImgUrl:"",mobile:"加载中......",identityCardNo:"加载中......",age:"加载中......",proofDate:"加载中......",mainSealContent:"",secondSealContent:"",studyProofCourseVos:[{id:"0",courseName:"加载中......",totalStudyProgress:"0"}]}}},mounted:function(){this.id=this.$route.query.id,this.drawSeal(),this.viewStudyProof();var t=this;setTimeout((function(){var e=window.location.href;t.generalQrCodeImg(e)}),1e3)},methods:{viewStudyProof:function(){var t=this;Object(r.h)({id:this.id}).then((function(e){var n=e.data;200===n.code?t.studyProof=n.data:n.code>=300&&n.code<400?(304==n.code&&t.$msgBox({content:"该账号被异地登录",isShowCancelBtn:!1}).then((function(){t.$store.dispatch("REDIRECT_LOGIN",n.code)})).catch((function(){t.$store.dispatch("REDIRECT_LOGIN",n.code)})),t.$msgBox({content:"登录超时、异常或者被异地登录，请重新登录",isShowCancelBtn:!1}).then((function(){t.$store.dispatch("REDIRECT_LOGIN",n.code)})).catch((function(){t.$store.dispatch("REDIRECT_LOGIN",n.code)}))):t.$msgBox({content:n.msg,isShowCancelBtn:!1}).catch((function(){}))})).catch((function(){}))},generalQrCodeImg:function(t){var canvas=document.getElementById("qrCodeImg");l.a.toCanvas(canvas,t,(function(t){t&&console.error(t),console.log("general qrCode success!")}))},doPrint:function(){var t=window.document.body.innerHTML,e=t.substr(t.indexOf("\x3c!--startprint--\x3e")+17);e=e.substring(0,e.indexOf("\x3c!--endprint--\x3e")),window.document.body.innerHTML=e,window.print(),location.reload()},draw5star:function(t,e,n,o,l,r){r.save(),r.fillStyle=o,r.translate(t,e),r.rotate(Math.PI+l),r.beginPath();Math.sin(0),Math.cos(0);for(var c=Math.PI/5*4,i=0;i<5;i++){var d=Math.sin(i*c),h=Math.cos(i*c);r.lineTo(d*n,h*n)}r.closePath(),r.stroke(),r.fill(),r.restore()},drawSeal:function(){var canvas=document.getElementById("canvas"),t=canvas.getContext("2d"),e="XX市第一中学",n=canvas.width/2,o=canvas.height/2;t.lineWidth=5,t.strokeStyle="#FF0000",t.beginPath(),t.arc(n,o,90,0,2*Math.PI),t.stroke(),this.draw5star(n,o,25,"#FF0000",0,t),t.font="20px 宋体",t.textBaseline="middle",t.textAlign="center",t.lineWidth=1,t.strokeStyle="#FF0000",t.strokeText("证明专用章",n,o+60),t.translate(n,o),t.font="23px 宋体";for(var l,r=e.length,c=4*Math.PI/(3*(r-1)),d=e.split(""),i=0;i<r;i++)l=d[i],0==i?t.rotate(5*Math.PI/6):t.rotate(c),t.save(),t.translate(70,0),t.rotate(Math.PI/2),t.strokeText(l,0,0),t.restore()}}},d=(n(664),n(28)),component=Object(d.a)(c,(function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{align:"center"}},[n("div",{staticStyle:{border:"2px solid red",width:"550px","margin-top":"100px"},attrs:{align:"center"}},[t._m(0),t._v(" "),t._m(1),t._v(" "),n("div",{staticStyle:{width:"500px","padding-left":"0px","margin-top":"20px"}},[n("div",{staticStyle:{float:"left"}},[n("div",[n("div",{staticStyle:{display:"inline-block"}},[t._m(2),t._v(" "),n("div",{staticStyle:{display:"inline-block",width:"256px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-size":"14px"}},[t._v(t._s(t.studyProof.realName))])])])]),t._v(" "),n("div",[n("div",{staticStyle:{display:"inline-block"}},[t._m(3),t._v(" "),n("div",{staticStyle:{display:"inline-block",width:"256px",height:"30px","text-align":"left"}},[1==t.studyProof.sex?n("span",{staticStyle:{"font-size":"14px"}},[t._v("男")]):t._e(),t._v(" "),2==t.studyProof.sex?n("span",{staticStyle:{"font-size":"14px"}},[t._v("女")]):t._e(),t._v(" "),3==t.studyProof.sex?n("span",{staticStyle:{"font-size":"14px"}},[t._v("保密")]):t._e()])])]),t._v(" "),n("div",[n("div",{staticStyle:{display:"inline-block"}},[t._m(4),t._v(" "),n("div",{staticStyle:{display:"inline-block",width:"256px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-size":"14px"}},[t._v(t._s(t.studyProof.age))])])])]),t._v(" "),n("div",[n("div",{staticStyle:{display:"inline-block"}},[t._m(5),t._v(" "),n("div",{staticStyle:{display:"inline-block",width:"256px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-size":"14px"}},[t._v(t._s(t.studyProof.mobile))])])])]),t._v(" "),n("div",[n("div",{staticStyle:{display:"inline-block"}},[t._m(6),t._v(" "),n("div",{staticStyle:{display:"inline-block",width:"256px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-size":"14px"}},[t._v(t._s(t.studyProof.identityCardNo))])])])])]),t._v(" "),n("div",{staticStyle:{float:"right",width:"100px",height:"130px"}},[n("div",{staticClass:"imgClass",staticStyle:{height:"100%"}},[n("img",{attrs:{src:t.studyProof.headImgUrl}})])])]),t._v(" "),t._m(7),t._v(" "),t._m(8),t._v(" "),t._m(9),t._v(" "),t._l(t.studyProof.studyProofCourseVos,(function(e,o){return n("div",{key:e.id,staticStyle:{width:"520px","margin-top":"5px"}},[n("div",[n("div",{staticStyle:{float:"left"}},[n("div",{staticStyle:{width:"350px","font-size":"15px",overflow:"hidden","text-overflow":"ellipsis","white-space":"nowrap"}},[t._v("\n            "+t._s(e.courseName)+"\n          ")])]),t._v(" "),n("div",{staticStyle:{float:"right"}},[n("span",{staticStyle:{"font-size":"15px"}},[t._v("完成"+t._s(e.totalStudyProgress)+"%")])])]),t._v(" "),n("br"),t._v(" "),n("hr")])})),t._v(" "),t._m(10),t._v(" "),n("div",{staticStyle:{"margin-left":"300px"}},[n("span",{staticStyle:{"font-size":"20px"}},[t._v(t._s(t.studyProof.proofDate))])])],2),t._v(" "),n("div",{staticStyle:{"margin-bottom":"40px"}})])}),[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{width:"540px",height:"35px","padding-top":"10px"}},[n("span",{staticStyle:{"font-size":"30px","font-weight":"bold"}},[t._v("学习证明")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticStyle:{"margin-top":"30px"}},[e("hr")])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{float:"left",display:"inline-block",width:"120px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-weight":"bold","font-size":"20px"}},[t._v("姓　　名")])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{display:"inline-block",width:"120px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-weight":"bold","font-size":"20px"}},[t._v("性　　别")])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{display:"inline-block",width:"120px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-weight":"bold","font-size":"20px"}},[t._v("年　　龄")])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{display:"inline-block",width:"120px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-weight":"bold","font-size":"20px"}},[t._v("手机号码")])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{display:"inline-block",width:"120px",height:"30px","text-align":"left"}},[n("span",{staticStyle:{"font-weight":"bold","font-size":"20px"}},[t._v("身份证号")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticStyle:{"margin-top":"213px"}},[e("hr")])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{width:"520px",height:"35px","margin-top":"5px"}},[n("span",{staticStyle:{"font-size":"20px","font-weight":"bold"}},[t._v("学习情况")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticStyle:{"margin-top":"5px"}},[e("hr")])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("div",{staticStyle:{float:"left",width:"60px",height:"80px","margin-left":"20px","margin-top":"40px"}},[n("div",{staticStyle:{height:"60px"}},[n("canvas",{attrs:{id:"qrCodeImg"}})])]),t._v(" "),n("div",{staticStyle:{"margin-left":"200px"}},[n("canvas",{attrs:{id:"canvas",width:"200",height:"200"}})])])}],!1,null,"5a05601c",null);e.default=component.exports}}]);