package com.przemo.rentcar.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.przemo.rentcar.entities.users.Administration;
import com.przemo.rentcar.exceptions.particularErrors.NotFoundEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
//@ActiveProfiles("test")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTest.sql")
})
public class AdministrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles={"Administration"})
    void shoudlReturnWholeListOfAdministation() throws Exception {
        //given
        int expectedSize = 3;
        //when
        MvcResult result = mockMvc.perform(get("/administration/administrations")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        String response = result.getResponse().getContentAsString();
        Administration[] administrations = objectMapper.readValue(response, Administration[].class);
        assertEquals(expectedSize,administrations.length);
    }

    @Test
    @WithMockUser(roles={"Administration"})
    void shoudlThrowNotFoundExceptionById() throws Exception {
        MvcResult result = mockMvc.perform(get("/administration/5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertAll(
                () ->assertTrue(result.getResolvedException() instanceof NotFoundEntity),
                () ->assertEquals("Not found administration with id 5",result.getResolvedException().getMessage())
        );
    }
}
