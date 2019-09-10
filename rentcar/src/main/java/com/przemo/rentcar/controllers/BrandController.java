package com.przemo.rentcar.controllers;

import com.przemo.rentcar.cars.Brand;
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
        return brandService.getAllBrandsWithCars();
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

    @PatchMapping("/brandId/{brandId}/plateNumber/{plateNumber}")
    public String addCarToBrand(@PathVariable long brandId,@PathVariable String plateNumber)
    {
        brandService.addCarToBrand(brandId,plateNumber);
        return  "carAddedToBrand";
    }
}
