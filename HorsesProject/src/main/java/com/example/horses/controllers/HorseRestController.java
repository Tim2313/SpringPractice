package com.example.horses.controllers;

import com.example.horses.models.Horse;
import com.example.horses.services.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HorseRestController {
    @Autowired
    private HorseService horseService;

    @GetMapping("/horses")
    public List<Horse> getAllHorses() {
        return horseService.listHorses();
    }
}
