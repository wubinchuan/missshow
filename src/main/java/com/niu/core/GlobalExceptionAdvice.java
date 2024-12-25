package com.niu.core;

import com.niu.core.configuration.ExceptionCodeConfiguration;
import com.niu.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req,Exception e){
       //异常分类：基类Throwable-error/Exception-错误和异常有什么区别，错误是系统级别的错误，异常可以通过代码处理
       //异常主要分为两种-RuntimeException/CheckException，后者必须处理，或者往外抛，前者则不是，不一定在编译阶段就能发现
       //自定义是集成那个异常，有全局异常的时候，这两个分类其实没什么区别，在外层都得到了统一的处理，如果说有异常必须在编译的时候处理
       //则需要定义为cheackexception（A调用B的c方法，但是c方法不存在）bug，但是一般是RuntimeException（查用户修改密码，但是用户查不到）
       //再次分类可以分为已知异常和未知异常，我们改如何处理
       //未知错误一般不会直接把错误信息返回给前端
        String requestUrl=req.getRequestURI();
        String method=req.getMethod();
        UnifyResponse message = new UnifyResponse(999,"服务器异常",method+" "+requestUrl);
        return message;
    }
    //自定义错误
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req,HttpException e){
        String requestUrl=req.getRequestURI();
        String method=req.getMethod();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus status= HttpStatus.resolve(e.getHttpStatusCode());
        UnifyResponse message = new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),method+" "+requestUrl);
        ResponseEntity<UnifyResponse> r = new ResponseEntity<>(message,headers,status);
        return r;
    }
    //处理body里出现错误
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UnifyResponse handleBeanValidation(HttpServletRequest req,MethodArgumentNotValidException e){
        String requestUrl=req.getRequestURI();
        String method=req.getMethod();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message=this.formatAllErrorMessage(allErrors);
        UnifyResponse messages = new UnifyResponse(1001,message,method+" "+requestUrl);
        return messages;
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UnifyResponse handleConstraintException(HttpServletRequest req,ConstraintViolationException e){
        String requestUrl=req.getRequestURI();
        String method=req.getMethod();
        UnifyResponse messages = new UnifyResponse(1002,e.getMessage(),method+" "+requestUrl);
        return messages;
    }
    private String formatAllErrorMessage(List<ObjectError> allErrors ){
        StringBuilder errorMag=new StringBuilder();
         allErrors.forEach(error->errorMag.append(error.getDefaultMessage()).append(";"));
         return errorMag.toString();
    }
}
