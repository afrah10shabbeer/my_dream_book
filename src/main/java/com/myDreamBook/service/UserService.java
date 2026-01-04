package com.myDreamBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myDreamBook.model.User;
import com.myDreamBook.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // REGISTER
    public boolean registerUser(String username, String password) {

        username = username.trim();
        password = password.trim();
        if (userRepo.existsByUsername(username)) {
            return false; // username already exists
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        userRepo.save(user);
        return true;
    }

    public boolean verifyUserLogin(String username, String password) {

        username = username.trim();
        password = password.trim();

        User userOpt = userRepo.findByUsername(username);
        if (userOpt == null) {
            return false; // username does not exist
        }

        return passwordEncoder.matches(password, userOpt.getPassword());
    }

}
