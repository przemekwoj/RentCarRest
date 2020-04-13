package com.przemo.rentcar.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderInfo {

    Long clientId;
    Long carId;
    String employeeMail;
    Date startDate;
    Date endDate;
}
