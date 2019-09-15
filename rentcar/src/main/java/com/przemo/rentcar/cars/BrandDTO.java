package com.przemo.rentcar.cars;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDTO
{
    private Long brand_id;

    private String brandName;

    private int amountOfCars;

    private int amountOfAvailableCars;
}
