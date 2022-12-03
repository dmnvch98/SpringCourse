package org.example.springmvc.dto;

import lombok.Value;

@Value
public class JwtResponse {
  String accessToken;
  String refreshToken;
}
