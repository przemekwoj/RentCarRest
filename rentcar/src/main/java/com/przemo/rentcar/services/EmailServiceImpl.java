package com.przemo.rentcar.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.przemo.rentcar.models.Email;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private final String EXCHANGE = "mailExchange";


    public EmailServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void pushEmailToRabbitQueue(Email email) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(EXCHANGE, email.getReceivers().getQueueKey(), objectMapper.writeValueAsString(email));
    }
}
