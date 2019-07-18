//index.js
//获取应用实例
const app = getApp()
const { $ } = require("../utils/request.js")
const { toastSuccess } = require("../utils/toast.js")

Page({
  data: {
    user: {}
  },
  /**注册 */
  submitRegister: function(e) {
    $.post({
      url: app.api.anonymous.register,
      data: e.detail.value,
      callback: function (data) {
        wx.setStorageSync('token', data.data)
      }
    })
  }
})