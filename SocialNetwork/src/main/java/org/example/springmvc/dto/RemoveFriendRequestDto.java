package org.example.springmvc.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RemoveFriendRequestDto {
    @NotEmpty
    @NotNull
    private final String friendRequestId;

    public long getFriendRequestId() {
        return Long.parseLong(friendRequestId);
    }
}
