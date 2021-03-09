package ru.emergency.room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.emergency.room.entity.HospitalWard;
import ru.emergency.room.service.HospitalWardService;

@Controller
public class HospitalWardController {

    @Autowired
    private HospitalWardService hospitalWardService;

    @GetMapping("/hospitalList")
    public String getHospitalList(Model model) {
        model.addAttribute("hospitalList", hospitalWardService.findAll());
        return "hospitalList";
    }

    @GetMapping("/addHospital")
    public String getAddHospitalWard(Model model) {
        model.addAttribute("hospitalWard", new HospitalWard());
        return "addHospital";
    }

    @PostMapping("/addHospital")
    public String addHospital(@ModelAttribute("hospitalWard") HospitalWard hospitalWard, Model model) {
        HospitalWard save = hospitalWardService.save(hospitalWard);
        if (save == null) {
            model.addAttribute("error", "Ошибка добавления");
            return "addHospital";
        }
        return "redirect:/hospitalList";
    }

    @GetMapping("/editHospital")
    public String getEditHospital(Model model, @RequestParam int id) {
        HospitalWard findById = hospitalWardService.findById(id);
        model.addAttribute("hospitalWard", findById);
        return "editHospital";
    }

    @PostMapping("/editHospital")
    public String editHospital(@ModelAttribute("hospitalWard") HospitalWard hospitalWard, Model model) {
        HospitalWard save = hospitalWardService.save(hospitalWard);
        if (save == null) {
            model.addAttribute("error", "Ошибка редактирования");
            return "editHospital";
        }
        return "redirect:/hospitalList";
    }
    
    @GetMapping("/deleteHospital")
    public String getDeleteHospital(Model model, @RequestParam int id){
        HospitalWard findById = hospitalWardService.findById(id);
        hospitalWardService.delete(findById);
        return "redirect:/hospitalList";
    }
}
