/**
 * 封装get/post请求，全部使用json形式,只需要传递：url ,data, 成功后响应数据
 * 注：登陆成功后，后端会颁发一个唯一token给小程序，需要将token存入缓存中，之后的请求头都将带着token访问
 * token存入缓存
 * 后端传递回前端的数据统一为：{code:xx, msg:xx, data:xx}
 * code表示状态码，只有0表示响应成功，其他状态码均为失败，data不论成功或失败都有可能存在数据()，或不存在数据
 * 请求错误的时候，统一提示网络异常
 */
const {
  toastSuccess,
  toastLoding,
  toastTip
} = require("./toast.js");

let Request = {
  /**
   * get请求
   * @param：url    请求的url
   * @param: data   请求的参数
   * @param: success 请求响应成功的函数  (不传递，使用默认处理方案，仅仅只做提示处,不做其他处理)
   * @param: callback 请求响应成功的回调函数（如果不使用success且不使用默认提示，那么需要设置此参数）
   * 
   * 注：请求成功的回调函数如果未设置，则使用默认的成功回调函数
   *    使用默认函数时，默认处理的方案只提示“操作成功”，如需要自定义，请穿入成功后执行函数callback
   */
  get: function ({ url, data, success, callback} = params) {
    wx.showLoading({
      title: '加载中...'
    })
    wx.request({
      url: params.url,
      data: params.data,
      header: {
        'content-type': 'application/json',

        'token': wx.getStorageSync('token') || ''
      },
      method: 'GET',
      dataType: 'json',
      success: params.success || function(data) {
        //默认处理方案
        let response = data.data;
        if (response.code === 0) {
          params.callback ? params.callback(response) : toastSuccess(response.msg);
        } else {
          toastTip(response.msg)
        }
      },
      fail: function(e) {
        toastTip('网络异常，请稍后再试')
      },
      complete: function () {
        wx.hideLoading()
      }
    })
  },
  /**
   * post请求
   * @param：url    请求的url
   * @param: data   请求的参数
   * @param: success  请求响应成功的函数  (不传递，使用默认处理方案，仅仅只做提示处,不做其他处理)
   * @param: callback 请求响应成功的回调函数（如果不使用success且不使用默认提示，那么需要设置此参数）
   * 
   * 注：请求成功的回调函数如果未设置，则使用默认的成功回调函数
   *    使用默认函数时，默认处理的方案只提示“操作成功”，如需要自定义，请穿入成功后执行函数callback
   */
  post: function ({ url, data, success, callback }) {
    wx.showLoading({
      title: '加载中...'
    })
    wx.request({
      url: url,
      data: data,
      header: {
        'content-type': 'application/json',
        'token': wx.getStorageSync('token') || ''
      },
      method: 'POST',
      dataType: 'json',
      success: success || function(data) {
        console.log(data)
        //默认处理方案
        let response = data.data;
        if (response.code === 0) {
          callback ? callback(response) : toastSuccess(response.msg);
        } else {
          toastTip(response.msg)
        }
      },
      fail: function(res) {
        toastTip('网络异常，请稍后再试')
      },
      complete: function() {
        console.log('=========')
        wx.hideLoading()
      }
    })
  }
}

module.exports.$ = Request