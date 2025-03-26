package com.example.horses.api.http.rest.controller;

import com.example.horses.api.dto.Horse;
import com.example.horses.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HorseRestController {
    @Autowired
    private HorseService horseService;

    @GetMapping("/horses")
    public List<Horse> getAllHorses() {
        return horseService.listHorses();
    }

    @PostMapping("/horses")
    public ResponseEntity<Horse> createHorse (@RequestBody Horse horse) {
        Horse savedHorse = horseService.addHorse(horse);
        return ResponseEntity.ok(savedHorse);
    }
}
