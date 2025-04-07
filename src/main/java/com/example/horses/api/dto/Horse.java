package com.example.horses.api.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "horses")
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "breed")
    private String breed;

    public Horse() {
    }

    public Horse(Long id, String name, String breed) {
        this.id = id;
        this.name = name;
        this.breed = breed;
    }

    public Long getId() {
        return id;
    }

    public Horse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Horse setName(String name) {
        this.name = name;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public Horse setBreed(String breed) {
        this.breed = breed;
        return this;
    }
}
