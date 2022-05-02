package ru.battalovasadyrov.meach.controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.repository.UserRepository;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername("user");
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/")
    public String hello() {
        return "index2";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/news-details")
    public String newsDetails(){
        return "news-details";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }
    @GetMapping("/project")
    public String getProject(){
        return "project";
    }

    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }
}


