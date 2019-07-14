package com.przemo.rentcar.cars;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @OneToMany
    @JoinColumn(name = "brand_id")
    private Set<Car> cars = new HashSet<>();

    public Brand(){}

    public Brand(@NotNull String brandName, int amountOfCars, int amountOfAvailableCars,Long brand_id) {
        this.brandName = brandName;
        this.amountOfCars = amountOfCars;
        this.amountOfAvailableCars = amountOfAvailableCars;
        this.brand_id = brand_id;
    }

    public void addCar(Car car)
    {
        cars.add(car);
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getAmountOfCars() {
        return amountOfCars;
    }

    public int getAmountOfAvailableCars() {
        return amountOfAvailableCars;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setAmountOfCars(int amountOfCars) {
        this.amountOfCars = amountOfCars;
    }

    public void setAmountOfAvailableCars(int amountOfAvailableCars) {
        this.amountOfAvailableCars = amountOfAvailableCars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand_id=" + brand_id +
                ", brandName='" + brandName + '\'' +
                ", amountOfCars=" + amountOfCars +
                ", amountOfAvailableCars=" + amountOfAvailableCars +
                ", cars=" + cars +
                '}';
    }
}
