package com.userService.Controller;

import com.userService.Entity.User_Address;
import com.userService.Service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller class
@RestController
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserAddressController {

    private final AddressService addressService;

    @Autowired
    public UserAddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public ResponseEntity<User_Address> addAddress( @RequestBody User_Address address) {
        log.info("Received address request for user: {}", address.getFullName());
        User_Address savedAddress = addressService.addAddress(address);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+savedAddress);

        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }


    @GetMapping("/getaddress")
    public ResponseEntity<List<User_Address>> getAddress( ) {

      List<User_Address> address= addressService.getAddrress();
        return new ResponseEntity<>(address, HttpStatus.FOUND);

    }
    }