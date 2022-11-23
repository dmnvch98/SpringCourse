package org.example.springmvc.session;

import lombok.Data;
import org.example.springmvc.model.User;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthContext {
  private boolean authorized;
  private String currentUsername;
  private User user;
}
