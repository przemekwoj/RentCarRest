package com.przemo.emailsender.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class Attachment {

    private String fileName;
    private String attachment_encoded_b64;
    private byte[] attachment_decoded_b64;

}
