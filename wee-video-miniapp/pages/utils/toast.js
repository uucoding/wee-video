/**提供小程序的三种提示方式，引入js后，一行代码提示*/
/**
 * 提示成功
 */
let toastSuccess = function(title) {
  wx.showToast({
    title: title,
    icon: 'success',
    duration: 1000,
  })
}

/**
 * 提示失败
 */
let toastError = function (title) {
  wx.showToast({
    title: title,
    icon: 'error',
    duration: 2000,
  })
}

/**
 * 普通小提示
 */
let toastTip = function(title) {
  wx.showToast({
    title: title,
    icon: 'none',
    duration: 2000,
  })
}

module.exports = { toastSuccess, toastError, toastTip}