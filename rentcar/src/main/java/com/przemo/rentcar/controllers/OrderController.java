package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.models.OrderInfo;
import com.przemo.rentcar.models.OrderInfoDTO;
import com.przemo.rentcar.services.CarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/order")
public class OrderController {

    private final CarOrderService carOrderService;

    @Autowired
    public OrderController(CarOrderService carOrderService) {
        this.carOrderService = carOrderService;

    }

    @GetMapping("/{id}")
    public CarOrder getOrderById(@PathVariable Long id)
    {
        return carOrderService.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<OrderInfoDTO> getOrdersInfo(){

        return carOrderService.getOrdersInfo();
    }

    @GetMapping("details/{id}")
    public CarOrderDetails getOrderDetailsById(@PathVariable Long id)
    {
        return carOrderService.getOrderDetailsById(id);
    }

    @PostMapping("/createNewOrder")
    public OrderInfo createNewOrder(@RequestBody OrderInfo orderInfo)
    {
        return carOrderService.addNewOrder(orderInfo);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOrderById(@PathVariable Long id){
         carOrderService.deleteOrderById(id);
    }

}
