// server基础配置
let server = {
  host: "http://localhost",
  port: "8080"
}
//domain 配置
let domain = `${server.host}:${server.port}`;

//api 配置
let api = {
  // 用户相关
  user : {
    register: `${domain}/api/v1/user/register`,
    login: `${domain}/api/v1/user/login`
  }
}

module.exports.api = api