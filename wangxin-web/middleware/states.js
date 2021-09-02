import axios from 'axios'
import {serviceInfo, getNavigation, aboutList} from '~/api/main'
import config from '../config'

export default function ({route, req, res, store, next}) {
  let now = new Date();
  // console.log(route)
  if (!store.state.webInfo || now.getTime() - store.state.webInfo.time > 300000) {
    // 获取站点信息
    serviceInfo({moduleId: 3}).then(res => {
        // console.log(res.data)
        console.log("res.data==============")
      if (res.data.code  == 200) {

        res.data.data.time = now.getTime();
        store.commit('SET_ITEMS', {key: 'webInfo', value: res.data.data});
      } else{
        console.log(res.data)
      }
    }).catch(() => {
      console.log("获取站点信息失败")
    })
}
  if (!store.state.navigationList || now.getTime() - store.state.navigationList.time > 500000) {
    // 获取导航信息
    getNavigation().then(res => {
      // console.log(res.data)
      // console.log('nav==============')
      if (res.data.code  == 200) {
        res.data.data.time = now.getTime();
        store.commit('SET_ITEMS', {key: 'navigationList', value: res.data.data});
      } else{
        console.log(res.data)
      }
    }).catch(() => {
      console.log("获取导航信息失败")
    })
  }

  // 获取PC客户端信息
  serviceInfo().then(res => {
    if (res.data.code  == 200) {
      config.CLIENT.no = res.data.data.orgNo;
      config.CLIENT.id = 'dskfksdjkf87328423kskdjdsfkkwieikk';
      config.CLIENT.name = res.data.data.websiteTitle;
      config.CLIENT.domain = res.data.data.cookieDomain;
      config.CLIENT.tokenName = res.data.data.cookieTokenName;
      config.CLIENT.mainUrl = res.data.data.pcwebMainUrl;
    } else{
      console.log(res.data)
    }
  }).catch(() => {
    console.log("获取PC客户端信息")
  })

}
