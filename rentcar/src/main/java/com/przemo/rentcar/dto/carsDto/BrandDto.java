package com.przemo.rentcar.dto.carsDto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto
{
    private Long brand_id;

    @NotNull(message = "brandName must not be null")
    private String brandName;

    private int amountOfCars;

    private int amountOfAvailableCars;
}
