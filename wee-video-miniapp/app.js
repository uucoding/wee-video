//domain 配置
let domain = "http://localhost:8080";
//app.js
App({
  tabbars: [
    {
      icons: ['/resource/images/bar_index.png', '/resource/images/bar_index_on.png'],
      text: "首页",
      page:"/pages/index/index"
    },
    {
      icons: ['/resource/images/bar_me.png', '/resource/images/bar_me_on.png'],
      text: "个人中心",
      page: "/pages/me/me"
    }
  ],
  //api 配置
  api: {
    // 匿名访问
    anonymous: {
      register: `${domain}/api/v1/anonymous/register`,
      login: `${domain}/api/v1/anonymous/login`
    },
    // 用户相关
    user: {
    }
  },
  //每当打开小程序，都需要向后台发送一个请求用以监听是否登陆
  onShow: function() {
    
  }
})