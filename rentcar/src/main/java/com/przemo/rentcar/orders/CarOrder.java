package com.przemo.rentcar.orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.przemo.rentcar.users.Administration;
import com.przemo.rentcar.users.Client;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carorder")
public class CarOrder
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long order_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stuff_id")
    @NotNull
    private Administration stuff;

    @OneToOne(mappedBy = "carOrder")
    private CarOrderDetails carOrderDetails;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Administration getStuff() {
        return stuff;
    }

    public void setStuff(Administration stuff) {
        this.stuff = stuff;
    }

    public CarOrderDetails getCarOrderDetails() {
        return carOrderDetails;
    }

    public void setCarOrderDetails(CarOrderDetails carOrderDetails) {
        this.carOrderDetails = carOrderDetails;
    }

    @Override
    public String toString() {
        return "CarOrder{" +
                "order_id=" + order_id +
                ", client=" + client +
                ", stuff=" + stuff +
                ", carOrderDetails=" + carOrderDetails +
                '}';
    }
}
