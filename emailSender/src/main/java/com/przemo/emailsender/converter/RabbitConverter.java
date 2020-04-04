package com.przemo.emailsender.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.przemo.emailsender.models.Email;
import org.springframework.messaging.Message;

public interface RabbitConverter {
    Email convertRabbitMessageToEmailInfo(Message message) throws JsonProcessingException;
}
