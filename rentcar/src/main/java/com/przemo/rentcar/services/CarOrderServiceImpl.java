package com.przemo.rentcar.services;

import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.orders.CarOrder;
import com.przemo.rentcar.orders.CarOrderDetails;
import com.przemo.rentcar.orders.OrderInfo;
import com.przemo.rentcar.orders.OrderInfoDTO;
import com.przemo.rentcar.repositoriesDB.CarOrderDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarOrderRepository;
import com.przemo.rentcar.users.Administration;
import com.przemo.rentcar.users.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Optional<CarOrderDetails> getOrderDetailsById(Long id) {
        return carOrderDetailsRepository.findById(id);
    }

    @Override
    public Optional<CarOrder> getOrderById(Long id) {
        return carOrderRepository.findById(id);
    }

    @Override
    public CarOrderDetails addNewOrder(OrderInfo orderInfo) {
        Administration administration = administrationService.getAdministrationByEmail(orderInfo.getEmployeeMail()).get();
        Client client = clientService.getOneClient(orderInfo.getClientId()).get();
        Car car = carService.getCarByIdLazy(orderInfo.getCarId()).get();
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
        return carOrderDetailsRepository.save(carOrderDetails);
    }

    @Override
    public List<OrderInfoDTO> getOrdersInfo() {
        return carOrderRepository.getOrdersInfo();
    }

    @Override
    public ResponseEntity<String>  deleteOrderById(Long orderId) {
        Car car = carOrderService.getOrderDetailsById(orderId).get().getCar();
        car.setAvailable(true);
        carService.persistCar(car);
        carOrderRepository.deleteById(orderId);
        return new ResponseEntity<>("{ \"text\":\"Order succesfully deleted\"}", HttpStatus.OK);
    }

    @Override
    @Transactional
    public void setDetained() {
        Date actualDate = new Date();
        carOrderDetailsRepository.setDetained(actualDate);
    }
}
