package com.przemo.rentcar.entities.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {

    Long clientId;
    Long carId;
    String employeeMail;
    Date startDate;
    Date endDate;
}
