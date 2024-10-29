package demo.securityapp.controllers;

import demo.securityapp.dto.request.CreateRegistroCarroDTO;
import demo.securityapp.dto.request.UpdateRegistroCarroDTO;
import demo.securityapp.services.ParkingRegisterServices;
import demo.securityapp.services.TypeCarServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableMethodSecurity
@RequestMapping("/parking")
@Tag(name = "Parking", description = "Controlador para la gestión de los registros de parqueo.")
public class ParkingController {
    @Autowired
    ParkingRegisterServices parkingRegisterServices;

    @Autowired
    TypeCarServices typeCarServices;

    @GetMapping
    @Operation(summary = "Mostrar registros de parqueo", description = "Muestra todos los registros de parqueo.")
    public String showParking(Model model){
        model.addAttribute("parking", parkingRegisterServices.getAllParkingRegisters());
        return "parking";
    }

    @GetMapping("/staff/parking")
    @Operation(summary = "Mostrar registros de parqueo para el personal", description = "Muestra todos los registros de parqueo con posibildad de realizar acciones sobre ellos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACOMODADOR')")
    public String showStaffParking(Model model){
        model.addAttribute("parking", parkingRegisterServices.getAllParkingRegisters());
        return "staff/parking";
    }

    @GetMapping("/edit/ubicacion/{placa}")
    @Operation(summary = "Editar la ubicación de un registro de parqueo", description = "Permite editar la ubicación de un registro de parqueo.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACOMODADOR')")
    public String showEditUbicacionForm(@PathVariable String placa, Model model){
        CreateRegistroCarroDTO parking = parkingRegisterServices.getParkingRegisterByPlaca(placa);

        model.addAttribute("parking", parking);
        return "editRegistroUbicacion";
    }

    @PostMapping("/update/ubicacion/{placa}")
    @Operation(summary = "Actualizar la ubicación de un registro de parqueo", description = "Permite actualizar la ubicación de un registro de parqueo.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACOMODADOR')")
    public String updateParkingRegisterUbicacion(@PathVariable String placa, @RequestParam String ubicacion){
        parkingRegisterServices.updateParkingRegisterUbicacion(placa, ubicacion);
        return "redirect:/parking";
    }


    @GetMapping("/delete/{placa}")
    @Operation(summary = "Eliminar un registro de parqueo", description = "Permite eliminar un registro de parqueo.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteParkingRegister(@PathVariable String placa){
        parkingRegisterServices.deleteParkingRegister(placa);
        return "redirect:/parking";
    }




    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Mostrar formulario de creación de registro de parqueo", description = "Muestra el formulario para crear un nuevo registro de parqueo.")
    public String showCreateParkingForm(Model model){
        model.addAttribute("typeCar", typeCarServices.getAllTypeCars());
        model.addAttribute("createParkingDTO", new CreateRegistroCarroDTO());
        return "createRegistro";
    }
    @PostMapping("/save")
    @Operation(summary = "Guardar registro de parqueo", description = "Permite guardar un registro de parqueo.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveParkingRegister(@ModelAttribute CreateRegistroCarroDTO createRegistroCarroDTO){
        parkingRegisterServices.saveParkingRegister(createRegistroCarroDTO);
        return "redirect:/parking";
    }

    @GetMapping("/edit/all/{placa}")
    @Operation(summary = "Pagina de edicion de registro de parqueo", description = "Muestra una pagina para  editar un registro de parqueo completamente, no solo la ubicacion.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showEditParkingForm(@PathVariable String placa, Model model){
        UpdateRegistroCarroDTO parking = parkingRegisterServices.getParkingRegisterByPlacaToUpdate(placa);
        model.addAttribute("parking", parking);
        model.addAttribute("typeCar", typeCarServices.getAllTypeCars());
        return "editRegistroAll";
    }
    @PostMapping("/update/all/{placa}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Editar un registro de parqueo", description = "Permite editar un registro de parqueo completo, no solo la ubicacion.")
    public String updateParkingRegisterAll(@PathVariable String placa, @ModelAttribute UpdateRegistroCarroDTO updateRegistroCarroDTO){
        parkingRegisterServices.updateParkingRegisterAll(placa, updateRegistroCarroDTO);
        return "redirect:/parking";
    }



}
