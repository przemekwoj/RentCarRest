package com.przemo.emailsender.models;

import com.przemo.emailsender.reveivers.QueueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class Email {
    private QueueType receivers;
    private String message;
    private String subject;
    private List<Attachment> attachments;
}

