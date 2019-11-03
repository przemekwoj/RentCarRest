package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.orders.CarOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CarOrderDetailsRepository extends JpaRepository<CarOrderDetails,Long> {
   // @Modifying
    //@Query("update CarOrderDetails cod set cod.detained = true where cod.dateOfReturn < :date")
    //void setDetained(@Param("date") Date date);
    @Modifying
    @Query("update CarOrderDetails cod set cod.isDetained = true where cod.dateOfReturn <:date")
    //@Query(value = CarOrderQueries.setDetainedOrders)
    void setDetained(@Param("date") Date date);
    //@Query("update User u set u.active = false where u.lastLoginDate < :date")
}
