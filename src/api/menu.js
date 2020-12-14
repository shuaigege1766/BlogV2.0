import request from "../utils/request"
//后台用户登录
//这里指定使用login 后台使用了spring security框架  或者后台去设置一下 其他的login请求路径
export  const getMenuList=()=>{
  return request({
    url: "menu/getMenuList",
    method: "get",
  })
}
