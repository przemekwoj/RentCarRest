package com.przemo.rentcar.models;

import com.przemo.rentcar.RabbitQueue.QueueType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class Email {

    private QueueType receivers;
    private String message;
    private String subject;
    private List<Attachment> attachments;


    @ToString
    @Getter
    @Setter
    private static class Attachment{
        private String fileName;
        private String attachment_encoded_b64;
    }
}



