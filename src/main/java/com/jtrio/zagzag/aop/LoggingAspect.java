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
    @Before("execution(* com.jtrio.zagzag..*Controller.*(..))")
    public void logging(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object securityUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("userId: {}, request: {}, params: {}", logInfo(securityUser), request.getRequestURL(), joinPoint.getArgs());
    }

    public Long logInfo(Object securityUser) {
        Long userId;
        if (securityUser instanceof SecurityUser) {
            userId = ((SecurityUser) securityUser).getUserId();
        } else {
            userId = -1L;
        }
        return userId;
    }
}