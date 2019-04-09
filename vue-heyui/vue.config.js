module.exports = {
  productionSourceMap: false,
  devServer: {
    proxy: 'http://localhost:8081'
  },
  configureWebpack: {
    plugins: [
      
    ]
  }
}