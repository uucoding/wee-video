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
      wx.redirectTo({
        url: '/pages/login/login'
      })
    }
    let _this = this;
    let user = wx.getStorageSync('user');
    _this.setData({
      user: user,
      faceImage: `${app.domain}${user.faceImage}`
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
    let user = wx.getStorageSync('user');
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
            id: this.data.user.id
          },
          success(res) {
            const data = res.data
            let response = JSON.parse(data);
            if (response.code === 0) {
              //更新缓存中的用户信息
              user.faceImage = response.data;
              wx.setStorageSync('user');
              //设置当前用户信息
              _this.setData({
                faceImage: `${app.domain}${response.data}`
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
   * 上传视频
   */
  uploadVideo: function() {
    wx.chooseVideo({
      sourceType: ['album', 'camera'],
      maxDuration: 60,
      camera: 'back',
      success(res) {
        if (res.duration > 60) {
          toastTip("视频不能超过60秒！");
          return false;
        }
        if (res.duration <= 1) {
          toastTip("视频不能小于1秒！")
          return false
        }
        //跳转至上传视频页面
        wx.navigateTo({
          url: `/pages/uploadVideo/uploadVideo?duration=${res.duration}&width=${res.width}&height=${res.height}&size=${res.size}&path=${res.tempFilePath}`
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