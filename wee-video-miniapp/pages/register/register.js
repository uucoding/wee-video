//index.js
//获取应用实例
const app = getApp()
var {API} = require('../utils/api.js')

Page({
  data: {
    user: {}
  },
  /**注册 */
  submitRegister: function(e) {
    let _this = this;
    _this.setData({
      user: e.detail.value
    })
    wx.request({
      url: API.user.register,
      dataType: 'json',
      method: 'post',
      data: this.data.user,
      success: function(data) {
        console.log(data)
      }
    })
  }
})
