// components/tableinput.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    tableInputList: {
      type: Array,
      value: []
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    /**
     * tableInputList说明：信息修改列表展示，传递数组，每个对象都代表一个input
     * 需要注意的是，键盘右下角的值为next,点击后自动聚焦下一个input,最后一个为done,点击后会触发submitEvent事件
     * label: 名称
     * isPassword: 默认fasle,如果有密码需要手动设置为true
     * name: input的name值
     * value：input的value值
     */
    tableInputList: [
      {
        label: "昵称",
        isPassword: false,
        confirmType: "next",
        name: "nickname",
        value: "aaaa"
      },
      {
        label: "mini号",
        isPassword: false,
        confirmType: "next",
        name: "username",
        value: "aaaaaaa"
      },
      {
        label: "密码",
        isPassword: true,
        confirmType: "done",
        name: "password",
        value: "neaaaaxt"
      }
    ],
    //默认聚焦为第一个input
    focusIndex: 0,
    //根据集合构建一个form表单
    formData:{}
  },
  methods: {
    /**
     * 如果input下标和数组长度相同，表示已经定位到最后一个，那么提交数据
     * 反之，光标定位到下一个input中，当定位到最后一个input框的时候，调用使用组件方传递过来的submitEvent方法
     */
    confirm: function(e) {
      //获取输入框的下标和输入框的name值，组装待提交的数据
      let inputIndex = e.currentTarget.dataset.inputIndex;
      let inputName = e.currentTarget.dataset.inputName;
      //设置表单的提交数据
      this.setData({
        //给form对象追加属性
        [`formData.${inputName}`]: e.detail.value,
      });
      if (e.currentTarget.dataset.inputIndex === this.data.tableInputList.length - 1) {
        this.submitEvent();
      } else {
        //设置聚焦的下标，光标下移一位
        this.setData({
          focusIndex: inputIndex + 1, 
        });
      }
    },
    submitEvent: function() {
      let myEventDetail = this.data.formData // detail对象，提供给事件监听函数
      let myEventOption = {} // 触发事件的选项
      this.triggerEvent('submit', myEventDetail, myEventOption)
    }
  }, 
  lifetimes: {
    // 数据传递进来之后，需要对数据设置一些默认值
    attached: function () {
      let inputList = this.data.tableInputList
      for (let i = 0; i < inputList.length; i++) {
        //设置密码
        if (!inputList[i]['password']) {
          inputList[i]['password'] = false;
        }
        //设置除最后一个input以外的其他input的confirmType = next 最后一个为done
        inputList[i]['confirmType'] = (i === inputList.length - 1) ? 'done' : 'next'
      }
      //初始化
      this.setData({
        tableInputList: inputList
      })
      console.log(this.data.tableInputList)
    }
  }
})
