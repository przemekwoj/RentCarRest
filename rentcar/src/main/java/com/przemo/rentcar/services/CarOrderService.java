package com.przemo.rentcar.services;

import com.przemo.rentcar.orders.CarOrder;
import com.przemo.rentcar.orders.CarOrderDetails;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface CarOrderService
{
    Optional<CarOrder> getOrderById(Long id);

    Optional<CarOrderDetails> getOrderDetailsById( Long id);
}
