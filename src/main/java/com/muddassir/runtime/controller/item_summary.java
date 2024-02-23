package com.muddassir.runtime.controller;

import com.muddassir.runtime.MyRequest;
import com.muddassir.runtime.TestFlow;
import com.muddassir.runtime.security.UserSecurityContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("item_summary")
@Tag(name = "item_summary")
public class item_summary {

  @Autowired private TestFlow testFlow;

  @PostMapping("/item_summary/search_by_image")
  @Operation(summary = "searchByImage", description = "")
  public void searchByImage(
      @RequestHeader("Content-Type") String ContentType,
      @RequestBody MyRequest myRequest,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    testFlow.run(ContentType, myRequest, securityContext);
  }
}
