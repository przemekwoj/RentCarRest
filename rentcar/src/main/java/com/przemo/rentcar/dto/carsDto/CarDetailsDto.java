package com.przemo.rentcar.dto.carsDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarDetailsDto {
    private Long carDetails_id;

    private String color;

    private int weight;

    private int high;
}
