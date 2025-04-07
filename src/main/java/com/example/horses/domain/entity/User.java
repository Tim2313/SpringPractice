package com.example.horses.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Identifiable<UUID> {

    public static final String ENTITY_NAME = "User";

    public static final int FIRST_NAME_LENGTH = 32;
    public static final int LAST_NAME_LENGTH = 32;
    public static final int EMAIL_LENGTH = 64;
    public static final int PASSWORD_LENGTH = 256;

    @Id
    @Column(name = "pk")
    @UuidGenerator
    @Access(AccessType.PROPERTY)
    private UUID id;

    @Column(name = "firstName", length = FIRST_NAME_LENGTH, nullable = false)
    @Basic(optional = false)
    private String firstName;

    @Column(name = "lastName", length = LAST_NAME_LENGTH, nullable = false)
    @Basic(optional = false)
    private String lastName;

    @Column(name = "email", length = EMAIL_LENGTH, nullable = false)
    @Basic(optional = false)
    private String email;

    @Column(name = "password", length = PASSWORD_LENGTH, nullable = false)
    @Basic(optional = false)
    private String password;

    public User() {
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
