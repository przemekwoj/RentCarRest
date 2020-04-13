package com.przemo.rentcar.entities.orders;

import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.entities.users.Client;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carorder")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarOrder
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long order_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    @NotNull(message = "client must not be null")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stuff_id")
    @NotNull(message = "stuff must not be null")
    private Administration stuff;

    @OneToOne(mappedBy = "carOrder", cascade = CascadeType.ALL)
    private CarOrderDetails carOrderDetails;
}
