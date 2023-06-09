package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AuthController(UserService userService, RoleRepository roleRepository, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("listRole", roleService.getAll());
        model.addAttribute("user", new User());
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String registration (@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }
}
