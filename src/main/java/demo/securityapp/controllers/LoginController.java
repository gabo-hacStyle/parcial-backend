package demo.securityapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password.");
        }
        if (logout != null) {
            modelAndView.addObject("message", "You have been logged out successfully.");
        }
        return modelAndView;
    }

    @PostMapping("/perform_login")
    public String performLogin() {
        // Spring Security will handle the login process
        return "redirect:/parking";
    }

    //Logout
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

}