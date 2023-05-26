package tech.assessment.pmu.race.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tech.assessment.pmu.race.factory.RaceFactory;
import tech.assessment.pmu.race.model.Race;
import tech.assessment.pmu.race.service.KafkaService;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class RaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KafkaService kafkaService;

    @BeforeEach
    public void beforeEach() {
        doNothing().when(kafkaService).push(Mockito.any(Race.class));
    }

    @Test
    public void create_ok() throws Exception {
        mockMvc.perform(post("/api/v1/race/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(RaceFactory.makeRaceConform())))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void create__not_acceptable() throws Exception {
        mockMvc.perform(post("/api/v1/race/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(RaceFactory.makeRaceNonConform1())))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
        ;
    }
}
