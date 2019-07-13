//index.js
//获取应用实例
const app = getApp()
const {$} = require("../utils/request.js")
Page({
  data: {
    user: {}
  },
  /**注册 */
  submitRegister: function(e) {
    let _this = this;
    $.post({
      url: app.api.user.register,
      data: e.detail.value,
      success: function (data){
        console.log(data)
      }
    })
  }
})
