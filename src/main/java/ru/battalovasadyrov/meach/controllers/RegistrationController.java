package ru.battalovasadyrov.meach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(HttpServletResponse response,
                               @CookieValue(value = "usernameCookie", defaultValue = "") String usernameCookie,
                               @RequestParam(value = "username", required = false) String username,
                               Model model) {
        Cookie cookie = new Cookie("usernameCookie", username);
        response.addCookie(cookie);
        model.addAttribute("usernameCookie", usernameCookie);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (!userService.addUser(user)) {
                model.addAttribute("message", "User exists!");
                return "registration";
            }
            return "redirect:/activate";
        } else {
            model.addAttribute("message", "Passwords not same");
            return "registration";
        }
    }

    @GetMapping("/password-reset")
    public String passwordResetPage(Model model) {
        return "password-reset";
    }

    @PostMapping("/password-reset")
    public String passwordReset(@RequestParam String email, Model model) {
        if (userService.sendResetEmail(email)) {
            return "set-password";
        } else {
            model.addAttribute("message", "You entered wrong E-Mail!");
            return "password-reset";
        }
    }

    @GetMapping("set-password")
    public String setPassword() {
        return "set-password";
    }

}