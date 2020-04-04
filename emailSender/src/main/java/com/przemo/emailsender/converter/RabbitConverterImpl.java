package com.przemo.emailsender.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.przemo.emailsender.models.Attachment;
import com.przemo.emailsender.models.Email;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class RabbitConverterImpl implements RabbitConverter {

    private final ObjectMapper objectMapper;

    public RabbitConverterImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Email convertRabbitMessageToEmailInfo(Message message) throws JsonProcessingException {
        Email emailInfo = objectMapper.readValue((String) message.getPayload(), Email.class);
        emailInfo.getAttachments().forEach(attachment -> decodeB64(attachment));
        return emailInfo;
    }

    private void decodeB64(Attachment attachment) {
        String encodedBytes = attachment.getAttachment_encoded_b64().replaceFirst(".*base64,", "");
        byte[] decoded = Base64.decodeBase64(encodedBytes.getBytes());
        attachment.setAttachment_decoded_b64(decoded);
    }
}
