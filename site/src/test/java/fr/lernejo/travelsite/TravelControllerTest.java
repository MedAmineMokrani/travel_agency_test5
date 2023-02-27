package fr.lernejo.travelsite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TravelControllerTest {

        @Autowired
        private MockMvc mockMvc;

    @Test
    void postInscriptionTest() throws Exception {


        TravelInscription travelInscription = new TravelInscription("John", "Doe", "johndoe@example.com", 2, TravelInscription.WeatherExpectation.COLDER);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(travelInscription);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/inscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetTravels() throws Exception {
        String userName = "testUser";
        ObjectMapper objectMapper = new ObjectMapper();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/travels")
                .param("userName", userName)
                .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        List<Map<String, Object>> temperatures = objectMapper.readValue(responseBody, new TypeReference<List<Map<String, Object>>>() {});


        Map<String, Object> temperature = temperatures.get(0);
        assertEquals(temperature.get("country"), "Afghanistan");
        assertEquals(temperature.get("temperature"), 24.5);
    }

}







