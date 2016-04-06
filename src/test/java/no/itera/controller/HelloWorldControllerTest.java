package no.itera.controller;

import no.itera.model.HelloWorld;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Pavol Rajzak, Itera.
 */
@ContextConfiguration(locations = {"classpath:spring/test-context.xml", "classpath:spring/mvc-core-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class HelloWorldControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void persistEntityScenario() throws Exception {
        // Save
        mockMvc.perform(post(HelloWorldController.RESOURCE_PATH)
                .content("{ \"message\" : \"Yo!\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        // Check if exists
        mockMvc.perform(get(HelloWorldController.RESOURCE_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].message").value("Yo!"))
                .andExpect(jsonPath("$[0].id").value(1));

        // Check for specific
        mockMvc.perform(get(HelloWorldController.RESOURCE_PATH + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Yo!"))
                .andExpect(jsonPath("$.id").value(1));
    }

}