package com.przemo.rentcar.logsAOP.controllersLogs;


import com.przemo.rentcar.dto.carsDto.BrandDto;
import com.przemo.rentcar.entities.cars.Brand;
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
public class BrandControllerLogs {
    private final Logger log = LoggerFactory.getLogger(BrandControllerLogs.class);

    @Pointcut("execution(* com.przemo.rentcar.controllers.BrandController.*(..))")
    public void allMethods(){
    }
    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.BrandController.getAllBrands())")
    public void getAllBrands() {
    }

    @Pointcut("execution(public com.przemo.rentcar.dto.carsDto.BrandDto com.przemo.rentcar.controllers.BrandController.getBrandById(Long))")
    public void getBrandById() {
    }

    @Pointcut("execution(public java.util.List com.przemo.rentcar.controllers.BrandController.getAllBrandsWithCarsAndDetails())")
    public void getAllBrandsWithCarsAndDetails() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.cars.Brand com.przemo.rentcar.controllers.BrandController.addNewBrand(com.przemo.rentcar.entities.cars.Brand))")
    public void addNewBrand() {
    }

    @Pointcut("execution(public void com.przemo.rentcar.controllers.BrandController.deleteBrandById(Long))")
    public void deleteBrandById() {
    }

    @Pointcut("execution(public com.przemo.rentcar.entities.cars.Brand com.przemo.rentcar.controllers.BrandController.updateBrand(com.przemo.rentcar.entities.cars.Brand))")
    public void updateBrand() {
    }

    @Before("getAllBrands()")
    public void logGetAllBrands() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get all brands ", authentication.getName());
    }

    @AfterReturning(pointcut = "getAllBrands()", returning = "brands")
    public void logReturnGetAllBrands(List<BrandDto> brands) {
        log.trace("all brands list returned");
        if (brands.isEmpty()) {
            log.warn("Returned administration list is empty");
        }
    }


    @Before("getBrandById()")
    public void logGetBrandById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get brand with id : {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "getBrandById()", returning = "brand")
    public void logReturnGetBrandById(BrandDto brand) {
        log.trace("returned brand : {}", brand.toString());
        if (brand == null) {
            log.warn("Brand is null");
        }
    }

    @Before("getAllBrandsWithCarsAndDetails()")
    public void logGetAllBrandsWithCarsAndDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("User with email {} , try to get brand with car details : {} ", authentication.getName());
    }

    @AfterReturning(pointcut = "getAllBrandsWithCarsAndDetails()", returning = "brands")
    public void logReturnGetAllBrandsWithCarsAndDetails(List<Brand> brands) {
        log.trace("returned brands");
        if (brands.isEmpty()) {
            log.warn("Brands list is empty");
        }
    }

    @Before("addNewBrand()")
    public void logAddNewBrand(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try add brand : {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "addNewBrand()", returning = "brand")
    public void logReturnAddNewBrand(Brand brand) {
        log.warn("brand added : {}", brand);
        if (brand == null) {
            log.warn("added brand is null");
        }
    }

    @Before("deleteBrandById()")
    public void logDeleteBrandById(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try delete brand with id : {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "deleteBrandById()")
    public void logReturnDeleteBrandById() {
        log.warn("brand deleted");
    }

    @Before("updateBrand()")
    public void logUpdateBrand(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("User with email {} , try update brand with values : {} ", authentication.getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "updateBrand()", returning = "brand")
    public void logReturnUpdateBrand(Brand brand) {
        log.warn("brand updated : {}", brand);
        if (brand == null) {
            log.warn("brand is null");
        }
    }

    @AfterThrowing(value = "allMethods()", throwing = "e")
    public void throwsErrorInBrandController(Exception e){
        log.error(e.getMessage());
    }
}
