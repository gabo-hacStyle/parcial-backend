package demo.securityapp.controllers;


import demo.securityapp.dto.request.CreateUserDTO;
import demo.securityapp.dto.response.UserResponseDto;
import demo.securityapp.services.UserEntityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserEntityServices userEntityServices;

    @GetMapping
    public String showAllUsers(Model model){
        model.addAttribute("users", userEntityServices.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("createUserDTO", new CreateUserDTO());
        return "createUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute CreateUserDTO createUserDTO){
        userEntityServices.saveUser(createUserDTO);
        return "redirect:/users";
    }

    @GetMapping("/edit/{username}")
    public String showEditUserForm(@PathVariable String username, Model model){
        UserResponseDto user = userEntityServices.getUserByUsername(username);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/update/{username}")
    public String updateUser(@PathVariable String username, @ModelAttribute  CreateUserDTO createUserDTO){
        userEntityServices.updateUser(username, createUserDTO);
        return "redirect:/users";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username){
        userEntityServices.deleteUser(username);
        return "redirect:/users";
    }


}
