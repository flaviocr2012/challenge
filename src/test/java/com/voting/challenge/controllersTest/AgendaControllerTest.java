package com.voting.challenge.controllersTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.challenge.dtos.AgendaRequest;
import com.voting.challenge.models.Agenda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AgendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterAgenda() throws Exception {

        var agenda = AgendaRequest
                .builder()
                .subject("The actual president should remains?")
                .build();

        var JsonAgenda = objectMapper.writeValueAsString(agenda);
        String URI = "/agenda";

        var jsonResp = mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonAgenda))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        var registeredAgenda =
                objectMapper.readValue(jsonResp, Agenda.class);

        assert registeredAgenda.getSubject().equals(agenda.getSubject());

    }

}
