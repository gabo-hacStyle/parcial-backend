package demo.securityapp.controllers;

import demo.securityapp.dto.request.CreateRegistroCarroDTO;
import demo.securityapp.services.ParkingRegisterServices;
import demo.securityapp.services.TypeCarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableMethodSecurity
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    ParkingRegisterServices parkingRegisterServices;

    @Autowired
    TypeCarServices typeCarServices;

    @GetMapping
    public String showParking(Model model){
        model.addAttribute("parking", parkingRegisterServices.getAllParkingRegisters());
        return "parking";
    }
    @GetMapping("/delete/{placa}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteParkingRegister(@PathVariable String placa){
        parkingRegisterServices.deleteParkingRegister(placa);
        return "redirect:/parking";
    }

    @GetMapping("/staff/parking")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACOMODADOR')")
    public String showStaffParking(Model model){
        model.addAttribute("parking", parkingRegisterServices.getAllParkingRegisters());
        return "staff/parking";
    }


    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showCreateParkingForm(Model model){
        model.addAttribute("typeCar", typeCarServices.getAllTypeCars());
        model.addAttribute("createParkingDTO", new CreateRegistroCarroDTO());
        return "createRegistro";
    }
    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveParkingRegister(@ModelAttribute CreateRegistroCarroDTO createRegistroCarroDTO){
        parkingRegisterServices.saveParkingRegister(createRegistroCarroDTO);
        return "redirect:/parking";
    }

    @GetMapping("/edit/all/{placa}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showEditParkingForm(@PathVariable String placa, Model model){
        CreateRegistroCarroDTO parking = parkingRegisterServices.getParkingRegisterByPlaca(placa);
        model.addAttribute("parking", parking);
        model.addAttribute("typeCar", typeCarServices.getAllTypeCars());
        return "editRegistroAll";
    }
    @PostMapping("/update/all/{placa}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateParkingRegisterAll(@PathVariable String placa, @ModelAttribute CreateRegistroCarroDTO createRegistroCarroDTO){
        parkingRegisterServices.updateParkingRegisterAll(placa, createRegistroCarroDTO);
        return "redirect:/parking";
    }

    @GetMapping("/edit/ubicacion/{placa}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACOMODADOR')")
    public String showEditUbicacionForm(@PathVariable String placa, Model model){
        CreateRegistroCarroDTO parking = parkingRegisterServices.getParkingRegisterByPlaca(placa);

        model.addAttribute("parking", parking);
        return "editRegistroUbicacion";
    }

    @PostMapping("/update/ubicacion/{placa}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACOMODADOR')")
    public String updateParkingRegisterUbicacion(@PathVariable String placa, @RequestParam String ubicacion){
        parkingRegisterServices.updateParkingRegisterUbicacion(placa, ubicacion);
        return "redirect:/parking";
    }

}
