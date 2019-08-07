//index.js
//获取应用实例
const app = getApp()
const {
  $
} = require("../utils/request.js")
const {
  toastSuccess,
  toastTip
} = require("../utils/toast.js")

Page({
  data: {
    //设置tabbar组件的值
    tabbars: app.tabbars,
    //tab选项
    tabs: ['作品', '喜欢'],
    // 默认选中的tab下标
    tabActiveIndex: 0,
    user: {},
    faceImage:'',
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
    let _this = this;
    console.log(app.userInfo)
    _this.setData({
      user: app.userInfo,
      faceImage: `${app.domain}/${app.userInfo.faceImage}`
    })
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
    let _this = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success(res) {
        const tempFilePaths = res.tempFilePaths;
        wx.uploadFile({
          url: app.api.user.uploadFaceImage,
          filePath: tempFilePaths[0],
          header: {
            'content-type': 'application/json',
            'token': wx.getStorageSync('token') || ''
          },
          name: 'file',
          formData: {
            id: app.userInfo.id
          },
          success(res) {
            const data = res.data
            let response = JSON.parse(data);
            console.log(response)
            if (response.code === 0) {
              //更新app中的用户信息
              app.userInfo['faceImage'] = response.data.faceImage;
              //设置当前用户信息
              _this.setData({
                faceImage: `${app.domain}/${response.data.faceImage}`
              })
            } else {
              toastTip(response.msg)
            }
          }
        })
      }
    })
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