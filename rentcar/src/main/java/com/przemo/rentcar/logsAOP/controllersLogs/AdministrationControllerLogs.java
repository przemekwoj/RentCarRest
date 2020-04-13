package com.przemo.rentcar.logsAOP.controllersLogs;

import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Employee;
import com.przemo.rentcar.entities.users.Supervisor;
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
public class AdministrationControllerLogs {
    private final Logger log = LoggerFactory.getLogger(AdministrationControllerLogs.class);

    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.AdministrationController.getAllAdministrations())")
    public void getAllAdministrations() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Administration com.przemo.rentcar.controllers.AdministrationController.getAdministratorById(Long))")
    public void getAdministratorById() {
    }

    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.AdministrationController.getAllEmployees())")
    public void getAllEmployees() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Administration com.przemo.rentcar.controllers.AdministrationController.getIdByMail(String))")
    public void getAdministrationByEmail() {
    }

    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.AdministrationController.getAllSupervisors())")
    public void getAllSupervisors() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Employee com.przemo.rentcar.controllers.AdministrationController.updateEmployee(com.przemo.rentcar.entities.users.Employee,Long))")
    public void updateEmployee() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Supervisor com.przemo.rentcar.controllers.AdministrationController.updateSupervisor(com.przemo.rentcar.entities.users.Supervisor,Long))")
    public void updateSupervisor() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Employee com.przemo.rentcar.controllers.AdministrationController.addEmployee(com.przemo.rentcar.entities.users.Employee))")
    public void addEmployee() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.users.Supervisor com.przemo.rentcar.controllers.AdministrationController.addSupervisor(com.przemo.rentcar.entities.users.Supervisor))")
    public void addSupervisor() {
    }

    @Pointcut("execution(public void com.przemo.rentcar.controllers.AdministrationController.deleteAdministratorById(Long))")
    public void deleteAdministratorById() {
    }

    @Pointcut("execution(* com.przemo.rentcar.controllers.AdministrationController.*(..))")
    public void allMethods(){
    }

    @Before("getAllAdministrations()")
    public void logGetAllAdministrations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get all administrations ", authentication.getName());
    }

    @AfterReturning(pointcut = "getAllAdministrations()", returning = "administrations")
    public void logReturnGetAllAdministrations(List<Administration> administrations) {
        if (administrations.isEmpty()) {
            log.warn("Returned administration list is empty");
        }
    }

    @Before("getAdministratorById()")
    public void logGetAdministratorById(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get administration with id {} ", authentication.getName(), joinpoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getAdministratorById()", returning = "administration")
    public void logReturnGetAdministratorById(Administration administration) {
        log.trace("returned administration {}", administration);
    }

    @Before("getAllEmployees()")
    public void logGetAllEmployees(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get administration with id {} ", authentication.getName(), joinpoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getAllEmployees()", returning = "employees")
    public void logReturnGetAllEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            log.warn("employees list is empty");
        }
    }

    @Before("getAdministrationByEmail()")
    public void logGetAdministrationByEmail(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get administration with email {} ", authentication.getName(), joinpoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getAdministrationByEmail()", returning = "administration")
    public void logReturnGetAdministrationByEmail(Administration administration) {
        log.trace("returned administration {}", administration);
    }

    @Before("getAllSupervisors()")
    public void logGetAllSupervisors() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get supervisors", authentication.getName());
    }

    @AfterReturning(pointcut = "getAllSupervisors()", returning = "supervisors")
    public void logReturnGetAllSupervisors(List<Supervisor> supervisors) {
        if (supervisors.isEmpty()) {
            log.warn("supervisors list is empty");
        }
    }

    @Before("updateEmployee()")
    public void logUpdateEmployee(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User with email {} , try to update employee with body : {} and id :{}",
                authentication.getName(), joinpoint.getArgs()[0], joinpoint.getArgs()[1]);
    }

    @AfterReturning(pointcut = "updateEmployee()", returning = "employee")
    public void logReturnUpdateEmployee(Employee employee) {
        log.info("employee after update : {}", employee);
    }

    @Before("updateSupervisor()")
    public void logUpdateSupervisor(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User with email {} , try to update supervisor with body : {} and id :{}",
                authentication.getName(), joinpoint.getArgs()[0], joinpoint.getArgs()[1]);
    }

    @AfterReturning(pointcut = "updateSupervisor()", returning = "supervisor")
    public void logReturnUpdateSupervisor(Supervisor supervisor) {
        log.info("supervisor after update : {}", supervisor);
    }

    @Before("addEmployee()")
    public void logAddEmployee(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User with email {} , try to add employee with body : {}",
                authentication.getName(), joinpoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "addEmployee()", returning = "employee")
    public void logReturnAddEmployee(Employee employee) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("employee added : {} , by user with email {}", employee, authentication.getName());
    }

    @Before("addSupervisor()")
    public void logAddSupervisor(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User with email {} , try to add supervisor with body : {}",
                authentication.getName(), joinpoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "addSupervisor()", returning = "supervisor")
    public void logReturnSupervisor(Supervisor supervisor) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("supervisor added : {} , by user with email {}", supervisor, authentication.getName());
    }


    @Before("deleteAdministratorById()")
    public void logDeleteAdministratorById(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try to delete administration with id : {}",
                authentication.getName(), joinpoint.getArgs()[0]);
    }

    @AfterReturning("deleteAdministratorById()")
    public void logAfterDeleteAdministratorById(JoinPoint joinpoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , delete administration with id : {}",
                authentication.getName(), joinpoint.getArgs()[0]);
    }

    @AfterThrowing(value = "allMethods()", throwing = "e")
    public void throwsErrorInAdministrationController(Exception e){
        log.error(e.getMessage());
    }
}
