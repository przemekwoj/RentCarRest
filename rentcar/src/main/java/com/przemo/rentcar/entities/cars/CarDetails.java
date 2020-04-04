package com.przemo.rentcar.entities.cars;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarDetails
{

    @Id
    private Long carDetails_id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "carId")
    @JsonBackReference
    private Car car;

    private String color;

    private int weight;

    private int high;



}

