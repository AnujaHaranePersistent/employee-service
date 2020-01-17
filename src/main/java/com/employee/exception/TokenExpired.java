package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "TOKEN_EXPIRED")
public class TokenExpired extends Exception {

  public TokenExpired() {
    super();
  }

  public TokenExpired(String message) {
    super(message);
  }
}
