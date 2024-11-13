package com.userService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Use Long instead of long for nullability

    private String firstName;
    private String lastName;
    private String email;
    private String password;




//    @OneToMany(mappedBy = "user", orphanRemoval = true)
//    private Set<User_Address> user_Addresses = new LinkedHashSet<>();
//
//    public Set<User_Address> getUser_Addresses() {
//        return user_Addresses;
//    }
//
//    public void setUser_Addresses(Set<User_Address> user_Addresses) {
//        this.user_Addresses = user_Addresses;
//    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
