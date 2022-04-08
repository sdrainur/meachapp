package ru.battalovasadyrov.meach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if(user.getPassword().equals(user.getConfirmPassword())) {
            if (!userService.addUser(user)) {
                model.addAttribute("message", "User exists!");
                return "registration";
            }
            return "/activate";
        } else {
            model.addAttribute("message", "Passwords not same");
            return "registration";
        }
    }

    @GetMapping("/activate")
    public String activationPage() {
        return "activate";
    }

    @PostMapping("/activate")
    public String activate(@RequestParam String activationCode, Model model) {
        boolean isActivated = userService.activateUser(activationCode);
        if (isActivated) {
            return "redirect:/login";
        } else {
            model.addAttribute("message", "Activation code is wrong!");
        }
        return "activate";
    }
}