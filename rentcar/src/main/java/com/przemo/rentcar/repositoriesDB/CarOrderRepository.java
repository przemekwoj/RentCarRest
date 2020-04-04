package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.entities.orders.CarOrder;
import com.przemo.rentcar.models.OrderInfoDTO;
import com.przemo.rentcar.repositoriesDB.queries.CarOrderQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarOrderRepository extends JpaRepository<CarOrder,Long>
{
    @Query(value = CarOrderQueries.allOrdersInfo, nativeQuery = true)
    List<OrderInfoDTO> getOrdersInfo();
}
