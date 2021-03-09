package ru.emergency.room.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.emergency.room.entity.Appointment;
import ru.emergency.room.entity.Search;
import ru.emergency.room.entity.User;
import ru.emergency.room.service.AppointmentService;
import ru.emergency.room.service.HospitalWardService;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private HospitalWardService hospitalWardService;

    @PostMapping("/search")
    public String search(@ModelAttribute("search") Search search, Model model){
        List<Appointment> find = appointmentService.search(search);
        Date currentDate = new Date();
        for (Appointment appointment : find) {
            appointment.setCountDay(daysBetween(currentDate, appointment.getReceiptDate()));
        }
        model.addAttribute("appointmentList", find);
        model.addAttribute("search", search);
        return "index";
    }

    @GetMapping("/index")
    public String getAppointmentList(Model model) {
        List<Appointment> appointmentList = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole().getName().equals("ROLE_ADMIN")) {
            appointmentList = appointmentService.findAll(Sort.by(Sort.Order.asc("lastName")));
        } else {
            appointmentList = appointmentService.findAll(Sort.by(Sort.Order.asc("hospitalWard"), Sort.Order.asc("lastName")));
        }
        Date currentDate = new Date();
        for (Appointment appointment : appointmentList) {
            appointment.setCountDay(daysBetween(currentDate, appointment.getReceiptDate()));
        }
        model.addAttribute("appointmentList", appointmentList);
        model.addAttribute("search", new Search());
        return "index";
    }

    @GetMapping("/addAppointment")
    public String getAddAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("hospitalList", hospitalWardService.findAll());
        return "addAppointment";
    }

    @PostMapping("/addAppointment")
    public String addAppointment(@ModelAttribute("appointment") Appointment appointment, Model model) {
        Appointment save = appointmentService.save(appointment);
        if (save == null) {
            model.addAttribute("error", "Ошибка добавления");
            return "addAppointment";
        }
        return "redirect:/index";
    }
    
    @GetMapping("/editAppointment")
    public String getEditAppointment(Model model, @RequestParam int id){
        model.addAttribute("appointment", appointmentService.findById(id));
        model.addAttribute("hospitalList", hospitalWardService.findAll());
        return "editAppointment";
    }
    
    @PostMapping("/editAppointment")
    public String editAppointment(@ModelAttribute("appointment") Appointment appointment, Model model){
        Appointment save = appointmentService.save(appointment);
        if (save == null) {
            model.addAttribute("error", "Ошибка обновления");
            return "editAppointment";
        }
        return "redirect:/index";
    }

    @GetMapping("/deleteAppointment")
    public String deleteAppointment(Model model, @RequestParam int id) {
        Appointment findById = appointmentService.findById(id);
        appointmentService.delete(findById);
        return "redirect:/index";
    }

    public long daysBetween(Date dateCurrent, Date dateBd) {
        long diffInMillies = Math.abs(dateCurrent.getTime() - dateBd.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
