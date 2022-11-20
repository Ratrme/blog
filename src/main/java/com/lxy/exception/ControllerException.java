package com.lxy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author lxy
 * @Description 异常处理，返回到页面
 * @Date 2022/11/20 20:45
 */

@Slf4j
@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, java.lang.Exception e) throws Exception {
        log.error("Request URL :{},ControllerException : {}",request.getRequestURL(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("url",request.getRequestURL());
        mav.addObject("exception",e);
        mav.setViewName("error/error");
        return mav;
    }
}
