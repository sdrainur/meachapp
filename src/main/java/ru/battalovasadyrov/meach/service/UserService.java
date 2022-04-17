package ru.battalovasadyrov.meach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ru.battalovasadyrov.meach.model.Role;
import ru.battalovasadyrov.meach.model.User;
import ru.battalovasadyrov.meach.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    // @PostMapping("/registration")
    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail());
        if (userFromDb != null) {
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setActivationCode(user);
        user.setConfirmPassword(null);
        userRepository.save(user);
        return true;
    }

    public boolean activateUser(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode);
        if (user == null){
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    private void setActivationCode(User user){
        user.setActivationCode(UUID.randomUUID().toString());
        if(!user.getEmail().isEmpty()){
            String message = String.format(
                    "Hello, %s \n" +
                            "Welcome to meach! Your activation code: %s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean sendResetEmail(String email){
        User user = userRepository.findByEmail(email);
        user.setActivationCode(UUID.randomUUID().toString());
        if(!user.getEmail().isEmpty()){
            String message = String.format(
                    "Hello, %s \n" + "! Your password-reset code: %s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Password reset", message);
            return true;
        }
        return false;
    }

    public boolean resetPassword(String email){
        User user = userRepository.findByEmail(email);
        setActivationCode(user);
        return true;
    }
}
