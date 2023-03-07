package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/show-users";
    }

    @GetMapping("show-user/{id}")
    public String getUser(@PathVariable("id") int id, Model model ) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show-user";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/showUsers";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable ("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin/edit-user";
    }
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute User user, @PathVariable("id") int id) {
        userService.update(user, id);
        return "redirect:/admin/showUsers";
    }
}
