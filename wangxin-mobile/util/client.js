/**
 * 配置编译环境和线上环境之间的切换
 * baseUrl: 域名地址
 */

let baseUrl = ''
if (process.env.NODE_ENV === 'development') {
  console.log("进来development")
  baseUrl = 'http://192.168.1.102:8080';
} else if (process.env.NODE_ENV === 'production') {
  console.log("进来production")
  baseUrl = 'http://192.168.1.102:8080';
}

export {
  baseUrl
}



