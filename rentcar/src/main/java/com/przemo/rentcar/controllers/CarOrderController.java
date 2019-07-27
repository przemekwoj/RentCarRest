package com.przemo.rentcar.controllers;

import com.przemo.rentcar.orders.CarOrder;
import com.przemo.rentcar.orders.CarOrderDetails;
import com.przemo.rentcar.services.CarOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/order")
public class CarOrderController
{
    @Autowired
    private CarOrderServiceImpl carOrderService;

    @GetMapping("/{id}")
    public CarOrder getOrderById(@PathVariable Long id)
    {
        return carOrderService.getOrderById(id).get();
    }

    @GetMapping("details/{id}")
    public CarOrderDetails getOrderDetailsById(@PathVariable Long id)
    {
        return carOrderService.getOrderDetailsById(id).get();
    }


}
