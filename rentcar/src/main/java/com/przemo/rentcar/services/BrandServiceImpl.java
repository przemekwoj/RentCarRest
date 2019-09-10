package com.przemo.rentcar.services;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.repositoriesDB.BrandRepository;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService
{
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public void addCarToBrand(long brandId, String plateNumber) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException());
        Car car = carRepository.findByPlateNumber(plateNumber).get();
        brand.addCar(car);
        brandRepository.save(brand);
    }


    @Override
    public List<Brand> getAllBrands()
    {
        List<Brand> brands =  brandRepository.getAllBrands();
        return brands;
    }

    @Override
    public List<Brand> getAllBrandsWithCars()
    {
        List<Brand> brands =  brandRepository.findAll();
        for(Brand b:brands)
        {
            for(Car c: b.getCars())
            {
                System.out.println(c.getCarDetails());
            }
        }
        return brands;
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.getBrandById(id);
    }

    @Override
    public Brand persistBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void updateBrand(Brand updatedBrand) {
        Brand brand = brandRepository.findById(updatedBrand.getBrand_id()).get();
        brand = updatedBrand;
        brandRepository.save(brand);
    }

}
