module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://101.37.91.173:8090",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
    disableHostCheck: true
  }
};
