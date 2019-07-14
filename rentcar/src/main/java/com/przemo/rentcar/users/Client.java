package com.przemo.rentcar.users;

import com.przemo.rentcar.orders.CarOrder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends User
{
    @OneToMany(mappedBy = "client",
            orphanRemoval = true)
    private Set<CarOrder> carOrders = new HashSet<>();

    public Set<CarOrder> getCarOrders() {
        return carOrders;
    }

    public void setCarOrders(Set<CarOrder> carOrders) {
        this.carOrders = carOrders;
    }
}
