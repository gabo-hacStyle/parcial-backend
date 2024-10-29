package demo.securityapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Tag(name = "Login", description = "Controlador para el login y logout de la aplicación.")
public class LoginController {

    @GetMapping("/login")
    @Operation(summary = "Página de login", description = "Muestra la página de login.")
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
    @Operation(summary = "Realizar login", description = "Realiza el login del usuario.")
    public String performLogin() {

        return "redirect:/parking";
    }

    //Logout
    @Operation(summary = "Logout", description = "Realiza el logout del usuario.")
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

}