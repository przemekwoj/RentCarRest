package com.przemo.rentcar.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.repositoriesDB.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTest.sql")
})
public class CarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    @WithMockUser(roles = {"Administration"})
    public void shouldReturnBrandsWithCarsAndCarsDetails() throws Exception {
        //given
        long brandId = 1L;
        int amountOfCarsBeforeAdd = brandRepository.findById(1L).get().getAmountOfAvailableCars();
        Car car = new Car();
        car.setPlateNumber("test");
        //when
        mockMvc.perform(post("/car/carWithBrand/"+brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        assertEquals(amountOfCarsBeforeAdd + 1,brandRepository.findById(1L).get().getAmountOfAvailableCars());
    }
}
