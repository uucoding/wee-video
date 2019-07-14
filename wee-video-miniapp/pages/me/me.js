//index.js
//获取应用实例
const app = getApp()
const { $ } = require("../utils/request.js")
const { toastSuccess} = require("../utils/toast.js")

Page({
  data: {
    //tab选项
    tabs:['作品', '喜欢'],
    // 默认选中的tab下标
    tabActiveIndex: 0,
    user: {
      nickname: 'weecoding',
      faceImage: '../resource/images/face-avator.png',
      likeCount: 20,
      followCount: 33,
      fansCount: 56,
    },
    videoList: [],
  },

  /**
   *  切换table选项卡
   */
  triggerTab: function(e) {
    this.setData({
      tabActiveIndex: e.currentTarget.dataset.index
    }) 
  }
})
