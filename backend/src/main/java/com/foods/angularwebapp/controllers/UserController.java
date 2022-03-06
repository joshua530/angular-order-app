package com.foods.angularwebapp.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.foods.angularwebapp.models.User;
import com.foods.angularwebapp.services.UserService;
import com.foods.angularwebapp.utils.AuthToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "login")
  public ResponseEntity<String> login(@RequestBody User user) {
    String authToken = userService.authenticateAndGenerateToken(user);
    String response;
    HttpStatus code;
    if (authToken != null) {
      code = HttpStatus.OK;
      response = String.format("{\"token\": \"%s\"}", (authToken == null ? null
          : authToken));
    } else {
      code = HttpStatus.BAD_REQUEST;
      response = "{\"token\": null}";
    }

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-type", "application/json");
    return new ResponseEntity<String>(response, headers, code);
  }

  @PostMapping(path = "logout")
  public ResponseEntity<String> logout(@RequestBody AuthToken token) {
    String authToken = token.getToken();
    String response;
    HttpStatus code;
    if (userService.isAuthenticated(authToken)) {
      code = HttpStatus.OK;
      authToken = userService.invalidateToken(authToken);
      response = String.format("{\"token\": null}", authToken);
    } else {
      code = HttpStatus.BAD_REQUEST;
      response = "{\"token\": null}";
    }
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-type", "application/json");
    return new ResponseEntity<String>(response, headers, code);
  }
  
  @PostMapping(path="validate-token")
  public ResponseEntity<String> validateToken(@RequestBody AuthToken token) {
    String authToken = token.getToken();
    String response;
    HttpStatus code;
    try {
      if (userService.isAuthenticated(authToken)) {
        code = HttpStatus.OK;
        response = String.format("{\"token\": \"%s\"}", authToken);
      } else {
        code = HttpStatus.BAD_REQUEST;
        response = "{\"token\": null}";
      }
    } catch (Exception e) {
      code = HttpStatus.BAD_REQUEST;
      response = "{\"token\": null}";
    }
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-type", "application/json");
    return new ResponseEntity<String>(response, headers, code);
  }
}
