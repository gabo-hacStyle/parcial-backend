package demo.securityapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorld {
        @GetMapping("/hello")
        public String helloWorld() {
            return "helloWorld";
        }

}
