package com.stala.reverseproxy;

import com.stala.reverseproxy.config.AppConfig;
import com.stala.reverseproxy.controllers.GlobalControllerExceptionHandler;
import com.stala.reverseproxy.controllers.ReverseProxyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ReverseProxyController.class, GlobalControllerExceptionHandler.class,
        AppConfig.class})
@AutoConfigureMockMvc
public class ReverseProxyExceptionTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void handleUnsupportedOperationExceptionTest() throws Exception {
        this.mvc.perform(delete("/")).andExpect(status().isBadRequest());
    }

    @Test
    public void handleResourceAccessExceptionTest() throws Exception {
        this.mvc.perform(put("http://localhot:8080/")).andExpect(status().isNotFound());
    }
}
