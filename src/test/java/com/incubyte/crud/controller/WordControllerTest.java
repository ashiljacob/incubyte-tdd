package com.incubyte.crud.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest
public class WordControllerTest {

    @Mock
    private WordRepository wordRepository;

    @Test
    public void givenValidGETRequest_thenReturnAllWordsInDB(){



    }
}
