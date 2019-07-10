//index.js
//获取应用实例
const app = getApp()
var {api} = require('../utils/api.js')

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
      url: api.user.register,
      dataType: 'json',
      method: 'post',
      data: this.data.user,
      success: function(data) {
        console.log(data)
      }
    })
  }
})
