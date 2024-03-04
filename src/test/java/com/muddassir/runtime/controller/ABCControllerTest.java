package com.muddassir.runtime.controller;

import com.muddassir.runtime.AppInit;
import com.muddassir.runtime.model.ABC;
import com.muddassir.runtime.request.ABCCreate;
import com.muddassir.runtime.request.ABCFilter;
import com.muddassir.runtime.request.ABCUpdate;
import com.muddassir.runtime.request.LoginRequest;
import com.muddassir.runtime.response.PaginationResponse;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppInit.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ABCControllerTest {

  private ABC testABC;
  @Autowired private TestRestTemplate restTemplate;

  @BeforeAll
  private void init() {
    ResponseEntity<Object> authenticationResponse =
        this.restTemplate.postForEntity(
            "/login",
            new LoginRequest().setUsername("admin@flexicore.com").setPassword("admin"),
            Object.class);
    String authenticationKey =
        authenticationResponse.getHeaders().get(HttpHeaders.AUTHORIZATION).stream()
            .findFirst()
            .orElse(null);
    restTemplate
        .getRestTemplate()
        .setInterceptors(
            Collections.singletonList(
                (request, body, execution) -> {
                  request.getHeaders().add("Authorization", "Bearer " + authenticationKey);
                  return execution.execute(request, body);
                }));
  }

  @Test
  @Order(1)
  public void testABCCreate() {
    ABCCreate request = new ABCCreate();

    request.setName("test-string");

    ResponseEntity<ABC> response =
        this.restTemplate.postForEntity("/ABC/createABC", request, ABC.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testABC = response.getBody();
    assertABC(request, testABC);
  }

  @Test
  @Order(2)
  public void testListAllABCs() {
    ABCFilter request = new ABCFilter();
    ParameterizedTypeReference<PaginationResponse<ABC>> t = new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<ABC>> response =
        this.restTemplate.exchange(
            "/ABC/getAllABCs", HttpMethod.POST, new HttpEntity<>(request), t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<ABC> body = response.getBody();
    Assertions.assertNotNull(body);
    List<ABC> ABCs = body.getList();
    Assertions.assertNotEquals(0, ABCs.size());
    Assertions.assertTrue(ABCs.stream().anyMatch(f -> f.getId().equals(testABC.getId())));
  }

  public void assertABC(ABCCreate request, ABC testABC) {
    Assertions.assertNotNull(testABC);

    if (request.getName() != null) {
      Assertions.assertEquals(request.getName(), testABC.getName());
    }
  }

  @Test
  @Order(3)
  public void testABCUpdate() {
    ABCUpdate request = new ABCUpdate().setId(testABC.getId());
    ResponseEntity<ABC> response =
        this.restTemplate.exchange(
            "/ABC/updateABC", HttpMethod.PUT, new HttpEntity<>(request), ABC.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testABC = response.getBody();
    assertABC(request, testABC);
  }
}
