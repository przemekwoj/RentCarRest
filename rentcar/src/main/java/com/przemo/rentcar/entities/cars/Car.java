package com.przemo.rentcar.entities.cars;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.przemo.rentcar.entities.orders.CarOrderDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
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


    public Car(Long car_id,String plateNumber,boolean available) {
        this.available = available;
        this.plateNumber = plateNumber;
        this.car_id = car_id;
    }

}
