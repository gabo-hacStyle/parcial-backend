package demo.securityapp.controllers;


import demo.securityapp.dto.request.CreateUserDTO;
import demo.securityapp.dto.response.UserResponseDto;
import demo.securityapp.services.UserEntityServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@Tag(name = "User", description = "Controlador para la gestión de usuarios.")
public class UserController {

    @Autowired
    private UserEntityServices userEntityServices;

    @GetMapping
    @Operation(summary = "Mostrar todos los usuarios", description = "Muestra todos los usuarios")
    public String showAllUsers(Model model){
        model.addAttribute("users", userEntityServices.getAllUsers());
        return "users";
    }

    @Operation(summary = "Crear usuario", description = "Muestra el formulario para crear un usuario.")
    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("createUserDTO", new CreateUserDTO());
        return "createUser";
    }

    @PostMapping("/save")
    @Operation(summary = "Guardar usuario", description = "Guarda un usuario.")
    public String saveUser(@ModelAttribute CreateUserDTO createUserDTO){
        userEntityServices.saveUser(createUserDTO);
        return "redirect:/users";
    }

    @GetMapping("/edit/{username}")
    @Operation(summary = "Editar usuario", description = "Muestra el formulario para editar un usuario.")
    public String showEditUserForm(@PathVariable String username, Model model){
        UserResponseDto user = userEntityServices.getUserByUsername(username);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/update/{username}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza un usuario.")
    public String updateUser(@PathVariable String username, @ModelAttribute  CreateUserDTO createUserDTO){
        userEntityServices.updateUser(username, createUserDTO);
        return "redirect:/users";
    }

    @GetMapping("/delete/{username}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario.")
    public String deleteUser(@PathVariable String username){
        userEntityServices.deleteUser(username);
        return "redirect:/users";
    }


}
