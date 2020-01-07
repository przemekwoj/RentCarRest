package com.przemo.rentcar.entities.cars;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDetailsDTO {
    private Long carDetails_id;

    private String color;

    private int weight;

    private int hight;
}
