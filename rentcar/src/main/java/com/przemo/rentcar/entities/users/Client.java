package com.przemo.rentcar.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.przemo.rentcar.entities.orders.CarOrder;
import lombok.Getter;
import lombok.Setter;

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
    @Getter
    @Setter
    private Set<CarOrder> carOrders = new HashSet<>();

    @Override
    public String toString() {
        return super.toString() + " ," +
                "carOrders=" + carOrders +
                '}';
    }
}
