package ru.kuznetsov.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void parseTest() throws Exception {
        mockMvc.perform(post("/api/parse")
                .param("data", "aaaaabcccc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("a: 5")))
                .andExpect(jsonPath("$[1]", is("c: 4")))
                .andExpect(jsonPath("$[2]", is("b: 1")));
    }

    @Test
    void parseReverseTest() throws Exception {
        mockMvc.perform(post("/api/parse")
                        .param("data", "aaaaabcccc")
                        .param("reverse", "false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("b: 1")))
                .andExpect(jsonPath("$[1]", is("c: 4")))
                .andExpect(jsonPath("$[2]", is("a: 5")));
    }

    @Test
    void parseErrorTest() throws Exception {
        mockMvc.perform(post("/api/parse")
                        .param("data", "aaaaabccccbbbbbccccccbbbbcbcbc"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/parse"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}