package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Brand;

import java.util.List;

public interface BrandService
{
    List<Brand> getAllBrands();

    List<Brand> getAllBrandsWithCars();

    Brand getBrandById(Long id);

    Brand persistBrand(Brand brand);

    void deleteBrandById(Long id);

    Brand updateBrand(Brand updatedBrand);

    void addCarToBrand(long brandId,String plateNumber);

}
