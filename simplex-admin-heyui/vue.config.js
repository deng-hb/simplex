const path = require('path');
const webpack = require('webpack');

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
        'req': ['@/req', 'default'],
        'HeyUI': 'heyui'
      })
    ]
  }
}