package ru.battalovasadyrov.meach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.repository.UserRepository;

import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-page";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam("username") String username) {
        Optional<User> optionalUserFromDb = userRepository.findById(id);
        User userFromDb = null;
        if (optionalUserFromDb.isPresent()) {
            userFromDb = optionalUserFromDb.get();
        }
        userFromDb.setUsername(username);
        userRepository.save(userFromDb);
        return "redirect:/admin";
    }
}
