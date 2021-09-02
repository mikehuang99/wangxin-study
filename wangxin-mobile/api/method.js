import * as axios from 'axios'
import VueCookie from 'vue-cookie'
import {
  baseUrl
} from '../util/client'


// console.log(axios.defaults)
const createHttp = (token) => {

// console.log("api==============")

  let options = {
  }
  let head = {
    orgno: 'wx',
    platform: 'mobile'
  }

  if (process.client) {
    // console.log(cookie.getInClient(config.CLIENT.tokenName));
    //head.token = cookie.getInClient(config.CLIENT.tokenName)
    head.token = VueCookie.get("wangxinMobileToken")
  }
  options.baseURL = baseUrl;
  options.headers = head;

  return axios.create(options)
}


export default createHttp
