package com.przemo.rentcar.controllers;

import com.przemo.rentcar.entities.cars.Brand;
import com.przemo.rentcar.entities.cars.BrandDTO;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.services.BrandService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("BrandControllerTest function testing")
public class BrandControllerTest {

    @Mock
    BrandService brandService;

    private static BrandController brandController;

    private static ModelMapper modelMapper = new ModelMapper();

    private static List<Brand> brands = new ArrayList<>();

    private static Brand brand1 = new Brand();

    @BeforeAll
     static void init(){

        brand1.setAmountOfAvailableCars(2);
        brand1.setAmountOfCars(2);
        brand1.setBrand_id(1L);
        brand1.setBrandName("BMW");
        Car car = new Car();
        car.setPlateNumber("123");
        brand1.setCars(new HashSet<>(Arrays.asList(car)));
        brands.add(brand1);

    }

    @Nested
    @DisplayName("testing get methods")
    class GetMethods {

        @Test
        @DisplayName("should return whole list of BrandDTO")
        void testFindAdministrations() {
            brandController = new BrandController(brandService,modelMapper);
            when(brandService.getAllBrands()).thenReturn(brands);
            List<BrandDTO> result = brandController.getAllBrands();
            assertEquals(brands.size(),result.size());
        }

        @Test
        @DisplayName("should return BrandDTO by Id")
        void testShouldReturnBrandDTOById(){
            brandController = new BrandController(brandService,modelMapper);
            when(brandService.getBrandById(1L)).thenReturn(brand1);
            BrandDTO actual = brandController.getBrandById(1L);
            assertAll("check all fields",
                    ()->assertEquals(brand1.getAmountOfAvailableCars(),actual.getAmountOfAvailableCars()),
                    ()->assertEquals(brand1.getAmountOfCars(),actual.getAmountOfCars()),
                    ()->assertEquals(brand1.getBrand_id(),actual.getBrand_id()),
                    ()->assertEquals(brand1.getBrandName(),actual.getBrandName()),
                    ()->assertNotEquals(brand1.getClass(),actual.getClass())
                    );
        }

        @Test
        @DisplayName("should return Brands with cars")
        void testShouldReturnBrandsWithCars(){
            brandController = new BrandController(brandService,modelMapper);
            when(brandService.getAllBrandsWithCarsAndDetails()).thenReturn(brands);
            List<Brand> actual = brandController.getAllBrandsWithCarsAndDetails();
            assertArrayEquals(brands.toArray(),actual.toArray());
        }
    }

    @Nested
    @DisplayName("testing post methods")
    class PostMethods {

        @Test
        @DisplayName("should add brand")
        void shouldAddEmployee() {
            Brand brand = new Brand();
            brand.setCars(new HashSet<Car>());
            brand.setBrandName("brand");
            brand.setAmountOfCars(2);
            brand.setAmountOfAvailableCars(2);

            brandController = new BrandController(brandService, modelMapper);
            when(brandService.persistBrand(brand)).thenReturn(brand);

            Brand actual = brandController.addNewBrand(brand);

            assertAll(
                    "fields inside added employee",
                    () -> assertEquals(brand.getBrandName(), actual.getBrandName()),
                    () -> assertEquals(brand.getAmountOfAvailableCars(), actual.getAmountOfAvailableCars()),
                    () -> assertEquals(brand.getCars(), actual.getCars()),
                    () -> assertEquals(brand.getAmountOfCars(), actual.getAmountOfCars())
            );
        }

        @Nested
        @DisplayName("testing put methods")
        class PutMethods {

            @Test
            @DisplayName("should update brand")
            void shouldUpdateBrand(){
                Brand brand = new Brand();
                brand.setCars(new HashSet<Car>());
                brand.setBrandName("brand");
                brand.setAmountOfCars(2);
                brand.setAmountOfAvailableCars(2);
                brand.setBrand_id(1L);

                brandController = new BrandController(brandService, modelMapper);
                when(brandService.updateBrand(brand)).thenReturn(brand);

                Brand actual = brandController.updateBrand(brand);

                assertAll(
                        "fields inside added employee",
                        () -> assertEquals(brand.getBrandName(), actual.getBrandName()),
                        () -> assertEquals(brand.getAmountOfAvailableCars(), actual.getAmountOfAvailableCars()),
                        () -> assertEquals(brand.getCars(), actual.getCars()),
                        () -> assertEquals(brand.getBrand_id(), actual.getBrand_id()),
                        () -> assertEquals(brand.getAmountOfCars(), actual.getAmountOfCars())
                );

            }
        }
    }
}
