package com.xiaoshuai.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 10-21-下午 18:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    @ApiModelProperty(value = "错误码")
    private  Integer code;
    @ApiModelProperty(value = "错误信息")
    private  String errMsg;

    public BusinessException(String errMsg){
          this.errMsg=errMsg;
    }

}
