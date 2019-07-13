//index.js
//获取应用实例
const app = getApp()
const { $ } = require("../utils/request.js")
const { toastSuccess} = require("../utils/toast.js")
Page({
  data: {
    loginData: {
      username: '',
      password: ''
    }
  }, 
  //登陆
  submitLogin: function(e) {
    this.data.loginData = e.detail.value
    $.post({
      url: app.api.user.login,
      data: e.detail.value,
      callback: function(data) {
        toastSuccess("登陆成功");
      }
    })
  }
})
