package AbstShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
 
    @GetMapping("/")
    public String homePage(Model model) {
      return "home";
    }
}

