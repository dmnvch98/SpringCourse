package com.example.messageservice.dto;

import java.util.Date;

import com.example.messageservice.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@RequiredArgsConstructor
@Jacksonized
public class MsgDto {
    private User sender;
    private User recipient;
    private Date messageDate;
    private String messageText;
}
