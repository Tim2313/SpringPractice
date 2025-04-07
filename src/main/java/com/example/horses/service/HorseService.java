package com.example.horses.service;

import com.example.horses.api.dto.Horse;
import com.example.horses.repositorie.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseService {

    @Autowired
    private HorseRepository horseRepository;

    public List<Horse> listHorses() {
        return horseRepository.findAll();
    }

    public Horse addHorse(Horse horse) {
        return horseRepository.save(horse);
    }

    public Horse getHorseById(Long id) {
        return horseRepository.findById(id).orElse(null);
    }
}
