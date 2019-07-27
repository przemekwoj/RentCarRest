package com.przemo.rentcar.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.przemo.rentcar.orders.CarOrder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "administration")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role")
public class Administration extends User
{
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "stuff",
            orphanRemoval = true)
    @JsonBackReference
    private Set<CarOrder> carOrders = new HashSet<>();

    public Set<CarOrder> getCarOrders() {
        return carOrders;
    }

    public void setCarOrders(Set<CarOrder> carOrders) {
        this.carOrders = carOrders;
    }
}
