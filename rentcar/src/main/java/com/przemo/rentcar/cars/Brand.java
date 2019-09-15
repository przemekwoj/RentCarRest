package com.przemo.rentcar.cars;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Brand
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long brand_id;

    @Column(unique = true)
    @NotNull
    private String brandName;

    private int amountOfCars;

    private int amountOfAvailableCars;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Set<Car> cars = new HashSet<>();


    public Brand(@NotNull String brandName, int amountOfCars, int amountOfAvailableCars,Long brand_id) {
        this.brandName = brandName;
        this.amountOfCars = amountOfCars;
        this.amountOfAvailableCars = amountOfAvailableCars;
        this.brand_id = brand_id;
    }

    public void addCar(Car car)
    {
        cars.add(car);
        amountOfAvailableCars++;
        amountOfCars++;
    }


}
