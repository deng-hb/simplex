module.exports = {
  publicPath: '/admin/',
  productionSourceMap: false,
  devServer: {
    proxy: 'http://localhost:8081'
  },
  configureWebpack: {
    plugins: [
      
    ]
  }
}