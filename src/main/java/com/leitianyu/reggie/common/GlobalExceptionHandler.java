package com.leitianyu.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/*
处理全局异常
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    //处理重复异常
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public  R<String> ExceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        //判断关键字
        if (ex.getMessage().contains("Duplicate entry")){
            String[] s = ex.getMessage().split(" ");
            String msg = s[2]+"已存在";
            return R.error(msg);
        }


        return R.error("未知错误");
    }
    //处理分类异常
    @ExceptionHandler(CustomException.class)
    public  R<String> ExceptionHandler(CustomException ex){
        log.error(ex.getMessage());



        return R.error(ex.getMessage());
    }
}
