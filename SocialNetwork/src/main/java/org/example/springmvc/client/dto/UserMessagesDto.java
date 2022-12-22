package org.example.springmvc.client.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@RequiredArgsConstructor
@Jacksonized
public class UserMessagesDto {
    List<MsgDto> userMessages;
}
