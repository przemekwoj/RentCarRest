package com.przemo.rentcar.services;

import com.przemo.rentcar.entities.cars.Brand;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.entities.cars.CarDetails;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import com.przemo.rentcar.repositoriesDB.CarDetailsRepository;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CarService function testing")
public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private BrandService brandService;

    @Mock
    private CarDetailsRepository carDetailsRepository;

    @Nested
    @DisplayName("Get methods test")
    class GetMethods{

        @Test
        @DisplayName("should return car by platenumber")
        void shouldReturnCarByPlatenumber(){
            //given
            Car car = new Car();
            car.setPlateNumber("abc");
            when(carRepository.findByPlateNumber("abc")).thenReturn(Optional.of(car));
            //when
            Car actual = carService.findByPlateNumber("abc");
            //then
            assertAll(
                    ()->assertEquals(car,actual),
                    ()->assertEquals(car.getPlateNumber(),actual.getPlateNumber())
            );
        }

        @Test
        @DisplayName("should Throw No tFound Exception By Wrong Platenumber")
        void shouldThrowNotFoundExceptionByWrongPlatenumber(){
            when(carRepository.findByPlateNumber("x")).thenThrow( new NotFoundEntity("Not found"));
            assertThrows(NotFoundEntity.class, () -> carRepository.findByPlateNumber("x"));
        }

        @Test
        @DisplayName("should return car by id")
        void shouldReturnCarById(){
            //given
            Car car = new Car();
            car.setCar_id(1L);
            when(carRepository.getCarByIdLazy(1L)).thenReturn(Optional.of(car));
            //when
            Car actual = carService.getCarByIdLazy(1L);
            //then
            assertAll(
                    ()->assertEquals(car,actual),
                    ()->assertEquals(car.getCar_id(),actual.getCar_id())
            );
        }

        @Test
        @DisplayName("should Throw No tFound Exception By Wrong Id")
        void shouldThrowNotFoundExceptionByWrongId(){
            when(carRepository.getCarByIdLazy(2L)).thenThrow( new NotFoundEntity("Not found"));
            assertThrows(NotFoundEntity.class, () -> carRepository.getCarByIdLazy(2L));
        }
    }

    @Nested
    @DisplayName("modyfied method test")
    class ModyfiedMethods{

        @Test
        @DisplayName("should add car to brand")
        void shouldAddCarToBrand(){
            //given
            Car car = new Car();
            Brand brand = new Brand();
            brand.setBrand_id(2L);
            when(brandService.getBrandById(2L)).thenReturn(brand);
            when( carDetailsRepository.save(any())).thenReturn(new CarDetails());
            //when
            carService.addNewCarWithBrand(car,2L);
            //then
            assertTrue(brand.getCars().contains(car),"should contain car");
        }

        @Test
        @DisplayName("should save Car With CarDetails")
        void shouldSaveCarWithCarDetails(){
            Car car = new Car();
            CarDetails carDetails = new CarDetails();
            when(carDetailsRepository.save(carDetails)).thenReturn(carDetails);
            carService.saveCarWithCarDetails(car,carDetails);
            assertEquals(carDetails.getCar(),car);

        }

        @Test
        @DisplayName("should update CarDetail")
        void shouldUpdateCarDetails(){
            //given
            CarDetails carDetails = new CarDetails();
            Car car = new Car();
            when(carRepository.getCarByIdLazy(1L)).thenReturn(Optional.of(car));
            when(carRepository.save(car)).thenReturn(car);
            //when
            carService.updateCarDetail(carDetails,1L);
            //then
            assertEquals(car.getCarDetails(),carDetails);



        }

    }

}
