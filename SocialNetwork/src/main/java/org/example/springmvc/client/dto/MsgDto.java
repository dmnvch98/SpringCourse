package org.example.springmvc.client.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.example.springmvc.model.User;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Jacksonized
public class MsgDto {
    private User sender;
    private User recipient;
    private Date messageDate;
    private String messageText;
}
