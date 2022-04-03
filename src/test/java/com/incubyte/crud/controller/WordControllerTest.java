package com.incubyte.crud.controller;

import com.incubyte.crud.model.Word;
import com.incubyte.crud.repository.WordRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class WordControllerTest {

    @MockBean
    private WordRepository wordRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    public void givenValidGETRequest_thenReturnAllWordsInDB(){

        given(wordRepository.findAll())
                .willReturn(Collections.singletonList(Word.builder().word("Test").build()));

        mvc.perform(get("/getAllWords")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].word").value("Test"));

    }

    @Test
    @SneakyThrows
    public void givenValidPOSTRequest_thenReturnOk(){

        given(wordRepository.save(Word.builder().word("Posting Word").build()))
                .willReturn(Word.builder().id(1L).word("Posting Word").build());

        mvc.perform(post("/word",Word.builder().word("Posting Word"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.word").value("Posting Word"));

    }
}
