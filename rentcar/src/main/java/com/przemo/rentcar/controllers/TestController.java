package com.przemo.rentcar.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.przemo.rentcar.entities.cars.Car;
import com.przemo.rentcar.repositoriesDB.CarRepository;
import com.przemo.rentcar.repositoriesDB.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
public class TestController
{
    static List<Car> cars = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ClientRepository clientRepository;

    private final String EXCHANGE = "mailExchange";

    private final String DIRECT_MAIL_RK = "directMailRoutingKey";

    private final String MAIL_TO_ALL_RK = "mailToAllRoutingKey";

    private final String MAIL_TO_GROUP_RK = "mailToGroupRoutingKey";


    @GetMapping("/test")
    public String test() throws JsonProcessingException{
        return "test";
    }

    @GetMapping("/test2")
    public String test2() throws JsonProcessingException {
        // emailService.sendSimpleMessage("przemekwojtcz@gmail.com","test","messageMoje");
        EmailDetails emailDetails = new EmailDetails("fromMe","toYou","mailToAll");
        rabbitTemplate.convertAndSend(EXCHANGE,MAIL_TO_ALL_RK,objectMapper.writeValueAsString(emailDetails));
        return "test";
    }
    @GetMapping("/test3")
    public String test3()throws JsonProcessingException{
        // emailService.sendSimpleMessage("przemekwojtcz@gmail.com","test","messageMoje");
        EmailDetails emailDetails = new EmailDetails("fromMe","toYou","mailToGruop");
        rabbitTemplate.convertAndSend(EXCHANGE,MAIL_TO_GROUP_RK,objectMapper.writeValueAsString(emailDetails));
        return "test";
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private class EmailDetails {

        private String to;
        private String from;
        private String message;
    }
}
