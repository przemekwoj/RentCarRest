package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Brand;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.BrandRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("BrandService function testing")
public class BrandServiceTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CarService carService;


    @Nested
    @DisplayName("Modyfied methods")
    class ModyfiedMethods{

        @Test
        @DisplayName("should add Car to Brand")
        void shouldAddCarToBrand(){
            //given
            Brand brand = new Brand();
            brand.setBrand_id(1L);
            Car car = new Car();
            car.setPlateNumber("997");
            when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
            when(carService.findByPlateNumber("997")).thenReturn(car);
            //when
            brandService.addCarToBrand(1L,"997");
            //then
            assertTrue(brand.getCars().contains(car),"should contain added car");

        }

        @Test
        @DisplayName("should throw Not Found Exception by email")
        void shouldThrowNowFoundExceptionByEmail(){
            when(brandRepository.findById(1L)).thenThrow( new NotFoundEntity("Not found"));
            assertThrows(NotFoundEntity.class, () -> brandService.addCarToBrand(1L,"997"));
        }
    }

    @Nested
    @DisplayName("Get methods test" )
    class GetMethods{

        @Test
        @DisplayName("should return brand with id")
        void shouldReturnBrandWithId(){
            //given
            Brand brand = new Brand();
            brand.setBrand_id(1L);
            when(brandRepository.getBrandById(1L)).thenReturn(Optional.of(brand));
            //when
            Brand actual = brandService.getBrandById(1L);
            //then
            assertAll(
                    ()->assertEquals(brand,actual),
                    ()->assertEquals(1L,actual.getBrand_id().longValue())
            );


        }

        @Test
        @DisplayName("should throw exception Not Found by brand id")
        void shouldThrowExceptionNotFoundByBrandId(){
            when(brandRepository.getBrandById(1L)).thenThrow( new NotFoundEntity("Not found"));
            assertThrows(NotFoundEntity.class, () -> brandService.getBrandById(1L));
        }

    }
}
