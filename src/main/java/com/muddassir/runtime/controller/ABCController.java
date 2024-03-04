package com.muddassir.runtime.controller;

import com.muddassir.runtime.model.ABC;
import com.muddassir.runtime.request.ABCCreate;
import com.muddassir.runtime.request.ABCFilter;
import com.muddassir.runtime.request.ABCUpdate;
import com.muddassir.runtime.response.PaginationResponse;
import com.muddassir.runtime.security.UserSecurityContext;
import com.muddassir.runtime.service.ABCService;
import com.muddassir.runtime.validation.Create;
import com.muddassir.runtime.validation.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ABC")
@Tag(name = "ABC")
public class ABCController {

  @Autowired private ABCService aBCService;

  @PostMapping("createABC")
  @Operation(summary = "createABC", description = "Creates ABC")
  public ABC createABC(
      @Validated(Create.class) @RequestBody ABCCreate aBCCreate, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return aBCService.createABC(aBCCreate, securityContext);
  }

  @PostMapping("getAllABCs")
  @Operation(summary = "getAllABCs", description = "lists ABCs")
  public PaginationResponse<ABC> getAllABCs(
      @Valid @RequestBody ABCFilter aBCFilter, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return aBCService.getAllABCs(aBCFilter, securityContext);
  }

  @PutMapping("updateABC")
  @Operation(summary = "updateABC", description = "Updates ABC")
  public ABC updateABC(
      @Validated(Update.class) @RequestBody ABCUpdate aBCUpdate, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return aBCService.updateABC(aBCUpdate, securityContext);
  }
}
