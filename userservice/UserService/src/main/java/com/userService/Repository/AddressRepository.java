package com.userService.Repository;

import com.userService.Entity.User_Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<User_Address, Long> {
}
