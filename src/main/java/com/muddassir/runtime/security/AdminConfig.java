package com.muddassir.runtime.security;

import com.muddassir.runtime.model.AppUser;
import com.muddassir.runtime.request.AppUserCreate;
import com.muddassir.runtime.request.AppUserFilter;
import com.muddassir.runtime.service.AppUserService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AdminConfig {

  @Autowired @Lazy private AppUserService appUserService;

  @Value("${admin.username:admin@FC-1201.com}")
  private String username;

  @Value("${admin.password:#{T(java.util.UUID).randomUUID().toString()}}")
  private String password;

  @Bean
  public AdminHolder admin() {
    AppUser appUser =
        appUserService
            .listAllAppUsers(new AppUserFilter().setUsername(Collections.singleton(username)), null)
            .stream()
            .findFirst()
            .orElseGet(
                () ->
                    appUserService.createAppUser(
                        new AppUserCreate()
                            .setUsername(username)
                            .setPassword(password)
                            .setRoles(Roles.Admin.name()),
                        null));
    return new AdminHolder(appUser);
  }

  public record AdminHolder(AppUser admin) {}
}
