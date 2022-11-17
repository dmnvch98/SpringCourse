package org.example.springmvc.session;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthContext {
  private boolean authorized;
}
