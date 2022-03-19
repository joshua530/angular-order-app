package com.foods.angularwebapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestController implements ErrorController {
  @RequestMapping("/error")
  public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
    model.addAttribute("attribute", "forwardWithForwardPrefix");
    return new ModelAndView("forward:/", model);
  }
}
