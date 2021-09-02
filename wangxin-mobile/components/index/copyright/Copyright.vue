<!--本页非经官方允许不能去除-->
<template>
    <div style="text-align: center" >
      <span>
        <br/>
        <p v-html="bottomName"></p>
      </span>
    </div>

</template>

<script>
    export default {
        name: "Copyright",
        data(){
            return {
                bottomName:''
            }
        },
        mounted(){
            this.tohanzi();
        },
        methods: {
            tohanzi()
            {
                this.bottomName = '<a href="http://wangxinvip.com">powered by 浪树学习考试系统</a>';
            }
        }
    }
</script>

<style scoped>

</style>
