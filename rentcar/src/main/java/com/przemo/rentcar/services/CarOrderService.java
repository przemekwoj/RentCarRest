package com.przemo.rentcar.services;

import com.przemo.rentcar.orders.CarOrder;
import com.przemo.rentcar.orders.CarOrderDetails;
import com.przemo.rentcar.orders.OrderInfo;
import com.przemo.rentcar.orders.OrderInfoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CarOrderService
{
    Optional<CarOrder> getOrderById(Long id);

    Optional<CarOrderDetails> getOrderDetailsById( Long id);

    CarOrderDetails addNewOrder(OrderInfo orderInfo);

    List<OrderInfoDTO> getOrdersInfo();

    ResponseEntity<String> deleteOrderById(Long orderId);

    void setDetained();

}
