package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.cars.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Long>
{
    @Query("SELECT NEW Brand (b.brandName,b.amountOfCars,b.amountOfAvailableCars,b.brand_id)FROM Brand b")
    List<Brand> getAllBrands();

    @Query("SELECT NEW Brand (b.brandName,b.amountOfCars,b.amountOfAvailableCars,b.brand_id)FROM Brand b " +
            "WHERE b.brand_id = :id")
    Optional<Brand> getBrandById(@Param("id") Long id);

}
