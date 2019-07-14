package com.przemo.rentcar.controllers;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.cars.Car;
import com.przemo.rentcar.repositoriesDB.BrandRepository;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import com.przemo.rentcar.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/brand")
public class BrandController
{
    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public List<Brand> getAllBrands()
    {
        return brandService.getAllBrands();
    }

    @GetMapping("/{brandId}")
    public Brand getBrandById(@PathVariable Long brandId) { return brandService.getBrandById(brandId).get(); }

    @GetMapping("/withCars")
    public List<Brand> getAllBrandsWithCars()
    {
        List<Brand> brands = brandService.getAllBrandsWithCars();
        for(Brand b:brands)
        {
            for(Car c: b.getCars())
            {
                System.out.println(c.getCarDetails());
            }
        }
        return brands;
    }

    @PostMapping("/brand")
    public Brand addNewBrand(@RequestBody Brand brand)
    {
        return brandService.persistBrand(brand);
    }

    @DeleteMapping("/{brandId}")
    public String deleteBrandById(@PathVariable Long brandId)
    {
        ///potem zmienic zeby zwracalo status zamiast Stringa
        brandService.deleteBrandById(brandId);
        return "delete successfuly";
    }

    @PutMapping("/brand")
    public String updateBrand(@RequestBody Brand updatedBrand)
    {
        brandService.updateBrand(updatedBrand);
        return "brand updated";
    }
}
