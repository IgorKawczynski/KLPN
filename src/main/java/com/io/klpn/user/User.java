package com.io.klpn.user;

import com.io.klpn.basic.BasicEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BasicEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean isAdmin;

    public User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = false;
    }

    // constructor which will be used in Student class constructor
    public User(Long id) {
        this.id = id;
    }

}
