// components/tabbar.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    tabbars: { // 属性名
      type: Array,
      value: []
    },
    tabActiveIndex: {
      type: Number,
      value: 0
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    /**
     * tabbars说明：设置tabbar上的参数，具体描述如下
     * text:  (必填)tabbar显示的名称
     * page:  (必填)点击跳转的页面
     * icons: (必填)tabbar的图片数组，设置两个：第一个为默认样式，第二个为选中样式
     */
    tabbars: [
      {
        text: "",
        icons: [],
        page: ""
      }
    ],
    tabActiveIndex: 0, 
  },

  /**
   * 组件的方法列表
   */
  methods: {
    //切换点击后的图标
    toggle: function(e) {
      //获取点击的下表
      this.setData({
        tabActiveIndex: e.currentTarget.dataset.index
      }) 
    }
  },
  lifetimes: {
    /**
     * 将根据
     * 注：最多只允许设置五个bar，超过五个则裁剪出前五个
     */
    attached: function () {
      //获取初始的tabbar数据
      let sliceTabbars = this.data.tabbars.slice(0, 5);
      this.setData({
        tabbars: sliceTabbars,
      })
     },
  }
})
