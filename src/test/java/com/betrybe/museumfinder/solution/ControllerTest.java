package com.betrybe.museumfinder.solution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void returnStatusOk() throws Exception {
    mockMvc.perform(get("/collections/count/história"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes[0]").value("história"))
        .andExpect(jsonPath("$.count").isNumber());
  }

  @Test
  public void shouldReturnCountForMultipleTypes() throws Exception {
    mockMvc.perform(get("/collections/count/hist,imag"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes[0]").value("hist"))
        .andExpect(jsonPath("$.collectionTypes[1]").value("imag"))
        .andExpect(jsonPath("$.count").isNumber());
  }
  
}
