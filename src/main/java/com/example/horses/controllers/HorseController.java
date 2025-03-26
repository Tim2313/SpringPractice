package com.example.horses.controllers;

import com.example.horses.services.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HorseController {
    private final HorseService horseService;

    @GetMapping("/")
    public String horses(Model model) {
        model.addAttribute("horse", horseService.listHorses());
        return "horse";
    }
}
