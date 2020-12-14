import request from "../utils/request"



export  const getApiInfoFromSwagger=()=>{
  return request({
    url: "/v2/api-docs",
    method: "get"
  })
}

export  const saveOrUpdateApiFromSwagger=(data)=>{
  return request({
    url: "/api/saveOrUpdateApiFromSwagger",
     method:"post",
    data:data
  })
}

export  const listApiInfoBack=(current,size,apiId)=>{
  return request({
    url: "/api/listApiInfoBack",
    method:"get",
    params:{
       current,
       size,
       apiId
    }
  })
}

export  const getRoleList=()=>{
  return request({
    url: "/role/getRoleList",
    method:"get",
  })
}

export  const getAssignedApiIdByUserRoleId=(actionId)=>{
  return request({
    url: "/role/getAssignedApiIdByUserRoleId",
    method:"get",
    params:{
      actionId
    }
  })
}

export  const saveRolePermissionList=(roleId,roleApiVO)=>{
  return request({
    url: "/role/saveRolePermissionList",
    method:"post",
    params:{
      roleId
    },
    data:roleApiVO

  })
}

