module.exports = {
  //mode: 'spa',//（工程内启动可以注释掉并使用universal，generate打包需要开启，要不刷新有问题）
  //mode: 'universal',
  mode: 'spa',//（工程内启动可以注释掉并使用universal，generate打包需要开启，要不刷新有问题）
  router: {
    base:'/wxmobile/',//导出静态文件的根目录，如果不需要根目录，可以去掉这个属性（工程内启动可以注释掉）
    mode:'hash',//（工程内启动可以注释掉，generate打包需要开启）
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
    title: 'wangxin-mobile',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'Nuxt.js project' }
    ],
    link: [
      //{ rel: 'icon', type: 'image/x-icon', href: '/wxmobile/favicon.ico' },
      { rel: 'stylesheet', href: '/wxmobile/css/common.css' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
    extend (config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push(...[{
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        },{
          test: /\.(gif|png|jpe?g|svg)$/i,
          use: [
            'file-loader',
            {
              loader: 'image-webpack-loader',
              options: {
                bypassOnDebug: true,
                mozjpeg: {
                  progressive: true,
                  quality: 65
                },
                // optipng.enabled: false will disable optipng
                optipng: {
                  enabled: true,
                },
                pngquant: {
                  quality: '65-90',
                  speed: 4
                },
                gifsicle: {
                  interlaced: false,
                },
                // the webp option will enable WEBP
                webp: {
                  quality: 75
                }
              },
            },
          ],
        }])
      }
    }
  }
}

