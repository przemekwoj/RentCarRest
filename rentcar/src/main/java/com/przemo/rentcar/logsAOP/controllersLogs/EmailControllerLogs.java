package com.przemo.rentcar.logsAOP.controllersLogs;

import com.przemo.rentcar.models.Email;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailControllerLogs {
    private final Logger log = LoggerFactory.getLogger(EmailControllerLogs.class);

    @Pointcut("execution(* com.przemo.rentcar.controllers.EmailController.*(..))")
    public void allMethods(){
    }
    @Pointcut("execution(public com.przemo.rentcar.models.Email com.przemo.rentcar.controllers.EmailController.sendEmail(com.przemo.rentcar.models.Email))")
    public void sendEmailToRabbit() {
    }

    @Before("sendEmailToRabbit()")
    public void logGetClientById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User with email {} , try to push email to queue {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "sendEmailToRabbit()", returning = "email")
    public void logReturnGetClientById(Email email) {
        log.info("pushed email to rabbit queue: {}", email);
        if (email == null) {
            log.warn("email pushed is null");
        }
    }

    @AfterThrowing(value = "allMethods()", throwing = "e")
    public void throwsErrorInEmailController(Exception e){
        log.error(e.getMessage());
    }
}
