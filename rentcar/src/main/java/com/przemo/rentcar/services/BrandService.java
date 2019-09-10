package com.przemo.rentcar.services;

import com.przemo.rentcar.cars.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService
{
    List<Brand> getAllBrands();

    List<Brand> getAllBrandsWithCars();

    Optional<Brand> getBrandById(Long id);

    Brand persistBrand(Brand brand);

    void deleteBrandById(Long id);

    void updateBrand(Brand updatedBrand);

    void addCarToBrand(long brandId,String plateNumber);

}
