<template>
   <div style="margin: 0 auto;text-align: center">
        <schart :canvasId="canvasData.canvasId"
                :type="canvasData.type"
                :width="canvasData.width"
                :height="canvasData.height"
                :data="canvasData.data"
                :options="canvasData.options"
                ref="pieChart"
        ></schart>
   </div>
</template>

<script>
    import schart from './pieChart.vue'
    import {setStore as setLocalStore,getStore} from '~/store/common.js'
    import {viewSystemConfig} from '~/api/system'
    import VueCookie from 'vue-cookie'

    import { logout } from '~/store/common'

    export default {
        props: ['userRightCount','userErrorCount'],
        name: "testPieChart",
        data(){
            return{
                canvasData: {
                    canvasId: 'myCanvas',
                    type: 'ring',
                    width: 200,
                    height: 200,
                    data: [
                        { name: 'errorCount', value: this.userErrorCount},
                        { name: 'rightCount', value: this.userRightCount }
                    ],
                    a:'',
                    b:'',
                    options: {
                        bgColor: '#e3e3e3',             // 默认背景颜色 #e3e3e3
                        titlePosition: 'top',           // 图表标题位置: top / bottom
                        colorList: ['red', 'green'],    // 环形图颜色列表
                        radius: 60,                    // 环形图外圆半径
                        innerRadius: 9                // 环形图内圆半径
                    }
                },
                isOpenDoExercisesDataSync: 0, //是否开启刷题数据同步,1为开启，0为关闭
                isDataRightFlag: false
        }
        },
        mounted(){
            /*
            console.log("this.userErrorCount uuu:"+(this.userErrorCount==undefined));
            setTimeout(() => {
                this.canvasData.data[0]['value'] = this.userErrorCount;
                this.canvasData.data[1]['value'] = this.userRightCount;
                this.$refs.pieChart.reDraw();
            }, 1000)
            console.log("this.userErrorCount:"+this.userErrorCount);*/
            this.pieChartDraw();

            let systemConfig = JSON.parse(getStore("systemConfig"));
            if(systemConfig==null){
                this.getSystemConfig();
            }else {
                this.isOpenDoExercisesDataSync = systemConfig.isOpenDoExercisesDataSync;
            }
        },
        methods:{
            pieChartDraw(){//判断数据是否正确
                let that = this;
                if(!that.isDataRightFlag) {
                    setTimeout(() => {
                        if (!(that.userErrorCount === undefined ||that.userErrorCount === '' || that.userRightCount === undefined || that.userRightCount === '')) {
                            that.isDataRightFlag = true;
                            that.canvasData.data[0]['value'] = that.userErrorCount;
                            that.canvasData.data[1]['value'] = that.userRightCount;
                            that.$refs.pieChart.reDraw();
                        }else{
                            that.pieChartDraw();
                        }
                    }, 300)
                }
            },
            getSystemConfig () {
                viewSystemConfig({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        // console.log("====>"+res.data)
                        setLocalStore("systemConfig", JSON.stringify(res.data));
                        this.isOpenDoExercisesDataSync = res.data.isOpenDoExercisesDataSync;
                    } else if (res.code > 300 && res.code < 400) {
                        setLocalStore("lastUrlTemp",window.location.href);
                        if(res.code === 304){
                            console.log("该账号被异地登录");
                            logout();
                            this.$router.push({path: '/login', query: {}});
                        }else {
                            console.log("登录超时、异常或者被异地登录，请重新登录")
                            logout();
                            this.$router.push({path: '/login', query: {}});
                        }
                    }else{
                        console.log("系统错误");
                    }
                }).catch(() => {
                })
            },
        },
        components: {
            schart
        }
    }
</script>

<style scoped>

</style>
