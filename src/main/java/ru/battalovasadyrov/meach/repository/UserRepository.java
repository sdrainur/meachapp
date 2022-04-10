package ru.battalovasadyrov.meach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.battalovasadyrov.meach.model.Role;
import ru.battalovasadyrov.meach.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndEmail(String username, String email);
    Iterable<User> findByRolesAndUsername(Role role, String username);
    Iterable<User> findByRoles(Role role);
    User findByActivationCode(String activationCode);
}