package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import com.przemo.rentcar.models.OrderInfo;
import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Client;
import com.przemo.rentcar.services.CarOrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CarControllerTest function testing")
public class OrderControllerTest {

     @Mock
     CarOrderService carOrderService;

     @InjectMocks
     OrderController orderController;

     private static CarOrder carOrder = new CarOrder();

     private static CarOrderDetails carOrderDetails = new CarOrderDetails();

     private static OrderInfo orderInfo = new OrderInfo();


    @BeforeAll
    static void init(){


        carOrder.setClient(new Client());
        carOrder.setStuff(new Administration());
        carOrder.setCarOrderDetails(new CarOrderDetails());
        carOrder.setOrder_id(1L);
        carOrderDetails.setCarOrder(carOrder);
        carOrderDetails.setCar(new Car());
        carOrderDetails.setPrice(23);
        carOrderDetails.setDateOfRental(new Date());
        carOrderDetails.setDateOfReturn(new Date());
        carOrderDetails.setCarOrderDetail_id(1L);
        carOrderDetails.setDetained(true);
        orderInfo.setCarId(1L);
        orderInfo.setClientId(1L);
        orderInfo.setEmployeeMail("asd");
        orderInfo.setEndDate(new Date());
        orderInfo.setStartDate(new Date());
    }


    @Nested
    @DisplayName("testing get methods")
    class GetMethods {

        @Test
        @DisplayName("should return CarOder by id")
        void shouldReturnCarOrderById() {
            when(carOrderService.getOrderById(1L)).thenReturn(carOrder);
            CarOrder actual = orderController.getOrderById(1L);
            assertAll(
                    "fields inside car Order",
                    ()->assertEquals(carOrder.getCarOrderDetails(),actual.getCarOrderDetails()),
                    ()->assertEquals(carOrder.getClient(),actual.getClient()),
                    ()->assertEquals(carOrder.getOrder_id(),actual.getOrder_id()),
                    ()->assertEquals(carOrder.getStuff(),actual.getStuff())
            );
        }

        @Test
        @DisplayName("should return CarOderDetails by id")
        void shouldReturnCarOderDetailsById() {
            when(carOrderService.getOrderDetailsById(1L)).thenReturn(carOrderDetails);
            CarOrderDetails actual = orderController.getOrderDetailsById(1L);
            assertAll(
                    "fields inside car Order",
                    ()->assertEquals(carOrderDetails.getCar(),actual.getCar()),
                    ()->assertEquals(carOrderDetails.getCarOrder(),actual.getCarOrder()),
                    ()->assertEquals(carOrderDetails.getCarOrderDetail_id(),actual.getCarOrderDetail_id()),
                    ()->assertEquals(carOrderDetails.getDateOfRental(),actual.getDateOfRental()),
                    ()->assertEquals(carOrderDetails.getDateOfReturn(),actual.getDateOfReturn()),
                    ()->assertEquals(carOrderDetails.getPrice(),actual.getPrice())
            );
        }
    }

    @Nested
    @DisplayName("testing post methods")
    class PostMethods {

        @Test
        @DisplayName("should add OrderInfo")
        void shouldAddOrderInfo(){
            when(carOrderService.addNewOrder(orderInfo)).thenReturn(orderInfo);
            OrderInfo actual = orderController.createNewOrder(orderInfo);
            assertAll(
                    "fields inside car Order",
                    ()->assertEquals(orderInfo.getCarId(),actual.getCarId()),
                    ()->assertEquals(orderInfo.getClientId(),actual.getClientId()),
                    ()->assertEquals(orderInfo.getEmployeeMail(),actual.getEmployeeMail()),
                    ()->assertEquals(orderInfo.getEndDate(),actual.getEndDate()),
                    ()->assertEquals(orderInfo.getStartDate(),actual.getStartDate())
            );

        }
    }
}
