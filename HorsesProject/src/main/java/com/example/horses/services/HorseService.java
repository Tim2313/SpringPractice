package com.example.horses.services;

import com.example.horses.models.Horse;
import com.example.horses.repositories.HorseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HorseService {
    private final HorseRepository horseRepository;
    public List<Horse> listHorses() {
        return horseRepository.findAll();
    }

    public Horse getHorseById (Long id) {
        return horseRepository.findById(id).orElse(null);
    }
}
