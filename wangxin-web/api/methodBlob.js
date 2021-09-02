import * as axios from 'axios'
import cookie from '../utils/cookies'
import LRU from 'lru-cache'
import config from '../config'
import VueCookie from 'vue-cookie'

// console.log(axios.defaults)
const createHttpBlob = (token) => {

// console.log("api==============")

  let options = {
  }
  let head = {
    orgno: config.CLIENT.no,
    platform: 'pcweb'
  }
  // 需要全路径才能工作
  if (process.server) {
    if (token) {
      head.token = token
    }
    options.baseURL = process.env._AXIOS_BASE_URL_
  }
  if (process.client) {
    // console.log(cookie.getInClient(config.CLIENT.tokenName));
    //head.token = cookie.getInClient(config.CLIENT.tokenName)
    head.token = VueCookie.get("wangxinToken")

  }

  options.headers = head;

  options.responseType = "blob"

  return axios.create(options)
}

export default createHttpBlob
