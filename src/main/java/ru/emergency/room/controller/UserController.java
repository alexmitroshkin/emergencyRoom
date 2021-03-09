package ru.emergency.room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.emergency.room.entity.Role;
import ru.emergency.room.entity.User;
import ru.emergency.room.service.RoleService;
import ru.emergency.room.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model mode) {
        if (userService.loadUserByUsername(user.getUsername()) == null) {
            if (user.getRole() == null) {
                Role role = roleService.findByName("ROLE_USER");
                user.setRole(role);
            }
            boolean saveUser = userService.saveUser(user);
            if (!saveUser) {
                return "registration";
            }
        }
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

}
