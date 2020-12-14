package com.xiaoshuai.handler;
import com.xiaoshuai.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 10-21-下午 18:02
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result err(Exception e){
        log.error(e.toString());
        e.printStackTrace();
        return  Result.error();
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result err1(BusinessException e){
       log.error(e.getErrMsg());
       return  Result.error().code(e.getCode()).message(e.getErrMsg());
    }


}
