package com.foods.angularwebapp.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
