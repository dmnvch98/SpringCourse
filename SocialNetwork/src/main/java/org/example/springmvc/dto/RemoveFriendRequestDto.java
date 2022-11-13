package org.example.springmvc.dto;

import lombok.Data;

@Data
public class RemoveFriendRequestDto {
    private final long id;
    private final String requestUsername;
    private final String approveUsername;
}
