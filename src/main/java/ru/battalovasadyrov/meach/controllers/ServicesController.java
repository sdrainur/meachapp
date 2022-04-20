package ru.battalovasadyrov.meach.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("services")
public class ServicesController {
    @GetMapping("")
    public String services(){
        return"services";
    }

    @GetMapping("/managed-it")
    public String managedIt(){
        return "managed-it";
    }

    @GetMapping("/it-support")
    public String itSupport(){
        return "it-support";
    }

    @GetMapping("it-consultancy")
    public String itConsultancy(){
        return "it-consultancy";
    }

    @GetMapping("/cloud-computing")
    public String cloudComputing(){
        return "cloud-computing";
    }

    @GetMapping("cyber-security")
    public String cyberSecurity(){
        return "cyber-security";
    }

    @GetMapping("custom-software")
    public String customSoftware(){
        return "custom-software";
    }
}
