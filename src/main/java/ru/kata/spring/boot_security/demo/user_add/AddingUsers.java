package ru.kata.spring.boot_security.demo.user_add;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
public class AddingUsers {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AddingUsers(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    @Transactional
    public void addUser() {
        User user = new User();
        user.setUsername("admin");
        user.setAge(20);
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setRoles(Collections.singleton((new Role("ROLE_ADMIN"))));
        userRepository.save(user);
        User user2 = new User();
        user2.setUsername("user");
        user2.setAge(20);
        user2.setPassword(bCryptPasswordEncoder.encode("user"));
        user2.setRoles((Collections.singleton(new Role("ROLE_USER"))));
        userRepository.save(user2);
    }
}