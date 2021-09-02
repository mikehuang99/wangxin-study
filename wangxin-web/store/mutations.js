import Vue from 'vue'
import cookie from '../utils/cookies'
import { setStore, getStore, removeStore, setSession, getSession } from '../utils/storage'
// import axios from '~/api/main.js'

export default {
  INIT_WEB: (state) => {
    console.log("重新初始化。。。。。")
    let token = cookie.getInClient("wangxinToken");
    console.log("重新初始化的token:"+token);
    if (token) {
      let userInfo = JSON.parse(cookie.getInClient("wangxinUserInfo"));
      //state.tokenInfo = token;
      //state.userInfo = userInfo;
    }else {
      //state.userInfo = '';
      removeStore('WxUserInfo');
      cookie.delInClient("wangxinUserInfo");
    }
  },
  SET_ITEMS: (state,  {key, value}) => {
    state[key] = value;
    // setStore(key, value);
  },
  SET_ADV: (state,  item) => {
    state.advData = item
  },
  // 记录token
  SET_TOKEN: (state,  token) => {
    console.log('进到记录token')
    console.log("state.clientData.tokenName:"+state.clientData.tokenName)
    //state.tokenInfo = token
    document.cookie = "WXTOKEN=" + token
    cookie.setInClient({key: state.clientData.tokenName, val: token})
    // setStore('tokenInfo', info.info)
  },
  // 服务端获取token
  GET_TOKEN_SERVER: (state,  req) => {
    let cook = cookie.getInServer(req);
    //state.tokenInfo = cook[state.clientData.tokenName];
  },
  // 退出登录
  SIGN_OUT: (state) => {
   // state.tokenInfo = ''
    state.userInfo = ''
    document.cookie = "WXTOKEN=" + '';
    cookie.delInClient(state.clientData.tokenName);
    console.log("退出登陆。。。。")
    removeStore('WxUserInfo');
    cookie.delInClient("wangxinToken");
    cookie.delInClient("wangxinUserInfo");

    // setStore('tokenInfo', info.info)
  },
  // 记录用户信息
  SET_USER: (state, data) => {
    console.log("记录用户信息...");
    //data.token = state.tokenInfo
    state.userInfo = data;
    setStore('WxUserInfo', data)
    // Vue.set(state.users, id, user || false) /* false means user not found */
  },
  // 记录当前url
  SET_TEMPORARYURL: (state, data) => {
    let uri = window.location.href;
    setSession('temporaryUrl', uri);
  },
  // 获取临时url
  GET_TEMPORARYURL (state) {
    let uri = getSession('temporaryUrl');
    if (uri) {
      state.temporaryUrl = uri;
    }
  },
  // 隐藏二维码
  HIDE_EWM (state) {
    state.changeEwm = Math.random()
  }
}
