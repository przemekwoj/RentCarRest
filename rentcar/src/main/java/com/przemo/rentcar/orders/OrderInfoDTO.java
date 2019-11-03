package com.przemo.rentcar.orders;

import java.util.Date;


public interface OrderInfoDTO {

     Long getOrderId();

     String getClientName();

     String getAdministrationName();

     Date getDateOfRental();

     Date getDateOfReturn();

     String getCarBrandName();

}
