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
  //卸载页面的时候，返回首页
  onUnload: function() {
    //返回到首页
    wx.redirectTo({
      url: '/pages/index/index',
    })
  },
  //登陆
  submitLogin: function(e) {
    this.data.loginData = e.detail.value
    $.post({
      url: app.api.anonymous.login,
      data: e.detail.value,
      callback: function(data) {
        //设置token
        wx.setStorageSync('token', data.token);
        app.userInfo = data.user;
        
        //跳转至首页
        wx.redirectTo({
          url: '/pages/me/me'
        })
      }
    })
  }
})
