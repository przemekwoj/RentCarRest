package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.orders.CarOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarOrderRepository extends JpaRepository<CarOrder,Long>
{

}
