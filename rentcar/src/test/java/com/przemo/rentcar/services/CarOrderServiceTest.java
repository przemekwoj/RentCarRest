package com.przemo.rentcar.services;


import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.entities.orders.OrderInfo;
import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.CarOrderDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarOrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CarOrderService function testing")
public class CarOrderServiceTest {

    @InjectMocks
    private CarOrderServiceImpl carOrderService;

    @Mock
    private CarOrderRepository carOrderRepository;

    @Mock
    private CarOrderDetailsRepository carOrderDetailsRepository;

    @Mock
    private CarService carService;

    @Mock
    private ClientService clientService;

    @Mock
    private AdministrationService administrationService;

    @Nested
    @DisplayName("Get Methods test")
    class GetMethod{
        @Test
        @DisplayName("should return Car Order Details by Id")
        void shouldReturnCarOrderDetailsWithId(){
            //given
            CarOrderDetails carOrderDetails = new CarOrderDetails();
            carOrderDetails.setCarOrderDetail_id(1L);
            when(carOrderDetailsRepository.findById(1L)).thenReturn(Optional.of(carOrderDetails));
            //when
            CarOrderDetails actual = carOrderService.getOrderDetailsById(1L);
            //then
            assertAll(
                    ()->assertEquals(carOrderDetails,actual),
                    ()->assertEquals(1L,actual.getCarOrderDetail_id().longValue())
            );


        }

        @Test
        @DisplayName("should throw exception Not Found by car order details id")
        void shouldThrowExceptionNotFoundByCarOrderDetailsId(){
            when(carOrderDetailsRepository.findById(1L)).thenThrow( new NotFoundEntity("Not found"));
            assertThrows(NotFoundEntity.class, () -> carOrderService.getOrderDetailsById(1L));
        }

        @Test
        @DisplayName("should return Car Order  by Id")
        void shouldReturnCarOrderWithId(){
            //given
            CarOrder carOrder = new CarOrder();
            carOrder.setOrder_id(1L);
            when(carOrderRepository.findById(1L)).thenReturn(Optional.of(carOrder));
            //when
            CarOrder actual = carOrderService.getOrderById(1L);
            //then
            assertAll(
                    ()->assertEquals(carOrder,actual),
                    ()->assertEquals(1L,actual.getOrder_id().longValue())
            );


        }

        @Test
        @DisplayName("should throw exception Not Found by car order  id")
        void shouldThrowExceptionNotFoundByCarOrderId(){
            when(carOrderRepository.findById(1L)).thenThrow( new NotFoundEntity("Not found"));
            assertThrows(NotFoundEntity.class, () -> carOrderService.getOrderById(1L));
        }
    }

    @Nested
    @DisplayName("Mofyfied methods")
    class ModyfiedMethods{

        @Test
        @DisplayName("should add set order info")
        void shouldSetOrderInfo(){
            //given
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setStartDate(new Date());
            orderInfo.setEndDate(new Date());
            orderInfo.setEmployeeMail("abc");
            orderInfo.setClientId(1L);
            orderInfo.setCarId(1L);
            Administration administration = new Administration();
            Client client = new Client();
            Car car = new Car();
            CarOrderDetails carOrderDetails = new CarOrderDetails();
            when(carOrderDetailsRepository.save(any())).thenReturn(carOrderDetails);
            when(administrationService.getAdministrationByEmail(orderInfo.getEmployeeMail())).thenReturn(administration);
            when(clientService.getOneClient(orderInfo.getClientId())).thenReturn(client);
            when(carService.getCarByIdLazy(orderInfo.getCarId())).thenReturn(car);
            //when
            carOrderService.addNewOrder(orderInfo);
            //then
            assertEquals(car.isAvailable(),false);
        }

        @Test
        @DisplayName("delete order by id")
        void shouldSetAvailableToTrue(){
            //given
            Car car = new Car();
            car.setAvailable(false);
            Long orderId = 1L;
            CarOrderDetails carOrderDetails = new CarOrderDetails();
            carOrderDetails.setCar(car);
            when(carOrderDetailsRepository.findById(orderId)).thenReturn(Optional.of(carOrderDetails));
            //when
            carOrderService.deleteOrderById(orderId);
            //then
            assertEquals(true,car.isAvailable());

        }
    }

}
