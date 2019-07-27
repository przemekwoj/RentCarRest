package com.przemo.rentcar.cars;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.przemo.rentcar.orders.CarOrderDetails;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Car
{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long car_id;

    private boolean available;

    @Column(unique = true)
    private String plateNumer;

    @OneToOne(mappedBy = "car",cascade = CascadeType.ALL)
    //@JsonBackReference
    private CarDetails carDetails;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "carOrderDetail_id")
    @JsonBackReference
    private CarOrderDetails carOrderDetails;

    public Car(){}

    public Car(Long car_id,String plateNumer,boolean available) {
        this.available = available;
        this.plateNumer = plateNumer;
        this.car_id = car_id;
    }

    public Long getCar_id() {
        return car_id;
    }

    public void setCar_id(Long car_id) {
        this.car_id = car_id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPlateNumer() {
        return plateNumer;
    }

    public void setPlateNumer(String plateNumer) {
        this.plateNumer = plateNumer;
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public CarOrderDetails getCarOrderDetails() {
        return carOrderDetails;
    }

    public void setCarOrderDetails(CarOrderDetails carOrderDetails) {
        this.carOrderDetails = carOrderDetails;
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + car_id +
                ", available=" + available +
                ", plateNumer='" + plateNumer + '\'' +
                ", carDetails=" + carDetails +
                ", carOrderDetails=" + carOrderDetails +
                '}';
    }
}
