package com.leew.springjpa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void HelloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello/get"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("Hello-World!!"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}