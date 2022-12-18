package com.example.messageservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@RequiredArgsConstructor
@Jacksonized
public class UserMessagesDto {
    List<MessageDto> userMessages;
}
