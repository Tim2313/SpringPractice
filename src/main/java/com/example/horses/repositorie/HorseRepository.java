package com.example.horses.repositorie;

import com.example.horses.api.dto.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseRepository extends JpaRepository<Horse, Long> {
}
