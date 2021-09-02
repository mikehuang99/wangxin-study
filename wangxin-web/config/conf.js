if (process.env.NODE_ENV == 'development') { //开发环境
  module.exports =  {
    //title: '旺鑫树学习平台dev',
    title: '...',
    baseUrl: 'http://127.0.0.1:8080/#/api'   //java项目接口地址
  }
}else if (process.env.NODE_ENV == 'testing') {  //测试环境
  module.exports =  {
    //title: '旺鑫树学习平台test',
    title: '...',
    baseUrl: 'http://127.0.0.1:8080/#/'  //java项目接口地址
  }
}else {
  module.exports =  {   //默认生产环境
    //title: '旺鑫树学习平台pro',
    title: '...',
    baseUrl: 'http://127.0.0.1:8080/#/api'   //java项目接口地址
  }
}
