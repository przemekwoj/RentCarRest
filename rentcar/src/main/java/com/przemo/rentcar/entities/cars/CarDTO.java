package com.przemo.rentcar.entities.cars;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private Long car_id;

    private boolean available;

    private String plateNumber;

    @Override
    public String toString() {
        return "CarDTO{" +
                "car_id=" + car_id +
                ", available=" + available +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
