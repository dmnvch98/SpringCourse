package org.example.springmvc.dto;

import lombok.Data;

@Data
public class CreateFriendRequestDto {
    private final String requestUsername;
    private final String approveUsername;
}
