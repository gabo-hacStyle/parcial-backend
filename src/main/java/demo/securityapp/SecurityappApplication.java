package demo.securityapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityappApplication.class, args);

    }



}
