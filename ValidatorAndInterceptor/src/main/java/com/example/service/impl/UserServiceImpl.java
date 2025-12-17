package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {

        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
