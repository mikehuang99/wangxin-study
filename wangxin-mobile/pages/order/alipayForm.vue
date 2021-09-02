<template>
    <div>
      <div v-html="alipayForm">
        {{alipayForm}}
      </div>
    </div>
</template>

<script>
    export default {
        name: "alipayForm",
        data() {
            return{
                alipayForm: ''
            }
        },
        mounted() {
            let form = this.$route.query.name;
            this.alipayForm = form;
            this.$nextTick(()=> {
                document.forms[0].submit();
            });
        }
    }
</script>

<style scoped>

</style>
