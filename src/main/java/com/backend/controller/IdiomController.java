package com.backend.controller;

import com.backend.entity.Idiom;
import com.backend.repository.IdiomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/idioms")
public class IdiomController {

    @Autowired
    private IdiomRepository idiomRepository;

    @GetMapping("/random")
    public Optional<Idiom> getRandomIdiom() {
        long count = idiomRepository.count();
        int randomIndex = (int) (Math.random() * count);
        return idiomRepository.findById((long) randomIndex + 1);
    }
}
