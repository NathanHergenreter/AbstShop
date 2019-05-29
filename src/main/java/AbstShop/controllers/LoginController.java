package AbstShop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import AbstShop.dto.UserDto;
import AbstShop.entities.User;
import AbstShop.service.UserService;
import AbstShop.validation.UsernameExistsException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String loginPage(Model model) {
      return "login";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "signup";
    }
    
    @RequestMapping(value = "/perform_signup", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
          (@ModelAttribute("user") @Valid UserDto accountDto, 
          BindingResult result, WebRequest request, Errors errors) {    
    	User registered = null;
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("username", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("signup", "user", accountDto);
        } 
        else {
            return new ModelAndView("hello", "user", accountDto);
        }
    }
    
    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UsernameExistsException e) {
            return null;
        }    
        return registered;
    }
}
