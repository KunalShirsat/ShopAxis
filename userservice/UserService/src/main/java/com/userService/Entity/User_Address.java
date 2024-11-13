package com.userService.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "useraddress")
public class User_Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Full name is required")
    @Column(name = "fullname")
    private String fullName;

//    @NotNull(message = "Mobile number is required")
    @Column(name = "mobilenumber")
    private Long mobileNumber;

//    @NotNull(message = "Pin code is required")
    @Column(name = "pincode")
    private Integer pincode;

    private String flat;
    private String houseNo;
    private String building;
    private String company;
    private String apartment;
    private String area;
    private String street;
    private String sector;
    private String village;
    private String landmark;

//    @NotNull(message = "City is required")
    private String city;

//    @NotNull(message = "State is required")
    private String state;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
}
