
export const loadMore = {
  directives: {
    'load-more': {
      bind: (el, ebind) => {
        let scrollToBottom = {
          getScrollTop: function () {
            if (document.body && document.documentElement) {
              let bodyScrollTop = document.body.scrollTop
              let documentScrollTop = document.documentElement.scrollTop
              return (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop
            }
            return 0;
          },
          getScrollHeight: function () {
            if (document.body && document.documentElement) {
              let bodyScrollHeight = document.body.scrollHeight
              let documentScrollHeight = document.documentElement.scrollHeight
              let scrollHeight =  (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
              return scrollHeight;
            }
            return 0;
          },
          getClientHeight: function () {
            let windowHeight = 0
            if (document.compatMode === "CSS1Compat"){
              windowHeight = document.documentElement.clientHeight
            } else {
              windowHeight = document.body.clientHeight
            }
            //alert("windowHeight:"+windowHeight);
            return windowHeight
          },
          onScrollEvent: function (callback) {
            let that = this
            window.onscroll = function () {
              if (that.getScrollTop() + that.getClientHeight() >= that.getScrollHeight()) {
                typeof callback === 'function' && callback.call(this)
              }
            }
          }
        }
        scrollToBottom.onScrollEvent(function () {
          ebind.value()
        })
      }
    }
  }
}
