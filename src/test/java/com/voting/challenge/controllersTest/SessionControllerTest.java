package com.voting.challenge.controllersTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.challenge.dtos.AgendaRequest;
import com.voting.challenge.dtos.SessionRequest;
import com.voting.challenge.models.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SessionControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @BeforeAll
        void createAgenda() throws Exception{

            var agenda = AgendaRequest
                    .builder()
                    .subject("Should we use mockMvc")
                    .build();

            var agendaJson = objectMapper.writeValueAsString(agenda);

            String URI = "/agenda";

            mockMvc
                    .perform(MockMvcRequestBuilders.post(URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(agendaJson))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void shouldRegisterSession() throws Exception {

            SessionRequest session = SessionRequest
                    .builder()
                    .start(LocalDateTime.now())
                    .end(LocalDateTime.now().plusMinutes(1L))
                    .sessionId(0L)
                    .build();

            var JsonSession = objectMapper.writeValueAsString(session);
            String URI = "/session";

            var jsonResp = mockMvc
                    .perform(MockMvcRequestBuilders.post(URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonSession))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString();

            var registeredSession =
                    objectMapper.readValue(jsonResp, Session.class);

            assert Objects.nonNull(registeredSession);

        }

    }

