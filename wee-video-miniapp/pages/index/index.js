//index.js
//获取应用实例
const app = getApp()
const { $ } = require("../utils/request.js")
const { toastSuccess} = require("../utils/toast.js")
Page({
  data: {
    //设置tabbar组件的值
    tabbars: app.tabbars,
  },
})
