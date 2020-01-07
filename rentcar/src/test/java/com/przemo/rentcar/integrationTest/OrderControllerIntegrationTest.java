package com.przemo.rentcar.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.przemo.rentcar.entities.orders.OrderInfo;
import com.przemo.rentcar.services.CarOrderService;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTest.sql")
})
public class OrderControllerIntegrationTest {

    @Autowired
    private CarOrderService carOrderService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles={"Administration"})
    void shoudlAddNewOrderInfo() throws Exception {
        //given
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCarId(2L);
        orderInfo.setClientId(2L);
        orderInfo.setEmployeeMail("jan@wp.pl");
        orderInfo.setEndDate(new Date());
        orderInfo.setStartDate(new Date());
        //when
        MvcResult result = mockMvc.perform(post("/order/createNewOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderInfo))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        int amountOfOrders = carOrderService.getOrdersInfo().size();

        assertEquals(2,amountOfOrders);
    }

}
