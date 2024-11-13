//package com.userService.Service;
//
//import com.userService.DTOs.UserRegistrationDto;
//import com.userService.Entity.User;
//import com.userService.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@ComponentScan
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
////    private PasswordEncoder passwordEncoder;
//
//
//    public User registerUser(@org.jetbrains.annotations.NotNull UserRegistrationDto dto){
//
//        User user = new User();
//
//
//
//        user.setFirstName(dto.getFirstName());
//        user.setLastName(dto.getLastName());
//        user.setEmail(dto.getEmail());
//
////        user.setPassword(passwordEncoder.encode(dto.getPassword()));
////        user.setRole(Role.USER);
//     //   user.setCreatedAt(LocalDateTime.now());
//
//        return userRepository.save(user);
//
//
//
//
//
//
//    }
//
//}
