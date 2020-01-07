package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.entities.orders.OrderInfo;
import com.przemo.rentcar.entities.orders.OrderInfoDTO;
import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.CarOrderDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CarOrderServiceImpl implements CarOrderService
{
    @Autowired
    private CarOrderRepository carOrderRepository;

    @Autowired
    private CarOrderDetailsRepository carOrderDetailsRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdministrationService administrationService;

    @Autowired
    private CarOrderService carOrderService;


    @Override
    public CarOrderDetails getOrderDetailsById(Long id) {
        return carOrderDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("Not found CarOrderDetails with id "+id));
    }

    @Override
    public CarOrder getOrderById(Long id) {
        return carOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("Not found CarOrder with id "+id));

    }

    @Override
    public OrderInfo addNewOrder(OrderInfo orderInfo) {
        Administration administration = administrationService.getAdministrationByEmail(orderInfo.getEmployeeMail());
        Client client = clientService.getOneClient(orderInfo.getClientId());
        Car car = carService.getCarByIdLazy(orderInfo.getCarId());
        car.setAvailable(false);
        CarOrderDetails carOrderDetails = new CarOrderDetails();
        carOrderDetails.setDateOfRental(orderInfo.getStartDate());
        carOrderDetails.setDateOfReturn(orderInfo.getEndDate());
        CarOrder carOrder = new CarOrder();
        carOrder.setStuff(administration);
        carOrder.setClient(client);
        carOrderDetails.setCar(car);
        carOrderDetails.setCarOrder(carOrder);
        carService.persistCar(car);
        carOrderDetailsRepository.save(carOrderDetails);
        return orderInfo;
    }

    @Override
    public List<OrderInfoDTO> getOrdersInfo() {
        return carOrderRepository.getOrdersInfo();
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Car car = getOrderDetailsById(orderId).getCar();
        car.setAvailable(true);
        carService.persistCar(car);
        carOrderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public void setDetained() {
        Date actualDate = new Date();
        carOrderDetailsRepository.setDetained(actualDate);
    }
}
