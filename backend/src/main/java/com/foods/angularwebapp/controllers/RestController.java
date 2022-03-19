package com.foods.angularwebapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController implements ErrorController {
  @RequestMapping("/error")
  public void handleError(HttpServletRequest request, HttpServletResponse response) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        response.addHeader("Location", "/500.html");
      } else {
        response.addHeader("Location", "/");
      }
      response.setStatus(302);
    }
  }
}
