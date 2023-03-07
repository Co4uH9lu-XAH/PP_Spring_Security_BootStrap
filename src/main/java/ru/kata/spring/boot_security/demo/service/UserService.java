package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);
    User getUserById(int id);
    List<User> getAll();
    void delete(int id);
    void update(User user, int id);

    User findByUserName(String name);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
