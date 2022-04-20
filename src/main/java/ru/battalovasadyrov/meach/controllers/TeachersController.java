package ru.battalovasadyrov.meach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.battalovasadyrov.meach.model.Role;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.repository.UserRepository;

@Controller
public class TeachersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/teachers")
        public String getTeachers(Model model){
            Iterable<User> teachers = userRepository.findByRoles(Role.TEACHER);
            model.addAttribute("teachers", teachers);
            return "teachers";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model){
        Iterable<User> teachers = userRepository.findByRolesAndUsername(Role.TEACHER, filter);
        model.addAttribute("teachers", teachers);
        return "teachers";
    }
}
