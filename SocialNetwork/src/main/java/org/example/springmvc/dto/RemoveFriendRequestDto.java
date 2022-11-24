package org.example.springmvc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RemoveFriendRequestDto {
    @NotEmpty
    @NotNull
    private String friendRequestId;

    public long getFriendRequestId() {
        return Long.parseLong(friendRequestId);
    }
}
