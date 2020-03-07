const path = require('path');
const webpack = require('webpack');
const globalVars = require('./src/css/var.js');

const resolve = (dir) => path.join(__dirname, dir);

module.exports = {
  publicPath: '/admin/',
  productionSourceMap: false,
  lintOnSave: false,
  devServer: {
    proxy: 'http://localhost:8081'
  },
  chainWebpack: config => {
    // 修复HMR
    config.resolve.symlinks(true);
  },
  configureWebpack: {
    resolve: {
      alias: {
        '@': resolve('src'),
      }
    },
    plugins: [
      new webpack.ProvidePlugin({
        'Api': ['@/api', 'default'],
        'HeyUI': 'heyui'
      })
    ]
  },
  css: {
    loaderOptions: {
      less: {
        globalVars
      }
    }
  },
}