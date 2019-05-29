package AbstShop.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import AbstShop.dto.UserDto;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String helloTest(@ModelAttribute("user") @Valid UserDto accountDto, 
            BindingResult result, WebRequest request, Errors errors) {
      return "hello";
    }
}
