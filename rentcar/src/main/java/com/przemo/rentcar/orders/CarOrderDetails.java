package com.przemo.rentcar.orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.przemo.rentcar.cars.Car;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class CarOrderDetails
{
    @Id
    private Long carOrderDetail_id;

    private int price;

    @Temporal(TemporalType.DATE)
    private Date dateOfRental;

    @Temporal(TemporalType.DATE)
    private Date dateOfReturn;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private CarOrder carOrder;

    @OneToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id")
    private Car car;

}
