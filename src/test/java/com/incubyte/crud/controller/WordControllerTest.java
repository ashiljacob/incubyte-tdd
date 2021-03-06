package com.incubyte.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.crud.model.Word;
import com.incubyte.crud.repository.WordRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class WordControllerTest {

    @MockBean
    private WordRepository wordRepository;

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private ObjectMapper objectMapper;


    @Test
    @SneakyThrows
    public void givenValidGETRequest_thenReturnAllWordsInDB(){

        List<Word> wordList = Collections.singletonList(Word.builder().word("Test").build());

        given(wordRepository.findAll())
                .willReturn(wordList);

        mvc.perform(get("/words")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].word").value("Test"));

    }

    @Test
    @SneakyThrows
    public void givenValidPOSTRequest_thenReturnOk(){

        Word requestBody = Word.builder().word("Posting Word").build();
        Word response = Word.builder().id(1L).word("Posting Word").build();

        given(wordRepository.save(requestBody))
                .willReturn(response);

        mvc.perform(post("/word")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.word").value("Posting Word"));

    }

    @Test
    @SneakyThrows
    public void givenValidPUTRequest_thenUpdateTheWord(){

        Word wordInDb = Word.builder().id(1L).word("Old Word").build();
        Word newWord = Word.builder().id(1L).word("Updated Word").build();

        given(wordRepository.findById(1L))
                .willReturn(Optional.ofNullable(wordInDb));
        given(wordRepository.save(newWord))
                .willReturn(newWord);

        mvc.perform(put("/word")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(newWord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.word").value(newWord.getWord()));
    }

    @Test
    @SneakyThrows
    public void givenValidDeleteRequest_thenDeleteTheWordFromDB(){

        Word wordInDb = Word.builder().id(1L).word("Word").build();

        given(wordRepository.findById(1L))
                .willReturn(Optional.ofNullable(wordInDb));



        mvc.perform(delete("/word/{wordId}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value("Success"))
                .andExpect(status().isOk());

    }
}
