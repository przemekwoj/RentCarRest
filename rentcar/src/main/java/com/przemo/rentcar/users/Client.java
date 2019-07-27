package com.przemo.rentcar.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.przemo.rentcar.orders.CarOrder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends User
{
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client",
            orphanRemoval = true)
    @JsonBackReference
    private Set<CarOrder> carOrders = new HashSet<>();

    public Set<CarOrder> getCarOrders() {
        return carOrders;
    }

    public void setCarOrders(Set<CarOrder> carOrders) {
        this.carOrders = carOrders;
    }

    @Override
    public String toString() {
        return "Client{" +
                "carOrders=" + carOrders +
                '}';
    }
}
