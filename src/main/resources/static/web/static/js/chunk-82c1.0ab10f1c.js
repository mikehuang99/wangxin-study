(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-82c1"],{"5Kzs":function(t,e,a){"use strict";a.r(e);var i=a("gDS+"),n=a.n(i),l=a("mvtb"),r=a("bBdc"),o={name:"Edit",data:function(){return{editor:{}}},props:{formData:{type:Object,default:function(){}},teacherExt:{type:Object,default:function(){}},visible:{type:Boolean,default:!1},title:{type:String,default:""}},watch:{visible:function(t){var e=this;t&&setTimeout(function(){e.editor.create(),e.editor.customConfig.customUploadImg=e.editorUpload,void 0!==e.formData.introduce&&""!==e.formData.introduce&&null!==e.formData.introduce?e.editor.txt.html(e.formData.introduce):e.editor.txt.html("")},200)}},mounted:function(){this.createEdit()},methods:{createEdit:function(){var t=a("GguQ");this.editor=new t("#introduce")},handleClose:function(t){this.editor.txt.clear(),this.$emit("close-callback")},handleChange:function(t){this.formData.sort=t},submitForm:function(t){var e=this;this.$refs[t].validate(function(t){t?void 0===e.formData.id?e.$message({type:"error",message:"提交失败"}):(e.formData.introduce=e.editor.txt.html(),e.loading.show(),l.l(e.formData).then(function(t){e.loading.hide(),200===t.code&&t.data>0?(e.tips("操作成功","success"),e.handleClose()):e.$message({type:"error",message:"提交失败"})}).catch(function(){e.loading.hide()})):(e.loading.hide(),e.$message({type:"error",message:"提交失败"}))})},editorUpload:function(t,e){var a=this,i=t[0],n=new FormData;n.append("picFile",i,i.name),r.d(n).then(function(t){if(200===t.code){var a=t.data;e(a)}}).catch(function(){a.$message({type:"error",message:"上传图片出错，请稍后重试"})})}}},s=a("KHd+"),c=Object(s.a)(o,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{width:"70%",title:t.title,visible:t.visible,"before-close":t.handleClose},on:{"update:visible":function(e){t.visible=e}}},[a("el-form",{ref:"formData",attrs:{model:t.formData,"label-width":"100px"}},[a("el-alert",{staticClass:"title",attrs:{closable:!1,title:"教师个人信息",type:"info"}}),t._v(" "),a("br"),t._v(" "),a("div",[a("br"),t._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("div",[a("el-form-item",{attrs:{label:"用户手机："}},[a("el-input",{attrs:{disabled:!0},model:{value:t.formData.teacherMobile,callback:function(e){t.$set(t.formData,"teacherMobile",e)},expression:"formData.teacherMobile"}})],1)],1)]),t._v(" "),a("el-col",{attrs:{span:12}},[a("div",[a("el-form-item",{attrs:{label:"教师名称："}},[a("el-input",{model:{value:t.formData.teacherName,callback:function(e){t.$set(t.formData,"teacherName",e)},expression:"formData.teacherName"}})],1)],1)])],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("div",[a("el-form-item",{attrs:{label:"邮箱："}},[a("el-input",{model:{value:t.formData.teacherEmail,callback:function(e){t.$set(t.formData,"teacherEmail",e)},expression:"formData.teacherEmail"}})],1)],1)]),t._v(" "),a("el-col",{attrs:{span:12}},[a("div",[a("el-form-item",{attrs:{label:"排序："}},[a("el-input-number",{staticStyle:{width:"300px"},attrs:{min:1},on:{change:t.handleChange},model:{value:t.formData.sort,callback:function(e){t.$set(t.formData,"sort",e)},expression:"formData.sort"}})],1)],1)])],1),t._v(" "),a("el-form-item",{attrs:{label:"教师简介："}},[a("div",{attrs:{id:"introduce"}})])],1)],1),t._v(" "),a("el-row",{staticStyle:{"margin-top":"17px"}},[a("el-button",{staticStyle:{float:"right","margin-left":"6px"},attrs:{size:"mini",type:"danger",plain:""},on:{click:t.handleClose}},[t._v("取 消")]),t._v(" "),a("el-button",{staticStyle:{float:"right"},attrs:{size:"mini",type:"primary"},on:{click:function(e){t.submitForm("formData")}}},[t._v("确 定")])],1)],1)},[],!1,null,null,null);c.options.__file="edit.vue";var d=c.exports,u={data:function(){return{textValidValue:{1:"正常",0:"禁用"},opts:{validValueList:[]}}},props:{formData:{type:Object,default:function(){}},teacherExt:{type:Object,default:function(){}},visible:{type:Boolean,default:!1},title:{type:String,default:""}},methods:{textClass:function(t){return{c_red:0===t,c_blue:1===t}},handleClose:function(t){this.$emit("close-callback")}}},h=Object(s.a)(u,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{width:"60%",title:t.title,visible:t.visible,"before-close":t.handleClose},on:{"update:visible":function(e){t.visible=e}}},[a("el-form",{attrs:{inline:!0}},[a("el-card",[a("el-divider",{staticStyle:{"font-weight":"bold","background-color":"#f4f4f5"}},[t._v("教师个人信息")]),t._v(" "),a("el-row",[a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"头像:"}},[a("div",{staticClass:"demo-image"},[a("div",{staticClass:"block"},[a("el-image",{staticStyle:{width:"100px",height:"100px"},attrs:{src:t.formData.headImgUrl}})],1)])])],1)]),t._v(" "),a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"教师编号:"}},[a("span",[t._v(t._s(t.formData.teacherUserNo))])]),t._v(" "),a("br"),t._v(" "),a("el-form-item",{attrs:{label:"教师名称:"}},[a("span",[t._v(t._s(t.formData.teacherName))])])],1)]),t._v(" "),a("br"),a("br"),a("br"),a("br"),t._v(" "),a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"状态:"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{class:t.textClass(t.formData.validValue)},[t._v(t._s(t.textValidValue[t.formData.validValue]))])]}}])})],1)])],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"手机号:"}},[a("span",[t._v(t._s(t.formData.teacherMobile))])])],1)]),t._v(" "),a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"邮箱:"}},[a("span",[t._v(t._s(t.formData.teacherEmail))])])],1)]),t._v(" "),a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"职位:"}},[a("span",[t._v(t._s(t.formData.position))])])],1)])],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"注册时间:"}},[a("span",[t._v(t._s(t.formData.createTime))])])],1)]),t._v(" "),a("el-col",{attrs:{span:8}},[a("div",[a("el-form-item",{attrs:{label:"排序:"}},[a("span",[t._v(t._s(t.formData.sort))])])],1)])],1)],1),t._v(" "),a("br"),t._v(" "),a("el-card",[a("el-divider",{staticStyle:{"font-weight":"bold","background-color":"#f4f4f5"}},[t._v("简介")]),t._v(" "),a("el-form-item",{domProps:{innerHTML:t._s(t.formData.introduce)}})],1),t._v(" "),a("br")],1),t._v(" "),a("el-row",{staticStyle:{"margin-top":"17px"}},[a("el-button",{staticStyle:{float:"right","margin-left":"6px"},attrs:{size:"mini",type:"danger",plain:""},on:{click:t.handleClose}},[t._v("取 消")])],1)],1)},[],!1,null,null,null);h.options.__file="view.vue";var p=h.exports,m={name:"BindStudentGroup",data:function(){return{map:{},operateType:"",opts:{validValueList:[{code:"",desc:"全部"},{code:1,desc:"已绑定"},{code:0,desc:"未绑定"}]},list:[],idList:[],selectedList:[],ctrl:{load:!1},page:{beginPageIndex:1,pageCurrent:1,endPageIndex:8,pageSize:20,totalCount:0,totalPage:0},textuValidValue:{0:"禁用",1:"正常"}}},props:{teacherId:{type:String,default:""},userId:{type:String,default:""},visible:{type:Boolean,default:!1},title:{type:String,default:""},bindType:{type:Number,default:""}},watch:{visible:function(t){t&&(this.idList=[],this.map={},this.userList(1))}},methods:{handleSizeChange:function(t){this.page.pageSize=t,this.userList()},handleCurrentChange:function(t){this.page.pageCurrent=t,this.userList()},handleCheck:function(){this.page.pageCurrent=1,this.userList()},handleReset:function(){this.map={},this.list=[],this.idList=[],this.userList()},userList:function(){var t=this;this.ctrl.load=!0,this.map.bindType=this.bindType,this.map.teacherId=this.teacherId,l.j(this.map,this.page.pageCurrent,this.page.pageSize).then(function(e){t.list=e.data.list,t.page.pageCurrent=e.data.pageCurrent,t.page.totalCount=e.data.totalCount,t.page.pageSize=e.data.pageSize,t.ctrl.load=!1}).catch(function(){t.ctrl.load=!1})},handleClickRow:function(t){console.log(t)},setSelect:function(){var t=this;this.list.map(function(e){-1!==t.idlist.indexOf(e.id)&&t.$refs.multipleTable.toggleRowSelection(e,!0)})},getRowKeys:function(t){return t.id},handleSelectionChange:function(t){for(var e=[],a=0;a<t.length;a++){for(var i=t[a].id,n=0,l=0;l<this.list.length;l++)this.list[l].id===i&&n++;0!==n&&e.push(t[a])}this.selectedList=e},handleBatch:function(t){var e=this;1===t&&this.$confirm("确定要绑定选中的用户？",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.loading.show(),e.changeStatus(1)}).catch(function(){}),2===t&&this.$confirm("确定要解绑选中的用户？",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.loading.show(),e.changeStatus(2)}).catch(function(){})},changeStatus:function(t){var e=this,a=[];if(this.selectedList.map(function(t){a.push(t.id)}),0===a.length)return this.$message({type:"error",message:"请勾选学生组"}),void this.loading.hide();l.k({teacherId:this.teacherId,studentGroupIds:a,bindType:t}).then(function(t){e.loading.hide(),200===t.code&&t.data>0?(e.tips("操作成功","success"),e.userList()):e.$message({type:"error",message:"提交失败"})}).catch(function(){e.loading.hide()})},handleClose:function(t){console.log("---999"),this.$emit("close-callback")},textClass:function(t){return{c_red:0===t,c_blue:2===t}}}},f=Object(s.a)(m,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{width:"60%",title:t.title,visible:t.visible,"before-close":t.handleClose},on:{"update:visible":function(e){t.visible=e}}},[a("div",{staticClass:"pad20"},[a("div",[a("div",[t._v("[注意：只能一页一页操作，不能两页同时勾选，例如第一页和第二页如果都有需要勾选的，请勾选完第一页的进行绑定，再回来操作第二页的]")]),t._v(" "),a("br"),t._v(" "),a("el-form",{attrs:{inline:!0,size:"mini"}},[a("el-form-item",{attrs:{label:"学生组名称："}},[a("el-input",{model:{value:t.map.groupName,callback:function(e){t.$set(t.map,"groupName","string"==typeof e?e.trim():e)},expression:"map.groupName"}})],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary",loading:t.ctrl.load},on:{click:t.handleCheck}},[t._v("查询")]),t._v(" "),a("el-button",{staticClass:"filter-item",on:{click:t.handleReset}},[t._v("重置")]),t._v(" "),2==t.bindType?a("el-button",{attrs:{type:"success",size:"mini"},on:{click:function(e){t.handleBatch(1)}}},[t._v("绑定学生组")]):t._e(),t._v(" "),1==t.bindType?a("el-button",{attrs:{type:"success",size:"mini"},on:{click:function(e){t.handleBatch(2)}}},[t._v("解绑学生组")]):t._e()],1)],1)],1),t._v(" "),a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.ctrl.load,expression:"ctrl.load"}],ref:"multipleTable",staticStyle:{width:"100%"},attrs:{size:"medium",data:t.list,"tooltip-effect":"dark","row-key":t.getRowKeys,"reserve-selection":!0,border:""},on:{"row-dblclick":t.handleClickRow,"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"index",width:"50",label:"序号"}}),t._v(" "),a("el-table-column",{attrs:{prop:"groupName",label:"群组名称"}}),t._v(" "),0!=t.bindType?a("el-table-column",{attrs:{width:"60","reserve-selection":!0,type:"selection",prop:"id"}}):t._e()],1),t._v(" "),a("el-pagination",{staticStyle:{float:"right","margin-top":"20px","margin-bottom":"22px"},attrs:{background:"","page-size":t.page.pageSize,"page-sizes":[20,50,100,200,500,1e3],layout:"total, sizes, prev, pager, next, jumper",total:t.page.totalCount},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1),t._v(" "),a("br"),t._v(" "),a("br")])])},[],!1,null,null,null);f.options.__file="BindStudentGroup.vue";var g=f.exports,v=a("xCwF"),b={components:{Edit:d,ViewTeacher:p,BindStudentGroup:g,validateAuthCert:v.a},data:function(){return{bindType:"",list:[],map:{},formData:{},teacherExt:{},teacherId:"",ctrl:{loading:!1,dialogVisible:!1,viewVisible:!1,setBindUserGroupDialogVisible:!1},opts:{validValueList:[]},page:{beginPageIndex:1,pageCurrent:1,endPageIndex:8,pageSize:20,totalCount:0,totalPage:0}}},mounted:function(){var t=this;this.$store.dispatch("GetOpts",{enumName:"ValidValueEnum",type:"arr"}).then(function(e){t.opts.validValueList=e}),this.teacherList(1)},methods:{bindStudentGroup:function(t,e,a){this.ctrl.setBindUserGroupDialogVisible=!0,this.ctrl.dialogTitle="绑定/解绑/查看/学生组",this.teacherId=t,this.bindType=a},handleEdit:function(t){this.getById(t,"edit")},handleDelete:function(t){var e=this;this.$confirm("确定删除这条数据?","我要删除",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.loading.show(),l.h({id:t}).then(function(t){e.loading.hide(),200===t.code&&t.data>0?(e.$message({type:"success",message:"操作成功"}),e.reload()):e.$message({type:"error",message:t.msg})}).catch(function(){e.loading.hide()})})},handleView:function(t){this.getById(t,"view")},closeCallback:function(){this.ctrl.viewVisible=!1,this.ctrl.proportionDialogVisible=!1,this.ctrl.dialogVisible=!1,this.ctrl.setBindUserGroupDialogVisible=!1,this.reload()},handleChangeStatus:function(t,e){var a=this;this.$confirm("确定要"+{0:"禁用",1:"启用"}[e]+"吗?",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.changeStatus(t,e),a.reload()}).catch(function(){a.reload()})},changeStatus:function(t,e){var a=this;this.ctrl.loading,l.l({id:t,validValue:e}).then(function(t){if(a.ctrl.loading=!1,200===t.code&&t.data>0){a.$message({type:"success",message:{0:"禁用成功",1:"启用成功"}[e]}),a.reload()}else{a.$message({type:"error",message:{0:"禁用失败",1:"启用失败"}[e]}),a.reload()}}).catch(function(){a.ctrl.loading=!1,a.reload()})},getById:function(t,e){var a=this;this.ctrl.loading=!0,l.m({id:t}).then(function(t){a.formData=t.data,n()(t.data.teacherExt),"proportion"===e?(a.ctrl.dialogTitle=t.data.teacherName+" —— 设置分成",a.ctrl.proportionDialogVisible=!0):"edit"===e?(a.ctrl.dialogTitle=t.data.teacherName+" —— 信息修改",a.ctrl.dialogVisible=!0):(a.ctrl.dialogTitle=t.data.teacherName+" —— 信息查看",a.ctrl.viewVisible=!0),a.ctrl.loading=!1}).catch(function(){a.ctrl.loading=!0})},handleSizeChange:function(t){this.page.pageSize=t,this.teacherList()},handleCurrentChange:function(t){this.page.pageCurrent=t,this.teacherList()},handleCheck:function(){this.page.pageCurrent=1,this.teacherList()},handleReset:function(){this.reload()},reload:function(){this.map={},this.formData={},this.teacherExt={},this.teacherList()},teacherList:function(){var t=this;this.ctrl.loading=!0,l.i(this.map,this.page.pageCurrent,this.page.pageSize).then(function(e){t.list=e.data.list,t.page.pageCurrent=e.data.pageCurrent,t.page.totalCount=e.data.totalCount,t.page.pageSize=e.data.pageSize,t.ctrl.loading=!1,Object(v.a)(t,t.$router,0,0,0,0,0,0,t.page.totalCount)}).catch(function(){t.ctrl.loading=!1})}}},_=Object(s.a)(b,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pad20"},[a("div",[a("el-form",{attrs:{inline:!0,size:"mini"}},[a("el-form-item",{attrs:{label:"教师名称："}},[a("el-input",{model:{value:t.map.teacherName,callback:function(e){t.$set(t.map,"teacherName","string"==typeof e?e.trim():e)},expression:"map.teacherName"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"手机号："}},[a("el-input",{model:{value:t.map.teacherMobile,callback:function(e){t.$set(t.map,"teacherMobile","string"==typeof e?e.trim():e)},expression:"map.teacherMobile"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"状态："}},[a("el-select",{staticClass:"auto-width",staticStyle:{width:"85px"},attrs:{clearable:"",filterable:"",placeholder:"状态"},model:{value:t.map.validValue,callback:function(e){t.$set(t.map,"validValue",e)},expression:"map.validValue"}},t._l(t.opts.validValueList,function(t){return a("el-option",{key:t.code,attrs:{label:t.desc,value:t.code}})}))],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{icon:"el-icon-search",type:"primary"},on:{click:t.handleCheck}},[t._v("查询")]),t._v(" "),a("el-button",{staticClass:"filter-item",attrs:{icon:"el-icon-refresh"},on:{click:t.handleReset}},[t._v("重置")])],1)],1)],1),t._v(" "),a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.ctrl.loading,expression:"ctrl.loading"}],staticStyle:{width:"100%"},attrs:{size:"medium",data:t.list,stripe:"",border:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{label:"手机号"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{directives:[{name:"has",rawName:"v-has",value:"/pcboss/user/teacher/view",expression:"'/pcboss/user/teacher/view'"}],attrs:{type:"text"},on:{click:function(a){t.handleView(e.row.id)}}},[t._v(t._s(e.row.teacherMobile))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"teacherName",label:"教师名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"teacherEmail",label:"邮箱"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"500"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("ul",{staticClass:"list-item-actions"},[a("li",[a("el-button",{directives:[{name:"has",rawName:"v-has",value:"/pcboss/user/teacher/update",expression:"'/pcboss/user/teacher/update'"}],attrs:{type:"success",size:"mini"},on:{click:function(a){t.handleEdit(e.row.id)}}},[t._v("修改")]),t._v(" "),a("el-button",{directives:[{name:"has",rawName:"v-has",value:"/pcboss/user/teacher/delete",expression:"'/pcboss/user/teacher/delete'"}],attrs:{type:"danger",size:"mini"},on:{click:function(a){t.handleDelete(e.row.id)}}},[t._v("删除")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){t.bindStudentGroup(e.row.id,e.row.groupName,2)}}},[t._v("绑定学生组")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){t.bindStudentGroup(e.row.id,e.row.groupName,1)}}},[t._v("解绑学生组")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){t.bindStudentGroup(e.row.id,e.row.groupName,0)}}},[t._v("查看学生组")])],1)])]}}])})],1)],1),t._v(" "),a("el-pagination",{staticStyle:{float:"right","margin-top":"20px","margin-bottom":"22px"},attrs:{background:"","page-size":t.page.pageSize,"page-sizes":[20,50,100,200,500,1e3],layout:"total, sizes, prev, pager, next, jumper",total:t.page.totalCount},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}),t._v(" "),a("edit",{attrs:{visible:t.ctrl.dialogVisible,formData:t.formData,teacherExt:t.teacherExt,title:t.ctrl.dialogTitle},on:{"close-callback":t.closeCallback}}),t._v(" "),a("view-teacher",{attrs:{visible:t.ctrl.viewVisible,formData:t.formData,teacherExt:t.teacherExt,title:t.ctrl.dialogTitle},on:{"close-callback":t.closeCallback}}),t._v(" "),a("bind-student-group",{attrs:{visible:t.ctrl.setBindUserGroupDialogVisible,bindType:t.bindType,teacherId:t.teacherId,title:t.ctrl.dialogTitle},on:{"close-callback":t.closeCallback}})],1)},[],!1,null,null,null);_.options.__file="index.vue";e.default=_.exports},mvtb:function(t,e,a){"use strict";a.d(e,"i",function(){return r}),a.d(e,"l",function(){return o}),a.d(e,"h",function(){return s}),a.d(e,"m",function(){return c}),a.d(e,"d",function(){return d}),a.d(e,"e",function(){return u}),a.d(e,"f",function(){return h}),a.d(e,"c",function(){return p}),a.d(e,"a",function(){return m}),a.d(e,"g",function(){return f}),a.d(e,"b",function(){return g}),a.d(e,"j",function(){return v}),a.d(e,"k",function(){return b});var i=a("QbLZ"),n=a.n(i),l=a("t3Un");function r(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,a=arguments.length>2&&void 0!==arguments[2]?arguments[2]:20;return Object(l.a)({url:"/pcboss/user/teacher/list",method:"post",data:n()({pageCurrent:e,pageSize:a},t)})}function o(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/update",method:"post",data:t})}function s(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/delete",method:"post",data:t})}function c(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/view",method:"post",data:t})}function d(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,a=arguments.length>2&&void 0!==arguments[2]?arguments[2]:20;return Object(l.a)({url:"/pcboss/user/teacher/audit/list",method:"post",data:n()({pageCurrent:e,pageSize:a},t)})}function u(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/audit/save",method:"post",data:t})}function h(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/audit/update",method:"post",data:t})}function p(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/audit/delete",method:"post",data:t})}function m(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/audit/audit",method:"post",data:t})}function f(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/audit/view",method:"post",data:t})}function g(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l.a)({url:"/pcboss/user/teacher/audit/check",method:"post",data:t})}function v(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,a=arguments.length>2&&void 0!==arguments[2]?arguments[2]:20;return Object(l.a)({url:"/pcboss/teacher/student/student/group/list",method:"post",data:n()({pageCurrent:e,pageSize:a},t)})}function b(t){return Object(l.a)({url:"/pcboss/teacher/student/student/group/save",method:"post",data:n()({},t)})}}}]);