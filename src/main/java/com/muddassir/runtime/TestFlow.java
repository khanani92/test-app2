package com.muddassir.runtime;

import com.muddassir.runtime.security.UserSecurityContext;
import org.springframework.stereotype.Component;

@Component
public class TestFlow {

  public boolean run(String ContentType, MyRequest body, UserSecurityContext securityContext) {

    return true;
  }
}
