package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.models.OrderInfo;
import com.przemo.rentcar.models.OrderInfoDTO;
import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.CarOrderDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CarOrderServiceImpl implements CarOrderService {
    private final CarOrderRepository carOrderRepository;

    private final CarOrderDetailsRepository carOrderDetailsRepository;

    private final CarService carService;

    private final ClientService clientService;

    private final AdministrationService administrationService;

    public CarOrderServiceImpl(CarOrderRepository carOrderRepository, CarOrderDetailsRepository carOrderDetailsRepository, CarService carService, ClientService clientService, AdministrationService administrationService) {
        this.carOrderRepository = carOrderRepository;
        this.carOrderDetailsRepository = carOrderDetailsRepository;
        this.carService = carService;
        this.clientService = clientService;
        this.administrationService = administrationService;
    }

    @Override
    public CarOrderDetails getOrderDetailsById(Long id) {
        return carOrderDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("Not found CarOrderDetails with id " + id));
    }

    @Override
    public CarOrder getOrderById(Long id) {
        return carOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("Not found CarOrder with id " + id));

    }

    @Override
    public OrderInfo addNewOrder(OrderInfo orderInfo) {
        Administration administration = administrationService.getAdministrationByEmail(orderInfo.getEmployeeMail());
        Client client = clientService.getOneClient(orderInfo.getClientId());
        Car car = carService.getCarByIdLazy(orderInfo.getCarId());

        CarOrder carOrder = createCarOrder(administration, client);

        CarOrderDetails carOrderDetails = createCarOrderDetails(orderInfo, car, carOrder);

        carOrderDetailsRepository.save(carOrderDetails);

        bookTheCar(car, carOrderDetails);

        return orderInfo;
    }

    private void bookTheCar(Car car, CarOrderDetails carOrderDetails) {
        car.setAvailable(false);
        car.setCarOrderDetails(carOrderDetails);
    }

    private CarOrderDetails createCarOrderDetails(OrderInfo orderInfo, Car car, CarOrder carOrder) {
        return CarOrderDetails.builder()
                .dateOfRental(orderInfo.getStartDate())
                .dateOfReturn(orderInfo.getEndDate())
                .car(car)
                .carOrder(carOrder)
                .build();
    }

    private CarOrder createCarOrder(Administration administration, Client client) {
        return CarOrder.builder()
                .stuff(administration)
                .client(client)
                .build();
    }

    @Override
    public List<OrderInfoDTO> getOrdersInfo() {
        return carOrderRepository.getOrdersInfo();
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Car car = getOrderDetailsById(orderId).getCar();
        car.setAvailable(true);
        carOrderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public void setDetained() {
        Date actualDate = new Date();
        carOrderDetailsRepository.setDetained(actualDate);
    }
}
