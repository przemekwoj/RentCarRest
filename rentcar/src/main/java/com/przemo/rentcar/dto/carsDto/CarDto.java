package com.przemo.rentcar.dto.carsDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarDto {

    private Long car_id;

    private boolean available;

    private String plateNumber;
}
