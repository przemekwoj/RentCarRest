package com.przemo.rentcar.controllers;

import com.przemo.rentcar.cars.Brand;
import com.przemo.rentcar.cars.BrandDTO;
import com.przemo.rentcar.services.BrandService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/brands")
    public List<BrandDTO> getAllBrands()
    {
        List<Brand> brands = brandService.getAllBrands();
        return  modelMapper.map(brands, new TypeToken<List<BrandDTO>>(){}.getType());
    }

    @GetMapping("/{brandId}")
    public BrandDTO getBrandById(@PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId).get();
        return modelMapper.map(brand,BrandDTO.class);}

    @GetMapping("/withCars")
    public List<BrandDTO> getAllBrandsWithCars()
    {
        List<Brand> brands = brandService.getAllBrandsWithCars();
        return  modelMapper.map(brands, new TypeToken<List<BrandDTO>>(){}.getType());
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
