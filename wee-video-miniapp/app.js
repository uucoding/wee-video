//domain 配置
let domain = "http://localhost:8080";
//app.js
App({
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
  }
})