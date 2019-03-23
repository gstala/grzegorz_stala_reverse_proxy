package com.stala.reverseproxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReverseProxyRequestTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void handleGetRequestTest() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string("Test GET"));
    }

    @Test
    public void handlePostRequestTest() throws Exception {
        this.mvc.perform(post("/")).andExpect(status().isOk())
                .andExpect(content().string("Test POST"));
    }

    @Test
    public void handlePutRequestTest() throws Exception {
        this.mvc.perform(put("/")).andExpect(status().isOk())
                .andExpect(content().string("Test PUT"));
    }

}
