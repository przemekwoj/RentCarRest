package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.cars.Brand;
import com.przemo.rentcar.entities.cars.BrandDTO;
import com.przemo.rentcar.services.BrandService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/brand")
public class BrandController
{

    private final BrandService brandService;

    private final ModelMapper modelMapper;

    @Autowired
    public BrandController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/brands")
    public List<BrandDTO> getAllBrands()
    {
        List<Brand> brands = brandService.getAllBrands();
        return  modelMapper.map(brands, new TypeToken<List<BrandDTO>>(){}.getType());
    }

    @GetMapping("/{brandId}")
    public BrandDTO getBrandById(@PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId);
        return modelMapper.map(brand,BrandDTO.class);}

    @GetMapping("/withCars")
    public List<Brand> getAllBrandsWithCarsAndDetails()
    {
        return brandService.getAllBrandsWithCarsAndDetails();
    }

    @PostMapping("/brand")
    public Brand addNewBrand(@Validated @RequestBody Brand brand)
    {
        return brandService.persistBrand(brand);
    }

    @DeleteMapping("/{brandId}")
    public void deleteBrandById(@PathVariable Long brandId)
    {
        brandService.deleteBrandById(brandId);
    }

    @PutMapping("/brand")
    public Brand updateBrand(@Validated @RequestBody Brand updatedBrand)
    {
        return brandService.updateBrand(updatedBrand);
    }
}
