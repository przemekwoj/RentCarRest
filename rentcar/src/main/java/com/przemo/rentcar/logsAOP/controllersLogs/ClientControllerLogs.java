package com.przemo.rentcar.logsAOP.controllersLogs;

import com.przemo.rentcar.entities.users.Client;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class ClientControllerLogs {
    private final Logger log = LoggerFactory.getLogger(ClientControllerLogs.class);

    @Pointcut("execution(* com.przemo.rentcar.controllers.ClientController.*(..))")
    public void allMethods() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Client com.przemo.rentcar.controllers.ClientController.getClientById(Long))")
    public void getClientById() {
    }

    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.ClientController.getClients())")
    public void getClients() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Client com.przemo.rentcar.controllers.ClientController.addClient(com.przemo.rentcar.entities.users.Client))")
    public void addClient() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Client com.przemo.rentcar.controllers.ClientController.updateClient(com.przemo.rentcar.entities.users.Client,Long))")
    public void updateClient() {
    }

    @Before("getClientById()")
    public void logGetClientById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get client with id {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getClientById()", returning = "client")
    public void logReturnGetClientById(Client client) {
        log.trace("client returned : {}", client);
        if (client == null) {
            log.warn("Returned client is null");
        }
    }

    @Before("getClients()")
    public void logGetClients() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get clients ", authentication.getName());
    }

    @AfterReturning(pointcut = "getClients()", returning = "clients")
    public void logReturnGetClients(List<Client> clients) {
        log.trace("client list returned : {}", clients);
        if (clients.isEmpty()) {
            log.warn("Returned client is empty");
        }
    }

    @Before("addClient()")
    public void logAddClient(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.error("User with email {} , try to add client ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "addClient()", returning = "client")
    public void logAddClient(Client client) {

        log.error("added client : {}", client);
        if (client == null) {
            log.error("added client is null");
        }
    }

    @Before("updateClient()")
    public void logUpdateClient(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.error("User with email {} , try update client with values {} and id {} ", authentication.getName(), joinPoint.getArgs()[0], joinPoint.getArgs()[1]);
    }

    @AfterReturning(pointcut = "updateClient()", returning = "client")
    public void logReturnUpdateClient(Client client) {
        log.error("updated client client : {}", client);
        if (client == null) {
            log.error("updated client is nul");
        }
    }

    @AfterThrowing(value = "allMethods()", throwing = "e")
    public void throwsErrorInClientController(Exception e) {
        log.error(e.getMessage());
    }
}
