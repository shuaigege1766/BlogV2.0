
import request from "../utils/request"


export  const listCategory=(current,size,categoryName)=>{
  return request({
    url: "/category/listCategory",
    method: "get",
    params:{
      current,
      size,
      categoryName
    }
  })
}

export  const addOrEditCategory=(data)=>{
  return request({
    url: "/category/addOrEditCategory",
    method: "post",
    data:data
  })
}

export  const deleteCategory=(categoryId)=>{
  return request({
    url: "/category/deleteCategory",
    method: "delete",
    params: {
    categoryId
    }
  })
}
