//index.js
//获取应用实例
const app = getApp()
const {
  $
} = require("../utils/request.js")
const {
  toastSuccess
} = require("../utils/toast.js")

Page({
  data: {
    //设置tabbar组件的值
    tabbars: app.tabbars,
    //tab选项
    tabs: ['作品', '喜欢'],
    // 默认选中的tab下标
    tabActiveIndex: 0,
    user: {
      username: 'weecoding',
      nickname: '张三风',
      faceImage: '/resource/images/face-avator.png',
      likeCount: 20,
      followCount: 33,
      fansCount: 56,
    },
    selfVideoList: [],
    likeVideoList: [],
  },
  /**
   * 如果token不存在，跳转至登陆页
   */
  onLoad: function() {
    if (!wx.getStorageSync('token')) {
      //跳转至登陆页
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
  },
  /**
   *  切换table选项卡
   */
  triggerTab: function(e) {
    this.setData({
      tabActiveIndex: e.currentTarget.dataset.index
    })
  },
  /**
   * 切换头像
   */
  editFace: function() {

  },
  /**
   * 编辑个人资料
   */
  editMe: function() {
    wx.navigateTo({
      url: '/pages/me/edit/edit'
    })
  },
})