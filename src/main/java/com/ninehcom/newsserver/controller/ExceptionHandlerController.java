package com.ninehcom.newsserver.controller;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.untils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServlet;

/**
 * Created by Administrator on 2017/5/8.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public Result ddd(HttpServlet req , Exception ex){
        return Result.Fail(1,ex.getMessage());
    }

}
