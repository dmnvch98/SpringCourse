package org.example.springmvc.session;

import lombok.Data;
import org.example.springmvc.model.User;

@Data
public class AuthContext {
  private boolean authorized;
  private String currentUsername;
  private User user;
}
