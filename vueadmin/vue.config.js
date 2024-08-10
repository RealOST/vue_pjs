const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy: {
      '/api': { // /api 表示拦截以 /api开头请求的路径
        target: 'http://localhost:8080/api/', // 目标服务器地址,要跨域的域名
        changeOrigin: true, // 是否改变源地址，是否开启跨域
        pathRewrite: {
          '^/api': '' // 重写路径，把/api变成空串
        }
      }
    }
  }
})
