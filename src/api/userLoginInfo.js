import request from "../utils/request"

//查询用户登录信息列表
export  const userLoginInfoList=(current,size,nickname)=>{
  return request({
    url: "/userLogin/getUserInfoList",
    method: "get",
    params:{
      current,
      size,
      nickname
    }
  })
}
