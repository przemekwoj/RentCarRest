package com.przemo.rentcar.controllers;

import com.przemo.rentcar.orders.CarOrder;
import com.przemo.rentcar.orders.CarOrderDetails;
import com.przemo.rentcar.orders.OrderInfo;
import com.przemo.rentcar.orders.OrderInfoDTO;
import com.przemo.rentcar.services.AdministrationService;
import com.przemo.rentcar.services.CarOrderServiceImpl;
import com.przemo.rentcar.services.CarService;
import com.przemo.rentcar.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/order")
public class OrderController {

    private final CarOrderServiceImpl carOrderService;

    private final AdministrationService administrationService;

    private final ClientService clientService;

    private final CarService carService;


    @Autowired
    public OrderController(CarOrderServiceImpl carOrderService, AdministrationService administrationService, ClientService clientService, CarService carService) {
        this.carOrderService = carOrderService;
        this.administrationService = administrationService;
        this.clientService = clientService;
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public CarOrder getOrderById(@PathVariable Long id)
    {
        return carOrderService.getOrderById(id).get();
    }

    @GetMapping("/orders")
    public List<OrderInfoDTO> getOrdersInfo(){

        return carOrderService.getOrdersInfo();
    }

    @GetMapping("details/{id}")
    public CarOrderDetails getOrderDetailsById(@PathVariable Long id)
    {
        return carOrderService.getOrderDetailsById(id).get();
    }

    @PostMapping("/createNewOrder")
    public ResponseEntity createNewOrder(@RequestBody OrderInfo orderInfo)
    {
        carOrderService.addNewOrder(orderInfo);
        return ResponseEntity.status(200).body(orderInfo);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteOrderById(@PathVariable Long id){

        return carOrderService.deleteOrderById(id);
    }

}
