//package com.userService.Controller;
//
//
//
//
//import com.userService.Entity.User;
//import com.userService.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("api/v1")
//
//public class UserController {
//    @Autowired
//    UserService userService;
//
//    @PostMapping ("register")
//    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto dto){
//
//        User newUser = userService.registerUser(dto);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//    }
//}
