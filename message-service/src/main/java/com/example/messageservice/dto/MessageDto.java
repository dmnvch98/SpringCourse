package com.example.messageservice.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@RequiredArgsConstructor
@Jacksonized
public class MessageDto {
    private long senderId;
    private long recipientId;
    private Date messageDate;
    private String messageText;
}
