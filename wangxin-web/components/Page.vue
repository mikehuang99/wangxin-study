<template>
  <div class="page">
    <a href="javascript:"
      v-for="(item, index) in _setPage()"
      class="ipg"
      :key="index"
      @click="pageClick(item)"
      :class="item == page.pageCurrent ? 'active' : ''"
      >{{item}}</a>
  </div>
</template>
<script>
export default {
  props: {
    page: {type: [Object, Array]}
  },
  data () {
    return {
    }
  },
  methods: {
    _setPage () {
      const data = this.page;
      let count = 8;
      let start = 1;
      let yb = Math.round(count / 2);
      let pageData = [];
      pageData.push(1);
      // 实际页数少于或等于限制总数
      if (data.totalPage <= count) {
        count = data.totalPage;
        yb = 0;
      }
      if (data.pageCurrent > 4 && yb) {
        start = data.pageCurrent - 3;
        pageData.push('...');
      }
      if (data.totalPage - data.pageCurrent <= 3 && yb) {
        start = data.totalPage - 7;
      }
      for (var i = 1; i < count - 1; i++) {
        pageData.push(start + i);
      }
      if (data.totalPage - data.pageCurrent > 3 && yb) {
        pageData.push('...');
      }
        pageData.push(data.totalPage);
      return pageData
    },
    pageClick (event) {
      this.$emit('btnClick', event)
    }
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
  .page{
    margin-top: 30px;
    text-align: center;
    font-weight: bold;
    .ipg{
      display: inline-block;
      width: 60px;
      text-align: center;
      line-height: 35px;
      color: #333;
      border: 1px solid #ccc;
      border-radius: 0px;
      margin-right: 10px;
    }
    .active{
      background-color: deepskyblue;
      color: #fff;
      border-color: deepskyblue;
    }
  }
</style>
