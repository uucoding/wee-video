// components/tableView.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    tableViewList: {
      type: Array,
      value: []
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    /**
     * tableViewList说明：设置列表展示
     * label: 标题
     * page: 点击跳转的页面
     * content：值，
     */
    tableViewList: [
      {
        label: "昵称",
        page: "/page",
        content: "小小张"
      },
      {
        label: "mini号",
        page: "/page",
        content: "weecoding"
      },
      {
        label: "密码",
        page: "/page",
        content: "******"
      }
    ]
  }
})
