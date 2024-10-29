package demo.securityapp.controllers;

import demo.securityapp.dto.request.CreateRegistroCarroDTO;
import demo.securityapp.services.ParkingRegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String showParking(Model model){
        model.addAttribute("parking", parkingRegisterServices.getAllParkingRegisters());
        return "parking";
    }

    @GetMapping("/create")
    public String showCreateParkingForm(Model model){
        model.addAttribute("createParkingDTO", new CreateRegistroCarroDTO());
        return "createParkingRegister";
    }
    @PostMapping("/save")
    public String saveParkingRegister(@ModelAttribute CreateRegistroCarroDTO createRegistroCarroDTO){
        parkingRegisterServices.saveParkingRegister(createRegistroCarroDTO);
        return "redirect:/parking";
    }

    @GetMapping("/edit/all/{placa}")
    public String showEditParkingForm(@PathVariable String placa, Model model){
        CreateRegistroCarroDTO parking = parkingRegisterServices.getParkingRegisterByPlaca(placa);
        model.addAttribute("parking", parking);
        return "editParkingRegister";
    }
    @PostMapping("/update/all/{placa}")
    public String updateParkingRegisterAll(@PathVariable String placa, @ModelAttribute CreateRegistroCarroDTO createRegistroCarroDTO){
        parkingRegisterServices.updateParkingRegisterAll(placa, createRegistroCarroDTO);
        return "redirect:/parking";
    }

    @GetMapping("/edit/ubicacion/{placa}")
    public String showEditUbicacionForm(@PathVariable String placa, Model model){
        CreateRegistroCarroDTO parking = parkingRegisterServices.getParkingRegisterByPlaca(placa);
        model.addAttribute("parking", parking);
        return "editUbicacion";
    }

    @PostMapping("/update/ubicacion/{placa}")
    public String updateParkingRegisterUbicacion(@PathVariable String placa, @RequestParam String ubicacion){
        parkingRegisterServices.updateParkingRegisterUbicacion(placa, ubicacion);
        return "redirect:/parking";
    }

}
