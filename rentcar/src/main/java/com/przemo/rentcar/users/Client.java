package com.przemo.rentcar.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.przemo.rentcar.orders.CarOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
public class Client extends User
{
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client",
            orphanRemoval = true)
    @JsonBackReference
    @Getter
    @Setter
    private Set<CarOrder> carOrders = new HashSet<>();

}
