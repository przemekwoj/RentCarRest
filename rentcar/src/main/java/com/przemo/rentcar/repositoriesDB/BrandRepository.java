package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.repositoriesDB.queries.BrandQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Long>
{
    @Query(BrandQueries.getAllBrands)
    List<Brand> getAllBrands();

    @Query(BrandQueries.getBrandById)
    Optional<Brand> getBrandById(@Param("id") Long id);


}
