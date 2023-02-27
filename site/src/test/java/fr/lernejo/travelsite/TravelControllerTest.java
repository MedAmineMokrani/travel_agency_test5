package fr.lernejo.travelsite;

import fr.lernejo.travelsite.TravelInscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

@SpringBootTest
class TravelControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TravelController travelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(travelController).build();
    }

    @Test
    void testGetTravels() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/travels")
                .param("userName", "test"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].country").value("France"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].temperature").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].country").value("Spain"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].temperature").isNumber());
    }

    @Test
    void testPostInscription() throws Exception {
        TravelInscription inscription = new TravelInscription("test", "test@gmail.com", "France",1, TravelInscription.WeatherExpectation.COLDER);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/inscription")
                .contentType("application/json")
                .content(inscription.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
