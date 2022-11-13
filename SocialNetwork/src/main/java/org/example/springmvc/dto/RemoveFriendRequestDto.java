package org.example.springmvc.dto;

import lombok.Data;

@Data
public class RemoveFriendRequestDto {
    private final String friendRequestId;

    public long getFriendRequestId() {
        return Long.parseLong(friendRequestId);
    }
}
