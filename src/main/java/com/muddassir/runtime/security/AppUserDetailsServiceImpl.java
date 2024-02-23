package com.muddassir.runtime.security;

import static java.lang.String.format;

import com.muddassir.runtime.request.AppUserFilter;
import com.muddassir.runtime.service.AppUserService;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsServiceImpl implements UserDetailsService {

  private final AppUserService appUserService;

  public AppUserDetailsServiceImpl(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return appUserService
        .listAllAppUsers(new AppUserFilter().setUsername(Collections.singleton(username)), null)
        .stream()
        .findFirst()
        .map(f -> new UserSecurityContext(f))
        .orElseThrow(() -> new UsernameNotFoundException(format("User: %s, not found", username)));
  }
}
