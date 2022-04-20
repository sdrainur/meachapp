package ru.battalovasadyrov.meach.controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
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


