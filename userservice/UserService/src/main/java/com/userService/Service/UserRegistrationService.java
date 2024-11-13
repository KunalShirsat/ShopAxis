package com.userService.Service;

import com.userService.Entity.User;
import com.userService.Exception.UseAlreadyExisteException;
import com.userService.Exception.UserValidationException;
import com.userService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserRegistrationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser ( User user) throws UseAlreadyExisteException, UserValidationException {
        validateUser(user);

        if (userRepository.existsByEmail(user.getEmail())){
            throw new UseAlreadyExisteException( "already exist plase login with " + user.getEmail());
        }
//        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setFirstName(user.getFirstName());
//        user.setLastName(user.getLastName());
      return   userRepository.save(user);

    }

    private void validateUser(User user) throws UserValidationException {
        if (user == null) {
            throw new UserValidationException("User cannot be null");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new UserValidationException("Email cannot be empty");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new UserValidationException("Password cannot be empty");
        }
}}
