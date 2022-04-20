package ru.battalovasadyrov.meach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.battalovasadyrov.meach.service.UserService;

@Controller
@RequestMapping("/activate")
public class ActivateController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String activationPage() {
        return "activate";
    }

    @PostMapping("")
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
