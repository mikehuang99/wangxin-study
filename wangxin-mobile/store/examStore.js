import VueCookie from 'vue-cookie'

import {cacheDoExercisesData,deleteDoExercisesData,findDoExercisesDataByCcpId} from '~/api/exam'

/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
  if (!name) return
    if (typeof content !== 'string') {
      content = JSON.stringify(content)
    }
    window.localStorage.setItem(name, content);

    let systemConfig = JSON.parse(getStore("systemConfig"));
    if(systemConfig.isOpenDoExercisesDataSync!=1){//是否开启刷题数据同步,1为开启，0为关闭
      return;
    }

    if(name.startsWith("continueStartIndex_")||
       name.startsWith("answer_ids_")||
        name.startsWith("answer_")||
        name.startsWith("answer_right_")||
        name.startsWith("answer_error_")){
      cacheDoExercisesData({key: name,value: content}).then(res => {
        res = res.data;
        if (res.code === 200 ) {
          //let data = res.data;

        } else if (res.code > 300 && res.code < 400) {
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
      }).catch(error => {
        //reject(error)
        console.log(error);
      })
    }
}

/**
 * 获取localStorage
 */
export const getStore = (name) => {
  if (!name) return
    return window.localStorage.getItem(name)
}

/**
 * 删除localStorage
 */
export const removeStore = name => {
  if (!name) return
  window.localStorage.removeItem(name);

  let systemConfig = JSON.parse(getStore("systemConfig"));
  if(systemConfig.isOpenDoExercisesDataSync!=1){//是否开启刷题数据同步,1为开启，0为关闭
    return;
  }

  if(name.startsWith("continueStartIndex_")||
    name.startsWith("answer_ids_")||
    name.startsWith("answer_")||
    name.startsWith("answer_right_")||
    name.startsWith("answer_error_")) {
    deleteDoExercisesData({key: name}).then(res => {
      res = res.data;
      if (res.code === 200) {
        //let data = res.data;

      } else if (res.code > 300 && res.code < 400) {
        if (res.code === 304) {
          console.log("该账号被异地登录");
          logout();
          this.$router.push({path: '/login', query: {}});
        } else {
          console.log("登录超时、异常或者被异地登录，请重新登录")
          logout();
          this.$router.push({path: '/login', query: {}});
        }
      } else {
        console.log("系统错误");
      }
    }).catch(error => {
      //reject(error)
      console.log(error);
    })
  }
}

/**
 * 退出登录
 */
export const logout = () => {
  VueCookie.delete("wangxinMobileUserInfo");
  VueCookie.delete("wangxinMobileToken");
}

