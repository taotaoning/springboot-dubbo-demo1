package com.nt.demo.exceptionHandler;


import com.nt.demo.middle.annotations.ResponseMessage;
import com.nt.demo.middle.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author  by TaoTaoNing
 * 2019-11-19
 **/

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(value = {MyException.class})
    public ResponseMessage paramErrorServletRequest(MyException ex){
        System.out.println("ExceptionHandler ========== " + ex.getMessage());
        return new ResponseMessage(ex.getErrorCode(), ex.getMessage());
    }
}
