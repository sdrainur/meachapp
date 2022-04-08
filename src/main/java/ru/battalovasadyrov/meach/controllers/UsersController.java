package ru.battalovasadyrov.meach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.repository.UserRepository;

@Controller
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String usersPage(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String userPage(@RequestParam Long id, Model model){
        return "user-page";
    }
}
