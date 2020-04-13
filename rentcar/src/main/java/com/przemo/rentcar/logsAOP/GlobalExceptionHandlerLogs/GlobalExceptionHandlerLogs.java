package com.przemo.rentcar.logsAOP.GlobalExceptionHandlerLogs;

import com.przemo.rentcar.exceptions.ApiError;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalExceptionHandlerLogs {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerLogs.class);

    @Pointcut("execution(* com.przemo.rentcar.exceptions.GlobalExceptionHandler.*(..))")
    public void allMethods() {
    }

    @Before("allMethods()")
    public void logsError(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Exception e = (Exception) joinPoint.getArgs()[0];
        log.error("User with email {} , invoke error : {} ", authentication.getName(),e.getMessage());
    }

    @AfterReturning(pointcut = "allMethods()", returning = "responseEntity")
    public void logReturnedEntity(ResponseEntity<Object> responseEntity) {
        ApiError apiError = (ApiError) responseEntity.getBody();
        log.error("returned entity :{} . At time : {}",apiError.getMessage(),apiError.getTimestamp());
        if (responseEntity == null) {
            log.error("Returned entity in null");
        }
    }
}
