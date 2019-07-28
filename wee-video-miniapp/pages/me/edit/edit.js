//edit.js
//获取应用实例
const app = getApp()
const {
  $
} = require("../../utils/request.js")
const {
  toastSuccess
} = require("../../utils/toast.js")

Page({
  data: {
    tableInputList: [
      {
        label: "昵称1",
        name: "nickname",
        value: "aaaa"
      },
      {
        label: "mini号1",
        name: "username",
        value: "aaaaaaa"
      },
      {
        label: "密码1",
        isPassword: true,
        name: "password",
        value: "neaaaaxt"
      }
    ]
  },
  /**
   * 回传给wee-table-input组件的事件
   * 用于做最后一步处理
   */
  modifyUser: function(e) {
    console.log(e)
  }
})