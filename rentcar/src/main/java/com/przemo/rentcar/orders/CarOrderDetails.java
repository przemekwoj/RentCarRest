package com.przemo.rentcar.orders;

import com.przemo.rentcar.cars.Car;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CarOrderDetails
{
    @Id
    private Long carOrderDetail_id;

    private int price;

    @Temporal(TemporalType.DATE)
    private Date dateOfRental;

    @Temporal(TemporalType.DATE)
    private Date dateOfReturn;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private CarOrder carOrder;

    @OneToOne(mappedBy="carOrderDetails")
    private Car car;

    public Long getCarOrderDetail_id() {
        return carOrderDetail_id;
    }

    public void setCarOrderDetail_id(Long carOrderDetail_id) {
        this.carOrderDetail_id = carOrderDetail_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(Date dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public CarOrder getCarOrder() {
        return carOrder;
    }

    public void setCarOrder(CarOrder carOrder) {
        this.carOrder = carOrder;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
