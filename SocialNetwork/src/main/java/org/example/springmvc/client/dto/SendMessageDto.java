package org.example.springmvc.client.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@RequiredArgsConstructor
@Jacksonized
public class SendMessageDto {
    private long recipientId;
    private String messageText;
}
