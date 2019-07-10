//index.js
//获取应用实例
const app = getApp()

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
    console.log(e.detail.value.username)
    console.log(e.detail.value.password)
    console.log(this.data.loginData)
  }
})
