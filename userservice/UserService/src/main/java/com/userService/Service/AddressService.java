package com.userService.Service;

import com.userService.Entity.User_Address;
import com.userService.Repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public User_Address addAddress(User_Address address) {
        try {
            log.info("Saving address for user: {}", address.getFullName());
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       efrvice_________________"+ String.valueOf(address));
            return addressRepository.save(address);
        } catch (Exception e) {
            log.error("Error saving address: ", e);
            throw new RuntimeException("Failed to save address", e);
        }
    }
    public List<User_Address> getAddrress(){


        List <User_Address> adresss=addressRepository.findAll();
        log.info("address",adresss);
       return adresss;
    }

}
