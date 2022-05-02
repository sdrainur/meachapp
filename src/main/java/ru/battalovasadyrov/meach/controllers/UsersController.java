package ru.battalovasadyrov.meach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.repository.UserRepository;

import java.util.Optional;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public String usersPage(Model model) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("users/{id}")
    public String userPage(@PathVariable("id") Long id, Model model) {
        Optional<User> optionalUserFromDb = userRepository.findById(id);
        User userFromDb = null;
        if (optionalUserFromDb.isPresent()) {
            userFromDb = optionalUserFromDb.get();
        }
        model.addAttribute("user", userFromDb);
        return "about-user";
    }

    @GetMapping("/info-editor")
    public String userEditor(){
        return "user-editor";
    }

    @PostMapping("/info-editor")
    public String editUser(){

         return "redirect:/home";
    }
}
