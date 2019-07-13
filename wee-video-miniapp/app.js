//domain 配置
let domain = "http://localhost:8080"
//app.js
App({
  //api 配置
  api: {
    // 用户相关
    user: {
      register: `${domain}/api/v1/user/register`,
      login: `${domain}/api/v1/user/login`
    }
  }
})