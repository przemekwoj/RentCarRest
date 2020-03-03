package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.entities.orders.OrderInfo;
import com.przemo.rentcar.entities.orders.OrderInfoDTO;

import java.util.List;

public interface CarOrderService {
    CarOrder getOrderById(Long id);

    CarOrderDetails getOrderDetailsById(Long id);

    OrderInfo addNewOrder(OrderInfo orderInfo);

    List<OrderInfoDTO> getOrdersInfo();

    void deleteOrderById(Long orderId);

    void setDetained();

}
