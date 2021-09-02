const pkg = require('./package')
const config = require('./config/conf')

module.exports = {
  mode: 'universal',//（工程内启动可以开启）
  //mode: 'spa',//（工程内启动可以注释掉并使用universal，generate打包需要开启，要不刷新有问题）

  router: {
    //base:'/w-web/',//generate打包需要开启,导出静态文件的根目录，如果不需要根目录，可以去掉这个属性（工程内启动可以注释掉）
    //mode:'hash',//（工程内启动可以注释掉，generate打包需要开启）
    middleware: ['states', 'checkuser'],
    extendRoutes (routes, resolve) {
      routes.push({
        name: 'custom',
        path: '*',
        component: resolve(__dirname, 'pages/index.vue')
      })
    }
  },
  /*
  ** Headers of the page
  */
  head: {
    title: config.title,
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: pkg.description }
    ],
    // link: [
    //   { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    // ]
  },

  /*
  ** Customize the progress-bar color
  */
  loading: { color: '#FF4040' },

  /*
  ** Global CSS
  */
  css: [
    '@/assets/css/main.scss'
  ],

  /*
  ** Plugins to load before mounting the App
  */
  plugins: [
    '~/plugins/message.js',
    '~/plugins/dragging.js'
  ],

  /*
  ** Nuxt.js modules
  */
  modules: [
    // Doc: https://github.com/nuxt-community/axios-module#usage
    '@nuxtjs/axios',
    '@gauseen/nuxt-proxy',
  ],
  /*
  ** Axios module configuration
  */
  axios: {
    // See https://github.com/nuxt-community/axios-module#options
    proxy: true
  },
  proxyTable: {
      '/course/api': {target: config.baseUrl, ws: false },
      '/course/auth': {target: config.baseUrl, ws: false },
      '/web': {target: config.baseUrl, ws: false},
      '/system': {target: config.baseUrl, ws: false },
      '/activity': {target: config.baseUrl, ws: false },
      '/user': {target: config.baseUrl, ws: false },
      '/agent': {target: config.baseUrl, ws: false },
      '/zuul': {target: config.baseUrl, ws: false },
      '/auth/course': {target: config.baseUrl, ws: false },
      '/auth/user': {target: config.baseUrl, ws: false },
      '/exam/auth': {target: config.baseUrl, ws: false },

      '/pcweb/course': {target: config.baseUrl, ws: false },
      '/pcweb/system': {target: config.baseUrl, ws: false },
      '/pcweb/user': {target: config.baseUrl, ws: false },
      '/pcweb/exam': {target: config.baseUrl, ws: false },
      '/pcweb/market': {target: config.baseUrl, ws: false },
       '/pcweb/order': {target: config.baseUrl, ws: false },

      '/pccommon/course': {target: config.baseUrl, ws: false },
      '/pccommon/system': {target: config.baseUrl, ws: false },
      '/pccommon/user': {target: config.baseUrl, ws: false },
      '/pccommon/exam': {target: config.baseUrl, ws: false },
      '/pccommon/market': {target: config.baseUrl, ws: false },
  },

  cache: true,
  // or
  // cache: {
  //   max: 1000,
  //   maxAge: 900000
  // },
  env: {
    NODE_ENV: process.env.NODE_ENV || 'production'
  },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** You can extend webpack config here
    */
    extend(config, ctx) {

    }
  }
}
