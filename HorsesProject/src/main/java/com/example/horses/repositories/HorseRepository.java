package com.example.horses.repositories;

import com.example.horses.models.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseRepository extends JpaRepository<Horse, Long> {
}
