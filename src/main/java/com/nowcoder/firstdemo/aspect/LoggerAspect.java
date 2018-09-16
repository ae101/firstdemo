package com.nowcoder.firstdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by ae on 2018/8/11.
 */
@Aspect
@Component
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    @Before("execution(* com.nowcoder.firstdemo.controller.IndexController.*(..))")//控制这before和after的切面范围
    public void beforeM(JoinPoint joinPoint) {
//        System.out.print("++++++++");
        StringBuffer sb = new StringBuffer();
        for (Object args: joinPoint.getArgs()) {
            sb.append("args" + args.toString() + "|");
        }
        logger.info("before Method:" + sb.toString());
    }

    @After("execution(* com.nowcoder.firstdemo.controller.IndexController.*(..))")//exction里的内容不对会影响logger的读取
    public void afterM() {
        logger.info("after Method" + new Date());
    }

}
