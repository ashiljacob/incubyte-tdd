package com.incubyte.crud.controller;

import com.incubyte.crud.model.Word;
import com.incubyte.crud.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WordController {


    private final WordRepository wordRepository;

    @GetMapping("/getAllWords")
    public List<Word> getAllWords(){
        return  wordRepository.findAll();
    }

}
