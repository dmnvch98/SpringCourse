package org.example.springmvc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class UserRestDto {
    Long id;
    String username;
    String role;
    Date createdAt;
}
