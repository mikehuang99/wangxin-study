(window.webpackJsonp=window.webpackJsonp||[]).push([[13],{228:function(t,e,o){var content=o(257);"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,o(65).default)("86ecacce",content,!0,{sourceMap:!1})},256:function(t,e,o){"use strict";o(228)},257:function(t,e,o){var n=o(64)(!1);n.push([t.i,".fix_bottom_contain[data-v-09ec2444]{width:100%;position:fixed;left:0;bottom:0;z-index:19881121}.fix_bottom_contain .bottom_btn[data-v-09ec2444]{height:50px;height-width:100%}.fix_bottom_contain .bottom_btn a[data-v-09ec2444]{display:inline-block;width:100%;height:100%;line-height:50px;text-align:center;color:#fff;font-size:15px;float:left}.fix_bottom_contain .bottom_btn .count_d[data-v-09ec2444]{background:red}.fix_bottom_contain .bottom_btn .count_d.before[data-v-09ec2444]{background:#0a0}.fix_bottom_contain .bottom_btn .count_d.after[data-v-09ec2444]{background:#fff}",""]),t.exports=n},269:function(t,e,o){"use strict";o(51),o(18),o(19),o(11),o(43);var n=o(20),r=o(129),c=o(42);function d(object,t){var e=Object.keys(object);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(object);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(object,t).enumerable}))),e.push.apply(e,o)}return e}var l={data:function(){return{layerMSg:"",isCart:!1,disabledCart:!1,disabledYu:!1,disabledAdd:!1,users:""}},mounted:function(){},computed:function(t){for(var i=1;i<arguments.length;i++){var source=null!=arguments[i]?arguments[i]:{};i%2?d(Object(source),!0).forEach((function(e){Object(n.a)(t,e,source[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(source)):d(Object(source)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(source,e))}))}return t}({},Object(c.c)(["courseDetail"])),props:["addCart","rightCount","errorCount","totalquestionscount"],methods:{goQuestion:function(t){this.$parent.goQuestion(t)},go:function(){this.$router.go(-1)},islogin:function(t){if(Object(r.getStore)("user")){if(this.disabledAdd)return;if(1===t){var e=JSON.parse(Object(r.getStore)("courseDetail")).courseId;this.$router.push({path:"/submitOrder/index",query:{id:e}})}else this.$router.push({path:"/subscribe/index"})}else this.$router.push({path:"/login"})}},components:{}},f=(o(256),o(32)),component=Object(f.a)(l,(function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"fix_bottom_contain"},[o("div",{staticClass:"bottom_btn"},[o("a",{staticClass:"count_d",class:{after:!0}},[o("span",{staticStyle:{float:"left","margin-left":"40px",color:"#333333","-webkit-border-radius":"5px","-moz-border-radius":"5px","border-radius":"5px"},on:{click:function(e){return t.goQuestion("last")}}},[o("img",{attrs:{src:"/wxmobile/img/icon_return.png"}})]),t._v(" "),o("span",[t._v("                    ")]),t._v(" "),o("span",{staticStyle:{float:"right","margin-right":"40px",color:"#333333","-webkit-border-radius":"5px","-moz-border-radius":"5px","border-radius":"5px"},on:{click:function(e){return t.goQuestion("next")}}},[o("img",{attrs:{src:"/wxmobile/img/icon_go.png"}})])])])])}),[],!1,null,"09ec2444",null);e.a=component.exports},332:function(t,e,o){var content=o(523);"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,o(65).default)("3990d6ce",content,!0,{sourceMap:!1})},522:function(t,e,o){"use strict";o(332)},523:function(t,e,o){var n=o(64)(!1);n.push([t.i,".box[data-v-3d8adb20]{margin:0;margin-padding:0;padding-bottom:50px;padding-bottom-width:100%;padding-bottom-height:100%;padding-bottom-background:#ff0;padding-bottom-position:absolute}",""]),t.exports=n},700:function(t,e,o){"use strict";o.r(e);var n=o(242),r=o(674),c=o(269),d=o(220),l=o(129),f={data:function(){return{questionId:"",ccpId:"",rightCount:"",errorCount:"",continuestartindex:"",totalquestionscount:"",test:"99",videooasid:"",playvideo:!1,playimg:!0,lessonname:"",startX:0,endX:0,disX:0,lastX:0,lastY:0,con:{translateX:"translateX(0px)"},courseDetail:{id:""},examManagementId:"",examPaperId:""}},computed:{},created:function(){},mounted:function(){this.examManagementId=this.$route.query.examManagementId,this.examPaperId=this.$route.query.examPaperId,this.ccpId=this.examPaperId,this.viewExamPaperContentByExamManagementIdAndExamPaperId()},methods:{setQuestionId:function(t){this.questionId=t},note:function(){this.$refs.aqc.note()},goQuestion:function(t){this.$refs.aqc.goQuestion(t)},viewExamPaperContentByExamManagementIdAndExamPaperId:function(){var t=this;Object(d.f)({id:this.examManagementId,examPaperId:this.examPaperId}).then((function(e){200===(e=e.data).code?Object(l.setStore)("examQuestions_"+t.examPaperId,e.data.examQuestionVos):e.code>300&&e.code<400?304===e.code?(console.log("该账号被异地登录"),Object(l.logout)(),t.$router.push({path:"/login",query:{}})):(console.log("登录超时、异常或者被异地登录，请重新登录"),Object(l.logout)(),t.$router.push({path:"/login",query:{}})):console.log("系统错误")})).catch((function(){}))}},components:{headerTop:n.a,answerQuestionContent:r.default,footerBtn:c.a}},m=(o(522),o(32)),component=Object(m.a)(f,(function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"box"},[o("header-top",{ref:"header",attrs:{questionId:t.questionId,continuestartindex:t.continuestartindex,totalquestionscount:t.totalquestionscount}}),t._v(" "),o("answer-question-content",{ref:"aqc",attrs:{ccpId:t.ccpId},on:{setRightCount:function(e){return t.rightCount=e},setErrorCount:function(e){return t.errorCount=e},setContinueStartIndex:function(e){return t.continuestartindex=e},setTotalQuestionsCount:function(e){return t.totalquestionscount=e}}}),t._v(" "),o("footer-btn",{attrs:{rightCount:t.rightCount,errorCount:t.errorCount,totalquestionscount:t.totalquestionscount}})],1)}),[],!1,null,"3d8adb20",null);e.default=component.exports}}]);