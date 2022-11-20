package com.lxy.aop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;


/**
 * @Author lxy
 * @Description Log处理的实体类
 * @Date 2022/11/20 22:14
 */

@Getter
@Setter
@AllArgsConstructor
public class RequestLog {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;


    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
