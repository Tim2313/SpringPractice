package com.example.horses.repositorie;

import com.example.horses.domain.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseRepository extends JpaRepository<Horse, Long> {
}
