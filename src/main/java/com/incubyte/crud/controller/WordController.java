package com.incubyte.crud.controller;

import com.incubyte.crud.model.Word;
import com.incubyte.crud.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.nio.file.LinkOption;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WordController {


    private final WordRepository wordRepository;

    @GetMapping("/words")
    public List<Word> getAllWords(){
        return  wordRepository.findAll();
    }

    @PostMapping("/word")
    public Word saveWord(@RequestBody Word word){
        return wordRepository.save(word);
    }

    @PutMapping("/word")
    @SneakyThrows
    public Word updateWord(@RequestBody Word word){
        return wordRepository.findById(word.getId())
                .map(wordFromDb ->{

                    wordFromDb.setWord(word.getWord());
                    wordRepository.save(wordFromDb);

                    return wordFromDb;
                } ).orElseThrow(() -> new Exception("Word Is Not Available"));
    }

    @DeleteMapping("word/{wordId}")
    @SneakyThrows
    public String deleteWord(@PathVariable Long wordId){

        return wordRepository.findById(wordId)
                .map(word -> {

                    wordRepository.delete(word);
                    return "Success";
                }).orElseThrow(() -> new Exception("Word Not Found"));
    }

}
