package com.przemo.rentcar.orders;

import com.przemo.rentcar.users.Administration;
import com.przemo.rentcar.users.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carorder")
@Getter
@Setter
public class CarOrder
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long order_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stuff_id")
    @NotNull
    private Administration stuff;

    @OneToOne(mappedBy = "carOrder", cascade = CascadeType.ALL)
    private CarOrderDetails carOrderDetails;



}
