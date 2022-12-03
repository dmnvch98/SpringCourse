package org.example.springmvc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class CredentialsDto {
  String username;
  String password;
}
