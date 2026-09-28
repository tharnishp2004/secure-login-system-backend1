package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    // Default constructor
    public User() {
    }

    // Constructor
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // GET ID
    public Long getId() {
        return id;
    }

    // GET EMAIL
    public String getEmail() {
        return email;
    }

    // SET EMAIL
    public void setEmail(String email) {
        this.email = email;
    }

    // GET NAME
    public String getName() {
        return name;
    }

    // SET NAME
    public void setName(String name) {
        this.name = name;
    }

    // GET PASSWORD
    public String getPassword() {
        return password;
    }

    // SET PASSWORD
    public void setPassword(String password) {
        this.password = password;
    }
}