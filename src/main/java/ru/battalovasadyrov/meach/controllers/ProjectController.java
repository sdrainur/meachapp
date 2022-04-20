package ru.battalovasadyrov.meach.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project")
public class ProjectController {
    @GetMapping("/")
    public String getProjects(){
        return "project";
    }

    @GetMapping("/project-details")
    public String getProjectDetails(){
        return "project-details";
    }
}
