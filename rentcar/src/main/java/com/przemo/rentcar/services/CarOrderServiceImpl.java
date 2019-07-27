package com.przemo.rentcar.services;

import com.przemo.rentcar.orders.CarOrder;
import com.przemo.rentcar.orders.CarOrderDetails;
import com.przemo.rentcar.repositoriesDB.CarOrderDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarOrderServiceImpl implements CarOrderService
{
    @Autowired
    private CarOrderRepository carOrderRepository;

    @Autowired
    private CarOrderDetailsRepository carOrderDetailsRepository;

    @Override
    public Optional<CarOrderDetails> getOrderDetailsById(Long id) {
        return carOrderDetailsRepository.findById(id);
    }

    @Override
    public Optional<CarOrder> getOrderById(Long id) {
        return carOrderRepository.findById(id);
    }
}
