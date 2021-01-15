package com.jtrio.zagzag.aop;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.jtrio.zagzag..*Controller.*(..)) && !@annotation(com.jtrio.zagzag.aop.NoLogging)")
    public void logging(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("userId: {}, request: {}, params: {}", securityUser.getUserId(), request.getRequestURL(), joinPoint.getArgs());
    }
}
