package com.hopniel.gestionstock.controller;

import com.hopniel.gestionstock.model.Role;
import com.hopniel.gestionstock.model.User;
import com.hopniel.gestionstock.repository.RoleRepository;
import com.hopniel.gestionstock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Vérifier si l'utilisateur existe déjà
        if (userService.existsByUsername(user.getUsername())) {
            return "redirect:/register?error=username";
        }
        if (userService.existsByEmail(user.getEmail())) {
            return "redirect:/register?error=email";
        }

        // Enregistrer l'utilisateur
        User savedUser = userService.saveUser(user);
        
        // Ajouter le rôle USER par défaut
        userService.addRoleToUser(savedUser.getUsername(), "ROLE_USER");
        
        return "redirect:/login?registered";
    }
}
