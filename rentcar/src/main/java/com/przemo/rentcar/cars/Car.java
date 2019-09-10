package com.przemo.rentcar.cars;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.przemo.rentcar.orders.CarOrderDetails;

import javax.persistence.*;

@Entity
public class Car
{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long car_id;

    private boolean available;

    @Column(unique = true)
    private String plateNumber;

    @OneToOne(mappedBy = "car",cascade = CascadeType.ALL)
    private CarDetails carDetails;

    @OneToOne(mappedBy="car" ,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    @JsonBackReference
    private CarOrderDetails carOrderDetails;

    public Car(){}

    public Car(Long car_id,String plateNumber,boolean available) {
        this.available = available;
        this.plateNumber = plateNumber;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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
                ", plateNumer='" + plateNumber + '\'' +
                ", carDetails=" + carDetails +
                ", carOrderDetails=" + carOrderDetails +
                '}';
    }
}
