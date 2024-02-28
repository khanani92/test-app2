package com.muddassir.runtime;

import com.muddassir.runtime.security.UserSecurityContext;
import org.springframework.stereotype.Component;

@Component
public class TestBFLow {

  public boolean run(String ContentType, MyRequest body, UserSecurityContext securityContext) {

    com.muddassir.runtime.model.Genre genre = body.getGenre();

    int myVar = 3;

    return true;
  }
}
