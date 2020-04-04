package com.przemo.rentcar.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.przemo.rentcar.models.Email;

public interface EmailService {

    void pushEmailToRabbitQueue(Email email) throws JsonProcessingException;
}
