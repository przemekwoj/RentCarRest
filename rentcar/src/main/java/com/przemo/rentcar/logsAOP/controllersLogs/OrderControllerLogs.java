package com.przemo.rentcar.logsAOP.controllersLogs;

import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.models.OrderInfo;
import com.przemo.rentcar.models.OrderInfoDTO;
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
public class OrderControllerLogs {
    private final Logger log = LoggerFactory.getLogger(OrderControllerLogs.class);

    @Pointcut("execution(* com.przemo.rentcar.controllers.OrderController.*(..))")
    public void allMethods(){
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.orders.CarOrder com.przemo.rentcar.controllers.OrderController.getOrderById(Long))")
    public void getOrderById() {
    }

    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.OrderController.getOrdersInfo())")
    public void getOrdersInfo() {
    }

    @Pointcut("execution(public com.przemo.rentcar.models.OrderInfo com.przemo.rentcar.controllers.OrderController.createNewOrder(com.przemo.rentcar.models.OrderInfo))")
    public void createNewOrder() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.orders.CarOrderDetails com.przemo.rentcar.controllers.OrderController.getOrderDetailsById(Long))")
    public void getOrderDetailsById() {
    }

    @Pointcut("execution(public void com.przemo.rentcar.controllers.OrderController.deleteOrderById(Long))")
    public void deleteOrderById() {
    }

    @Before("getOrderById()")
    public void logGetOrderById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try get order with id : {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getOrderById()", returning = "order")
    public void logReturnGetOrderById(CarOrder order) {
        log.trace("returned order : {}", order);
        if (order == null) {
            log.warn("order is null");
        }
    }

    @AfterThrowing(value = "getOrderById()", throwing = "e")
    public void logAfterThrowingGetOrderById(Exception e) {
        log.error(e.getMessage());
    }

    @Before("getOrdersInfo()")
    public void logGetOrderInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try get orders info dto", authentication.getName());
    }

    @AfterReturning(pointcut = "getOrdersInfo()", returning = "orderInfoDTO")
    public void logReturnGetOrderInfo(List<OrderInfoDTO> orderInfoDTO) {
        log.trace("returned orderInfoDTO list");
        if (orderInfoDTO.isEmpty()) {
            log.warn("orderInfoDTO is empty");
        }
    }

    @AfterThrowing(value = "getOrdersInfo()", throwing = "e")
    public void logAfterThrowingGetOrdersInfo(Exception e) {
        log.error(e.getMessage());
    }

    @Before("getOrderDetailsById()")
    public void logGetOrderDetailsById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try get carOrderDetails with id : {}", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getOrderDetailsById()", returning = "carOrderDetails")
    public void logReturnGetOrderDetailsById(CarOrderDetails carOrderDetails) {
        log.trace("returned carOrderDetails {}", carOrderDetails);
        if (carOrderDetails == null) {
            log.warn("carOrderDetails is null");
        }
    }

    @AfterThrowing(value = "getOrderDetailsById()", throwing = "e")
    public void logAfterThrowingGetOrderDetailsById(Exception e) {
        log.error(e.getMessage());
    }

    @Before("createNewOrder()")
    public void logCreateNewOrder(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to create new order : {}", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "createNewOrder()", returning = "orderInfo")
    public void logReturnCreateNewOrder(OrderInfo orderInfo) {
        log.trace("created orderInfo {}", orderInfo);
        if (orderInfo == null) {
            log.warn("orderInfo is null");
        }
    }

    @AfterThrowing(value = "createNewOrder()", throwing = "e")
    public void logAfterThrowingCreateNewOrder(Exception e) {
        log.error(e.getMessage());
    }

    @Before("deleteOrderById()")
    public void logDeleteOrderById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try to delete order with id : {}", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "deleteOrderById()")
    public void logReturnCreateNewOrder(JoinPoint joinPoint) {
        log.warn("orderd deleted with id: {}", joinPoint.getArgs()[0]);
    }

    @AfterThrowing(value = "deleteOrderById()", throwing = "e")
    public void logAfterThrowingDeleteOrderById(Exception e) {
        log.error(e.getMessage());
    }

    @AfterThrowing(value = "allMethods()", throwing = "e")
    public void throwsErrorInOrderController(Exception e){
        log.error(e.getMessage());
    }
}
