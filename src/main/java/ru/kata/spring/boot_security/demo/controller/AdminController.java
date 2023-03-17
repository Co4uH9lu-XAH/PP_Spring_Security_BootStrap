package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("show-user/{id}")
    public String getUser(@PathVariable("id") int id, Model model ) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show-user";
    }
    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/admin";
    }

    @PatchMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        getUserRoles(user);
        userService.update(user, id);
        return "redirect:/admin/admin";
    }


    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        User authenticatedUser = userService.findByUserName(principal.getName());
        model.addAttribute("authenticatedUser", authenticatedUser);
        model.addAttribute("authenticatedUserRoles", authenticatedUser.getRoles());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleService.getAll());
        return "/admin/admin";
    }

    private void getUserRoles(User user) {
        user.setRole(user.getRoles().stream()
                .map(role -> roleService.getRoleByName(role.getRole()))
                .collect(Collectors.toSet()));
    }
}
