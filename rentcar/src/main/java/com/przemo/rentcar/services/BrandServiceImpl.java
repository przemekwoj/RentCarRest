package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Brand;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.getAllBrands();
    }

    @Override
    public List<Brand> getAllBrandsWithCarsAndDetails() {
        List<Brand> brands = brandRepository.findAll();
        for (Brand brand : brands) {
            brand.getCars().forEach(car -> car.getCarDetails());
        }
        return brands;
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.getBrandById(id)
                .orElseThrow(() -> new NotFoundEntity("Not found brand with id " + id));
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
    public Brand updateBrand(Brand updatedBrand) {
        return brandRepository.save(updatedBrand);
    }

}
